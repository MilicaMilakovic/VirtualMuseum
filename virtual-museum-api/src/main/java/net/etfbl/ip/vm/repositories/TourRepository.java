package net.etfbl.ip.vm.repositories;

import net.etfbl.ip.vm.models.entities.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TourRepository extends JpaRepository<TourEntity,Integer> {

    @Query("select t from TourEntity t where  t.museum.id = :id")
    List<TourEntity> getToursInMuseum(Integer id);
}
