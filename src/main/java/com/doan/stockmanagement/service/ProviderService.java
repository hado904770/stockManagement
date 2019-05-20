package com.doan.stockmanagement.service;

import java.util.List;

import com.doan.stockmanagement.common.ResponseApi;
import com.doan.stockmanagement.entities.Provider;

public interface ProviderService {

    ResponseApi<List<Provider>> getProvider(Provider provider);

    ResponseApi<Provider> saveProvider(Provider provider);

    ResponseApi<Object> deleteProvider(Integer id);

}
