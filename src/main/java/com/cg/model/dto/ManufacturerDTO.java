package com.cg.model.dto;

import com.cg.model.Manufacturer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ManufacturerDTO {

    @NotNull(message = "Role is required")
    private Long id;

    private String manufacturerName;

    public Manufacturer toManufacturer() {
        return new Manufacturer()
                .setId(id)
                .setManufacturerName(manufacturerName);
    }
}
