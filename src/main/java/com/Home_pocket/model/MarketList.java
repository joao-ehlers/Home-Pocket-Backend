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
@Table(name="market_list")
public class MarketList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please, register the name of the item")
    private String itemName;

    @NotNull(message = "The item situation is needed")
    private Boolean isFinished;

    @NotNull(message = "Please, register the quantity of this item")
    private Integer quantity;

    @NotNull(message = "Please, register a category")
    @Enumerated(EnumType.STRING)
    private Category category;

    private Double mediumPrice;//implementar futuramente uma tabela apenas para preco, salvando onde foi comprado e mais infos

    //usuario responsavel no futuro

}
