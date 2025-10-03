package com.texnoera.menu_service.service.abstraction;

import com.texnoera.menu_service.dto.request.CreateMealRequest;
import com.texnoera.menu_service.dto.response.MealResponse;
import com.texnoera.menu_service.model.Meal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MealService {

    MealResponse save(CreateMealRequest request);

    Page<MealResponse> findAll(Pageable pageable);

    MealResponse findById(Long id);

    List<Meal> findMealsByIds(List<Long> mealIds);

    Meal findMealById(Long id);

}
