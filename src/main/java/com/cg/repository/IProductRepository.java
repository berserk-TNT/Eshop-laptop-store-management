package com.cg.repository;

import com.cg.model.Product;
import com.cg.model.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    Boolean existsByTitleAndIdIsNot(String title, Long id);

    @Query("SELECT NEW com.cg.model.dto.ProductDTO(" +
            "p.id, " +
            "p.imageUrl, " +
            "p.title, " +
            "p.manufacturer, " +
            "p.price, " +
            "p.quantity) " +
            "FROM Product AS p " +
            "WHERE p.deleted = false")
    List<ProductDTO> findAllProductDTO();

    @Query("SELECT NEW com.cg.model.dto.ProductDTO(" +
            "p.id, " +
            "p.imageUrl, " +
            "p.title, " +
            "p.manufacturer, " +
            "p.price, " +
            "p.quantity) " +
            "FROM Product AS p " +
            "JOIN Manufacturer AS m " +
            "ON m.id = p.manufacturer.id " +
            "WHERE " +
            "p.deleted = false " +
            "AND CONCAT(" +
            "p.id, " +
            "p.title, " +
            "m.manufacturerName, " +
            "p.price, " +
            "p.quantity) " +
            "LIKE %?1% ")
    List<ProductDTO> searchProduct(String keyword);
}
