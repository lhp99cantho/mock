package com.example.service02.javax1.service;

import com.example.service02.javax1.model.store.product.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;

@SessionScope
@Service
public interface ShoppingCartService {
    int getCount();

    double getAmount();

    void clear();

    Collection<Product> getProductItems();

    void remove(int productID);

    void update(int productID, int quantity);

    void add(Product product);
}
