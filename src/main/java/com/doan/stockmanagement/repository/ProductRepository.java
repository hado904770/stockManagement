package com.doan.stockmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doan.stockmanagement.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
