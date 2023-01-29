package com.livraria.controller;

import com.livraria.model.Livro;
import com.livraria.repository.LivroRepository;
import com.livraria.service.LivroService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated // necessario por conta do @Valid no metodo create
@RequestMapping("/api/livros")

class LivroController {

    private final LivroService livroService;

    LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    //@CrossOrigin - liberar individualmente
    public @ResponseBody List<Livro> list() {
        return livroService.list();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    //  @ResponseStatus(code = HttpStatus.CREATED) - utiliza esta anotação generica quando não precisa manipular o response, como inserir um cabeçalho
    public Livro create(@RequestBody @Valid Livro livro) {
        return livroService.create(livro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> findById(@PathVariable @NotNull @Positive Long id) {
        return livroService.findById(id)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Livro livro) {
        return livroService.update(id,livro)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    // para casos de efeito em que o registro precise ser mantido no BD
    // seguir a lógica do update e setar a propriedade de status para inativo, por exemplo.
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id) {
        if (livroService.delete(id)) {
            return ResponseEntity.noContent().<Void>build();
        }
        return ResponseEntity.notFound().build();
    }
}
