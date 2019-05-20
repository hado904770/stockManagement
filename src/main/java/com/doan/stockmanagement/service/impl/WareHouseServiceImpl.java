package com.doan.stockmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.doan.stockmanagement.common.CommonUtils;
import com.doan.stockmanagement.common.ResponseApi;
import com.doan.stockmanagement.entities.Warehouse;
import com.doan.stockmanagement.repository.WarehouseRepository;
import com.doan.stockmanagement.service.WarehouseService;
import com.doan.stockmanagement.specs.WarehouseSpecification;

@Service
public class WareHouseServiceImpl implements WarehouseService {
    
    private static Logger LOGGER = LoggerFactory.getLogger(WareHouseServiceImpl.class);
    
    @Autowired
    private WarehouseRepository warehouseRepository;
    
    @Override
    public ResponseApi<List<Warehouse>> getWarehouse(Warehouse warehouse) {

        ResponseApi<List<Warehouse>> responseApi = new ResponseApi<>();

        try {
            List<Warehouse> warehouses = warehouseRepository.findAll(
                    Specification.where(WarehouseSpecification.hasId(warehouse.getId()))
                    .and(WarehouseSpecification.hasName(warehouse.getName())));
            
            responseApi = CommonUtils.buildResponse(HttpStatus.OK.value(),
                    HttpStatus.OK.name(),
                    warehouses);
        } catch (Exception e) {
            LOGGER.error("ERROR getWarehouse: ", e);
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
            LOGGER.error("ERROR saveWarehouse: ", e);
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
            LOGGER.error("ERROR deleteWarehouse: ", e);
            responseApi = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST.value(),
                    e.getMessage(),
                    null);
        }

        return responseApi;
    }

}
