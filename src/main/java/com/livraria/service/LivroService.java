package com.livraria.service;

import com.livraria.model.Livro;
import com.livraria.repository.LivroRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;
import java.util.Optional;

@Service
@Validated
//teste
public class LivroService {
    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }
    public List<Livro> list() {
        return livroRepository.findAll();
    }
    public Optional<Livro> findById(@PathVariable @NotNull @Positive Long id) {
        return livroRepository.findById(id);
    }
    public Livro create( @Valid Livro livro) {
        return livroRepository.save(livro);
    }
    public Optional<Livro>  update( @NotNull @Positive Long id, @Valid Livro livro) {
        return livroRepository.findById(id)
                .map(record -> {
                    record.setNome(livro.getNome());
                    record.setAutor(livro.getAutor());
                    record.setGenero(livro.getGenero());
                    return livroRepository.save(record);
                });
    }
    public boolean delete(@PathVariable @NotNull @Positive Long id) {
        return livroRepository.findById(id)
                .map(record -> {
                    livroRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }
}
