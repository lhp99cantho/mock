package com.example.service02.javax1.model.report;

import com.example.service02.javax1.model.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Report2 implements Serializable {

    @Id
    private User user;

    private String userName;

    private Long countOrderTotal;

    //private Long total;

}
