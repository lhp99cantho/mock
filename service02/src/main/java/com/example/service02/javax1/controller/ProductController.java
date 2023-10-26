package com.example.service02.javax1.controller;


import com.example.service02.javax1.model.store.product.Category;
import com.example.service02.javax1.model.store.product.Product;
import com.example.service02.javax1.service.CategoryService;
import com.example.service02.javax1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/product")
    public String findAll(Model model, @RequestParam(value = "category", required = false) String categoryId) {
        List<Category> category = categoryService.findAll();
        List<Product> product = productService.findAll();


        model.addAttribute("category", category);
        model.addAttribute("product", product);
        return "product";
    }

}
