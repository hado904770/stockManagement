package com.doan.stockmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.doan.stockmanagement.common.CommonUtils;
import com.doan.stockmanagement.common.ResponseApi;
import com.doan.stockmanagement.entity.Product;
import com.doan.stockmanagement.entity.ProductStatus;
import com.doan.stockmanagement.entity.dto.ProductDTO;
import com.doan.stockmanagement.enums.Status;
import com.doan.stockmanagement.mapper.ProductMapper;
import com.doan.stockmanagement.repository.ProductRepository;
import com.doan.stockmanagement.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ProductMapper productMapper;

    private static Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public ResponseApi<List<ProductDTO>> findAllProduct() {

        ResponseApi<List<ProductDTO>> responseApi = new ResponseApi<>();

        try {
            List<ProductDTO> productDTOs = productMapper.toFindAllProductDTO(productRepository.findAll());
            responseApi = CommonUtils.buildResponseApi(HttpStatus.OK.value(), HttpStatus.OK.name(),
                    productDTOs);
        } catch (Exception e) {
            LOGGER.error("ERROR GET LIST PRODUCT: ", e);
            responseApi = CommonUtils.buildResponseApi(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(),
                    new ArrayList<>());
        }

        return responseApi;
    }

    @Override
    public ResponseApi<ProductDTO> insertProduct(Product product) {

        ResponseApi<ProductDTO> responseApi = new ResponseApi<>();

//        try {
//            ProductStatus productStatus = new ProductStatus();
//            productStatus.setStatus(Status.INPUT_WAREHOUSE);
//            productStatus.setProduct(product);
//            
//            product.setProductStatus(productStatus);
//            ProductDTO productDTO = productMapper.productToProductDTO(productRepository.save(product));
//            
//            responseApi = CommonUtils.buildResponseApi(HttpStatus.OK.value(), HttpStatus.OK.name(),
//                    productDTO);
//        } catch (DataIntegrityViolationException e) {
//            LOGGER.error("ERROR INSET OR UPDATE Integrity Violation Product: ", e);
//            responseApi = CommonUtils.buildResponseApi(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.name(),
//                    new ProductDTO());
//        } catch (Exception e) {
//            LOGGER.error("ERROR INSET OR UPDATE Product: ", e);
//            responseApi = CommonUtils.buildResponseApi(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(),
//                    new ProductDTO());
//        }

        return responseApi;
    }
    
    @Override
    public ResponseApi<ProductDTO> updateProduct(Product product) {

        ResponseApi<ProductDTO> responseApi = new ResponseApi<>();

        try {
            ProductDTO productDTO = productMapper.productToProductDTO(productRepository.save(product));
            
            responseApi = CommonUtils.buildResponseApi(HttpStatus.OK.value(), HttpStatus.OK.name(),
                    productDTO);
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("ERROR INSET OR UPDATE Integrity Violation Product: ", e);
            responseApi = CommonUtils.buildResponseApi(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.name(),
                    new ProductDTO());
        } catch (Exception e) {
            LOGGER.error("ERROR INSET OR UPDATE Product: ", e);
            responseApi = CommonUtils.buildResponseApi(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(),
                    new ProductDTO());
        }

        return responseApi;
    }

    @Override
    public ResponseApi<Object> deleteProduct(Integer id) {

        ResponseApi<Object> responseApi = new ResponseApi<>();

        try {
            productRepository.deleteById(id);
            responseApi = CommonUtils.buildResponseApi(HttpStatus.OK.value(), HttpStatus.OK.name(), null);
        } catch (DataRetrievalFailureException e) {
            LOGGER.error("ERROR DELETE Retrieval Failure Product: ", e);
            responseApi = CommonUtils.buildResponseApi(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), null);
        } catch (Exception e) {
            LOGGER.error("ERROR DELETE Product: ", e);
            responseApi = CommonUtils.buildResponseApi(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), null);
        }

        return responseApi;
    }

}
