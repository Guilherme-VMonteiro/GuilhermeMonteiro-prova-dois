package trier.jovemdev.provadois.guilherme_monteiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import trier.jovemdev.provadois.guilherme_monteiro.dto.ItemVendaDto;
import trier.jovemdev.provadois.guilherme_monteiro.dto.ProdutoDto;
import trier.jovemdev.provadois.guilherme_monteiro.dto.ProdutoQuantidadeDto;
import trier.jovemdev.provadois.guilherme_monteiro.dto.VendaDto;
import trier.jovemdev.provadois.guilherme_monteiro.entity.VendaEntity;
import trier.jovemdev.provadois.guilherme_monteiro.enums.StatusVendaEnum;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas.CampoInvalidoException;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas.EntidadeNaoEncontradaException;
import trier.jovemdev.provadois.guilherme_monteiro.repository.VendaRepository;
import trier.jovemdev.provadois.guilherme_monteiro.repository.custom.VendaRepositoryCustom;
import trier.jovemdev.provadois.guilherme_monteiro.service.ItemVendaService;
import trier.jovemdev.provadois.guilherme_monteiro.service.MercadoService;
import trier.jovemdev.provadois.guilherme_monteiro.service.VendaService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class VendaServiceImpl implements VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private VendaRepositoryCustom vendaRepositoryCustom;

    @Autowired
    private MercadoService mercadoService;

    @Autowired
    private ItemVendaService itemVendaService;

    public VendaDto findById(Long vendaId) throws EntidadeNaoEncontradaException {
        return new VendaDto(vendaRepository.findById(vendaId).orElseThrow(() -> new EntidadeNaoEncontradaException("venda", vendaId)));
    }

    public Page<VendaDto> findAllPaginado(Long idMercado, Pageable pageable, StatusVendaEnum status, Long idProduto) throws EntidadeNaoEncontradaException {
        return vendaRepositoryCustom.findAll(idMercado, pageable, status, idProduto);
    }

    public VendaDto criarVendaEmAberto(VendaDto dto) throws EntidadeNaoEncontradaException {
        VendaDto vendaDto = new VendaDto();
        vendaDto.setMercado(mercadoService.findById(dto.getMercado().getId()));
        vendaDto.setStatus(StatusVendaEnum.EM_ABERTO);
        vendaDto.setDataCriacao(LocalDate.now());
        vendaDto.setValorTotal(BigDecimal.ZERO);

        return new VendaDto(vendaRepository.save(new VendaEntity(vendaDto)));
    }

    public VendaDto criarVendaFinalizadaComItens(VendaDto vendaDto) throws EntidadeNaoEncontradaException, CampoInvalidoException {
        return null;
    }

    public VendaDto adicionarItemsEmUmaVenda(Long vendaId, List<ProdutoQuantidadeDto> produtos) throws EntidadeNaoEncontradaException, CampoInvalidoException {

        for (ProdutoQuantidadeDto produtoQuantidade : produtos) {
            ItemVendaDto itemVendaDto = new ItemVendaDto();
            itemVendaDto.setVendaId(vendaId);
            itemVendaDto.setProdutoId(produtoQuantidade.getProduto().getId());
            itemVendaDto.setQuantidade(produtoQuantidade.getQuantidade());
            itemVendaDto.setValorTotal(produtoQuantidade.getProduto().getValorUnitario().multiply(new BigDecimal(produtoQuantidade.getQuantidade())));

            itemVendaService.create(itemVendaDto);
        }

        return findById(vendaId);
    }

    public VendaDto excluirItem(Long vendaId, Long idItem) throws EntidadeNaoEncontradaException {
        return null;
    }

    public void excluirVenda(Long vendaId) throws EntidadeNaoEncontradaException {

    }

    public VendaDto finalizarVenda(Long vendaId) throws EntidadeNaoEncontradaException {
        return null;
    }

}
