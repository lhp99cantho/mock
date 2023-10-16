package com.example.service02.javax1.model.store.product;

import com.example.service02.javax1.model.store.Store;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table (name = "ProductDetail")
public class ProductDetail implements Serializable {

    @Id
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "productID", referencedColumnName = "productID")
    private Product productID;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "productName", referencedColumnName = "productName")
    private Product productName;

    @Column (name = "productQuantity")
    private int productQuantity;

    @Column (name = "productPrice")
    private int productPrice;

    @Column (name = "productSold")
    private int productSold;

    @Column (name = "productIsActive")
    private boolean productIsActive;

    @Column (name = "productIsSelling")
    private boolean productIsSelling;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "categoryID", referencedColumnName = "categoryID")
    protected Category categoryID;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "storeID", referencedColumnName = "storeID")
    private Store storeID;

    @Column (name = "productRating")
    @NotEmpty (message = "Product Rating is required!")
    private float productRating;

    @Column (name = "productCreateDate")
    @Temporal(TemporalType.DATE)
    @NotEmpty (message = "Product Create Date is required!")
    private LocalDateTime productCreateDate;

    @Column (name = "productUpdateDate")
    @Temporal(TemporalType.DATE)
    @NotEmpty (message = "Product Update Date is required!")
    private LocalDateTime productUpdateDate;

    @NotEmpty (message = "Product image is required!")
    @Column (name = "productImg")
    private String productImg;

    public String toString(){
        return "ProductDetail [productID " + productID + ", productName " + productName + ", productQuantity " + productQuantity + ", productPrice " + productPrice + ", productSold " + productSold + ", productIsActive " + productIsActive + ", productIsSelling " + productIsSelling + ", categoryID " + categoryID + ", storeID " + storeID + ", productRating " + productRating + ", productCreateDate " + productCreateDate + ", productUpdateDate "+ productUpdateDate + ", productImg " + productImg + "]";
    }

}
