package com.example.service02.javax1.model.store.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "categoryFollow")
public class CategoryFollow implements Serializable {
    @Id
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "categoryID", referencedColumnName = "categoryID")
    private Category categoryID;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "categoryName", referencedColumnName = "categoryName")
    private Category categoryName;

    @Column (name = "categoryIsDelete")
    @NotNull
    private boolean categoryIsDelete;

    @Column (name = "dateCreate")
    @CreationTimestamp
    @NotNull
    private LocalDateTime dateCreate;

    @Column (name = "dateUpdate")
    @CreationTimestamp
    @NotNull
    private LocalDateTime dateUpdate;

    public String toString(){
        return "CategoryFollow [categoryID " + categoryID + ", categoryName " + categoryName + ", categoryIsDelete " + categoryIsDelete + ", dateCreate " + dateCreate + ", dateUpdate" + dateUpdate + "]";
    }
}

