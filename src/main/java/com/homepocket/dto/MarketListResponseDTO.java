package com.homepocket.dto;

import com.homepocket.model.Category;
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
