package com.example.service02.javax1.service;

import com.example.service02.javax1.model.store.product.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(int id);
}
