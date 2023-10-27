package com.example.service02.javax1.controller;

import com.example.service02.javax1.dao.store.order.OrderDAO;
import com.example.service02.javax1.model.store.order.Order;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderDAO orderDAO;

    @GetMapping(value = "/order")
    public String showAll(Model model, @RequestParam(value = "order", required = false) String orderId){
        List<Order> orders =
    }
}
