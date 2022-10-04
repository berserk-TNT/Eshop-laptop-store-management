package com.cg.service.product;

import com.cg.model.Manufacturer;
import com.cg.model.Product;
import com.cg.model.dto.ProductDTO;
import com.cg.repository.IManufacturerRepository;
import com.cg.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IManufacturerRepository manufacturerRepository;

    @Override
    public List<ProductDTO> findAllProductDTO() {
        return productRepository.findAllProductDTO();
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

    @Override
    public Product getById(Long id) {
        return null;
    }

    @Override
    public Product save(Product product) {
        Manufacturer manufacturer = manufacturerRepository.save(product.getManufacturer());

        product.setManufacturer(manufacturer);

        return productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Boolean existsByTitleAndIdIsNot(String title, Long id) {
        return productRepository.existsByTitleAndIdIsNot(title, id);
    }

    @Override
    public List<ProductDTO> searchProduct(String keyword) {
        return productRepository.searchProduct(keyword);
    }
}
