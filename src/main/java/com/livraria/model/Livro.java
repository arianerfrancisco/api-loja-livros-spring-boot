package com.livraria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotBlank
    @NotNull
    @Length(min=5,max=100)
    @Column(length = 200, nullable = false) // não aceitará valores nulos
    private String nome;

    @NotNull
    @Length(max=100)
    @Column(length = 100, nullable = false)
    @Pattern(regexp = "Autor 1|Autor 2")
    private String autor;
}
