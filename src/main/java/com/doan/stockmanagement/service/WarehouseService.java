package com.doan.stockmanagement.service;

import java.util.List;

import com.doan.stockmanagement.common.ResponseApi;
import com.doan.stockmanagement.entities.Warehouse;

public interface WarehouseService {

    ResponseApi<List<Warehouse>> getWarehouse(Warehouse warehouse);

    ResponseApi<Warehouse> saveWarehouse(Warehouse warehouse);

    ResponseApi<Object> deleteWarehouse(Integer id);

}
