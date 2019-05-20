package com.doan.stockmanagement.specs;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.doan.stockmanagement.entities.Warehouse;

public class WarehouseSpecification {
    
    public static Specification<Warehouse> hasId(Integer id) {
        if (id == null) {
            return null;
        }

        return (warehouse, q, cb) -> cb.equal(warehouse.get("id"), id);
    }

    public static Specification<Warehouse> hasName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }

        return (warehouse, q, cb) -> cb.or(cb.like(warehouse.get("name"), "%" + name.trim() + "%"),
                cb.like(warehouse.get("name"), "%" + name.toLowerCase().trim() + "%"),
                cb.like(warehouse.get("name"), "%" + name.toUpperCase().trim() + "%"));
    }
    
}
