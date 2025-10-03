package com.texnoera.menu_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "restaurant-service",
        url = "localhost:8080",
        path = "/v1/restaurants"
)
public interface RestaurantServiceClient {

    @GetMapping("/exists/{id}")
    ResponseEntity<Boolean> existsById(@PathVariable("id") Long id);

}
