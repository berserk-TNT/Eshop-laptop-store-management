package com.cg.model;

import com.cg.model.dto.ManufacturerDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "manufacturers")
@Accessors(chain = true)
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "manufacturer_name")
    private String manufacturerName;

    @OneToMany(targetEntity = Product.class, mappedBy = "manufacturer" , fetch = FetchType.EAGER)
    private Set<Product> products;


    public ManufacturerDTO toManufacturerDTO(){
        return new ManufacturerDTO()
                .setId(id)
                .setManufacturerName(manufacturerName);
    }
}

