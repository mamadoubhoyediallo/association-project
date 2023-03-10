package org.mbd.aeesgs.repositories;

import org.mbd.aeesgs.model.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormationRepo extends JpaRepository<Formation, Long> {
    Optional<Formation> findByPhoto(String photo);
}
