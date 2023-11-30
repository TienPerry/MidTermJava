package com.example.SpringCommerce.service;

import com.example.SpringCommerce.models.Category;
import com.example.SpringCommerce.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;
    public List<Category> getAllCategory(){
        return categoryRepo.findAll();
    }
    public void addCategory (Category category){
        categoryRepo.save(category);
    }
    public void removeOneCate(int id){
        categoryRepo.deleteById(id);
    }
    public Optional<Category> getOneCate(int id){
        return categoryRepo.findById(id);
    }
    public void updateCategory(Category category){
        categoryRepo.save(category);
    }
}
