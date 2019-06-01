package com.doan.stockmanagement.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.doan.stockmanagement.common.Result;
import com.doan.stockmanagement.entities.Customer;

public interface CustomerService {

    ResponseEntity<Result<List<Customer>>> getCustomer(Customer customer);

    ResponseEntity<Result<Object>> saveCustomer(Customer customer);

    ResponseEntity<Result<Object>> deleteCustomer(Integer id);

}
