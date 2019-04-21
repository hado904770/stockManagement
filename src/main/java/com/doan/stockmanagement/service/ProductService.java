package com.doan.stockmanagement.service;

import java.util.List;

import com.doan.stockmanagement.common.ResponseApi;
import com.doan.stockmanagement.entities.Product;

public interface ProductService {
    
    ResponseApi<List<Product>> getProduct();

    ResponseApi<Product> saveProduct(Product product);

    ResponseApi<Object> deleteProduct(Integer id);
    
}
