package com.texnoera.menu_service.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record MenuResponse(

        Long id,

        List<Long> mealIds,

        Long restaurantId

) {
}
