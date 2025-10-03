package com.texnoera.menu_service.repository;

import com.texnoera.menu_service.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
