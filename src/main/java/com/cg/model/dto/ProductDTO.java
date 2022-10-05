package com.cg.model.dto;

import com.cg.model.Manufacturer;
import com.cg.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ProductDTO {

    @NotNull(message = "Product ID is required!")
    private Long id;

    @NotBlank(message = "Image name is required!")
    private String imageUrl;

    @NotBlank(message = "Title is required!")
    @Size(min = 6, message = "Title length contains 2 characters minimum!")
    @Size(max = 100, message = "Title length contains 100 characters maximum!")
    private String title;

    @NotBlank(message = "Price is required!")
    @Pattern(regexp = "^\\d+$", message = "Price must be number!")
    @Min(value = 50000, message = "Price is 50.000 VND minimum!")
    @Max(value = 100000000, message = "Price is 100.000.000 VND maximum!")
    private String price;

    @NotBlank(message = "Quantity is required!")
    @Pattern(regexp = "^\\d+$", message = "Quantity must be number!")
    @Min(value = 1, message = "Quantity is 1 minimum!")
    @Max(value = 100, message = "Quantity is 100 maximum!")
    private String quantity = "1";

    private ManufacturerDTO manufacturer;

    public ProductDTO(Long id, String imageUrl, String title, Manufacturer manufacturer, BigDecimal price, Integer quantity){
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.manufacturer = manufacturer.toManufacturerDTO();
        this.price = price.toString();
        this.quantity = quantity.toString();
    }


    public Product toProduct() {
        return new Product()
                .setId(id)
                .setImageUrl(imageUrl)
                .setTitle(title)
                .setManufacturer(manufacturer.toManufacturer())
                .setPrice(new BigDecimal(Long.parseLong(price)))
                .setQuantity(Integer.parseInt(quantity));
    }
}
