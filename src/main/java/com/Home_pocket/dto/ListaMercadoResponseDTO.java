package com.Home_pocket.dto;

import com.Home_pocket.model.Categoria;
import lombok.Builder;

@Builder
public record ListaMercadoResponseDTO(
        Long id,
        String nomeItem,
        Boolean StFinalizado,
        Integer quantidade,
        Categoria categoria,
        Double precoMedio) {
}
