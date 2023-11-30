package com.example.SpringCommerce.repository;

import com.example.SpringCommerce.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Integer> {

}
