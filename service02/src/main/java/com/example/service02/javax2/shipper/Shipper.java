package com.example.service02.javax2.shipper;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "shipper")
public class Shipper {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, length = 10)
    protected Long id;

    @OneToOne (mappedBy = "shipper")
    protected Shipper shipper;


}
