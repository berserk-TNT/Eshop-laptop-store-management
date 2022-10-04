package com.cg.repository;

import com.cg.model.Manufacturer;
import com.cg.model.dto.ManufacturerDTO;
import com.sun.org.apache.xml.internal.serialize.LineSeparator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    @Query("SELECT NEW com.cg.model.dto.ManufacturerDTO(" +
            "m.id, " +
            "m.manufacturerName) " +
            "FROM Manufacturer AS m")
    List<ManufacturerDTO> findAllManufacturerDTO();
}
