package com.cafe.cafe.management.system.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderDTO {
    private Long id;          
    private LocalDateTime orderDate; 
    private Long customerId; 
}