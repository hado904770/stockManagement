package com.doan.stockmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.doan.stockmanagement.common.CommonUtils;
import com.doan.stockmanagement.common.ResponseApi;
import com.doan.stockmanagement.entities.Customer;
import com.doan.stockmanagement.repository.CustomerRepository;
import com.doan.stockmanagement.service.CustomerService;
import com.doan.stockmanagement.specs.CustomerSpecification;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ResponseApi<List<Customer>> getCustomer(Customer customer) {
        
        ResponseApi<List<Customer>> responseApi = new ResponseApi<>();

        try {
            List<Customer> customers = customerRepository.findAll(
                    Specification.where(CustomerSpecification.hasId(customer.getId()))
                    .and(CustomerSpecification.hasName(customer.getName())));
            
            responseApi = CommonUtils.buildResponse(HttpStatus.OK.value(),
                    HttpStatus.OK.name(),
                    customers);
        } catch (Exception e) {
            LOGGER.error("ERROR getCustomer: ", e);
            responseApi = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST.value(),
                    e.getMessage(),
                    new ArrayList<>());
        }

        return responseApi;
    }

    @Override
    public ResponseApi<Customer> saveCustomer(Customer customer) {
        
        ResponseApi<Customer> responseApi = new ResponseApi<>();

        try {
            responseApi = CommonUtils.buildResponse(HttpStatus.OK.value(),
                    HttpStatus.OK.name(),
                    customerRepository.save(customer));
        } catch (Exception e) {
            LOGGER.error("ERROR saveCustomer: ", e);
            responseApi = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST.value(),
                    e.getMessage(),
                    new Customer());
        }

        return responseApi;
    }

    @Override
    public ResponseApi<Object> deleteCustomer(Integer id) {
        
        ResponseApi<Object> responseApi = new ResponseApi<>();

        try {
            customerRepository.deleteById(id);
            responseApi = CommonUtils.buildResponse(HttpStatus.OK.value(),
                    HttpStatus.OK.name(),
                    null);
        } catch (Exception e) {
            LOGGER.error("ERROR deleteCustomer: ", e);
            responseApi = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST.value(),
                    e.getMessage(),
                    null);
        }

        return responseApi;
    }

}
