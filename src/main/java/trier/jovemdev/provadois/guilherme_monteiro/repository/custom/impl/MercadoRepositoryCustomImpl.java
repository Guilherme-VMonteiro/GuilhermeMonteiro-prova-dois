package trier.jovemdev.provadois.guilherme_monteiro.repository.custom.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import trier.jovemdev.provadois.guilherme_monteiro.dto.ProdutoDto;
import trier.jovemdev.provadois.guilherme_monteiro.entity.QItemVendaEntity;
import trier.jovemdev.provadois.guilherme_monteiro.entity.QMercadoEntity;
import trier.jovemdev.provadois.guilherme_monteiro.entity.QProdutoEntity;
import trier.jovemdev.provadois.guilherme_monteiro.entity.QVendaEntity;
import trier.jovemdev.provadois.guilherme_monteiro.repository.custom.MercadoRepositoryCustom;

@Repository
public class MercadoRepositoryCustomImpl implements MercadoRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    final QMercadoEntity mercado = QMercadoEntity.mercadoEntity;
    final QProdutoEntity produto = QProdutoEntity.produtoEntity;
    final QVendaEntity venda = QVendaEntity.vendaEntity;
    final QItemVendaEntity itemVenda = QItemVendaEntity.itemVendaEntity;

    public ProdutoDto produtoMaisVendidoPeloMercado(Long mercadoId) {

//        JPAQuery<ProdutoDto> query = new JPAQuery<>(em);
//
//        query
//                .select(Projections.constructor(ProdutoDto.class, produto))
//                .from(produto)
//                .innerJoin(itemVenda)
//                .innerJoin(venda)
//                .innerJoin(mercado)
//                .where(mercado.id.eq(mercadoId))
//                .groupBy(produto.id)
//                .orderBy(itemVenda.id.asc())

        //TODO
        return null;

    }
}
