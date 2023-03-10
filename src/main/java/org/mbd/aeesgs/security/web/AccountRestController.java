package org.mbd.aeesgs.security.web;

import net.bytebuddy.implementation.bind.annotation.Origin;
import org.mbd.aeesgs.security.model.AppRole;
import org.mbd.aeesgs.security.model.AppUser;
import org.mbd.aeesgs.security.model.RoleUserForm;
import org.mbd.aeesgs.security.repositories.AppUserRepository;
import org.mbd.aeesgs.security.service.AuthenticationService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@CrossOrigin("*")
public class AccountRestController {

	private AuthenticationService authenticationService;
	private AppUserRepository appUserRepository;

	public AccountRestController(AuthenticationService authenticationService, AppUserRepository appUserRepository) {
		this.appUserRepository = appUserRepository;
		this.authenticationService = authenticationService;
	}
	
	@GetMapping(path = "/users")
	//@PostAuthorize("hasAuthority('ADMIN')")
	public List<AppUser> listUsers(){
		return authenticationService.listUsers();
	}
	
	@PostMapping(path = "/addUsers")
	public void addNewUser(@RequestBody AppUser appUser) {
		authenticationService.addNewUser(appUser);
	}
	
	@PostMapping(path = "/roles")
	public void addNewRole(@RequestBody AppRole appRole) {
		authenticationService.addNewRole(appRole);
	}
	
	@PostMapping(path = "/addRoleToUser")
	public void addRoleToUser(@RequestBody RoleUserForm roleUserForm) {
		authenticationService.addRoleToUser(roleUserForm.getUsername(), roleUserForm.getRoleName());
	}
//	@GetMapping(path = "/acceuil")
//	public String acceuil(HttpServletRequest request, HttpServletResponse response, Model model){
////		String username = request.getRemoteUser();
////		AppUser appUser = appUserRepository.findByUsername(username);
////		model.addAttribute(username, appUser);
//		return "acceuil.jsp";
//	}
//	@GetMapping(path = "/menu")
//	public String menu(){
//		return "menu.jsp";
//	}
	
}


