package com.example.service02.javax1.service.Impl;

import com.example.service02.javax1.model.store.product.Category;
import com.example.service02.javax1.repository.CategoryRepository;
import com.example.service02.javax1.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
    @Override
    public Category findById(int id){
        return categoryRepository.findById(id).orElse(null);
    }


}
