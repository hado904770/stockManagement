package com.doan.stockmanagement.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doan.stockmanagement.common.CommonConstants;
import com.doan.stockmanagement.common.ResponseApi;
import com.doan.stockmanagement.dtos.ProductDTO;
import com.doan.stockmanagement.entities.Product;
import com.doan.stockmanagement.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    private static final String REQUEST_GET_CODE = "/get-code";
    
    @Autowired
    private ProductService productService;

    @PostMapping(value = CommonConstants.REQUEST_FILTER)
    public ResponseApi<List<ProductDTO>> getProduct(@RequestBody Product product) {
        return productService.getProduct(product);
    }

    @PostMapping(value = CommonConstants.REQUEST_SAVE)
    public ResponseApi<ProductDTO> saveCustomer(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PostMapping(value = CommonConstants.REQUEST_DELETE)
    public ResponseApi<Object> deleteCustomer(@RequestBody Product product) {
        return productService.deleteProduct(product.getId());
    }
    
    @PostMapping(value = REQUEST_GET_CODE)
    public ResponseApi<String> getCodeProduct() {
        return productService.getCodeProduct();
    }

}
