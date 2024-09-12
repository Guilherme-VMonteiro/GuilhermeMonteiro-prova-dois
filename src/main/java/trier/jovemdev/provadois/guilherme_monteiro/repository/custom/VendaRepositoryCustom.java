package trier.jovemdev.provadois.guilherme_monteiro.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import trier.jovemdev.provadois.guilherme_monteiro.dto.VendaDto;
import trier.jovemdev.provadois.guilherme_monteiro.enums.StatusVendaEnum;

import java.util.Optional;

public interface VendaRepositoryCustom {

    Optional<VendaDto> findById(Long id);

    Page<VendaDto> findAll(Long idMercado, Pageable pageable, StatusVendaEnum status, Long idProduto);
}
