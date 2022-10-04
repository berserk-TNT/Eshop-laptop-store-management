package com.cg.repository;

import com.cg.model.RegionLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRegionLocationRepository extends JpaRepository<RegionLocation, Long> {
}
