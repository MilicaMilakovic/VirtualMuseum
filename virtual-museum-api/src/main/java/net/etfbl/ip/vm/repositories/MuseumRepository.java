package net.etfbl.ip.vm.repositories;

import net.etfbl.ip.vm.models.entities.MuseumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MuseumRepository extends JpaRepository<MuseumEntity, Integer> {
}
