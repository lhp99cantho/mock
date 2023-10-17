package com.example.service02.javax1.dao.store.category;

import com.example.service02.javax1.model.store.product.Category;
import com.example.service02.javax1.model.store.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryDAO extends JpaRepository<Category, String> {

    Page<Category> findAllBynameLike(String keywords, Pageable pageable);

    @Query("SELECT DISTINCT p.categoryID FROM Product p")
    List<Category> findAllCategoryInProduct();

    @Query(value = "SELECT p FROM Product p WHERE p.categoryID = ?1")
    Page<Product> getAllCategoryProduct(String keywords, Pageable pageable);

    @Query("SELECT count(c) FROM Category c")
    Integer getCount();

}
