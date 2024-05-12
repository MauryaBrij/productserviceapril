package com.example.productserviceapril.services;

import com.example.productserviceapril.dtos.FakeStoreProductDto;
import com.example.productserviceapril.models.Category;
import com.example.productserviceapril.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeProductService implements ProductService{

    private RestTemplate restTemplate;

    @Autowired // not required this annotation but let it be.
    FakeProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) {
        FakeStoreProductDto fakeStoreProductDto =
                restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProduct() {
        FakeStoreProductDto[] fakeStroeProductDtosList =
                restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);

        // converts results
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto:fakeStroeProductDtosList){
           products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return products;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = convertProductToDto(product);
        fakeStoreProductDto = restTemplate.patchForObject(
                "https://fakestoreapi.com/products/"+ id, fakeStoreProductDto, FakeStoreProductDto.class);
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = convertProductToDto(product);
        fakeStoreProductDto = restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreProductDto, FakeStoreProductDto.class);
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Product deleteProduct() {
        return null;
    }

    @Override
    public Product replaceProduct() {
        return null;
    }

    // Converts a FakeStoreProductDto to a Product object
    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        if(fakeStoreProductDto == null){
            return null;
        }
        Product product = new Product();

        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());

        Category category = new Category();

        category.setId(0L);
        category.setTitle(fakeStoreProductDto.getCategory());

        product.setCategory(category);

        return product;
    }

    // Converts a Product to a FakeStoreProductDto object
    public FakeStoreProductDto convertProductToDto(Product product) {
        if (product == null) {
            return null;
        }

        FakeStoreProductDto dto = new FakeStoreProductDto();

        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());

        // Assuming the category title is what needs to be set in the DTO's category field
        if (product.getCategory() != null) {
            dto.setCategory(product.getCategory().getTitle());
        } else {
            dto.setCategory(null); // or set to a default value as appropriate
        }

        return dto;
    }
}
