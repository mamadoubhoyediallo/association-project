package org.mbd.aeesgs.security.repositories;

import org.mbd.aeesgs.security.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	AppUser findByUsername(String username);
}
