package com.example.fakejy.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class Response<T> implements Serializable {

    private static final long serialVersionUID = -1209575785814253865L;

    private T result;

    private Boolean success;

    private Integer errorCode;

    private String errorMsg;

    /**
     * 成功
     */
    public static <T> Response<T> success() {
        var result = new Response<T>();
        result.setSuccess(true);
        return result;
    }

    /**
     * 失败
     */
    public static <T> Response<T> error() {
        var result = new Response<T>();
        result.setSuccess(false);
        return result;
    }

    public Response<T> result(T result) {
        this.setResult(result);
        return this;
    }

    public Response<T> errorMsg(String errorMsg) {
        this.setErrorMsg(errorMsg);
        return this;
    }

    public Response<T> errorCode(Integer errorCode) {
        this.setErrorCode(errorCode);
        return this;
    }

}
