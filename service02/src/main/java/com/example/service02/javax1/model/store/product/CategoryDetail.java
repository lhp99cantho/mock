package com.example.service02.javax1.model.store.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table (name = "CategoryDetail")
public class CategoryDetail implements Serializable {
    @Id
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "categoryID", referencedColumnName = "categoryID")
    private Category categoryID;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "categoryName", referencedColumnName = "categoryName")
    private Category categoryName;

    @Column (name = "categoryStatus")
    @NotNull
    private boolean categoryStatus;

    @Column (name = "dateCreate")
    @CreationTimestamp
    @NotNull
    private LocalDateTime dateCreate;

    @Column (name = "dateUpdate")
    @CreationTimestamp
    @NotNull
    private LocalDateTime dateUpdate;

    public String toString(){
        return "CategoryDetail [categoryID " + categoryID + ", categoryName " + categoryName + ", categoryStatus " + categoryStatus + ", dateCreate " + dateCreate + ", dateUpdate" + dateUpdate + "]";
    }
}

