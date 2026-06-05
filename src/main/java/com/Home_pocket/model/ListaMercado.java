package com.Home_pocket.model;

import jakarta.persistence.*;
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

    private String nomeItem;

    private Boolean StFinalizado;

    private Double quantidade;

    private String categoria;

    private Double precoMedio;//implementar futuramente uma tabela apenas para preco, salvando onde foi comprado e mais infos

    public ListaMercado(String nomeItem, String categoria, Boolean StFinalizado, Double quantidade, Double precoMedio) {
    }

    //usuario responsavel no futuro

}
