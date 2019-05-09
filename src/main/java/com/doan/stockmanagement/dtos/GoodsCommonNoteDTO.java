package com.doan.stockmanagement.dtos;

import java.time.LocalDate;
import java.util.List;

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
public class GoodsCommonNoteDTO extends CommonEntity {
    
    private String content;
    private List<ProductDTO> products;
    
    @Setter
    @Getter
    public static class ProductDTO extends CommonEntity {
        
        private String code;
        private String name;
        
        @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
        private LocalDate mfg; // Manufacturing date

        @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
        private LocalDate exp; // Expiry date
        
        private Warehouse warehouse;
        private Provider provider;
        private Customer customer;
        
    }
}
