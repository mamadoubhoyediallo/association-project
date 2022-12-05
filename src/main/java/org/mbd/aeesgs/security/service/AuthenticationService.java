package org.mbd.aeesgs.security.service;

import org.mbd.aeesgs.security.model.AppRole;
import org.mbd.aeesgs.security.model.AppUser;

import java.util.List;

public interface AuthenticationService {
	
	AppUser addNewUser(AppUser appUser);
	AppRole addNewRole(AppRole appRole);
	void addRoleToUser(String username, String roleName);
	AppUser loadUserByUsername(String username);
	List<AppUser> listUsers();
}
