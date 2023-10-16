package com.example.service02.javax1.model.store.cart;

import lombok.*;

@Data
@NoArgsConstructor
public class Item {

    private Long itemID;
    private Long itemIDProduct;
    private String itemName;
    private double itemPrice;
    private int itemQty = 1;
    private String itemImg;

    public Item(Long itemID,Long itemIDProduct, String itemName, double itemPrice, int itemQty, String itemImg) {
        super();
        this.itemID = itemID;
        this.itemIDProduct = itemIDProduct;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQty = itemQty;
        this.itemImg = itemImg;
    }
}
