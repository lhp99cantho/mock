package com.example.service02.javax1.model.user;

import com.example.service02.javax1.model.store.Store;
import com.example.service02.javax1.model.store.order.Order;
import com.example.service02.javax1.model.store.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "comments")
public class Comments {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "commentID", unique = true, length = 12)
    private int commentID;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "userID", referencedColumnName = "userID")
    private User userID;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "userName", referencedColumnName = "userName")
    private User userName;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "userAvatar", referencedColumnName = "userAvatar")
    private User userAvatar;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "productID", referencedColumnName = "productID")
    private Product productID;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "storeID", referencedColumnName = "storeID")
    private Store storeID;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "orderID", referencedColumnName = "orderID")
    private Order orderID;

    @Column (name = "commentRating")
    private int commentRating;

    @Column (name = "commentContent")
    @NotBlank
    @NotEmpty (message = "Comment content is required!")
    private String commentContent;

    @CreationTimestamp
    @NotNull
    @Column (name = "commentCreateAt")
    private LocalDateTime commentCreateAt;

    @CreationTimestamp
    @NotNull
    @Column (name = "commentUpdateAt")
    private LocalDateTime commentUpdateAt;

    public String toString() {
        return "Comments [commentID " + commentID + ", userID " + userID + ", userName " + userName + ", userAvatar " + userAvatar + ", productID " + productID + ", storeID " + storeID + ", orderID " + orderID + ", commentRating " + commentRating + ", commentContent " + commentContent + ", commentContent " + commentCreateAt + ", commentUpdateAt " + commentUpdateAt + "]";
    }

}
