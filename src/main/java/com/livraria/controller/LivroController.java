package com.livraria.controller;

import com.livraria.model.Livro;
import com.livraria.repository.LivroRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated // necessario por conta do @Valid no metodo create
@RequestMapping("/api/livros")
@AllArgsConstructor // insere livroRepository no construtor
class LivroController {

    private final LivroRepository livroRepository;

    @GetMapping
    public List<Livro> list() {
        return livroRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    //  @ResponseStatus(code = HttpStatus.CREATED) - utiliza esta anotação generica quando não precisa manipular o response, como inserir um cabeçalho
    public Livro create(@RequestBody @Valid Livro livro) {
        return livroRepository.save(livro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> findById(@PathVariable @NotNull @Positive Long id) {
        return livroRepository.findById(id)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> update(@PathVariable  @NotNull @Positive Long id,@RequestBody @Valid Livro livro){
        return livroRepository.findById(id)
                .map(record -> {
                    record.setNome(livro.getNome());
                    record.setAutor(livro.getAutor());
                    Livro updated=livroRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    // para casos de efeito em que o registro precise ser mantido no BD
    // seguir a lógica do update e setar a propriedade de status para inativo, por exemplo.
    public ResponseEntity<Void> delete (@PathVariable  @NotNull @Positive  Long id){
        return livroRepository.findById(id)
                .map(record -> {
                    livroRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
