package com.texnoera.menu_service.service.implementation;

import com.texnoera.menu_service.dto.request.CreateMealRequest;
import com.texnoera.menu_service.dto.response.MealResponse;
import com.texnoera.menu_service.exception.NotFoundException;
import com.texnoera.menu_service.mapper.MealMapper;
import com.texnoera.menu_service.model.Meal;
import com.texnoera.menu_service.repository.MealRepository;
import com.texnoera.menu_service.service.abstraction.MealService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.texnoera.menu_service.mapper.MealMapper.toEntity;
import static com.texnoera.menu_service.mapper.MealMapper.toResponse;
import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Slf4j
public class MealServiceHandler implements MealService {
    MealRepository mealRepository;

    @Override
    public MealResponse save(CreateMealRequest request) {
        Meal meal = toEntity(request);

        mealRepository.save(meal);

        return toResponse(meal);
    }

    @Override
    public Page<MealResponse> findAll(Pageable pageable) {
        return mealRepository.findAll(pageable)
                .map(MealMapper::toResponse);
    }

    @Override
    public MealResponse findById(Long id) {
        Meal meal = mealRepository.findById(id).orElseThrow(() -> new NotFoundException("Meal not found"));

        return toResponse(meal);
    }


    @Override
    public List<Meal> findMealsByIds(List<Long> mealIds) {
        return mealIds.stream()
                .map(this::findMealById).toList();
    }

    @Override
    public Meal findMealById(Long id) {
        return mealRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Meal not found by id: " + id));
    }

}
