package com.Home_pocket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    //usuario responsavel no futuro

}
