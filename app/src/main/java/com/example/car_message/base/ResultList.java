package com.example.car_message.base;

import java.util.ArrayList;

/**
 * @author : 张鑫
 * @time 2017/4/13 14:40.
 */

public class ResultList<T> {
    boolean status;
    String msg;
    ArrayList<T> data;

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

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }
}
