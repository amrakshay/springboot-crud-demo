package com.ensat.controllers;

import com.ensat.entities.Product;
import com.ensat.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Product controller.
 */
@RestController
public class ProductAPIController {
     @Autowired
     private ProductService productService;

    @GetMapping("/api/products")
     public List<Product> listProducts() {
         List<Product> products = new ArrayList<>();
         Iterable<Product> productIterable = productService.listAllProducts();
         Iterator<Product> productIterator = productIterable.iterator();
         while (productIterator.hasNext()) {
             products.add(productIterator.next());
         }
         return products;
     }

    @GetMapping("/api/product/{id}")
    public Product getProduct(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @PostMapping("/api/products")
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/api/product/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable Integer id) {
        Product currentProduct = productService.getProductById(id);
        currentProduct.setProductId(product.getProductId());
        currentProduct.setName(product.getName());
        currentProduct.setPrice(product.getPrice());
        return productService.saveProduct(currentProduct);
    }

    @DeleteMapping("/api/product/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }
}
