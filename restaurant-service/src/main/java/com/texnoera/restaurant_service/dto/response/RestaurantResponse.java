package com.texnoera.restaurant_service.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@Builder
public record RestaurantResponse(
        Long id,
        String name,
        String address
) {
}
