package trier.jovemdev.provadois.guilherme_monteiro.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import trier.jovemdev.provadois.guilherme_monteiro.dto.ItemVendaDto;
import trier.jovemdev.provadois.guilherme_monteiro.dto.VendaDto;
import trier.jovemdev.provadois.guilherme_monteiro.enums.StatusVendaEnum;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas.*;

import java.util.List;

public interface VendaService {

    Page<VendaDto> findAllPaginado(Long idMercado, Pageable pageable, StatusVendaEnum status, Long idProduto) throws EntidadeNaoEncontradaException;

    VendaDto criarVendaEmAberto(VendaDto vendaDto) throws EntidadeNaoEncontradaException, VendaVaziaException, EstoqueInsuficienteException;

    //ITEM_PRODUTO DENTRO DA VENDA NAO PODE TER A QUANTIDADE 0
    VendaDto criarVendaFinalizadaComItens(VendaDto vendaDto) throws EntidadeNaoEncontradaException, CampoInvalidoException;

    VendaDto adicionarItemsEmUmaVendaEmAberto(Long vendaId, List<ItemVendaDto> itemsVendaList) throws EntidadeNaoEncontradaException, CampoInvalidoException, VendaFechadaException, EstoqueInsuficienteException;

    VendaDto excluirItem(Long vendaId, Long idItem) throws EntidadeNaoEncontradaException;

    void excluirVendaComItens(Long vendaId) throws EntidadeNaoEncontradaException, VendaFechadaException;

    VendaDto finalizarVenda(Long vendaId) throws EntidadeNaoEncontradaException;

    VendaDto findById(Long vendaId) throws EntidadeNaoEncontradaException;
}
