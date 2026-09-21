package com.github.jhonatasfelipe76.baozistore.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
@Getter
@Setter
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "nome_produto", nullable = false, length = 70)
    private String nome;

    @Column (name = "preco_produto", nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column (name = "estoque", nullable = false)
    private Boolean estoque;
}
