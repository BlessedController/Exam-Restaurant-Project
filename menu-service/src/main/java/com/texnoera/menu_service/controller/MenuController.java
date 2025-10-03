package com.texnoera.menu_service.controller;

import com.texnoera.menu_service.dto.request.CreateMenuRequest;
import com.texnoera.menu_service.dto.response.MenuResponse;
import com.texnoera.menu_service.service.abstraction.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/v1/menu")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class MenuController {
    MenuService menuService;

    @PostMapping
    public ResponseEntity<MenuResponse> save(@RequestBody @Valid CreateMenuRequest request) {
        var body = menuService.save(request);

        return status(CREATED).body(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuResponse> findById(@PathVariable Long id) {
        var body = menuService.findById(id);

        return status(OK).body(body);
    }

    @GetMapping
    public ResponseEntity<Page<MenuResponse>> findAll(Pageable pageable) {
        var body = menuService.findAll(pageable);

        return status(OK).body(body);
    }
}
