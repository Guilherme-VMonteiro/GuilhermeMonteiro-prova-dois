package trier.jovemdev.provadois.guilherme_monteiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import trier.jovemdev.provadois.guilherme_monteiro.entity.ProdutoEntity;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long>, QuerydslPredicateExecutor<ProdutoEntity> {
}
