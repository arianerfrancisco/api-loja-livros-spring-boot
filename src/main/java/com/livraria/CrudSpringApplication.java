package com.livraria;

import com.livraria.model.Livro;
import com.livraria.repository.LivroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
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


