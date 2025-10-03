package com.texnoera.menu_service.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateMealRequest(

        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotNull(message = "Price must not be null")
        @DecimalMin(value = "0.00", inclusive = false, message = "Price must be greater than zero")
        BigDecimal price

) {
}
