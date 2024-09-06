package trier.jovemdev.provadois.guilherme_monteiro.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import trier.jovemdev.provadois.guilherme_monteiro.dto.ProdutoDto;

public interface ProdutoRepositoryCustom {

    Page<ProdutoDto> findAll(Long idMercado, Pageable pageable, String nomeProduto);

    Boolean existeVendasEmAbertoComProduto(Long idProduto);
}
