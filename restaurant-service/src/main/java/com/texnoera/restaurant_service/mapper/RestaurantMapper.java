package com.texnoera.restaurant_service.mapper;

import com.texnoera.restaurant_service.dto.request.CreateRestaurantRequest;
import com.texnoera.restaurant_service.dto.response.RestaurantResponse;
import com.texnoera.restaurant_service.model.Restaurant;

public class RestaurantMapper {

    public static RestaurantResponse toResponse(Restaurant restaurant) {
        return RestaurantResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .build();
    }


    public static Restaurant toEntity(CreateRestaurantRequest request) {
        return Restaurant.builder()
                .name(request.name())
                .address(request.address())
                .build();
    }
}
