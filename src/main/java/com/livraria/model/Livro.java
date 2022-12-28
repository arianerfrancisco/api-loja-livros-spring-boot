package com.livraria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    @JsonIgnore  // ignora o envio para o response json
    private Long id;

    @Column(length = 200, nullable = false) // não aceitará valores nulos
    private String nome;

    @Column(length = 100, nullable = false)
    private String autor;
}
