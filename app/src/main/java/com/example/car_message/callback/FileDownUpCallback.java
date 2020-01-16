package com.example.car_message.callback;

import java.io.File;

/**
 * Created by coderliu on 2016/9/6.
 * <p>
 * Description：
 */

public abstract class FileDownUpCallback<T> implements DataCallback {

    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
    }
    public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
    }
    public abstract void onSuccess(File file, T t);
}
