package trier.jovemdev.provadois.guilherme_monteiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trier.jovemdev.provadois.guilherme_monteiro.entity.ItemVendaEntity;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVendaEntity, Long> {
}
