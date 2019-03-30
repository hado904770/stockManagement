package com.doan.stockmanagement.common;

import lombok.Data;

@Data
public class ResponseApi <T> {
    
    private int statusCode;
    private String message;
    private T results;
    
}
