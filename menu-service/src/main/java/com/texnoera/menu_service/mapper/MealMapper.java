package com.texnoera.menu_service.mapper;

import com.texnoera.menu_service.dto.request.CreateMealRequest;
import com.texnoera.menu_service.dto.response.MealResponse;
import com.texnoera.menu_service.model.Meal;

public class MealMapper {

    public static MealResponse toResponse(Meal meal) {
        return MealResponse.builder()
                .id(meal.getId())
                .name(meal.getName())
                .price(meal.getPrice())
                .build();
    }

    public static Meal toEntity(CreateMealRequest request) {
        return Meal.builder()
                .name(request.name())
                .price(request.price())
                .build();
    }
}
