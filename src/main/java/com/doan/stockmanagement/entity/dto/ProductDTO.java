package com.doan.stockmanagement.entity.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private Integer id;
    private String name;
    private String code;
    private String unit;
    private Integer amount;
    private Float unitPrice;
    private Double intoMoney;
    private ProductStatusDTO productStatusDTO;

}
