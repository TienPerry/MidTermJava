package com.example.SpringCommerce.controllers;

import com.example.SpringCommerce.dto.ProductDTO;
import com.example.SpringCommerce.models.Category;
import com.example.SpringCommerce.models.Product;
import com.example.SpringCommerce.service.CategoryService;
import com.example.SpringCommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/products";
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    // This page load all categories
    @GetMapping("/admin")
    public String adminPage(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("category", new Category());
        return "adminPage";
    }



    @PostMapping("/admin/addNewCat")
    public String addNewCat(@ModelAttribute("category") Category category, @RequestParam("description") String[] descriptions) {
        String description = String.join("", descriptions);
        category.setDescription(description);
        categoryService.addCategory(category);
        return "redirect:/admin";
    }

    @PostMapping("/admin/deleteCategory/{id}")
    public String deleteCat(@PathVariable int id){
        categoryService.removeOneCate(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/updateCategory/{id}")
    public String updateCat(@PathVariable int id, @RequestParam("description") String[] descriptions, Model model){
        Optional<Category> category = categoryService.getOneCate(id);
        category.ifPresent(c -> {
                String description = String.join("", descriptions);
                c.setDescription(description);
                categoryService.updateCategory(c);
                model.addAttribute("category", c);
        });
        return "redirect:/admin";
    }

    @GetMapping("/category/{id}")
    public String getCategory(@PathVariable int id, Model model) {
        Optional<Category> category = categoryService.getOneCate(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "category";
        } else {
            return "error";
        }
    }

    // Handle product:
    @GetMapping("/admin/books")
    public String bookAll(Model model){
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("productDTO", new ProductDTO());
        return "adminProducts";
    }

    @PostMapping("/admin/books/add")
    public String bookAdd(@ModelAttribute("productDTO")ProductDTO productDTO,
                          @RequestParam("imageProduct")MultipartFile file,
                          @RequestParam("imgName")String imageName,
                          @RequestParam("description") String[] descriptions) throws IOException{
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.getOneCate(productDTO.getCategoryID()).get());
        product.setAuthor(productDTO.getAuthor());
        String description = String.join("", descriptions);
        product.setDescription(description);
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());

        // Upload image :
        String img;
        if(!file.isEmpty()){
            long timestamp = System.currentTimeMillis();
            String originalName = file.getOriginalFilename();
            String extension = originalName.substring(originalName.lastIndexOf('.'));
            String uniqueFileName = timestamp + extension;
            img = uniqueFileName;
            Path fileNameAndPath = Paths.get(uploadDir, uniqueFileName);
            Files.write(fileNameAndPath, file.getBytes());
        }
        else {
            img = imageName;
        }
        product.setImageFile(img);
        productService.addProduct(product);
        return "redirect:/admin/books";
    }

    @GetMapping("/book/{id}")
    @ResponseBody
    public ResponseEntity<ProductDTO> getProduct(@PathVariable long id) {
        Optional<Product> optionalProduct = productService.getOneProduct(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setAuthor(product.getAuthor());
            productDTO.setPrice(product.getPrice());
            productDTO.setImageFile(product.getImageFile());
            productDTO.setQuantity(product.getQuantity());
            productDTO.setCategoryID(product.getCategory().getId());
            productDTO.setDescription(product.getDescription());

            return ResponseEntity.ok(productDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/admin/books/update/{id}")
    public String updateProduct(@PathVariable long id,@ModelAttribute("productDTO")ProductDTO productDTO,
                                @RequestParam("imageProduct")MultipartFile file,
                                @RequestParam("imgNameUpd")String imageName,
                                @RequestParam("description") String[] descriptions,
                                Model model) {
        Optional<Product> product = productService.getOneProduct(id);
        product.ifPresent(c -> {
//            c.setId(productDTO.getId());
            c.setName(productDTO.getName());
            c.setCategory(categoryService.getOneCate(productDTO.getCategoryID()).get());
            c.setAuthor(productDTO.getAuthor());
            String description = String.join("", descriptions);
            c.setDescription(description);
            c.setPrice(productDTO.getPrice());
            c.setQuantity(productDTO.getQuantity());

            // Upload image :
            String img;
            // File empty
            if(imageName.isEmpty() && file.isEmpty()){
                System.out.println("================ENTER FILE IS EMPTY===========================");
            }
            // New file
            else if (!imageName.equals(c.getImageFile())){
                System.out.println("================ENTER NEW FILE===========================");
                long timestamp = System.currentTimeMillis();
                String originalName = file.getOriginalFilename();
                String extension = originalName.substring(originalName.lastIndexOf('.'));
                String uniqueFileName = timestamp + extension;
                img = uniqueFileName;
                Path fileNameAndPath = Paths.get(uploadDir, uniqueFileName);
                try {
                    Files.write(fileNameAndPath, file.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                c.setImageFile(img);
                productService.updateProduct(c);
            }
            // Old file
            else {
                System.out.println("================ENTER OLD FILE===========================");
                c.setImageFile(imageName);
                productService.updateProduct(c);
            }
        });
        return "redirect:/admin/books";
    }
}
