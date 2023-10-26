package com.example.service02.javax1.model.store.product;

import com.example.service02.javax1.model.store.Store;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "category")
public class Category implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id", unique = true, length = 9)
    protected long id;

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
    @Temporal(TemporalType.DATE)
    @Column(name = "categoryCreateDate")
    private LocalDateTime categoryCreateDate;

    @NotEmpty(message = "Category Update Date is required!")
    @NotBlank
    @Temporal(TemporalType.DATE)
    @Column(name = "categoryUpdateDate")
    private LocalDateTime categoryUpdateDate;

    @Column(name = "isDel")
    private boolean isDel;

    @OneToOne(mappedBy = "category")
    protected ProductDetail productDetail;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    protected List<Product> product = new ArrayList<>();

    @OneToOne(mappedBy = "category")
    protected CategoryDetail categoryDetail;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    protected Store store;


    public Category (Long id, String categoryName, String categoryImages) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryImages = categoryImages;
    }

    @Override
    public String toString() {
        return "Category [categoryID = " + id + ", categoryName = " + categoryName + ", categoryImages = " + categoryImages + ", categoryCreateDate = "
                + categoryCreateDate + ", categoryUpdateDate = " + categoryUpdateDate + ", isDel=" + isDel + "]";
    }

}
