package com.keyin.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing product resources
 * Provides endpoints for CRUD and product sorting
 */
@RestController
@RequestMapping("/products")
public class ProductController
{

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product addNewProduct(@RequestBody Product product)
    {
        return productService.addNewProduct(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts()
    {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id)
    {
        return productService.getProductById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product)
    {
        return productService.updateProduct(id, product)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id)
    {
        boolean deleted = productService.deleteProductById(id);
        if (deleted)
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/sorted")
    public List<Product> getSorted(@RequestParam String by)
    {
        return productService.getSorted(by);
    }
}