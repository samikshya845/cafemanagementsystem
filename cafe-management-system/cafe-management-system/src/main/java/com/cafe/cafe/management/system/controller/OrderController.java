package com.cafe.cafe.management.system.controller;

import com.cafe.cafe.management.system.entity.Order;
import com.cafe.cafe.management.system.model.OrderDTO;
import com.cafe.cafe.management.system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders().stream()
                .map(order -> {
                    OrderDTO dto = new OrderDTO();
                    dto.setId(order.getId());
                    dto.setOrderDate(order.getOrderDate());
                    dto.setCustomerId(order.getCustomer() != null ? order.getCustomer().getId() : null);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(order -> {
                    OrderDTO dto = new OrderDTO();
                    dto.setId(order.getId());
                    dto.setOrderDate(order.getOrderDate());
                    dto.setCustomerId(order.getCustomer() != null ? order.getCustomer().getId() : null);
                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderDate(orderDTO.getOrderDate());
        // Customer reference should be set manually if provided
        Order savedOrder = orderService.createOrder(order);
        orderDTO.setId(savedOrder.getId());
        return orderDTO;
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        Order order = orderService.getOrderById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setOrderDate(orderDTO.getOrderDate());
        Order updatedOrder = orderService.updateOrder(id, order);
        orderDTO.setId(updatedOrder.getId());
        return ResponseEntity.ok(orderDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}