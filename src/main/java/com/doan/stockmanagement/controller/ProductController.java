package com.doan.stockmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doan.stockmanagement.common.ResponseApi;
import com.doan.stockmanagement.entity.Product;
import com.doan.stockmanagement.entity.dto.ProductDTO;
import com.doan.stockmanagement.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/filter")
    public ResponseApi<List<ProductDTO>> getProductAll() {
        return productService.findAllProduct();
    }

    @PostMapping("/insert")
    public ResponseApi<ProductDTO> insertProduct(@RequestBody Product product) {
        return productService.insertProduct(product);
    }

    @PostMapping("/update")
    public ResponseApi<ProductDTO> updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @PostMapping("/delete")
    public ResponseApi<Object> deleteProduct(@RequestBody Product product) {
        return productService.deleteProduct(product.getId());
    }

}
