package com.doan.stockmanagement.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Result <T> {
    
    private int status;
    private String message;
    private T data;
    
}
