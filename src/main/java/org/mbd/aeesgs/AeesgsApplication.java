package org.mbd.aeesgs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Amicale des Élèves, Étudiants et Stagiaires Guinéens au Sénégal",
        description = "Une plateforme où vous retrouverez toutes les informations concernent l'AEESGS", version = "v1",
        license = @License(name = "aeesgs", url = "https://www.aeesgs.org")))
public class AeesgsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AeesgsApplication.class, args);
    }

}
