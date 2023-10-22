package com.example.service02.javax1.model.store;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table (name = "storeFollow")
public class StoreDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "id")
    protected Long id;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "store_id", referencedColumnName = "id")
    private Store store;

    @NotEmpty
    @JoinColumn(name = "storeName")
    protected Store storeName;

    @Column (name = "storesIsDeleted")
    private boolean storesIsDeleted;

    @Column (name = "storeAvatar")
    protected String storeAvatar;

    public String toString () {
        return "StoreDetail [storeID " + id + ", storeName " + storeName + ", storeIsDelete " + storesIsDeleted + ", storeAvatar " + storeAvatar + "]";
    }
}
