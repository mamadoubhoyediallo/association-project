package org.mbd.aeesgs.security.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.AllArgsConstructor;
import org.mbd.aeesgs.security.filters.JwtAuthenticationFilter;
import org.mbd.aeesgs.security.filters.JwtAuthorizatrionFilter;
import org.mbd.aeesgs.security.model.AppUser;
import org.mbd.aeesgs.security.service.AuthenticationService;
import org.mbd.aeesgs.utils.EndPointAeesgs;
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
@AllArgsConstructor
@SecurityScheme(name = "bearer", // can be set to anything
		type = SecuritySchemeType.HTTP, scheme = "bearer")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private AuthenticationService accountService;

	private static final String[] AUTH_WHITELIST = {
			// -- Swagger UI v2
			"/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
			"/configuration/security", "/swagger-ui.html", "/webjars/**",
			// -- Swagger UI v3 (OpenAPI)
			"/v3/api-docs/**", "/swagger-ui/**",
			// On autorise l'url qui génére le token
			EndPointAeesgs.SIGN_IN_URL};

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
		http.cors().and().csrf().disable().authorizeRequests().antMatchers(AUTH_WHITELIST);;
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		/*http.authorizeRequests().antMatchers(HttpMethod.GET, "/users/**").hasAnyAuthority("ADMIN")*/;
		http.authorizeRequests().antMatchers("/addUsers").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new JwtAuthorizatrionFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public CorsFilter corsFilter() {

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOriginPatterns(Collections.singletonList("*"));
		config.setAllowedHeaders(
				Arrays.asList("Origin", "Content-Type", "Accept", "responseType", "Authorization"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

}
