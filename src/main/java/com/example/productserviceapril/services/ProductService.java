package com.example.productserviceapril.services;

import com.example.productserviceapril.models.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id);
    List<Product> getAllProduct();
    Product updateProduct();
    Product createProduct();
    Product deleteProduct();
    Product replaceProduct();

}
