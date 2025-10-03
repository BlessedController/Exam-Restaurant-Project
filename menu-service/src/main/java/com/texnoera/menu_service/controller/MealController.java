package com.texnoera.menu_service.controller;

import com.texnoera.menu_service.dto.request.CreateMealRequest;
import com.texnoera.menu_service.dto.response.MealResponse;
import com.texnoera.menu_service.service.abstraction.MealService;
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
@RequestMapping("/v1/meals")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class MealController {
    MealService mealService;

    @PostMapping
    ResponseEntity<MealResponse> save(@Valid @RequestBody CreateMealRequest request) {
        var body = mealService.save(request);

        return status(CREATED).body(body);
    }

    @GetMapping
    ResponseEntity<Page<MealResponse>> findAll(@PageableDefault(sort = "id", direction = ASC) Pageable pageable) {
        var body = mealService.findAll(pageable);

        return status(OK).body(body);
    }

    @GetMapping("/{id}")
    ResponseEntity<MealResponse> findById(@PathVariable Long id) {
        var body = mealService.findById(id);

        return status(OK).body(body);
    }

}
