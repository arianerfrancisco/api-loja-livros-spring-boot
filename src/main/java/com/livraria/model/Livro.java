package com.livraria.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@SQLDelete(sql="UPDATE Livro SET status='inativo' WHERE id=?")
@Where(clause = "status='ativo'")
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
    @Pattern(regexp = "autor1|autor2")
    private String autor;

    //@JsonIgnore
    @NotNull
    @Length(max=10)
    @Column(length = 10, nullable = false)
    @Pattern(regexp = "ativo|inativo")
    private String status="ativo";
}
