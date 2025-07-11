package com.cafe.cafe.management.system.model;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long id;          
    private Long orderId;     
    private Long menuItemId;  
    private int quantity;    
}