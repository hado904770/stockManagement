package com.doan.stockmanagement.service;

import java.util.List;

import com.doan.stockmanagement.common.ResponseApi;
import com.doan.stockmanagement.entity.Product;
import com.doan.stockmanagement.entity.dto.ProductDTO;

public interface ProductService {

    ResponseApi<List<Product>> findAllProduct();

    ResponseApi<Product> insertProduct(Product product);

    ResponseApi<Product> updateProduct(Product product);

    ResponseApi<Object> deleteProduct(Integer id);

}
