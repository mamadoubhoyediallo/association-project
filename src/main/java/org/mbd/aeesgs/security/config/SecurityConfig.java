package org.mbd.aeesgs.security.config;

import org.mbd.aeesgs.security.filters.JwtAuthenticationFilter;
import org.mbd.aeesgs.security.filters.JwtAuthorizatrionFilter;
import org.mbd.aeesgs.security.model.AppUser;
import org.mbd.aeesgs.security.service.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private AuthenticationService accountService;

	public SecurityConfig(AuthenticationService accountService) {
		this.accountService = accountService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				
				AppUser appUser = accountService.loadUserByUsername(username);
				Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				appUser.getAppRoles().forEach(role->{
					authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
				});
				return new User(appUser.getUsername(), appUser.getPassword(), authorities);
			}
		});
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.formLogin();
		//http.authorizeRequests().antMatchers(HttpMethod.GET, "/users/**").hasAnyAuthority("ADMIN");
		http.authorizeRequests().antMatchers("/addUsers").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new JwtAuthorizatrionFilter(), UsernamePasswordAuthenticationFilter.class);
//		http.authorizeRequests().antMatchers("/acceuil").hasAnyAuthority("ADMIN");
//		http.authorizeRequests().antMatchers("/menu").hasAnyAuthority("USER", "ADMIN");
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

}
