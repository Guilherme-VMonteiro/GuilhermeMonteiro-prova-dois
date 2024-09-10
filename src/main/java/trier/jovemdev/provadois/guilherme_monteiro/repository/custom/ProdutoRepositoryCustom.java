package trier.jovemdev.provadois.guilherme_monteiro.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import trier.jovemdev.provadois.guilherme_monteiro.dto.ProdutoDto;

import java.util.List;

public interface ProdutoRepositoryCustom {

    Page<ProdutoDto> findAll(Long idMercado, Pageable pageable, String nomeProduto);

    Boolean existeVendasEmAbertoComProduto(Long idProduto);

    List<ProdutoDto>  contaProdutosExistentesPorListaDeIds(List<Long> listaDeIds);
}
