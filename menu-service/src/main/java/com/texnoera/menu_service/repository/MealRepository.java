package com.texnoera.menu_service.repository;

import com.texnoera.menu_service.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {

}
