package com.doan.stockmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doan.stockmanagement.entities.Provider;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {

}
