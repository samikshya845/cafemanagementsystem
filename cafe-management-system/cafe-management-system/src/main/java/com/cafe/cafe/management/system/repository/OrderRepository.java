package com.cafe.cafe.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe.cafe.management.system.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}