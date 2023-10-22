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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn (name = "id")
    private Long id;

    @OneToOne(mappedBy = "orderDetail")
    protected Order order;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "product_id", referencedColumnName = "id")
    private Product product;

    @JoinColumn (name = "productName")
    protected Product productName;

    @JoinColumn (name = "productPrice")
    protected Product productPrice;

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
        return "OrderDetail [orderID " + id + ", productName " + productName + ", productPrice " + productPrice + ", orderCount " + orderCount + ", orderStatus " + orderStatus + "]";
    }
}
