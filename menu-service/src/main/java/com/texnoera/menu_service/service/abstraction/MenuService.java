package com.texnoera.menu_service.service.abstraction;

import com.texnoera.menu_service.dto.request.CreateMenuRequest;
import com.texnoera.menu_service.dto.response.MenuResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MenuService {
    MenuResponse save(CreateMenuRequest request);
    MenuResponse findById(Long id);
    Page<MenuResponse> findAll(Pageable pageable);
}
