package com.Home_pocket.dto;

import com.Home_pocket.model.Category;
import lombok.Builder;

@Builder
public record MarketListResponseDTO(
        Long id,
        String itemName,
        Boolean isFinished,
        Integer quantity,
        Category category,
        Double mediumPrice) {
}
