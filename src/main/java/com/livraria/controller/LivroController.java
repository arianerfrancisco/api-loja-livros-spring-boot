package com.livraria.controller;

import com.livraria.model.Livro;
import com.livraria.repository.LivroRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
@AllArgsConstructor // insere livroRepository no construtor
class LivroController {

    private final LivroRepository livroRepository;

    @GetMapping
    public List<Livro> list() {
        return livroRepository.findAll();
    }

    //@PostMapping
    // public ResponseEntity<Livro> create(@RequestBody Livro livro){
    //    return ResponseEntity.status(HttpStatus.CREATED).body(livroRepository.save(livro));}

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    // utiliza esta anotação generica quando não precisa manipular o response, como inserir um cabeçalho
    public Livro create(@RequestBody Livro livro) {
        return livroRepository.save(livro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Long id) {
        return livroRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
}
