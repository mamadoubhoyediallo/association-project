package org.mbd.aeesgs.repositories;

import org.mbd.aeesgs.model.FormationCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormationCategoryRepo extends JpaRepository<FormationCategory, Long> {
    FormationCategory findByLibelle(String libelle);
}
