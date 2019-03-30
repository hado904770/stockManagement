package com.doan.stockmanagement.entity.dto;

import com.doan.stockmanagement.enums.Status;
import com.doan.stockmanagement.enums.StatusDTO;

import lombok.Data;

@Data
public class ProductStatusDTO {

    private Integer id;
    private Status status;
}
