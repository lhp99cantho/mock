package com.example.service02.javax1.model.store.product;

import com.example.service02.javax1.model.store.Store;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "productId", unique = true, length = 11)
    @OneToOne (mappedBy = "productID")
    protected Product productID;

    @NotEmpty (message = "Product name is required!")
    @NotBlank
    @Column (name = "productName")
    @OneToOne (mappedBy = "productName")
    protected Product productName;

    @NotEmpty (message = "Product Description is required!")
    @NotBlank
    @Column (name = "productDes")
    protected String productDes;

    @NotEmpty (message = "Product price is required!")
    @NotBlank
    @Column (name = "productPrice")
    protected int productPrice;

    @NotEmpty (message = "Product quantity is required!")
    @NotBlank
    @Column (name = "productQuantity")
    protected int productQuantity;


    @OneToOne (cascade = CascadeType.ALL)
    private Store storeID;

    @OneToOne (cascade = CascadeType.ALL)
    private Store storeName;

    @OneToOne (cascade = CascadeType.ALL)
    private Category categoryID;

    @NotEmpty (message = "Product images is required!")
    @NotBlank
    @Column (name = "productImg")
    protected String productImg;

    @NotEmpty (message = "Product rating is required!")
    @NotBlank
    @Column (name = "productRating")
    protected float productRating;

    @Override
    public String toString(){
        return "Product [productID " + productID + ", productName " + productName + ", productDes " + productDes + ", productPrice " + productPrice + ", productQuantity " + productQuantity + ", productImg " + productImg + ", productRating " + productRating + ", categoryID " + categoryID + ", storeID " + storeID + ", storeName " + storeName + "]";
    }
}
