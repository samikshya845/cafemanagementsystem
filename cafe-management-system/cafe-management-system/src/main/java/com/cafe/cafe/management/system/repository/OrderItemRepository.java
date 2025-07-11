package com.cafe.cafe.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe.cafe.management.system.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}