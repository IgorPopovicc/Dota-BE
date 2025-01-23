package com.artigo.dota.controller;

import com.artigo.dota.dto.*;
import com.artigo.dota.exception.ImageProcessingException;
import com.artigo.dota.exception.ProductNotProcessedException;
import com.artigo.dota.service.ProductDetailsService;
import com.artigo.dota.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private final ProductDetailsService productDetailsService;

    public ProductController(ProductService productService, ProductDetailsService productDetailsService) {
        this.productService = productService;
        this.productDetailsService = productDetailsService;
    }

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ProductDTO>> getProductsPageable(Pageable pageable) {
        return ResponseEntity.ok(productService.getProductsPageable(pageable));
    }

    @GetMapping("type/{type}")
    public ResponseEntity<List<ProductDTO>> getProductsByType(@PathVariable String type) {
        return ResponseEntity.ok(productService.getProductsByType(type));
    }

    @GetMapping("type/{type}/page")
    public ResponseEntity<Page<ProductDTO>> getProductsByTypePageable(Pageable pageable, @PathVariable String type) {
        return ResponseEntity.ok(productService.getProductsByTypePageable(pageable, type));
    }

    @GetMapping("details/{id}")
    public ResponseEntity<ProductDetailsDTO> getProductDetailById(@PathVariable Long id) {
        return ResponseEntity.ok(productDetailsService.getProductById(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping("details/availability")
    public ResponseEntity<List<ProductDetailQuantityDTO>> getProductDetailQuantities(@RequestBody ProductDetailQuantitiesRequestDTO productDetailQuantitiesRequestDTO) {
        return ResponseEntity.ok(productDetailsService.getProductDetailQuantities(productDetailQuantitiesRequestDTO.getProductDetailIds()));
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(
            @RequestPart("product")@Valid ProductSubmitDTO product,
            @RequestPart("files") List<MultipartFile> files) {

        try {
            ProductDTO processedProduct = productService.processProduct(product, files);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(processedProduct);

        }catch (ImageProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (ProductNotProcessedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

    @DeleteMapping("details/{id}")
    public ResponseEntity<ProductDetailsDTO> deleteProductDetail(@PathVariable Long id) {
        return ResponseEntity.ok(productDetailsService.deleteProductDetail(id));
    }

}
