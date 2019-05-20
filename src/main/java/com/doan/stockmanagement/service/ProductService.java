package com.doan.stockmanagement.service;

import java.util.List;

import com.doan.stockmanagement.common.ResponseApi;
import com.doan.stockmanagement.dtos.ProductDTO;
import com.doan.stockmanagement.entities.Product;

public interface ProductService {

    ResponseApi<List<ProductDTO>> getProduct(Product product);

    ResponseApi<ProductDTO> saveProduct(Product product);

    ResponseApi<Object> deleteProduct(Integer id);
    
    ResponseApi<String> getCodeProduct();
}
