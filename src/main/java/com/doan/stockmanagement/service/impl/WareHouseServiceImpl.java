package com.doan.stockmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.doan.stockmanagement.common.CommonUtils;
import com.doan.stockmanagement.common.Result;
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
    public ResponseEntity<Result<List<Warehouse>>> getWarehouse(Warehouse warehouse) {

        ResponseEntity<Result<List<Warehouse>>> responseEntity = new ResponseEntity<>(HttpStatus.PROCESSING);

        try {
            List<Warehouse> warehouses = warehouseRepository
                    .findAll(Specification.where(WarehouseSpecification.hasId(warehouse.getId()))
                            .and(WarehouseSpecification.hasName(warehouse.getName())));

            responseEntity = CommonUtils.buildResponse(HttpStatus.OK, HttpStatus.OK.name(), warehouses);
        } catch (Exception e) {
            LOGGER.error("ERROR getWarehouse: ", e);
            responseEntity = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), new ArrayList<>());
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<Result<Object>> saveWarehouse(Warehouse warehouse) {

        ResponseEntity<Result<Object>> responseEntity = new ResponseEntity<>(HttpStatus.PROCESSING);

        try {
            warehouseRepository.save(warehouse);
            responseEntity = CommonUtils.buildResponse(HttpStatus.OK, HttpStatus.OK.name(), null);
        } catch (Exception e) {
            LOGGER.error("ERROR saveWarehouse: ", e);
            responseEntity = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<Result<Object>> deleteWarehouse(Integer id) {

        ResponseEntity<Result<Object>> responseEntity = new ResponseEntity<>(HttpStatus.PROCESSING);

        try {
            warehouseRepository.deleteById(id);
            responseEntity = CommonUtils.buildResponse(HttpStatus.OK, HttpStatus.OK.name(), null);
        } catch (Exception e) {
            LOGGER.error("ERROR deleteWarehouse: ", e);
            responseEntity = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }

        return responseEntity;
    }

}
