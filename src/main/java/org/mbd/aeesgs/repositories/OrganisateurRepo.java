package org.mbd.aeesgs.repositories;

import org.mbd.aeesgs.model.Organisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganisateurRepo extends JpaRepository<Organisateur, Long> {
    Organisateur findByLibelle(String libelle);
}
