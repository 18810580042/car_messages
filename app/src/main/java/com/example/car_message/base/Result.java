package com.example.car_message.base;

/**
 * @author : 张鑫
 * @time 2017/4/13 14:40.
 */

public class Result<T>{
    boolean status;
    String msg;
    T data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
