package com.example.msi.websocket.callback;

import com.example.msi.websocket.websocket.common.DataModel;

/**
 * Created by Administrator on 2018/5/17.
 */

public interface MeslistLisener {
    void mesListListener(DataModel.DataBean.MyMessageBean dataModel);
}
