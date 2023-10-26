package com.example.service02.javax1.controller;

import com.example.service02.javax1.dao.store.order.OrderDAO;
import com.example.service02.javax1.dao.store.order.OrderDetailDAO;
import com.example.service02.javax1.dao.store.product.ProductDAO;
import com.example.service02.javax1.model.store.cart.CartItems;
import com.example.service02.javax1.model.store.order.Order;
import com.example.service02.javax1.model.store.order.OrderDetail;
import com.example.service02.javax1.model.user.Address;
import com.example.service02.javax1.model.user.User;
import com.example.service02.javax1.service.ItemsService;
import com.example.service02.javax1.service.SessionService;
import com.example.service02.javax1.service.ShoppingCartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Collection;

@Controller
@RequestMapping ("UTOP")
public class CartController {

    @Autowired
    ProductDAO productDAO;

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    OrderDetailDAO orderDetailDAO;

    @Autowired
    ItemsService item;

    @Autowired
    ShoppingCartService cart;

    @Autowired
    SessionService session;

    @Autowired
    HttpServletRequest request;

    //Cart page
    String uri;
    String urlPage = "user/";
    @RequestMapping("/carts.html")
    public String cartsPage(Model model) {
        model.addAttribute("items", item.getProductItems());
        Collection<CartItems> cartItemsCollection = cart.getProductItems();
        model.addAttribute("cartItems", cartItemsCollection);
        model.addAttribute("total", cart.getAmount());
        model.addAttribute("NoOfItems", cart.getCount());

        return urlPage + "carts";
    }

    @RequestMapping("/carts.html/views")
    public String cartsView(Model model) {
        Collection<CartItems> cartItemsCollection = cart.getProductItems();
        model.addAttribute("cartItems", cartItemsCollection);
        model.addAttribute("total", cart.getAmount());
        model.addAttribute("NoOfItems", cart.getCount());

        return urlPage + "carts";
    }

    @RequestMapping("/carts.html/add/{id}")
    public String addCart(@PathVariable("id") Long id) {

        // System.out.println(id);
        ItemsService itemsService = item;
        System.out.println(item.toString());

        if (item != null) {
            CartItems cartItems = new CartItems ();
            BeanUtils.copyProperties(item, cartItems);
            cartItems.setCartItemsIdProduct(item.getProductItems());
            cartItems.setCartItemsQty(1);

            cart.add(cartItems);
        }

        // return "redirect:/carts.html";
        String url = session.get("urisu", null);
        if (url != null) {
            return "redirect:" + url;
        } else {
            return "redirect:/UTOP/products.html";
        }
    }

    @RequestMapping("/carts.html/remove/{id}")
    public String remove(@PathVariable("id") int id) {
        cart.remove(id);

        String url = session.get("urisu", null);
        if (url != null) {
            return "redirect:" + url;
        } else {
            return "redirect:/UTOP/products.html";
        }
    }

    @PostMapping("/carts.html/update/{id}")
    public String update(@RequestParam("id") Long id, @RequestParam("qty") Integer qty) {
        cart.update(id, qty);
        return "redirect:/UTOP/carts.html/views";
    }

    @RequestMapping("/carts.html/clear")
    public String clear() {
        cart.clear();
        return "redirect:/UTOP/carts.html/views";
    }

    // Check out page
    int check = 0;
    @RequestMapping("/checkout.html")
    public String checkoutPage(Model model) {
		if (check != 0) {
			model.addAttribute("message", "*Đặt hàng thành công!");
			check = 0;
		}
        return urlPage + "checkout";
    }

    @PostMapping("/checkout.html")
    public String productOrder(Model model, @RequestParam("address") String address, @NotNull LocalDateTime Date) {
        User user = (User) session.get("user", null);
        Collection<CartItems> cartItemsCollection = cart.getProductItems();
        int loi = 0;
        if (address.isEmpty()) {
            model.addAttribute("message", "*Vui lòng nhập địa chỉ giao hàng của bạn!");
            loi++;
        }
        if (loi == 0) {
            Order order = new Order();
            order.setUser(user);
            order.setUserAddress(new Address());
            order.setOrderDateCreate(Date);
            order.setOrderStatus(false);
            order.setCancelOrder(false);

            orderDAO.save(order);

            for (CartItems item : cartItemsCollection) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(order);
                orderDetail.setProduct(productDAO.findById(item.getCartItemsID()).get());
                orderDetail.setProductPrice(item.getCartItemsPrice());
                orderDetail.setProductQty(item.getCartItemsQty());

                orderDetailDAO.save(orderDetail);
            }
            cart.clear();
            // check++;
        } else {
            return urlPage + "checkout";
        }
        String url = session.get("urisu", null);
        if (url != null) {
            return "redirect:" + url;
        } else {
            return "redirect:/UTOP/products.html";
        }
    }

}
