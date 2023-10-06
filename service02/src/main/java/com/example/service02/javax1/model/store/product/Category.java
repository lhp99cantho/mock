package com.example.service02.javax1.model.store.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "categoryID", unique = true, length = 9)
    private int categoryID;

    @NotEmpty(message = "Category Name is required!")
    @NotBlank
    @Column(name = "categoryName")
    private String categoryName;

    @NotEmpty(message = "Category Images is required!")
    @NotBlank
    @Column(name = "categoryImages")
    private String categoryImages;

    @NotEmpty(message = "Category Create Date is required!")
    @NotBlank
    @CreationTimestamp
    @Column(name = "categoryCreateDate")
    private LocalDateTime categoryCreateDate;

    @NotEmpty(message = "Category Update Date is required!")
    @NotBlank
    @CreationTimestamp
    @Column(name = "categoryUpdateDate")
    private LocalDateTime categoryUpdateDate;

    @Column(name = "isDel")
    private boolean isDel;

    public Category (int categoryID, String categoryName, String categoryImages) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.categoryImages = categoryImages;
    }

    @Override
    public String toString() {
        return "Category [categoryID = " + categoryID + ", categoryName = " + categoryName + ", categoryImages = " + categoryImages + ", categoryCreateDate = "
                + categoryCreateDate + ", categoryUpdateDate = " + categoryUpdateDate + ", isDel=" + isDel + "]";
    }

}
