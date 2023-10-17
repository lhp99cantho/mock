package com.example.service02.javax1.model.store.order;

import com.example.service02.javax1.model.store.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table (name = "orderDetail")
public class OrderDetail implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "orderID", referencedColumnName = "orderID")
    private Order orderID;

    @Id
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "productID", referencedColumnName = "productID")
    private Product productID;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "productName", referencedColumnName = "productName")
    private Product productName;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "productPrice", referencedColumnName = "productPrice")
    private Product productPrice;

    @Column(name = "productQty")
    @NotNull
    private int productQty;

    @NotNull
    @Column (name = "orderCount")
    private int orderCount;

    @NotNull
    @Column (name = "orderStatus")
    private int orderStatus;

    public String toString() {
        return "OrderDetail [orderID " + orderID + ", productID " + productID + ", productName " + productName + ", productPrice " + productPrice + ", orderCount " + orderCount + ", orderStatus " + orderStatus + "]";
    }
}
