package com.example.service02.javax1.model.store.product;

import com.example.service02.javax1.model.store.Store;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "productFollow")
public class ProductFollow implements Serializable {

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
    private Category categoryID;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "storeID", referencedColumnName = "storeID")
    private Store storeID;

    @Column (name = "productRating")
    @NotEmpty (message = "Product Rating is required!")
    private float productRating;

    @Column (name = "productCreateDate")
    @CreationTimestamp
    @NotEmpty (message = "Product Create Date is required!")
    private LocalDateTime productCreateDate;

    @Column (name = "productUpdateDate")
    @CreationTimestamp
    @NotEmpty (message = "Product Update Date is required!")
    private LocalDateTime productUpdateDate;

    @NotEmpty (message = "Product image is required!")
    @Column (name = "productImg")
    private String productImg;

    public String toString(){
        return "ProductFollow [productID " + productID + ", productName " + productName + ", productQuantity " + productQuantity + ", productPrice " + productPrice + ", productSold " + productSold + ", productIsActive " + productIsActive + ", productIsSelling " + productIsSelling + ", categoryID " + categoryID + ", storeID " + storeID + ", productRating " + productRating + ", productCreateDate " + productCreateDate + ", productUpdateDate "+ productUpdateDate + ", productImg " + productImg + "]";
    }

}
