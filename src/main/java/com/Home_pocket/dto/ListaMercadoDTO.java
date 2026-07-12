package com.Home_pocket.dto;

import lombok.Builder;

@Builder
public record ListaMercadoDTO(
         String nomeItem,
         Boolean stFinalizado,
         Double quantidade,
         String categoria,
         Double precoMedio) {
}
