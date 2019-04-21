package com.doan.stockmanagement.common;

import lombok.Data;

@Data
public class ResponseApi <T> {
    
    private int status;
    private String message;
    private T results;
    
}
