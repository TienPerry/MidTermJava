package com.example.SpringCommerce.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String author;
    private int categoryID;
    private double price;
    private String imageFile;
    private String description;
    private int quantity;
}
