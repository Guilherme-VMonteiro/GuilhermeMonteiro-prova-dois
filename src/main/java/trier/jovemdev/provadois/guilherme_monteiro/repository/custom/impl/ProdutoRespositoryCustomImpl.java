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
import trier.jovemdev.provadois.guilherme_monteiro.dto.ProdutoDto;
import trier.jovemdev.provadois.guilherme_monteiro.entity.QItemVendaEntity;
import trier.jovemdev.provadois.guilherme_monteiro.entity.QProdutoEntity;
import trier.jovemdev.provadois.guilherme_monteiro.entity.QVendaEntity;
import trier.jovemdev.provadois.guilherme_monteiro.entity.VendaEntity;
import trier.jovemdev.provadois.guilherme_monteiro.enums.StatusVendaEnum;
import trier.jovemdev.provadois.guilherme_monteiro.repository.custom.ProdutoRepositoryCustom;

import java.util.List;
import java.util.Objects;

@Repository
public class ProdutoRespositoryCustomImpl implements ProdutoRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    final QProdutoEntity produto = QProdutoEntity.produtoEntity;
    final QVendaEntity venda = QVendaEntity.vendaEntity;
    final QItemVendaEntity itemVenda = QItemVendaEntity.itemVendaEntity;

    public Page<ProdutoDto> findAll(Long idMercado, Pageable pageable, String nomeProduto) {
        BooleanBuilder condicoes = new BooleanBuilder();

        condicoes.and(produto.mercado.id.eq(idMercado));

        if (Objects.nonNull(nomeProduto) && !nomeProduto.isEmpty()) {
            condicoes.and(produto.nome.containsIgnoreCase(nomeProduto));
        }

        JPAQuery<ProdutoDto> query = new JPAQuery<>(em);

        query
                .select(Projections.constructor(ProdutoDto.class, produto))
                .from(produto)
                .where(condicoes)
                .orderBy(produto.id.desc());

        query.limit(pageable.getPageSize());
        query.offset(pageable.getOffset());

        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }

    public Boolean existeVendasEmAbertoComProduto(Long idProduto) {
        JPAQuery<VendaEntity> query = new JPAQuery<>(em);

        query
                .select(venda)
                .from(venda)
                .innerJoin(venda.itemVendas, itemVenda)
                .innerJoin(itemVenda.produto, produto)
                .where(produto.id.eq(idProduto).and(venda.status.eq(StatusVendaEnum.EM_ABERTO)));

        return query.fetchOne() != null;
    }

    public List<ProdutoDto> contaProdutosExistentesPorListaDeIds(List<Long> listaDeIds) {
        JPAQuery<ProdutoDto> query = new JPAQuery<>(em);

        query
                .select(Projections.constructor(ProdutoDto.class, produto))
                .from(produto)
                .where(produto.id.in(listaDeIds))
                .orderBy(produto.id.asc());

        return query.fetch();
    }

    public ProdutoDto findProdutoMaisVendidoPorMercado(Long mercadoId) {
        JPAQuery<ProdutoDto> query = new JPAQuery<>(em);

        query
                .select(Projections.constructor(ProdutoDto.class, produto))
                .from(produto)
                .innerJoin(produto.itemVendas, itemVenda)
                .where(produto.mercado.id.eq(mercadoId))
                .groupBy(produto.id)
                .orderBy(produto.id.desc())
                .limit(1);

        return query.fetchOne();
    }
}
