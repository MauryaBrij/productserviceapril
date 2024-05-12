package com.example.productserviceapril.services;

import com.example.productserviceapril.models.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id);
    List<Product> getAllProduct();
    Product updateProduct(Long id, Product product);
    Product createProduct(Product product);
    Product deleteProduct();
    Product replaceProduct();

}
