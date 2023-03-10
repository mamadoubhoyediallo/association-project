package org.mbd.aeesgs.repositories;

import org.mbd.aeesgs.model.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenementRepo extends JpaRepository<Evenement, Long> {
}
