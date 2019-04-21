package com.doan.stockmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doan.stockmanagement.common.CommonConstants;
import com.doan.stockmanagement.common.ResponseApi;
import com.doan.stockmanagement.entities.Warehouse;
import com.doan.stockmanagement.service.WarehouseService;

@RequestMapping(value = CommonConstants.DOMAIN_WAREHOUSE)
@RestController
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping(value = CommonConstants.REQUEST_GET_ALL)
    public ResponseApi<List<Warehouse>> getWarehouse() {
        return warehouseService.getWarehouse();
    }

    @PostMapping(value = CommonConstants.REQUEST_SAVE)
    public ResponseApi<Warehouse> saveWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.saveWarehouse(warehouse);
    }

    @PostMapping(value = CommonConstants.REQUEST_DELETE)
    public ResponseApi<Object> deleteWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.deleteWarehouse(warehouse.getId());
    }

}
