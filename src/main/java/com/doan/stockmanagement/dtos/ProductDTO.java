package com.doan.stockmanagement.dtos;

import java.time.LocalDate;

import com.doan.stockmanagement.common.CommonEntity;
import com.doan.stockmanagement.entities.Customer;
import com.doan.stockmanagement.entities.Provider;
import com.doan.stockmanagement.entities.Warehouse;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDTO extends CommonEntity {
    
    private String code;
    private String name;
    
    @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate mfg; // Manufacturing date

    @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate exp; // Expiry date
    
    private Warehouse warehouse;
    private Provider provider;
    private Customer customer;
    private GoodsReceiptNoteDTO goodsReceiptNote;
    private GoodsDeliveryNoteDTO goodsDeliveryNote;
    
    @Setter
    @Getter
    public static class GoodsReceiptNoteDTO extends CommonEntity {
        private String content;
    }
    
    @Setter
    @Getter
    public static class GoodsDeliveryNoteDTO extends CommonEntity {
        private String content;
    }
    
}
