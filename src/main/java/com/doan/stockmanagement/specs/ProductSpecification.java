package com.doan.stockmanagement.specs;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.doan.stockmanagement.entities.Product;

public class ProductSpecification {

    public static Specification<Product> hasId(Integer id) {
        if (id == null) {
            return null;
        }

        return (product, q, cb) -> cb.equal(product.get("id"), id);
    }

    public static Specification<Product> hasName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }

        return (product, q, cb) -> cb.or(cb.like(product.get("name"), "%" + name.trim() + "%"),
                cb.like(product.get("name"), "%" + name.toLowerCase().trim() + "%"),
                cb.like(product.get("name"), "%" + name.toUpperCase().trim() + "%"));
    }

    public static Specification<Product> hasCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }

        return (product, q, cb) -> cb.or(cb.like(product.get("code"), "%" + code.trim() + "%"),
                cb.like(product.get("code"), "%" + code.toLowerCase().trim() + "%"),
                cb.like(product.get("code"), "%" + code.toUpperCase().trim() + "%"));
    }
}
