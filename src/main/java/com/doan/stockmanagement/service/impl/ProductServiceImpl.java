package com.doan.stockmanagement.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.doan.stockmanagement.common.CommonUtils;
import com.doan.stockmanagement.common.Result;
import com.doan.stockmanagement.dtos.ProductDTO;
import com.doan.stockmanagement.entities.Product;
import com.doan.stockmanagement.mapper.ProductMapper;
import com.doan.stockmanagement.repository.ProductRepository;
import com.doan.stockmanagement.service.ProductService;
import com.doan.stockmanagement.specs.ProductSpecification;

@Service
public class ProductServiceImpl implements ProductService {

    private static Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ResponseEntity<Result<List<ProductDTO>>> getProduct(Product product) {

        ResponseEntity<Result<List<ProductDTO>>> responseEntity = new ResponseEntity<>(HttpStatus.PROCESSING);

        try {
            List<Product> products = productRepository
                    .findAll(Specification.where(ProductSpecification.hasId(product.getId()))
                            .and(ProductSpecification.hasCode(product.getCode()))
                            .and(ProductSpecification.hasName(product.getName())));
            List<ProductDTO> productDTOs = productMapper.toProductDTOs(products);

            responseEntity = CommonUtils.buildResponse(HttpStatus.OK, HttpStatus.OK.name(), productDTOs);
        } catch (Exception e) {
            LOGGER.error("ERROR getProduct: ", e);
            responseEntity = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), new ArrayList<>());
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<Result<Object>> saveProduct(Product product) {

        ResponseEntity<Result<Object>> responseEntity = new ResponseEntity<>(HttpStatus.PROCESSING);

        try {
            productRepository.save(product);
            responseEntity = CommonUtils.buildResponse(HttpStatus.OK, HttpStatus.OK.name(), null);
        } catch (Exception e) {
            LOGGER.error("ERROR saveProduct: ", e);
            responseEntity = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<Result<Object>> deleteProduct(Integer id) {
        ResponseEntity<Result<Object>> responseEntity = new ResponseEntity<>(HttpStatus.PROCESSING);

        try {
            productRepository.deleteById(id);
            responseEntity = CommonUtils.buildResponse(HttpStatus.OK, HttpStatus.OK.name(), null);
        } catch (Exception e) {
            LOGGER.error("ERROR deleteProduct: ", e);
            responseEntity = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<Result<String>> getCodeProduct() {
        ResponseEntity<Result<String>> responseEntity = new ResponseEntity<>(HttpStatus.PROCESSING);

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

            responseEntity = CommonUtils.buildResponse(HttpStatus.OK, HttpStatus.OK.name(), code.toString());
        } catch (Exception e) {
            LOGGER.error("ERROR getCodeProduct: ", e);
            responseEntity = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }

        return responseEntity;
    }

}
