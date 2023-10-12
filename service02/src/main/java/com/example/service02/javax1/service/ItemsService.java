package com.example.service02.javax1.service;

import com.example.service02.javax1.model.store.product.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@SessionScope
@Service
public interface ItemsService {
    List<Product> getProductItems();
    void setProductItems();
    void add(Product product);
    void remove(int id);
    void update(Product product);
    Product findById(int productId);
    void setProductItems(List<Product> list);
}
