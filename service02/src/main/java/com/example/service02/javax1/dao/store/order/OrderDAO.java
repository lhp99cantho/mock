package com.example.service02.javax1.dao.store.order;

import com.example.service02.javax1.model.report.Report2;
import com.example.service02.javax1.model.store.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o, User u WHERE o.orderStatus = ?1 and o.cancelOrder = ?2 and u.Name = ?3")
    Page<Order> getAllOrder (int orderStatus, Boolean cancelOrder, String keyword, Pageable pageable);

    @Query ("SELECT o FROM Order o WHERE o.orderStatus = ?1 ")
    Page<Order> getOrdersByOrderStatus (int orderStatus);

    @Query("SELECT count(o) FROM Order o WHERE o.cancelOrder = false")
    Integer getCount();

    @Query("SELECT o FROM Order o WHERE o.user = ?1 and o.cancelOrder = false")
    List<Order> getAllOrderUser(String user);

    @Query("SELECT count(o) FROM Order o WHERE o.user = ?1 and o.cancelOrder = false and o.orderStatus = false")
    Integer getCountOrder(String id);

    @Query("SELECT o FROM Order o WHERE o.user = ?1 and o.user_id = ?2 and o.cancelOrder = false")
    Order getOrders(String user,Long id);

    @Query(value = "SELECT NEW Report2(o.user, o.user_id,count(o) AS countOrder "
            + "FROM Order o WHERE o.user_id LIKE ?1"
            + " GROUP BY o.user,o.user_id")
    Page<Report2> getListReport(String keyword, Pageable pageable);
}
