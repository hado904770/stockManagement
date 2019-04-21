package com.doan.stockmanagement.common;


public class CommonUtils {

    public static <T> ResponseApi<T> buildResponse(int status, String message, T results) {

        ResponseApi<T> responseApi = new ResponseApi<>();
        responseApi.setStatus(status);
        responseApi.setMessage(message);
        responseApi.setResults(results);

        return responseApi;
    }

}
