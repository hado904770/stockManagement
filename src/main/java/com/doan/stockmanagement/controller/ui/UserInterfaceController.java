package com.doan.stockmanagement.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.doan.stockmanagement.common.CommonConstants;

@Controller
public class UserInterfaceController {

    @GetMapping(value = CommonConstants.DOMAIN_INIT)
    public String init() {
        return "redirect:" + CommonConstants.DOMAIN_HOME;
    }

    @GetMapping(value = CommonConstants.DOMAIN_HOME)
    public String home(Model model) {
        model.addAttribute(CommonConstants.PATH_ACTION, CommonConstants.DOMAIN_HOME);

        return CommonConstants.PAGE + CommonConstants.DOMAIN_HOME;
    }

    @GetMapping(value = CommonConstants.DOMAIN_WAREHOUSE)
    public String warehouse(Model model) {
        model.addAttribute(CommonConstants.PATH_ACTION, CommonConstants.DOMAIN_WAREHOUSE);

        return CommonConstants.PAGE + CommonConstants.DOMAIN_WAREHOUSE;
    }

    @GetMapping(value = CommonConstants.DOMAIN_PROVIDER)
    public String provider(Model model) {
        model.addAttribute(CommonConstants.PATH_ACTION, CommonConstants.DOMAIN_PROVIDER);

        return CommonConstants.PAGE + CommonConstants.DOMAIN_PROVIDER;
    }

    @GetMapping(value = CommonConstants.DOMAIN_CUSTOMER)
    public String customer(Model model) {
        model.addAttribute(CommonConstants.PATH_ACTION, CommonConstants.DOMAIN_CUSTOMER);

        return CommonConstants.PAGE + CommonConstants.DOMAIN_CUSTOMER;
    }

    @GetMapping(value = CommonConstants.DOMAIN_PRODUCT)
    public String product(Model model) {
        model.addAttribute(CommonConstants.PATH_ACTION, CommonConstants.DOMAIN_PRODUCT);

        return CommonConstants.PAGE + CommonConstants.DOMAIN_PRODUCT;
    }

    @GetMapping(value = CommonConstants.DOMAIN_GOODS_RECEIPT_NOTE)
    public String goodsReceiptNote(Model model) {
        model.addAttribute(CommonConstants.PATH_ACTION, CommonConstants.GOODS_RECEIPT_NOTE);

        return CommonConstants.PAGE + CommonConstants.GOODS_RECEIPT_NOTE;
    }

    @GetMapping(value = CommonConstants.DOMAIN_GOODS_DELIVERY_NOTE)
    public String goodsDeliveryNote(Model model) {
        model.addAttribute(CommonConstants.PATH_ACTION, CommonConstants.GOODS_DELIVERY_NOTE);

        return CommonConstants.PAGE + CommonConstants.GOODS_DELIVERY_NOTE;
    }

}