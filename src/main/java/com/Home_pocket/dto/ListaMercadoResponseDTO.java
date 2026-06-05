package com.Home_pocket.dto;

import lombok.Builder;

@Builder
public record ListaMercadoResponseDTO(
        Long id,
        String nomeItem,
        Boolean StFinalizado,
        Double quantidade,
        String categoria,
        Double precoMedio) {
}
