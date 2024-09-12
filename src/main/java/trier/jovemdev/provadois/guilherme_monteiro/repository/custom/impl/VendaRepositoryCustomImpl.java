package trier.jovemdev.provadois.guilherme_monteiro.repository.custom.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import trier.jovemdev.provadois.guilherme_monteiro.dto.VendaDto;
import trier.jovemdev.provadois.guilherme_monteiro.entity.QItemVendaEntity;
import trier.jovemdev.provadois.guilherme_monteiro.entity.QMercadoEntity;
import trier.jovemdev.provadois.guilherme_monteiro.entity.QProdutoEntity;
import trier.jovemdev.provadois.guilherme_monteiro.entity.QVendaEntity;
import trier.jovemdev.provadois.guilherme_monteiro.enums.StatusVendaEnum;
import trier.jovemdev.provadois.guilherme_monteiro.repository.custom.VendaRepositoryCustom;

import java.util.Objects;
import java.util.Optional;

@Repository
public class VendaRepositoryCustomImpl implements VendaRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    final QProdutoEntity produto = QProdutoEntity.produtoEntity;
    final QVendaEntity venda = QVendaEntity.vendaEntity;
    final QItemVendaEntity itemVenda = QItemVendaEntity.itemVendaEntity;
    final QMercadoEntity mercado = QMercadoEntity.mercadoEntity;

    public Optional<VendaDto> findById(Long id) {
        em.clear();

        JPAQuery<VendaDto> query = new JPAQuery<>(em);

        query
                .select(Projections.constructor(VendaDto.class, venda))
                .from(venda)
                .where(venda.id.eq(id));

        return Optional.ofNullable(query.fetchOne());
    }

    public Page<VendaDto> findAll(Long idMercado, Pageable pageable, StatusVendaEnum status, Long idProduto) {

        BooleanBuilder condicoes = new BooleanBuilder();

        condicoes.and(venda.mercado.id.eq(idMercado));

        if (Objects.nonNull(status)) {
            condicoes.and(venda.status.eq(status));
        }

        if (Objects.nonNull(idProduto) && idProduto > 0) {
            condicoes.and(venda.itemVendas.any().id.eq(idProduto));
        }

        JPAQuery<VendaDto> query = new JPAQuery<>(em);

        query
                .select(Projections.constructor(VendaDto.class, venda))
                .from(venda)
                .where(condicoes)
                .orderBy(venda.id.desc());

        query.limit(pageable.getPageSize());
        query.offset(pageable.getOffset());

        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }
}
