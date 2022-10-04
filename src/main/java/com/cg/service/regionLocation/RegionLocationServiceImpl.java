package com.cg.service.regionLocation;

import com.cg.model.RegionLocation;
import com.cg.repository.IRegionLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RegionLocationServiceImpl implements IRegionLocationService{
    @Autowired
    private IRegionLocationRepository regionLocationRepository;

    @Override
    public List<RegionLocation> findAll() {
        return null;
    }

    @Override
    public Optional<RegionLocation> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public RegionLocation getById(Long id) {
        return null;
    }

    @Override
    public RegionLocation save(RegionLocation locationRegion) {
        return regionLocationRepository.save(locationRegion);
    }

    @Override
    public void remove(Long id) {
    }
}
