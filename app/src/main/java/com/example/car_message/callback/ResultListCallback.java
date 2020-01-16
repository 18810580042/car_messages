package com.example.car_message.callback;

import java.util.List;


public interface ResultListCallback<T> extends DataCallback{
    void onSuccess(List<T> data);

}
