package com.example.service02.javax1.service.Impl;

import com.example.service02.javax1.model.store.order.Order;
import com.example.service02.javax1.service.OrderService;
import com.example.service02.javax1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Override
    public Page<Order> getAllOrder(int orderStatus, Boolean cancelOrder, String keyword, Pageable pageable){
        return orderService.getAllOrder(orderStatus,cancelOrder,keyword,pageable);
    }
    @Override
    public Page<Order> getOrdersByOrderStatus (int orderStatus){
        return orderService.getOrdersByOrderStatus(orderStatus);
    }

    @Override
    public Integer getCountOrder(String id){
        return orderService.getCountOrder(id);
    }


}
