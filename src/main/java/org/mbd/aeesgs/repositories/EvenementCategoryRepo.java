package org.mbd.aeesgs.repositories;

import org.mbd.aeesgs.model.EvenementCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EvenementCategoryRepo extends JpaRepository<EvenementCategory, Long> {
    Optional<EvenementCategory> findByLibelle(String lebelle);
}
