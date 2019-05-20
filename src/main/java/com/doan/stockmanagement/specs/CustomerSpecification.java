package com.doan.stockmanagement.specs;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.doan.stockmanagement.entities.Customer;

public class CustomerSpecification {

    public static Specification<Customer> hasId(Integer id) {
        if (id == null) {
            return null;
        }

        return (customer, q, cb) -> cb.equal(customer.get("id"), id);
    }

    public static Specification<Customer> hasName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }

        return (customer, q, cb) -> cb.or(cb.like(customer.get("name"), "%" + name.trim() + "%"),
                cb.like(customer.get("name"), "%" + name.toLowerCase().trim() + "%"),
                cb.like(customer.get("name"), "%" + name.toUpperCase().trim() + "%"));
    }

    public static Specification<Customer> hasAddress(String address) {
        if (StringUtils.isEmpty(address)) {
            return null;
        }

        return (customer, q, cb) -> cb.or(cb.like(customer.get("address"), "%" + address.trim() + "%"),
                cb.like(customer.get("address"), "%" + address.toLowerCase().trim() + "%"),
                cb.like(customer.get("address"), "%" + address.toUpperCase().trim() + "%"));
    }
}
