package com.livraria;

import com.livraria.model.Livro;
import com.livraria.repository.LivroRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(title = "API de Livros", version = "1.0.0", description = "API de acesso aos dados de livros"),
        servers =
                {
                        @Server(url = "http://localhost:8080")
                }
)
//http://localhost:8080/swagger-ui/index.html#
public class CrudSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudSpringApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(LivroRepository livroRepository) {
        return args -> {
            livroRepository.deleteAll();

            Livro c = new Livro();
            c.setNome("Autor 1");
            c.setAutor("autor2");
            livroRepository.save(c);

        };
    }
}


