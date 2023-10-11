package com.example.service02.javax1.model.store.order;

import com.example.service02.javax1.model.store.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWarDeployment;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "orderFollow")
public class OrderFollow implements Serializable {
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

    @NotNull
    @Column (name = "productCount")
    private int productCount;

    public String toString() {
        return "OrderFollow [orderID " + orderID + ", productID " + productID + ", productName " + productName + ", productPrice " + productPrice + ", productCount " + productCount + "]";
    }
}
