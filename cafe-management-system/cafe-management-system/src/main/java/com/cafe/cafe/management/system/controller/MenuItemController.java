package com.cafe.cafe.management.system.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.cafe.management.system.entity.MenuItem;
import com.cafe.cafe.management.system.model.MenuItemDTO;
import com.cafe.cafe.management.system.service.MenuItemService;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {
    @Autowired
    private MenuItemService menuItemService;

    @GetMapping
    public List<MenuItemDTO> getAllMenuItems() {
        return menuItemService.getAllMenuItems().stream()
                .map(menuItem -> {
                    MenuItemDTO dto = new MenuItemDTO();
                    dto.setId(menuItem.getId());
                    dto.setName(menuItem.getName());
                    dto.setPrice(menuItem.getPrice());
                    dto.setCategory(menuItem.getCategory());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemDTO> getMenuItemById(@PathVariable Long id) {
        return menuItemService.getMenuItemById(id)
                .map(menuItem -> {
                    MenuItemDTO dto = new MenuItemDTO();
                    dto.setId(menuItem.getId());
                    dto.setName(menuItem.getName());
                    dto.setPrice(menuItem.getPrice());
                    dto.setCategory(menuItem.getCategory());
                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public MenuItemDTO createMenuItem(@RequestBody MenuItemDTO menuItemDTO) {
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemDTO.getName());
        menuItem.setPrice(menuItemDTO.getPrice());
        menuItem.setCategory(menuItemDTO.getCategory());
        MenuItem savedMenuItem = menuItemService.createMenuItem(menuItem);
        menuItemDTO.setId(savedMenuItem.getId());
        return menuItemDTO;
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItemDTO> updateMenuItem(@PathVariable Long id, @RequestBody MenuItemDTO menuItemDTO) {
        MenuItem menuItem = menuItemService.getMenuItemById(id)
                .orElseThrow(() -> new RuntimeException("MenuItem not found"));
        menuItem.setName(menuItemDTO.getName());
        menuItem.setPrice(menuItemDTO.getPrice());
        menuItem.setCategory(menuItemDTO.getCategory());
        MenuItem updatedMenuItem = menuItemService.updateMenuItem(id, menuItem);
        menuItemDTO.setId(updatedMenuItem.getId());
        return ResponseEntity.ok(menuItemDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }
}