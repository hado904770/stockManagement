package com.doan.stockmanagement.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.doan.stockmanagement.common.Result;
import com.doan.stockmanagement.entities.Provider;

public interface ProviderService {

    ResponseEntity<Result<List<Provider>>> getProvider(Provider provider);

    ResponseEntity<Result<Object>> saveProvider(Provider provider);

    ResponseEntity<Result<Object>> deleteProvider(Integer id);

}
