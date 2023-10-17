package com.example.service02.javax1.dao.store.order;

import com.example.service02.javax1.model.store.order.Order;
import com.example.service02.javax1.model.store.order.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Order> {

    @Query(value = "SELECT o FROM OrderDetail o WHERE o.orderID = ?1")
    List<OrderDetail> getAllOrder(Order orderID);

    @Query(value = "SELECT sum(o.productQty * p.productPrice) FROM OrderDetail o, Product p WHERE o.orderID = ?1")
    Double getAmount(Order orderID);

    @Query(value = "SELECT sum(o.productQty * p.productPrice) FROM OrderDetail o, Product p WHERE o.orderStatus = 1")
    Double getTotal();
}
