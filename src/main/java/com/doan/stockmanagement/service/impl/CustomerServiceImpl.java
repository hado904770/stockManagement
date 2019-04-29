package com.doan.stockmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.doan.stockmanagement.common.CommonUtils;
import com.doan.stockmanagement.common.ResponseApi;
import com.doan.stockmanagement.entities.Customer;
import com.doan.stockmanagement.repository.CustomerRepository;
import com.doan.stockmanagement.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ResponseApi<List<Customer>> getCustomer() {
        
        ResponseApi<List<Customer>> responseApi = new ResponseApi<>();

        try {
            responseApi = CommonUtils.buildResponse(HttpStatus.OK.value(),
                    HttpStatus.OK.name(),
                    customerRepository.findAll());
        } catch (Exception e) {
            responseApi = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST.value(),
                    e.getMessage(),
                    new ArrayList<>());
        }

        return responseApi;
    }

    @Override
    public ResponseApi<Customer> getCustomerById(Integer id) {
        
        ResponseApi<Customer> responseApi = new ResponseApi<>();

        try {
            responseApi = CommonUtils.buildResponse(HttpStatus.OK.value(),
                    HttpStatus.OK.name(),
                    customerRepository.findById(id).get());
        } catch (Exception e) {
            responseApi = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST.value(),
                    e.getMessage(),
                    new Customer());
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
            responseApi = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST.value(),
                    e.getMessage(),
                    null);
        }

        return responseApi;
    }

}
