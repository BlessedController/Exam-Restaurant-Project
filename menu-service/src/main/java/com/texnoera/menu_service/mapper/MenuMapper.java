package com.texnoera.menu_service.mapper;

import com.texnoera.menu_service.dto.request.CreateMenuRequest;
import com.texnoera.menu_service.dto.response.MenuResponse;
import com.texnoera.menu_service.model.Meal;
import com.texnoera.menu_service.model.Menu;

import java.util.List;
import java.util.stream.Collectors;

public class MenuMapper {

    public static MenuResponse toResponse(Menu menu) {

        var mealIds = menu.getMeals()
                .stream()
                .map(Meal::getId)
                .collect(Collectors.toList());


        return MenuResponse.builder()
                .id(menu.getId())
                .mealIds(mealIds)
                .restaurantId(menu.getRestaurantId())
                .build();
    }

    public static Menu toEntity(CreateMenuRequest request, List<Meal> meals) {
        return Menu.builder()
                .meals(meals)
                .restaurantId(request.restaurantId())
                .build();
    }

}
