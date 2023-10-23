package com.example.service02.javax1.model.user;

import com.example.service02.javax1.model.store.Store;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table (name = "UserDetail")
public class UserDetail {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected long id;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "user_id", referencedColumnName = "id")
    protected User user;


    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "store_id", referencedColumnName = "id")
    private Store store;

    @NotNull
    @NotBlank
    @Column (name = "isDeleted")
    private boolean isDeleted;

    @Temporal(TemporalType.DATE)
    @NotBlank
    @Column (name = "dateUpdate")
    private LocalDateTime dateUpdate;

    @Override
    public String toString() {
        return "UserfollowModels [Userid=" + id + ", Storeid=" + store + ", isdeleted=" + isDeleted + ", dateUpdate ="
                + dateUpdate + "]";
    }
}
