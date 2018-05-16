package com.jcfy.xkt.api;

/**
 * @author linzheng
 */
public class BaseData<T> {


    private boolean status;

    private String errorCode;

    private String errorMsg;

    private T data;


    public boolean isStatus() {
        return status;
    }

    public BaseData<T> setStatus(boolean status) {
        this.status = status;
        return this;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public BaseData<T> setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public BaseData<T> setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    public T getData() {
        return data;
    }

    public BaseData<T> setData(T data) {
        this.data = data;
        return this;
    }
}
