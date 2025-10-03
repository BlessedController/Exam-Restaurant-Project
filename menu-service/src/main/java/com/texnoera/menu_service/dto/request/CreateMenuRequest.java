package com.texnoera.menu_service.dto.request;

import jakarta.validation.constraints.*;

import java.util.List;

public record CreateMenuRequest(

        @NotNull(message = "Meal ID list cannot be null")
        @NotEmpty(message = "Meal ID list must contain at least one entry")
        List<Long> mealIds,

        @NotNull(message = "Restaurant ID cannot be null")
        @Positive(message = "Restaurant ID must be a positive number")
        Long restaurantId
) {
}
