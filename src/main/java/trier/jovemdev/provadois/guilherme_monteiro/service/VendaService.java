package trier.jovemdev.provadois.guilherme_monteiro.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import trier.jovemdev.provadois.guilherme_monteiro.dto.ProdutoDto;
import trier.jovemdev.provadois.guilherme_monteiro.dto.ProdutoQuantidadeDto;
import trier.jovemdev.provadois.guilherme_monteiro.dto.VendaDto;
import trier.jovemdev.provadois.guilherme_monteiro.enums.StatusVendaEnum;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas.CampoInvalidoException;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas.EntidadeNaoEncontradaException;

import java.util.List;

public interface VendaService {

    Page<VendaDto> findAllPaginado(Long idMercado, Pageable pageable, StatusVendaEnum status, Long idProduto) throws EntidadeNaoEncontradaException;

    VendaDto criarVendaEmAberto(VendaDto vendaDto) throws EntidadeNaoEncontradaException;

    //ITEM_PRODUTO DENTRO DA VENDA NAO PODE TER A QUANTIDADE 0
    VendaDto criarVendaFinalizadaComItens(VendaDto vendaDto) throws EntidadeNaoEncontradaException, CampoInvalidoException;

    VendaDto adicionarItemsEmUmaVenda(Long vendaId, List<ProdutoQuantidadeDto> produtos) throws EntidadeNaoEncontradaException, CampoInvalidoException;

    VendaDto excluirItem(Long vendaId, Long idItem) throws EntidadeNaoEncontradaException;

    void excluirVenda(Long vendaId) throws EntidadeNaoEncontradaException;

    VendaDto finalizarVenda(Long vendaId) throws EntidadeNaoEncontradaException;

    VendaDto findById(Long vendaId) throws EntidadeNaoEncontradaException;
}
