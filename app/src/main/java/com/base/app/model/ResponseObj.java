package com.base.app.model;


import com.base.app.utils.Response;

import io.reactivex.annotations.Nullable;

public class ResponseObj<T> {
    private T mObj;
    private Response response;
    private String err;

    public ResponseObj(@Nullable T obj) {
        this.mObj = obj;
    }

    public ResponseObj(@Nullable T obj, Response response) {
        this.mObj = obj;
        this.response = response;
    }

    public ResponseObj(@Nullable T obj, Response response, String err) {
        this.mObj = obj;
        this.response = response;
        this.err = err;
    }

    public T getObj() {
        return mObj;
    }

    public void setObj(T obj) {
        this.mObj = obj;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }
}
