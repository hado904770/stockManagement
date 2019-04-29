package com.doan.stockmanagement.service;

import java.util.List;

import com.doan.stockmanagement.common.ResponseApi;
import com.doan.stockmanagement.entities.Customer;

public interface CustomerService {

    ResponseApi<List<Customer>> getCustomer();

    ResponseApi<Customer> getCustomerById(Integer id);

    ResponseApi<Customer> saveCustomer(Customer customer);

    ResponseApi<Object> deleteCustomer(Integer id);

}
