package com.doan.stockmanagement.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.doan.stockmanagement.common.CommonUtils;
import com.doan.stockmanagement.common.ResponseApi;
import com.doan.stockmanagement.dtos.ProductDTO;
import com.doan.stockmanagement.entities.Product;
import com.doan.stockmanagement.mapper.ProductMapper;
import com.doan.stockmanagement.repository.ProductRepository;
import com.doan.stockmanagement.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private static Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ProductMapper productMapper;

    @Override
    public ResponseApi<List<ProductDTO>> getProduct() {
        
        ResponseApi<List<ProductDTO>> responseApi = new ResponseApi<>();

        try {
            List<Product> products = productRepository.findAll();
            List<ProductDTO> productDTOs = productMapper.toProductDTOs(products);
            
            responseApi = CommonUtils.buildResponse(HttpStatus.OK.value(),
                    HttpStatus.OK.name(),
                    productDTOs);
        } catch (Exception e) {
            LOGGER.error("ERROR getProduct: ", e);
            responseApi = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST.value(),
                    e.getMessage(),
                    new ArrayList<>());
        }

        return responseApi;
    }

    @Override
    public ResponseApi<ProductDTO> getProductById(Integer id) {
        
        ResponseApi<ProductDTO> responseApi = new ResponseApi<>();

        try {
            Product product = productRepository.findById(id).get();
            ProductDTO productDTO = productMapper.toProductDTO(product);
            
            responseApi = CommonUtils.buildResponse(HttpStatus.OK.value(),
                    HttpStatus.OK.name(),
                    productDTO);
        } catch (Exception e) {
            LOGGER.error("ERROR getProductById: ", e);
            responseApi = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST.value(),
                    e.getMessage(),
                    new ProductDTO());
        }

        return responseApi;
    }

    @Override
    public ResponseApi<ProductDTO> saveProduct(Product product) {
        
        ResponseApi<ProductDTO> responseApi = new ResponseApi<>();

        try {
            Product p = productRepository.save(product);
            ProductDTO productDTO = productMapper.toProductDTO(p);
            
            responseApi = CommonUtils.buildResponse(HttpStatus.OK.value(),
                    HttpStatus.OK.name(),
                    productDTO);
        } catch (Exception e) {
            LOGGER.error("ERROR saveProduct: ", e);
            responseApi = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST.value(),
                    e.getMessage(),
                    new ProductDTO());
        }

        return responseApi;
    }

    @Override
    public ResponseApi<Object> deleteProduct(Integer id) {
        ResponseApi<Object> responseApi = new ResponseApi<>();

        try {
            productRepository.deleteById(id);
            responseApi = CommonUtils.buildResponse(HttpStatus.OK.value(),
                    HttpStatus.OK.name(),
                    null);
        } catch (Exception e) {
            LOGGER.error("ERROR deleteProduct: ", e);
            responseApi = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST.value(),
                    e.getMessage(),
                    null);
        }

        return responseApi;
    }

    @Override
    public ResponseApi<String> getCodeProduct() {
        ResponseApi<String> responseApi = new ResponseApi<>();
        
        try {
            LocalDateTime ldt = LocalDateTime.now();
            
            StringBuilder code = new StringBuilder();
            code.append("P");
            code.append(ldt.getYear());
            if (ldt.getMonthValue() < 10) {
                code.append(BigDecimal.ZERO.toString() + ldt.getMonthValue());
            } else {
                code.append(ldt.getMonthValue());
            }
            
            if (ldt.getDayOfMonth() < 10) {
                code.append(BigDecimal.ZERO.toString() + ldt.getDayOfMonth());
            } else {
                code.append(ldt.getDayOfMonth());
            }
            
            if (ldt.getHour() < 10) {
                code.append(BigDecimal.ZERO.toString() + ldt.getHour());
            } else {
                code.append(ldt.getHour());
            }
            
            if (ldt.getMinute() < 10) {
                code.append(BigDecimal.ZERO.toString() + ldt.getMinute());
            } else {
                code.append(ldt.getMinute());
            }
            
            if (ldt.getSecond() < 10) {
                code.append(BigDecimal.ZERO.toString() + ldt.getSecond());
            } else {
                code.append(ldt.getSecond());
            }
            
            responseApi = CommonUtils.buildResponse(HttpStatus.OK.value(),
                    HttpStatus.OK.name(),
                    code.toString());
        } catch (Exception e) {
            LOGGER.error("ERROR getCodeProduct: ", e);
            responseApi = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST.value(),
                    e.getMessage(),
                    null);
        }
        
        return responseApi;
    }

}
