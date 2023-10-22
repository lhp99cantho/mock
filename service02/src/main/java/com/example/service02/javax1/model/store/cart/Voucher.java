package com.example.service02.javax1.model.store.cart;

import com.example.service02.javax1.model.store.Store;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "voucher")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", unique = true)
    protected Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "store_id", referencedColumnName = "id")
    protected Store store;

    @JoinColumn (name = "storeName")
    private Store storeName;

    @NotEmpty(message = "Voucher content is required!")
    @Column (name = "voucherContent")
    private String voucherContent;

    @CreationTimestamp
    @PastOrPresent
    @NotNull
    @Column (name = "voucherDateCreate")
    private LocalDateTime voucherDateCreate;
}

