package com.example.service02.javax1.service;

import com.example.service02.javax1.model.report.Report2;
import com.example.service02.javax1.model.store.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    Page<Order> getAllOrder (int orderStatus, Boolean cancelOrder, String keyword, Pageable pageable);

    Page<Order> getOrdersByOrderStatus (int orderStatus);

    Integer getCountOrder(String id);


}
