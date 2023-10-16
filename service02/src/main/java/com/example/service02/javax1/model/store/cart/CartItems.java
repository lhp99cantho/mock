package com.example.service02.javax1.model.store.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartItems {

    private Long cartItemsID;

    private Long cartItemsIdProduct;

    private String cartItemsName;

    private double cartItemsPrice;

    private int cartItemsQty = 1;

    private String cartItemsImg;
}
