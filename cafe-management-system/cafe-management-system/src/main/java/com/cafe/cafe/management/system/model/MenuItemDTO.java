package com.cafe.cafe.management.system.model;

import lombok.Data;

@Data
public class MenuItemDTO {
    private Long id;          
    private String name;      
    private double price;     
    private String category;  
    
}