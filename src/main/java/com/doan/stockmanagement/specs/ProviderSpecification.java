package com.doan.stockmanagement.specs;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.doan.stockmanagement.entities.Provider;

public class ProviderSpecification {
    
    public static Specification<Provider> hasId(Integer id) {
        if (id == null) {
            return null;
        }

        return (provider, q, cb) -> cb.equal(provider.get("id"), id);
    }

    public static Specification<Provider> hasName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }

        return (provider, q, cb) -> cb.or(cb.like(provider.get("name"), "%" + name.trim() + "%"),
                cb.like(provider.get("name"), "%" + name.toLowerCase().trim() + "%"),
                cb.like(provider.get("name"), "%" + name.toUpperCase().trim() + "%"));
    }
    
    public static Specification<Provider> hasAddress(String address) {
        if (StringUtils.isEmpty(address)) {
            return null;
        }

        return (provider, q, cb) -> cb.or(cb.like(provider.get("address"), "%" + address.trim() + "%"),
                cb.like(provider.get("address"), "%" + address.toLowerCase().trim() + "%"),
                cb.like(provider.get("address"), "%" + address.toUpperCase().trim() + "%"));
    }
    
}
