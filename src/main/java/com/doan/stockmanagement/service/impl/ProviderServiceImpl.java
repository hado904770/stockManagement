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
import com.doan.stockmanagement.entities.Provider;
import com.doan.stockmanagement.repository.ProviderRepository;
import com.doan.stockmanagement.service.ProviderService;
import com.doan.stockmanagement.specs.ProviderSpecification;

@Service
public class ProviderServiceImpl implements ProviderService {

    private static Logger LOGGER = LoggerFactory.getLogger(ProviderServiceImpl.class);

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public ResponseEntity<Result<List<Provider>>> getProvider(Provider provider) {

        ResponseEntity<Result<List<Provider>>> responseEntity = new ResponseEntity<>(HttpStatus.PROCESSING);

        try {
            List<Provider> providers = providerRepository
                    .findAll(Specification.where(ProviderSpecification.hasId(provider.getId()))
                            .and(ProviderSpecification.hasName(provider.getName()))
                            .and(ProviderSpecification.hasAddress(provider.getAddress())));

            responseEntity = CommonUtils.buildResponse(HttpStatus.OK, HttpStatus.OK.name(), providers);
        } catch (Exception e) {
            LOGGER.error("ERROR getProvider: ", e);
            responseEntity = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), new ArrayList<>());
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<Result<Object>> saveProvider(Provider provider) {

        ResponseEntity<Result<Object>> responseEntity = new ResponseEntity<>(HttpStatus.PROCESSING);

        try {
            providerRepository.save(provider);
            responseEntity = CommonUtils.buildResponse(HttpStatus.OK, HttpStatus.OK.name(), null);
        } catch (Exception e) {
            LOGGER.error("ERROR saveProvider: ", e);
            responseEntity = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<Result<Object>> deleteProvider(Integer id) {

        ResponseEntity<Result<Object>> responseEntity = new ResponseEntity<>(HttpStatus.PROCESSING);

        try {
            providerRepository.deleteById(id);
            responseEntity = CommonUtils.buildResponse(HttpStatus.OK, HttpStatus.OK.name(), null);
        } catch (Exception e) {
            LOGGER.error("ERROR deleteProvider: ", e);
            responseEntity = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }

        return responseEntity;
    }

}
