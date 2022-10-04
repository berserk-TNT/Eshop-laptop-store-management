package com.cg.model;

import com.cg.model.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
@Accessors(chain = true)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(nullable = false)
    private String title;

    @Digits(integer = 12, fraction = 0)
    private BigDecimal price;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id", updatable = false)
    private Manufacturer manufacturer;


    public ProductDTO toProductDTO(){
        return new ProductDTO()
                .setId(id)
                .setImageUrl(imageUrl)
                .setTitle(title)
                .setManufacturer(manufacturer.toManufacturerDTO())
                .setPrice(price.toString())
                .setQuantity(String.valueOf(quantity));
    }
}
