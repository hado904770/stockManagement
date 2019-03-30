package com.doan.stockmanagement.common;

public class CommonUtils {

    public static <T> ResponseApi<T> buildResponseApi(int statusCode, String message, T results) {

        ResponseApi<T> responseApi = new ResponseApi<T>();
        responseApi.setStatusCode(statusCode);
        responseApi.setMessage(message);
        responseApi.setResults(results);

        return responseApi;
    }

}
