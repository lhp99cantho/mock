package com.example.service02.javax1.model.store.order;

import com.example.service02.javax1.model.store.Store;
import com.example.service02.javax1.model.store.product.Product;
import com.example.service02.javax1.model.user.Address;
import com.example.service02.javax1.model.user.Comments;
import com.example.service02.javax1.model.user.User;
import com.example.service02.javax2.shipper.Shipper;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "order")
public class Order implements Serializable {

    @Serial
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, length = 6)
    protected long id;

    @ManyToOne
    @JoinColumn (name = "store_id", referencedColumnName = "id")
    protected Store store;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    protected User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderDetail_id", referencedColumnName = "id")
    protected OrderDetail orderDetail;

    @OneToMany(mappedBy = "order" ,cascade = CascadeType.ALL)
    protected Product product;

    @OneToOne (mappedBy = "comments")
    protected Comments comments;

    @JoinColumn (name = "userAddress")
    protected Address userAddress;

    @NotEmpty(message = "User Phone Number is required!")
    @NotBlank
    @NumberFormat
    @Column(name = "PhoneNumber",unique = true, length = 10)
    protected String phoneNumber;

    @NotNull
    @DateTimeFormat
    @Temporal(TemporalType.DATE)
    @Column(name = "orderDateCreate")
    protected LocalDateTime orderDateCreate;

    @NotEmpty(message = "Order Status is required!")
    @NotBlank
    @Max(5)
    @Column(name = "orderStatus")
    protected boolean orderStatus;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "shipper_id", referencedColumnName = "id")
    protected Shipper shipper;

    @NotEmpty(message = "Order Summary Cost is required!")
    @NotBlank
    @Column(name = "orderSumCost")
    protected int orderSumCost;

    protected Boolean cancelOrder;

    @Column(name = "shippingStatus")
    protected int shippingStatus;

    @Override
    public String toString() {
        return "Order [oderID = " + id + ", storeID = " + store + ", userAddress = " + userAddress
                + ", userPhoneNumber = " + phoneNumber + ", status = " + orderStatus + ", orderSummaryCost = " + orderSumCost + ", shippingStatus = " + shippingStatus
                + "]";
    }


}
