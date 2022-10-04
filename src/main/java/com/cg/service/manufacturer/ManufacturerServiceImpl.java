package com.cg.service.manufacturer;

import com.cg.model.Manufacturer;
import com.cg.model.dto.ManufacturerDTO;
import com.cg.repository.IManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ManufacturerServiceImpl implements IManufacturerService {
    @Autowired
    private IManufacturerRepository manufacturerRepository;

    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public List<ManufacturerDTO> findAllManufacturerDTO() {
        return manufacturerRepository.findAllManufacturerDTO();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return manufacturerRepository.findById(id);
    }

    @Override
    public Manufacturer getById(Long id) {
        return null;
    }

    @Override
    public Manufacturer save(Manufacturer category) {
        return manufacturerRepository.save(category);
    }

    @Override
    public void remove(Long id) {
        manufacturerRepository.deleteById(id);
    }
}
