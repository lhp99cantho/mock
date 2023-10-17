package com.example.service02.javax1.model.report;

import com.example.service02.javax1.model.store.product.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Report3 {

    @Id
    private Category group;

    private double sum;

    private long count;

}
