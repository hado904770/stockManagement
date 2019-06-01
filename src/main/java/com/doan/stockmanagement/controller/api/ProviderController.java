package com.doan.stockmanagement.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doan.stockmanagement.common.CommonConstants;
import com.doan.stockmanagement.common.Result;
import com.doan.stockmanagement.entities.Provider;
import com.doan.stockmanagement.service.ProviderService;

@RequestMapping(value = CommonConstants.DOMAIN_API + CommonConstants.DOMAIN_PROVIDER)
@RestController
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @PostMapping(value = CommonConstants.REQUEST_FILTER)
    public ResponseEntity<Result<List<Provider>>> getProvider(@RequestBody Provider provider) {
        return providerService.getProvider(provider);
    }

    @PostMapping(value = CommonConstants.REQUEST_SAVE)
    public ResponseEntity<Result<Object>> saveProvider(@RequestBody Provider provider) {
        return providerService.saveProvider(provider);
    }

    @PostMapping(value = CommonConstants.REQUEST_DELETE)
    public ResponseEntity<Result<Object>> deleteProvider(@RequestBody Provider provider) {
        return providerService.deleteProvider(provider.getId());
    }

}
