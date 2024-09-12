package trier.jovemdev.provadois.guilherme_monteiro.repository.custom.impl;

import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import trier.jovemdev.provadois.guilherme_monteiro.entity.QItemVendaEntity;
import trier.jovemdev.provadois.guilherme_monteiro.entity.QMercadoEntity;
import trier.jovemdev.provadois.guilherme_monteiro.entity.QProdutoEntity;
import trier.jovemdev.provadois.guilherme_monteiro.entity.QVendaEntity;
import trier.jovemdev.provadois.guilherme_monteiro.repository.custom.MercadoRepositoryCustom;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public class MercadoRepositoryCustomImpl implements MercadoRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    final QProdutoEntity produto = QProdutoEntity.produtoEntity;
    final QVendaEntity venda = QVendaEntity.vendaEntity;
    final QItemVendaEntity itemVenda = QItemVendaEntity.itemVendaEntity;
    final QMercadoEntity mercado = QMercadoEntity.mercadoEntity;

    public BigDecimal findFaturamentoPorDia(Long idMercado, LocalDate dia) {

        JPAQuery<BigDecimal> query = new JPAQuery<>(em);

        query
                .select(venda.valorTotal.sum())
                .from(itemVenda)
                .join(itemVenda.produto, produto)
                .join(itemVenda.venda, venda)
                .where(produto.mercado.id.eq(idMercado).and(venda.dataCriacao.eq(dia)));

        return query.fetchOne();
    }
}
