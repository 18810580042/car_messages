package com.example.msi.websocket.callback;

import com.example.msi.websocket.websocket.common.MessageBean;

/**
 * Created by 郭金龙
 * on 2018/4/10 19:23
 */

public interface MessageListener {
    void messageListener(MessageBean.DataBean message);
}
