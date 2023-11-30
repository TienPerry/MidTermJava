package com.example.SpringCommerce.repository;

import com.example.SpringCommerce.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
