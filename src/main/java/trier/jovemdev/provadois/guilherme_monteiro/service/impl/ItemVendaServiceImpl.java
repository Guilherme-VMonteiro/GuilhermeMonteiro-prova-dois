package trier.jovemdev.provadois.guilherme_monteiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public ItemVendaDto create(ItemVendaDto dto) throws EntidadeNaoEncontradaException {
        return new ItemVendaDto(itemVendaRepository.save(new ItemVendaEntity(dto)));
    }
}
