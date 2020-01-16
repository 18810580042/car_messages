package com.example.msi.websocket.websocket.common;

import com.example.msi.websocket.websocket.request.Action;
import com.example.msi.websocket.websocket.request.Request;

import java.util.concurrent.ScheduledFuture;

/**
 * Created by zly on 2017/7/23.
 */

public class CallbackWrapper {

    private final IWsCallback tempCallback;
    private final ScheduledFuture timeoutTask;
    private final Action action;
    private final MessageBean.DataBean request;


    public CallbackWrapper(IWsCallback tempCallback, ScheduledFuture timeoutTask, Action action, MessageBean.DataBean request) {
        this.tempCallback = tempCallback;
        this.timeoutTask = timeoutTask;
        this.action = action;
        this.request = request;
    }


    public IWsCallback getTempCallback() {
        return tempCallback;
    }


    public ScheduledFuture getTimeoutTask() {
        return timeoutTask;
    }


    public Action getAction() {
        return action;
    }


    public MessageBean.DataBean getRequest() {
        return request;
    }
}
