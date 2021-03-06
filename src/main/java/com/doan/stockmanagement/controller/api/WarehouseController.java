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
import com.doan.stockmanagement.entities.Warehouse;
import com.doan.stockmanagement.service.WarehouseService;

@RequestMapping(value = CommonConstants.DOMAIN_API + CommonConstants.DOMAIN_WAREHOUSE)
@RestController
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping(value = CommonConstants.REQUEST_FILTER)
    public ResponseEntity<Result<List<Warehouse>>> getWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.getWarehouse(warehouse);
    }

    @PostMapping(value = CommonConstants.REQUEST_SAVE)
    public ResponseEntity<Result<Object>> saveWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.saveWarehouse(warehouse);
    }

    @PostMapping(value = CommonConstants.REQUEST_DELETE)
    public ResponseEntity<Result<Object>> deleteWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.deleteWarehouse(warehouse.getId());
    }

}
