package com.texnoera.restaurant_service.service.abstraction;

import com.texnoera.restaurant_service.dto.request.CreateRestaurantRequest;
import com.texnoera.restaurant_service.dto.response.RestaurantResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RestaurantService {

    RestaurantResponse save(CreateRestaurantRequest request);

    RestaurantResponse findById(Long id);

    Page<RestaurantResponse> findAll(Pageable pageable);

    Boolean existsById(Long id);
}
