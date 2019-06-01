package com.doan.stockmanagement.service.impl;

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
    public ResponseEntity<Result<List<Customer>>> getCustomer(Customer customer) {

        ResponseEntity<Result<List<Customer>>> responseEntity = new ResponseEntity<>(HttpStatus.PROCESSING);

        try {
            List<Customer> customers = customerRepository
                    .findAll(Specification.where(CustomerSpecification.hasId(customer.getId()))
                            .and(CustomerSpecification.hasName(customer.getName())));

            responseEntity = CommonUtils.buildResponse(HttpStatus.OK, HttpStatus.OK.name(), customers);
        } catch (Exception e) {
            LOGGER.error("ERROR getCustomer: ", e);
            responseEntity = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), new ArrayList<>());
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<Result<Object>> saveCustomer(Customer customer) {

        ResponseEntity<Result<Object>> responseEntity = new ResponseEntity<>(HttpStatus.PROCESSING);

        try {
            customerRepository.save(customer);
            responseEntity = CommonUtils.buildResponse(HttpStatus.OK, HttpStatus.OK.name(), null);
        } catch (Exception e) {
            LOGGER.error("ERROR saveCustomer: ", e);
            responseEntity = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<Result<Object>> deleteCustomer(Integer id) {

        ResponseEntity<Result<Object>> responseEntity = new ResponseEntity<>(HttpStatus.PROCESSING);

        try {
            customerRepository.deleteById(id);
            responseEntity = CommonUtils.buildResponse(HttpStatus.OK, HttpStatus.OK.name(), null);
        } catch (Exception e) {
            LOGGER.error("ERROR deleteCustomer: ", e);
            responseEntity = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }

        return responseEntity;
    }

}
