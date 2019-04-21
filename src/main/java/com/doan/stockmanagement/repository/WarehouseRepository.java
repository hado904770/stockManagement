package com.doan.stockmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doan.stockmanagement.entities.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

}
