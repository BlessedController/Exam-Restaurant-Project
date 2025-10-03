package com.texnoera.restaurant_service.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateRestaurantRequest(

        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotBlank(message = "Address must be defined")
        String address
) {
}
