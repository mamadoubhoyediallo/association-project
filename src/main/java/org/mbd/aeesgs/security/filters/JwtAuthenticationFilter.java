package org.mbd.aeesgs.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mbd.aeesgs.security.model.AppUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;
	//private static final Logger logger = LogManager.getLogger(JwtAuthenticationFilter.class);
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		//logger.info("Attempt Authentication");
		/*String username = request.getParameter("username");
		String password = request.getParameter("password");
		logger.info("username -> "+username);
		logger.info("password -> "+password);
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);*/
		AppUser appUser = null;
		try {
			appUser = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
			//logger.info("username -> "+appUser.getUsername());
			//logger.info("password -> "+appUser.getPassword());
		}catch (Exception e){
			throw new RuntimeException(e);
		}
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));
		//return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		//logger.info("Success Authentication");
		User user = (User) authResult.getPrincipal();
		Algorithm algorithm1 = Algorithm.HMAC256("secret9876");
		
		String jwtAccessToken = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+60*60*1000))
				.withIssuer(request.getRequestURI().toString())
				.withClaim("roles", user.getAuthorities().stream().map(ga->ga.getAuthority()).collect(Collectors.toList()))
				.sign(algorithm1);
				
		response.setHeader("Authorization", jwtAccessToken);
		
		String jwtRefreshToken = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+15*60*1000))
				.withIssuer(request.getRequestURI().toString())
				.sign(algorithm1);
		
		Map<String, String> idToken = new HashMap<>();
		idToken.put("accessToken", jwtAccessToken);
		idToken.put("refreshToken", jwtRefreshToken);
		idToken.put("username", user.getUsername());
		
		response.setContentType("application/json");
		new ObjectMapper().writeValue(response.getOutputStream(), idToken);
	}
}
