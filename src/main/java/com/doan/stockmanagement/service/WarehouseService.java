package com.doan.stockmanagement.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.doan.stockmanagement.common.Result;
import com.doan.stockmanagement.entities.Warehouse;

public interface WarehouseService {

    ResponseEntity<Result<List<Warehouse>>> getWarehouse(Warehouse warehouse);

    ResponseEntity<Result<Object>> saveWarehouse(Warehouse warehouse);

    ResponseEntity<Result<Object>> deleteWarehouse(Integer id);

}
