package com.example.service02.javax1.model.user;

import com.example.service02.javax1.model.store.Store;
import com.example.service02.javax1.model.store.order.Order;
import com.example.service02.javax1.model.store.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table (name = "comments")
public class Comments implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "id", unique = true, length = 12)
    protected int id;

    @ManyToOne
    @JoinColumn (name = "user_id", referencedColumnName = "id")
    protected User user;


    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "product_id", referencedColumnName = "id")
    protected Product product;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "store_id", referencedColumnName = "id")
    protected Store store;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "order_id", referencedColumnName = "id")
    private Order order;

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

//    public String toString() {
//        return "Comments [commentID " + id + ", userID " + ", userName " + userName + ", userAvatar " + userAvatar + ", productID " + productID + ", storeID " + storeID + ", orderID " + orderID + ", commentRating " + commentRating + ", commentContent " + commentContent + ", commentContent " + commentCreateAt + ", commentUpdateAt " + commentUpdateAt + "]";
//    }

}
