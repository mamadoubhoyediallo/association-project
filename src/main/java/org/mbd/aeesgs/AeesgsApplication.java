package org.mbd.aeesgs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Amicale des Élèves, Étudiants et Stagiaires Guinéens au Sénégal",
        description = "Une plateforme où vous retrouverez toutes les informations concernent l'AEESGS", version = "v1",
        license = @License(name = "aeesgs", url = "https://www.aeesgs.org")))
@EnableCaching
public class AeesgsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AeesgsApplication.class, args);
    }
    // Used by spring security if CORS is enabled.
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
