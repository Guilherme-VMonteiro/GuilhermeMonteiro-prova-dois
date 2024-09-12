package trier.jovemdev.provadois.guilherme_monteiro.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import trier.jovemdev.provadois.guilherme_monteiro.dto.ProdutoDto;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas.CampoInvalidoException;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas.EdicaoDoProdutoNaoPermitidaException;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas.EntidadeNaoEncontradaException;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoService {

    ProdutoDto findById(Long id) throws EntidadeNaoEncontradaException;

    ProdutoDto create(ProdutoDto produto) throws EntidadeNaoEncontradaException, CampoInvalidoException;

    Page<ProdutoDto> findAll(Long idMercado, Pageable pageable, String nomeProduto) throws EntidadeNaoEncontradaException;

    ProdutoDto atualizaEstoque(Long idProduto, Integer estoque) throws EntidadeNaoEncontradaException, CampoInvalidoException, EdicaoDoProdutoNaoPermitidaException;

    ProdutoDto atualizaValorUnitario(Long idProduto, BigDecimal valorUnitario) throws EntidadeNaoEncontradaException, CampoInvalidoException;

    List<ProdutoDto> coletaProdutosPorListaDeIds(List<Long> listaDeIds);

    ProdutoDto findProdutoMaisVendido(Long mercadoId) throws EntidadeNaoEncontradaException;

    ProdutoDto diminuiEstoqueEmVenda(ProdutoDto produto, Integer quantidadeComprada);

    ProdutoDto aumentaEstoque(ProdutoDto produto, Integer quantidadeEstornada);
}
