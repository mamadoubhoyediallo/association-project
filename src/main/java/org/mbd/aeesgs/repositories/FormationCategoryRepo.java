package org.mbd.aeesgs.repositories;

import org.mbd.aeesgs.model.FormationCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormationCategoryRepo extends JpaRepository<FormationCategory, Long> {
    Optional<FormationCategory> findByLibelle(String libelle);
}
