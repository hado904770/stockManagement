package com.doan.stockmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doan.stockmanagement.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
