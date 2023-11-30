package com.example.SpringCommerce.controllers;


import com.example.SpringCommerce.global.GlobalData;
import com.example.SpringCommerce.models.Product;
import com.example.SpringCommerce.models.User;
import com.example.SpringCommerce.repository.UserRepo;
import com.example.SpringCommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    @Autowired
    ProductService productService;
    @Autowired
    UserRepo userRepo;

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id){
        GlobalData.cart.add(productService.getOneProduct(id).get());
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String cartGet(Model model){
        model.addAttribute("cartNumber", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", GlobalData.cart);
        return "cart";
    }

    @GetMapping("/removeItem/{idx}")
    public String cartItemRemove(@PathVariable int idx){
        GlobalData.cart.remove(idx);
        return "redirect:/cart";
    }

}
