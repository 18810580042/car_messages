package com.example.car_message.callback;

/**
 *
 * 标准接口,T代表返回的data部分
 */

public interface ResultCallback<T> extends DataCallback{
    void onSuccess(T data);
}
