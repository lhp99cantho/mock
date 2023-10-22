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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn (name = "id")
    protected Long id;

    @JoinColumn (name = "productName")
    protected String productName;

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
    @JoinColumn (name = "product_id", referencedColumnName = "id")
    protected Product product;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "category_id", referencedColumnName = "id")
    protected Category category;

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
        return "ProductDetail [ID " + id + ", productName " + productName + ", productQuantity " + productQuantity + ", productPrice " + productPrice + ", productSold " + productSold + ", productIsActive " + productIsActive + ", productIsSelling " + productIsSelling + ", categoryID " + category+ ", storeID "  + ", productRating " + productRating + ", productCreateDate " + productCreateDate + ", productUpdateDate "+ productUpdateDate + ", productImg " + productImg + "]";
    }

}
