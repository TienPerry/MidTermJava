package com.example.SpringCommerce.controllers;

import com.example.SpringCommerce.global.GlobalData;
import com.example.SpringCommerce.models.Product;
import com.example.SpringCommerce.service.CategoryService;
import com.example.SpringCommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping({"/"})
    public String home(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("active", "active");
        model.addAttribute("cartNumber", GlobalData.cart.size());
        return "home";
    }

    @GetMapping({"/search"})
    public String getProByName(@RequestParam("name") String name, Model model){
        List<Product> products = productService.getAllProByName(name);
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", products);
        model.addAttribute("cartNumber", GlobalData.cart.size());
        return "home";
    }

    @GetMapping({"/cate/{id}"})
    public String getProByCate(@PathVariable("id") int id, Model model){
        List<Product> products = productService.getAllProByCate(id);
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", products);
        model.addAttribute("cartNumber", GlobalData.cart.size());
        return "home";
    }


    @GetMapping({"/viewProduct/{id}"})
    public String viewProduct(@PathVariable("id") long id, Model model){
//        Product p = productService.getOneProduct(id).get();
        model.addAttribute("product", productService.getOneProduct(id).get());
        model.addAttribute("cartNumber", GlobalData.cart.size());
        return "detailsProduct";
    }
}
