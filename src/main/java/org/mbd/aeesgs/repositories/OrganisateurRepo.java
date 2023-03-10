package org.mbd.aeesgs.repositories;

import org.mbd.aeesgs.model.Organisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganisateurRepo extends JpaRepository<Organisateur, Long> {
    Optional<Organisateur> findByLibelle(String libelle);
    Optional<Organisateur> findById(Long id);
}
