package trier.jovemdev.provadois.guilherme_monteiro.repository.custom;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface MercadoRepositoryCustom {

    BigDecimal findFaturamentoPorDia(Long idMercado, LocalDate dia);
}
