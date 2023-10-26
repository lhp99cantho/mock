package com.example.service02.javax1.dao.store.product;

import com.example.service02.javax1.model.store.product.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product, Long> {
    Page<Product> findAllByProductNameLike (String productName, Pageable pageable);

    @Query ("SELECT max(p.product_id) FROM Product p")
    Long findIDMax();

    @Query("SELECT p FROM Product p ORDER BY p.productPrice DESC")
    Page<Product> findAllTop12Product(Pageable pageable);

    @Query ("SELECT p from Product p WHERE p.category_id = ?1 ")
    Page<Product> findAllByCategoryID (String id, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.category_id= ?1 and p.productName != ?2")
    List<Product> getAllProductSame(String id, String name);

    @Query ("SELECT count(p) FROM Product p")
    Integer getCount();
}
