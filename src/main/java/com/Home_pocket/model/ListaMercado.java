package com.Home_pocket.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name="lista_mercado")
public class ListaMercado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do item necessario")
    private String nomeItem;

    @NotNull(message = "Identificacao da situacao do item necessaria")
    private Boolean stFinalizado;

    @NotNull(message = "Quantidade necessaria")
    private Integer quantidade;

    @NotBlank(message = "Categoria necessaria")
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private Double precoMedio;//implementar futuramente uma tabela apenas para preco, salvando onde foi comprado e mais infos

    public ListaMercado(String nomeItem, String categoria, Boolean StFinalizado, Integer quantidade, Double precoMedio) {
    }

    //usuario responsavel no futuro

}
