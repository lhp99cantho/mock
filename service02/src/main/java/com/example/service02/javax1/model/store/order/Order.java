package com.example.service02.javax1.model.store.order;

import com.example.service02.javax1.model.store.Store;
import com.example.service02.javax1.model.user.Address;
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

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderID", unique = true, length = 6)
    private int orderID;

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User userID;

    @Id
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "storeID", referencedColumnName = "storeID")
    private Store storeID;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "userAddress", referencedColumnName = "userAddress")
    private Address userAddress;

    @NotEmpty(message = "User Phone Number is required!")
    @NotBlank
    @NumberFormat
    @Column(name = "userPhoneNumber",unique = true, length = 10)
    private String userPhoneNumber;

    @NotNull
    @DateTimeFormat
    @CreationTimestamp
    @Column(name = "orderDateCreate")
    private LocalDateTime orderDateCreate;

    @NotEmpty(message = "Order Status is required!")
    @NotBlank
    @Max(5)
    @Column(name = "orderStatus")
    private int orderStatus;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "shipperID", referencedColumnName = "shiperID")
    private Shipper shipperID;

    @NotEmpty(message = "Order Summary Cost is required!")
    @NotBlank
    @Column(name = "orderSumCost")
    private int orderSumCost;

    @Column(name = "shippingStatus")
    private int shippingStatus;

    @Override
    public String toString() {
        return "Order [oderID = " + orderID + ", userID = " + userID + ", storeID = " + storeID + ", userAddress = " + userAddress
                + ", userPhoneNumber = " + userPhoneNumber + ", status = " + orderStatus + ", orderSummaryCost = " + orderSumCost + ", shippingStatus = " + shippingStatus
                + "]";
    }
}
