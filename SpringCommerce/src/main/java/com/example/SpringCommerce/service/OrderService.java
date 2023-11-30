package com.example.SpringCommerce.service;

import com.example.SpringCommerce.models.Category;
import com.example.SpringCommerce.models.Order;
import com.example.SpringCommerce.repository.OrderRepo;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService{
    @Autowired
    OrderRepo orderRepo;

    public List<Order> getAllOrders(){
        return orderRepo.findAll();
    }
    public void createOrder (Order order){
        orderRepo.save(order);
    }
    public Optional<Order> getOneOrder(int id){
        return orderRepo.findById(id);
    }
}
