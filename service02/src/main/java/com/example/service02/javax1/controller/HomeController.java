package com.example.service02.javax1.controller;

import com.example.service02.javax1.dao.store.category.CategoryDAO;
import com.example.service02.javax1.dao.store.order.OrderDAO;
import com.example.service02.javax1.dao.store.product.ProductDAO;
import com.example.service02.javax1.dao.user.FavoriteDAO;
import com.example.service02.javax1.model.store.product.Product;
import com.example.service02.javax1.model.user.User;
import com.example.service02.javax1.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping ("UTOP")
public class HomeController {

    @Autowired
    SessionService session;

    @Autowired
    HttpServletRequest request;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    FavoriteDAO favDAO;

    String uri;
    String urlPage = "user/";

    public void setUri() {
        uri = request.getRequestURI();
        session.set("urisu", uri);
    }

    // Home page
    @RequestMapping("/index.html")
    public String homePage(Model m, @RequestParam("field") Optional<String> field) {
        setUri();

        String sort;
        if (field.isEmpty()) {
            sort = String.valueOf(categoryDAO.findAll().get(0).getId());
        } else {
            sort = field.get();
        }
        PageRequest pageable = PageRequest.of(0, 12);

        m.addAttribute("top12", productDAO.findAllTop12Product(pageable));

        Page<Product> list = productDAO.findAllByCategoryID(sort, PageRequest.of(0, 12));

        m.addAttribute("product1", list);

        //phân trang
        m.addAttribute("product2", productDAO.findAll(PageRequest.of(0, 3)));
        m.addAttribute("product3", productDAO.findAll(PageRequest.of(1, 3)));
        m.addAttribute("product4", productDAO.findAll(PageRequest.of(2, 3)));

        m.addAttribute("field2", sort);

        return "home/" + "index";
    }

    // Product page
    @RequestMapping({ "/products.html", "/products.html/sort" })
    public String productsPage(Model m, @RequestParam("field") Optional<String> field,
                               @RequestParam("show") Optional<Integer> show, @RequestParam("sort") Optional<Integer> criteria,
                               @RequestParam("p") Optional<Integer> p, @RequestParam("category") Optional<String> category,
                               @RequestParam("keywords") Optional<String> kw) {
        setUri();
        String kwords = kw.orElse(session.get("keywords", ""));
        session.set("keywords", kwords);

        int s = criteria.orElse(0);
        int paging = p.orElse(0);
        int shows = show.orElse(9);

        Sort sort;
        String tim;
        PageRequest pageable;

        if (s == 0) {
            sort = Sort.by(Sort.Direction.ASC, field.orElse("id"));
        } else {
            sort = Sort.by(Sort.Direction.DESC, field.orElse("id"));
        }

        m.addAttribute("sort", s);
        m.addAttribute("field2", field.orElse("id"));
        m.addAttribute("show", shows);

        m.addAttribute("number", paging);

        pageable = PageRequest.of(paging, shows, sort);
        Page<Product> list;
        if (!category.isEmpty()) {
            tim = category.get();
            m.addAttribute("catego", tim);
            list = productDAO.findAllByCategoryID(tim, pageable);
        } else {
            list = productDAO.findAllByProductNameLike("%"+kwords+"%", pageable);
        }
        if(list.isEmpty()) {
            m.addAttribute("check",true);
        }else {m.addAttribute("check",false);}

        m.addAttribute("product", list);

        return urlPage + "products";
    }

    // Contact page
    @RequestMapping("/contact.html")
    public String contactPage() {
        setUri();
        return urlPage + "contact";
    }

    // About page
    @RequestMapping("/about.html")
    public String aboutPage() {
        setUri();
        return urlPage + "about";
    }
    @ModelAttribute("ordersCount")
    public Integer getOderCount() {
        User user = (User) session.get("user", null);

        if (user != null) {
            return orderDAO.getCountOrder(user.getUserName());
        } else {
            return null;
        }
    }
    @ModelAttribute("favCount")
    public Integer getFavcount() {
        User user = (User) session.get("user", null);

        if (user != null) {
            return favDAO.getCountFav(user.getUserName());
        } else {
            return null;
        }
    }

}
