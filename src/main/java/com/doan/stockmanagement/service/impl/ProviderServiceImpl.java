package com.doan.stockmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.doan.stockmanagement.common.CommonUtils;
import com.doan.stockmanagement.common.ResponseApi;
import com.doan.stockmanagement.entities.Provider;
import com.doan.stockmanagement.repository.ProviderRepository;
import com.doan.stockmanagement.service.ProviderService;

@Service
public class ProviderServiceImpl implements ProviderService {

    private static Logger LOGGER = LoggerFactory.getLogger(ProviderServiceImpl.class);
    
    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public ResponseApi<List<Provider>> getProvider() {

        ResponseApi<List<Provider>> responseApi = new ResponseApi<>();

        try {
            responseApi = CommonUtils.buildResponse(HttpStatus.OK.value(),
                    HttpStatus.OK.name(),
                    providerRepository.findAll());
        } catch (Exception e) {
            LOGGER.error("ERROR getProvider: ", e);
            responseApi = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST.value(),
                    e.getMessage(),
                    new ArrayList<>());
        }

        return responseApi;
    }

    @Override
    public ResponseApi<Provider> getProviderById(Integer id) {
        
        ResponseApi<Provider> responseApi = new ResponseApi<>();

        try {
            responseApi = CommonUtils.buildResponse(HttpStatus.OK.value(),
                    HttpStatus.OK.name(),
                    providerRepository.findById(id).get());
        } catch (Exception e) {
            LOGGER.error("ERROR getProviderById: ", e);
            responseApi = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST.value(),
                    e.getMessage(),
                    new Provider());
        }

        return responseApi;
    }

    @Override
    public ResponseApi<Provider> saveProvider(Provider provider) {

        ResponseApi<Provider> responseApi = new ResponseApi<>();

        try {
            responseApi = CommonUtils.buildResponse(HttpStatus.OK.value(),
                    HttpStatus.OK.name(),
                    providerRepository.save(provider));
        } catch (Exception e) {
            LOGGER.error("ERROR saveProvider: ", e);
            responseApi = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST.value(),
                    e.getMessage(),
                    new Provider());
        }

        return responseApi;
    }

    @Override
    public ResponseApi<Object> deleteProvider(Integer id) {
        
        ResponseApi<Object> responseApi = new ResponseApi<>();

        try {
            providerRepository.deleteById(id);
            responseApi = CommonUtils.buildResponse(HttpStatus.OK.value(),
                    HttpStatus.OK.name(),
                    null);
        } catch (Exception e) {
            LOGGER.error("ERROR deleteProvider: ", e);
            responseApi = CommonUtils.buildResponse(HttpStatus.BAD_REQUEST.value(),
                    e.getMessage(),
                    null);
        }

        return responseApi;
    }

}
