package com.cg.controller.rest;

import com.cg.exception.DataInputException;
import com.cg.exception.EmailExistsException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.Manufacturer;
import com.cg.model.Product;
import com.cg.model.dto.ManufacturerDTO;
import com.cg.model.dto.ProductDTO;
import com.cg.service.manufacturer.IManufacturerService;
import com.cg.service.product.IProductService;
import com.cg.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    @Autowired
    private AppUtil appUtils;
    @Autowired
    private IProductService productService;
    @Autowired
    private IManufacturerService manufacturerService;

    @GetMapping("/manufacturers")
    public ResponseEntity<?> getAllManufacturerDTO() {
        List<ManufacturerDTO> manufacturerDTO = manufacturerService.findAllManufacturerDTO();
        return new ResponseEntity<>(manufacturerDTO, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllProductDTO() {
        List<ProductDTO> productDTO = productService.findAllProductDTO();
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            throw new ResourceNotFoundException("Invalid product ID!");
        }
        return new ResponseEntity<>(productOptional.get().toProductDTO(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreateProduct(@Validated @RequestBody ProductDTO productDTO, BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Boolean existsByTitle = productService.existsByTitleAndIdIsNot(productDTO.getTitle(), productDTO.getId());
        if (existsByTitle) {
            throw new EmailExistsException("Title existed!");
        }

        productDTO.setId(0L);
        Optional<Manufacturer> category = manufacturerService.findById(productDTO.getManufacturer().getId());
        if (!category.isPresent()) {
            throw new EmailExistsException("Manufacturer ID doesn't exist!");
        }
        try {
            Product newProduct = productService.save(productDTO.toProduct());
            return new ResponseEntity<>(newProduct.toProductDTO(), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            throw new DataInputException("Data error!");
        }

    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> doUpdateProduct(@Validated @RequestBody ProductDTO productDTO, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Boolean existsById = productService.existsById(productDTO.getId());
        if (!existsById) {
            throw new EmailExistsException("ID doesn't exist!");
        }

        Boolean existsByTitle = productService.existsByTitleAndIdIsNot(productDTO.getTitle(), productDTO.getId());
        if (existsByTitle) {
            throw new EmailExistsException("Title existed!");
        }

        Optional<Manufacturer> manufacturer = manufacturerService.findById(productDTO.getManufacturer().getId());
        if (!manufacturer.isPresent()) {
            throw new EmailExistsException("Manufacturer ID doesn't exist!");
        }

        try {
            Product updateProduct = productService.save(productDTO.toProduct());
            return new ResponseEntity<>(updateProduct.toProductDTO(), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            throw new DataInputException("Data error!");
        }
    }

    @PatchMapping("/remove/{id}")
    public ResponseEntity<?> removeAllProductDTO(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            product.get().setDeleted(true);
            productService.save(product.get());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            throw new DataInputException("Data error!");
        }
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<?> searchProductDTO(@PathVariable String keyword) {
        List<ProductDTO> productDTO = productService.searchProduct(keyword);
        if (productDTO.isEmpty()) {
            throw new DataInputException("There's no result for this search!");
        }
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }
}
