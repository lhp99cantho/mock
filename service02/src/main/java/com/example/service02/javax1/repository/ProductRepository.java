package com.example.service02.javax1.repository;

import com.example.service02.javax1.model.store.product.Category;
import com.example.service02.javax1.model.store.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory(Category category);

    List<Product> findByName(String name);

    List<Product> findByProductNamedQuery(@Param("name") String name);
    List<Product> findByCategoryName(String category);
}
