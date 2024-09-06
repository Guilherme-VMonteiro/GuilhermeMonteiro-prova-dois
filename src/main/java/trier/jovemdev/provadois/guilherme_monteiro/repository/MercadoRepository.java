package trier.jovemdev.provadois.guilherme_monteiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import trier.jovemdev.provadois.guilherme_monteiro.entity.MercadoEntity;

import java.util.Optional;

@Repository
public interface MercadoRepository extends JpaRepository<MercadoEntity, Long>, QuerydslPredicateExecutor<MercadoEntity> {

    Optional<MercadoEntity> findByCnpj(String cnpj);
}
