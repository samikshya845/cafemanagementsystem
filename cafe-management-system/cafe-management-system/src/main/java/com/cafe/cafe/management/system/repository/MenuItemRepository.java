package com.cafe.cafe.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe.cafe.management.system.entity.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}