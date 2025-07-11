package com.cafe.cafe.management.system.controller;

import com.cafe.cafe.management.system.entity.OrderItem;
import com.cafe.cafe.management.system.model.OrderItemDTO;
import com.cafe.cafe.management.system.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public List<OrderItemDTO> getAllOrderItems() {
        return orderItemService.getAllOrderItems().stream()
                .map(orderItem -> {
                    OrderItemDTO dto = new OrderItemDTO();
                    dto.setId(orderItem.getId());
                    dto.setOrderId(orderItem.getOrder() != null ? orderItem.getOrder().getId() : null);
                    dto.setMenuItemId(orderItem.getMenuItem() != null ? orderItem.getMenuItem().getId() : null);
                    dto.setQuantity(orderItem.getQuantity());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDTO> getOrderItemById(@PathVariable Long id) {
        return orderItemService.getOrderItemById(id)
                .map(orderItem -> {
                    OrderItemDTO dto = new OrderItemDTO();
                    dto.setId(orderItem.getId());
                    dto.setOrderId(orderItem.getOrder() != null ? orderItem.getOrder().getId() : null);
                    dto.setMenuItemId(orderItem.getMenuItem() != null ? orderItem.getMenuItem().getId() : null);
                    dto.setQuantity(orderItem.getQuantity());
                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public OrderItemDTO createOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        // Order and MenuItem references should be set manually if provided
        orderItem.setQuantity(orderItemDTO.getQuantity());
        OrderItem savedOrderItem = orderItemService.createOrderItem(orderItem);
        orderItemDTO.setId(savedOrderItem.getId());
        return orderItemDTO;
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDTO> updateOrderItem(@PathVariable Long id, @RequestBody OrderItemDTO orderItemDTO) {
        OrderItem orderItem = orderItemService.getOrderItemById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem not found"));
        orderItem.setQuantity(orderItemDTO.getQuantity());
        OrderItem updatedOrderItem = orderItemService.updateOrderItem(id, orderItem);
        orderItemDTO.setId(updatedOrderItem.getId());
        return ResponseEntity.ok(orderItemDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
}