package com.doan.stockmanagement.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doan.stockmanagement.common.CommonConstants;
import com.doan.stockmanagement.common.Result;
import com.doan.stockmanagement.entities.Customer;
import com.doan.stockmanagement.service.CustomerService;

@RequestMapping(value = CommonConstants.DOMAIN_API + CommonConstants.DOMAIN_CUSTOMER)
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = CommonConstants.REQUEST_FILTER)
    public ResponseEntity<Result<List<Customer>>> getCustomer(@RequestBody Customer customer) {
        return customerService.getCustomer(customer);
    }

    @PostMapping(value = CommonConstants.REQUEST_SAVE)
    public ResponseEntity<Result<Object>> saveCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @PostMapping(value = CommonConstants.REQUEST_DELETE)
    public ResponseEntity<Result<Object>> deleteCustomer(@RequestBody Customer customer) {
        return customerService.deleteCustomer(customer.getId());
    }

}
