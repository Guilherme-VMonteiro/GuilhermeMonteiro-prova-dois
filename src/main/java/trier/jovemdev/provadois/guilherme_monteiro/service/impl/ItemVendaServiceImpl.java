package trier.jovemdev.provadois.guilherme_monteiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trier.jovemdev.provadois.guilherme_monteiro.dto.ItemVendaDto;
import trier.jovemdev.provadois.guilherme_monteiro.entity.ItemVendaEntity;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas.EntidadeNaoEncontradaException;
import trier.jovemdev.provadois.guilherme_monteiro.repository.ItemVendaRepository;
import trier.jovemdev.provadois.guilherme_monteiro.service.ItemVendaService;
import trier.jovemdev.provadois.guilherme_monteiro.service.ProdutoService;

@Service
public class ItemVendaServiceImpl implements ItemVendaService {

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    @Autowired
    private ProdutoService produtoService;

    public ItemVendaDto findById(Long id) throws EntidadeNaoEncontradaException {
        return new ItemVendaDto(itemVendaRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("ItemVenda", id)));
    }

    public ItemVendaDto create(ItemVendaDto dto) {
        return new ItemVendaDto(itemVendaRepository.save(new ItemVendaEntity(dto)));
    }

    public void delete(ItemVendaDto itemVendaDto) throws EntidadeNaoEncontradaException {
        itemVendaRepository.delete(new ItemVendaEntity(findById(itemVendaDto.getId())));
        produtoService.aumentaEstoque(itemVendaDto.getProdutoDto(), itemVendaDto.getQuantidade());
    }
}
