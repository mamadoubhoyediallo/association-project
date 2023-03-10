package org.mbd.aeesgs.security.repositories;

import org.mbd.aeesgs.security.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
	AppRole findByRoleName(String roleName);
}
