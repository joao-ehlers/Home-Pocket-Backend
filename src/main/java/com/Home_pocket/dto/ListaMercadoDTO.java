package com.Home_pocket.dto;

import com.Home_pocket.model.Categoria;
import lombok.Builder;

@Builder
public record ListaMercadoDTO(
         String nomeItem,
         Boolean stFinalizado,
         Integer quantidade,
         Categoria categoria,
         Double precoMedio) {
}
