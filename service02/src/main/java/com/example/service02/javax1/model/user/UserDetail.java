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
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "userID", referencedColumnName = "userID")
    private User userID;

    @Id
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "storeID", referencedColumnName = "storeID")
    private Store storeID;

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
        return "UserfollowModels [Userid=" + userID + ", Storeid=" + storeID + ", isdeleted=" + isDeleted + ", dateUpdate ="
                + dateUpdate + "]";
    }
}
