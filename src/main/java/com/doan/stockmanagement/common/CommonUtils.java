package com.doan.stockmanagement.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CommonUtils {

    public static <T> ResponseEntity<Result<T>> buildResponse(HttpStatus httpStatus, String message, T data) {

        Result<T> result = new Result<>();
        result.setStatus(httpStatus.value());
        result.setMessage(message);
        result.setData(data);

        return new ResponseEntity<Result<T>>(result, httpStatus);

    }

}
