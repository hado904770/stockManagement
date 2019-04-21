package com.doan.stockmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.doan.stockmanagement.common.CommonUtils;
import com.doan.stockmanagement.common.ResponseApi;
import com.doan.stockmanagement.entities.Warehouse;
import com.doan.stockmanagement.repository.WarehouseRepository;
import com.doan.stockmanagement.service.WarehouseService;

@Service
public class WareHouseServiceImpl implements WarehouseService {
    
    @Autowired
    private WarehouseRepository warehouseRepository;
    
    @Override
    public ResponseApi<List<Warehouse>> getWarehouse() {

        ResponseApi<List<Warehouse>> responseApi = new ResponseApi<>();

        try {
            responseApi = CommonUtils.buildResponse(HttpStatus.OK.value(),
                    HttpStatus.OK.name(),
                    warehouseRepository.findAll());
        } catch (Exception e) {
            responseApi = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST.value(),
                    e.getMessage(),
                    new ArrayList<>());
        }

        return responseApi;
    }

    @Override
    public ResponseApi<Warehouse> saveWarehouse(Warehouse warehouse) {
        
        ResponseApi<Warehouse> responseApi = new ResponseApi<>();

        try {
            responseApi = CommonUtils.buildResponse(HttpStatus.OK.value(),
                    HttpStatus.OK.name(),
                    warehouseRepository.save(warehouse));
        } catch (Exception e) {
            responseApi = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST.value(),
                    e.getMessage(),
                    new Warehouse());
        }

        return responseApi;
    }

    @Override
    public ResponseApi<Object> deleteWarehouse(Integer id) {
        
        ResponseApi<Object> responseApi = new ResponseApi<>();

        try {
            warehouseRepository.deleteById(id);
            responseApi = CommonUtils.buildResponse(HttpStatus.OK.value(),
                    HttpStatus.OK.name(),
                    null);
        } catch (Exception e) {
            responseApi = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST.value(),
                    e.getMessage(),
                    null);
        }

        return responseApi;
    }

}
