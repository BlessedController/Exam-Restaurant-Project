package com.texnoera.restaurant_service.controller;

import com.texnoera.restaurant_service.dto.request.CreateRestaurantRequest;
import com.texnoera.restaurant_service.dto.response.RestaurantResponse;
import com.texnoera.restaurant_service.service.abstraction.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/v1/restaurants")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class RestaurantController {
    RestaurantService restaurantService;

    @PostMapping
    ResponseEntity<RestaurantResponse> save(@Valid @RequestBody CreateRestaurantRequest request) {
        var body = restaurantService.save(request);

        return status(CREATED).body(body);
    }

    @GetMapping
    ResponseEntity<Page<RestaurantResponse>> findAll(@PageableDefault(sort = "id", direction = ASC) Pageable pageable) {
        var all = restaurantService.findAll(pageable);

        return status(OK).body(all);
    }

    @GetMapping("/{id}")
    ResponseEntity<RestaurantResponse> findById(@PathVariable Long id) {
        var body = restaurantService.findById(id);

        return status(OK).body(body);
    }


    @GetMapping("/exists/{id}")
    ResponseEntity<Boolean> existsById(@PathVariable("id") Long id){
        var body = restaurantService.existsById(id);

        return status(OK).body(body);
    }

}
