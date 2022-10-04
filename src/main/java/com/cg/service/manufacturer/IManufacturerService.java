package com.cg.service.manufacturer;

import com.cg.model.Manufacturer;
import com.cg.model.dto.ManufacturerDTO;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IManufacturerService extends IGeneralService<Manufacturer> {

    Manufacturer save(Manufacturer category);

    List<ManufacturerDTO> findAllManufacturerDTO();
}
