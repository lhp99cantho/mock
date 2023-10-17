package com.example.service02.javax1.model.report;

import com.example.service02.javax1.model.store.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Report1 implements Serializable {

    @Id
    private Long id;

    public Product favorite;

    private String name;

    private Long count;

}
