package com.example.service02.javax1.service.Impl;

import com.example.service02.javax1.model.store.product.Category;
import com.example.service02.javax1.model.store.product.Product;
import com.example.service02.javax1.repository.ProductRepository;
import com.example.service02.javax1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll(){
        return productRepository.findAll();
    }
    @Override
    public Product findById(int id){
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findByCategory(int categoryId){
        Category category = new Category();
        category.setId(categoryId);

        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> findByCategoryWithHQL(int categoryId){
        return productRepository.findByName(null);
    }

}
