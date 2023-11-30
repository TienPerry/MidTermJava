package com.example.SpringCommerce.controllers;

import com.example.SpringCommerce.global.GlobalData;
import com.example.SpringCommerce.models.Product;
import com.example.SpringCommerce.models.User;
import com.example.SpringCommerce.repository.UserRepo;
import com.example.SpringCommerce.service.OrderService;
import com.example.SpringCommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class OrderController {
    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;
    @Autowired
    UserRepo userRepo;


    @GetMapping("/checkout")
    public String checkout(Model model){
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name =  authentication.getName();
        Optional<User> user = userRepo.findUserByEmail(name);
        return "redirect:/";
    }
}
