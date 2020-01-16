package com.example.car_message.callback;

import java.util.List;

/**
 * 张鑫
 * by .2017/3/12 16:36
 */
public interface  CommontListCallback<T> extends DataCallback{
    void onSuccess(List<T> data);
}
