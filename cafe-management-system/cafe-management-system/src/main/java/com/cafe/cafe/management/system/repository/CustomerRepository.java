package com.cafe.cafe.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe.cafe.management.system.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}