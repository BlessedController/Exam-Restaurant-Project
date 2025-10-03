package com.texnoera.menu_service.dto.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record MealResponse(
        Long id,
        String name,
        BigDecimal price
) {
}
