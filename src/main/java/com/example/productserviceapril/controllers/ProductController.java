package com.example.productserviceapril.controllers;
import com.example.productserviceapril.models.Product;
import com.example.productserviceapril.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
/*
 GET- get a product.
 DELETE - delete a product
 POST - create a product.
 PUT - replace a product.
 PATCH - Updating a product.

 GET product -> Modify -> PUT
 */


@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProduct();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return new Product();
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public Product deleteProductById(@PathVariable("id") Long id){
        return new Product();
    }
}
