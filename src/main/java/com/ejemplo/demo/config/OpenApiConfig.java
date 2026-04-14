//Programacion3
//CarlosRam0s
//09052314141

package com.ejemplo.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Workshop Spring Boot API")
                        .version("1.0.0")
                        .description("API de ejemplo para Programacion 3 TareaCR20260409- Ingenieria de Sistemas Carlos Ramos 0905 23 14141")
                        .contact(new Contact()
                                .name("Carlos Ramos")
                                .email("cramosm11@miumg.edu.gt")));
    }
}