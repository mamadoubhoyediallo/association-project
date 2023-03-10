package org.mbd.aeesgs.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Amicale des Élèves, Étudiants et Stagiaires Guinéens au Sénégal")
                        .description("Une plateforme où vous retrouverez toutes les informations concernent l'AEESGS")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("aeesgs")
                                .url("https://www.aeesgs.org")
                                .email("mbdiallo1isidk@groupeisi.com"))
                        .termsOfService("TOC")
                        .license(new License().name("License").url("#"))
                );
    }
}
