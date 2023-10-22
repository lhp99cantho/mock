package com.example.service02.javax1.model.store.product;

import com.example.service02.javax1.model.store.Store;
import com.example.service02.javax1.model.store.order.Order;
import com.example.service02.javax1.model.store.order.OrderDetail;
import com.example.service02.javax1.model.user.Comments;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "product")
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "id", unique = true, length = 11)
    protected Long id;

    @NotEmpty (message = "Product name is required!")
    @NotBlank
    @Column (name = "productName")
    protected String productName;

    @NotEmpty (message = "Product Description is required!")
    @NotBlank
    @Column (name = "productDes")
    protected String productDes;

    @NotEmpty (message = "Product price is required!")
    @NotBlank
    @Column (name = "productPrice")
    protected float productPrice;

    @NotEmpty (message = "Product quantity is required!")
    @NotBlank
    @Column (name = "productQuantity")
    protected int productQuantity;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    protected Order order;

    @OneToOne(mappedBy = "product")
    protected OrderDetail orderDetail;

    @ManyToOne
    @JoinColumn(name = "store_id",referencedColumnName = "id")
    protected Store store;

    @OneToOne(mappedBy = "product")
    protected ProductDetail productDetail;

    @JoinColumn(name = "storeName")
    protected Store storeName;

    @ManyToOne
    @JoinColumn(name = "category_id" ,referencedColumnName = "id")
    protected Category category;

    @OneToOne(mappedBy = "product")
    protected Comments comments;

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
        return "Product [productID " + id + ", productName " + productName + ", productDes " + productDes + ", productPrice " + productPrice + ", productQuantity " + productQuantity + ", productImg " + productImg + ", productRating " + productRating + ", categoryID " +  ", storeID " + id + ", storeName " + storeName + "]";
    }
}
