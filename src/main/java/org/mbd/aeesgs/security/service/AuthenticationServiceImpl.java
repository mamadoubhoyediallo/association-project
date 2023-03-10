package org.mbd.aeesgs.security.service;

import lombok.AllArgsConstructor;
import org.mbd.aeesgs.security.model.AppRole;
import org.mbd.aeesgs.security.model.AppUser;
import org.mbd.aeesgs.security.repositories.AppRoleRepository;
import org.mbd.aeesgs.security.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
	
	private AppUserRepository appUserRepository;
	private AppRoleRepository appRoleRepository;
	private PasswordEncoder passwordEncoder;

	@Override
	public AppUser addNewUser(AppUser appUser) {
		String password = appUser.getPassword();
		appUser.setPassword(passwordEncoder.encode(password));
		return appUserRepository.save(appUser);
	}

	@Override
	public AppRole addNewRole(AppRole appRole) {
		
		return appRoleRepository.save(appRole);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		AppUser appUser = appUserRepository.findByUsername(username);
		AppRole appRole = appRoleRepository.findByRoleName(roleName);
		appUser.getAppRoles().add(appRole);
	}

	@Override
	public AppUser loadUserByUsername(String username) {
		
		return appUserRepository.findByUsername(username);
	}

	@Override
	public List<AppUser> listUsers() {
		
		return appUserRepository.findAll();
	}

}
