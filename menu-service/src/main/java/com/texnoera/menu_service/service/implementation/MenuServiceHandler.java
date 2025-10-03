package com.texnoera.menu_service.service.implementation;

import com.texnoera.menu_service.client.RestaurantServiceClient;
import com.texnoera.menu_service.dto.request.CreateMenuRequest;
import com.texnoera.menu_service.dto.response.MenuResponse;
import com.texnoera.menu_service.exception.NotFoundException;
import com.texnoera.menu_service.mapper.MenuMapper;
import com.texnoera.menu_service.model.Meal;
import com.texnoera.menu_service.model.Menu;
import com.texnoera.menu_service.repository.MenuRepository;
import com.texnoera.menu_service.service.abstraction.MealService;
import com.texnoera.menu_service.service.abstraction.MenuService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.texnoera.menu_service.mapper.MenuMapper.toEntity;
import static com.texnoera.menu_service.mapper.MenuMapper.toResponse;
import static java.lang.Boolean.TRUE;
import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Slf4j
public class MenuServiceHandler implements MenuService {
    MenuRepository menuRepository;
    RestaurantServiceClient restaurantServiceClient;
    MealService mealService;

    @Override
    public MenuResponse save(CreateMenuRequest request) {

        validateRestaurantExists(request.restaurantId());

        List<Meal> meals = mealService.findMealsByIds(request.mealIds());

        Menu menu = toEntity(request, meals);

        menuRepository.save(menu);

        return toResponse(menu);
    }

    @Override
    public MenuResponse findById(Long id) {
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new NotFoundException("Menu not found"));
        return toResponse(menu);
    }

    @Override
    public Page<MenuResponse> findAll(Pageable pageable) {

        return menuRepository
                .findAll(pageable)
                .map(MenuMapper::toResponse);

    }


    private void validateRestaurantExists(Long id) {
        Boolean isRestaurantExist;
        try {
            isRestaurantExist = restaurantServiceClient.existsById(id).getBody();
            log.info("Feign call executed for restaurant id {}: exists={}", id, isRestaurantExist);
        } catch (FeignException e) {
            log.warn("Feign call failed for restaurant id {}", id, e);
            throw new NotFoundException("Restaurant service is not available");
        }

        if (!TRUE.equals(isRestaurantExist)) {
            throw new NotFoundException("Restaurant not found");
        }

    }


}
