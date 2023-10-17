package com.example.service02.javax1.controller;

import com.example.service02.javax1.dao.store.order.OrderDAO;
import com.example.service02.javax1.dao.store.order.OrderDetailDAO;
import com.example.service02.javax1.dao.store.product.ProductDAO;
import com.example.service02.javax1.model.store.order.OrderDetail;
import com.example.service02.javax1.model.user.Favorite;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("UTOP")
public class ProductController {

    @Autowired
    ProductDAO productDAO;

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    OrderDetailDAO orderDetailDAO;

    @Autowired
    Favorite favorite;
}
