package com.livraria;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API de Livros", version = "1.0.0", description = "API de acesso aos dados de livros"), servers = {@Server(url = "http://localhost:8080")})
//http://localhost:8080/swagger-ui/index.html#
public class CrudSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrudSpringApplication.class, args);
    }

}


