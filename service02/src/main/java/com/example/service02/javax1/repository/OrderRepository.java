package com.example.service02.javax1.repository;

import com.example.service02.javax1.model.store.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
