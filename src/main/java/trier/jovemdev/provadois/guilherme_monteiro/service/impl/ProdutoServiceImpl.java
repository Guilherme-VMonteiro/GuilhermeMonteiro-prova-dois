package trier.jovemdev.provadois.guilherme_monteiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import trier.jovemdev.provadois.guilherme_monteiro.dto.ProdutoDto;
import trier.jovemdev.provadois.guilherme_monteiro.entity.ProdutoEntity;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas.CampoInvalidoException;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas.EdicaoDoProdutoNaoPermitidaException;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas.EntidadeNaoEncontradaException;
import trier.jovemdev.provadois.guilherme_monteiro.repository.ProdutoRepository;
import trier.jovemdev.provadois.guilherme_monteiro.repository.custom.ProdutoRepositoryCustom;
import trier.jovemdev.provadois.guilherme_monteiro.service.MercadoService;
import trier.jovemdev.provadois.guilherme_monteiro.service.ProdutoService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoRepositoryCustom produtoRepositoryCustom;

    @Autowired
    private MercadoService mercadoService;

    public ProdutoDto findById(Long id) throws EntidadeNaoEncontradaException {
        return new ProdutoDto(produtoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("produto", id)));
    }

    public ProdutoDto create(ProdutoDto dto) throws EntidadeNaoEncontradaException, CampoInvalidoException {
        validaCriacaoProduto(dto);

        return new ProdutoDto(produtoRepository.save(new ProdutoEntity(dto)));
    }

    public Page<ProdutoDto> findAll(Long idMercado, Pageable pageable, String nomeProduto) throws EntidadeNaoEncontradaException {
        return produtoRepositoryCustom.findAll(idMercado, pageable, nomeProduto);
    }

    public ProdutoDto atualizaEstoque(Long idProduto, Integer estoque) throws EntidadeNaoEncontradaException, CampoInvalidoException, EdicaoDoProdutoNaoPermitidaException {
        validaEdicao(idProduto);

        ProdutoEntity produtoEntity = new ProdutoEntity(findById(idProduto));
        produtoEntity.atualizaEstoque(estoque);

        return new ProdutoDto(produtoRepository.save(produtoEntity));
    }

    public ProdutoDto atualizaValorUnitario(Long idProduto, BigDecimal valorUnitario) throws EntidadeNaoEncontradaException, CampoInvalidoException {
        validaEdicao(idProduto);

        ProdutoEntity produtoEntity = new ProdutoEntity(findById(idProduto));
        produtoEntity.atualizaPreco(valorUnitario);

        return new ProdutoDto(produtoRepository.save(produtoEntity));
    }

    private void validaCriacaoProduto(ProdutoDto dto) throws CampoInvalidoException, EntidadeNaoEncontradaException {
        if (Objects.isNull(dto.getMercadoId())) {
            throw new CampoInvalidoException("mercadoId", "null");
        } else if (Objects.isNull(dto.getNome()) || dto.getNome().isBlank()) {
            throw new CampoInvalidoException("nome", dto.getNome());
        } else if (Objects.isNull(dto.getEstoque())) {
            throw new CampoInvalidoException("estoque", "null");
        } else if (Objects.isNull(dto.getValorUnitario())) {
            throw new CampoInvalidoException("valorUnitario", "null");
        }

        mercadoService.findById(dto.getMercadoId());
    }

    private void validaEdicao(Long produtoId) throws EntidadeNaoEncontradaException, EdicaoDoProdutoNaoPermitidaException {
        findById(produtoId);

        if (produtoRepositoryCustom.existeVendasEmAbertoComProduto(produtoId)) {
            throw new EdicaoDoProdutoNaoPermitidaException(produtoId);
        }
    }

    public List<ProdutoDto>  coletaProdutosPorListaDeIds(List<Long> listaDeIds){
        return produtoRepositoryCustom.contaProdutosExistentesPorListaDeIds(listaDeIds);
    }

    public ProdutoDto diminuiEstoqueEmVenda(ProdutoDto produto, Integer quantidadeComprada) throws EntidadeNaoEncontradaException {
        ProdutoEntity produtoEntity = new ProdutoEntity(findById(produto.getId()));
        produtoEntity.atualizaEstoque(produtoEntity.getEstoque() - quantidadeComprada);
        return new ProdutoDto(produtoRepository.save(produtoEntity));
    }

    public ProdutoDto aumentaEstoque(ProdutoDto produto, Integer quantidadeEstornada) {
        ProdutoEntity produtoEntity = new ProdutoEntity(findById(produto.getId()));
        produtoEntity.atualizaEstoque(produtoEntity.getEstoque() + quantidadeEstornada);
        return new ProdutoDto(produtoRepository.save(produtoEntity));
    }
}
