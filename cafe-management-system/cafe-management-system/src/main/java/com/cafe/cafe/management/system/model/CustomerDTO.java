package com.cafe.cafe.management.system.model;

import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;          
    private String name;      
    private String email;    
    private String phone;     
}