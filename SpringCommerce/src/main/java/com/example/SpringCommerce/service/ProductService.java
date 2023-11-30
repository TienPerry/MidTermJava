package com.example.SpringCommerce.service;

import com.example.SpringCommerce.models.Product;
import com.example.SpringCommerce.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }
    public void addProduct (Product product){
        productRepo.save(product);
    }
    public void removeOneProduct(long id){
        productRepo.deleteById(id);
    }
    public Optional<Product> getOneProduct(long id){
        return productRepo.findById(id);
    }
    public void updateProduct(Product product){
        productRepo.save(product);
    }
    public List<Product> getAllProByCate(int id){
        return productRepo.findAllByCategory_Id(id);
    }
    public List<Product> getAllProByName(String name){
        return productRepo.findAllByNameLikeIgnoreCase("%" + name + "%");
    }
}
