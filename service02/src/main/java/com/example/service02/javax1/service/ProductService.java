package com.example.service02.javax1.service;

import com.example.service02.javax1.model.store.product.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(int id);
    List<Product> findByCategory(int categoryId);
    List<Product> findByCategoryWithHQL(int categoryId);

}
