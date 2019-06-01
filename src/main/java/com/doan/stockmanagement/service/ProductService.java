package com.doan.stockmanagement.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.doan.stockmanagement.common.Result;
import com.doan.stockmanagement.dtos.ProductDTO;
import com.doan.stockmanagement.entities.Product;

public interface ProductService {

    ResponseEntity<Result<List<ProductDTO>>> getProduct(Product product);

    ResponseEntity<Result<Object>> saveProduct(Product product);

    ResponseEntity<Result<Object>> deleteProduct(Integer id);

    ResponseEntity<Result<String>> getCodeProduct();
}
