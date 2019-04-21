package com.doan.stockmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doan.stockmanagement.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
