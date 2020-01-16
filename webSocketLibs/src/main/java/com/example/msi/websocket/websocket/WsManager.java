package com.example.msi.websocket.websocket;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

import android.text.TextUtils;
import android.util.Log;

import com.example.msi.websocket.BuildConfig;
import com.example.msi.websocket.callback.MessageListener;
import com.example.msi.websocket.websocket.common.CallbackDataWrapper;
import com.example.msi.websocket.websocket.common.CallbackWrapper;
import com.example.msi.websocket.websocket.common.DataModel;
import com.example.msi.websocket.websocket.common.ICallback;
import com.example.msi.websocket.websocket.common.IWsCallback;
import com.example.msi.websocket.websocket.common.Meassage;
import com.example.msi.websocket.websocket.common.MessageBean;
import com.example.msi.websocket.websocket.common.UrlsUtils;
import com.example.msi.websocket.websocket.common.WsStatus;
import com.example.msi.websocket.websocket.request.Action;
import com.google.gson.Gson;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zly on 2017/6/8.
 */

public class WsManager {
    private final String TAG = this.getClass().getSimpleName();

    /**
     * WebSocket config
     */
    private static final long HEARTBEAT_INTERVAL = 30000;//心跳间隔
    private static final int FRAME_QUEUE_SIZE = 5;
    private static final int CONNECT_TIMEOUT = 5000;
    private static final int REQUEST_TIMEOUT = 10000;//请求超时时间
    private static final String DEF_TEST_URL = UrlsUtils.LOCAL;//测试服默认地址
    private static final String DEF_RELEASE_URL = "ws://echo.websocket.org";//正式服默认地址
    private static final String DEF_URL = BuildConfig.DEBUG ? DEF_TEST_URL : DEF_RELEASE_URL;
    private String url;//地址

    private WsStatus mStatus;  //websocket状态
    private WebSocket ws;
    private WsListener mListener;
    private WsListListener wsListListener;
    private AtomicLong seqId = new AtomicLong(SystemClock.uptimeMillis());//每个请求的唯一标识
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private Map<Long, CallbackWrapper> callbacks = new HashMap<>();

    private final int SUCCESS_HANDLE = 0x01;
    private final int ERROR_HANDLE = 0x02;
    private static Context mContext;
    private String LoginName; //登录名
    private Meassage meassage;
    /**
     * 接收到消息监听
     */
    private MessageListener messageListener;

    public void getListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }


    /**
     * 构造器
     */
    private WsManager() {
    }

    /**
     * 单例
     */
    private static class WsManagerHolder {
        private static WsManager mInstance = new WsManager();
    }

    public static WsManager getInstance(Context context) {
        mContext = context;
        return WsManagerHolder.mInstance;
    }

    /**
     * 消息发送成功与失败
     */
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS_HANDLE:
                    CallbackDataWrapper successWrapper = (CallbackDataWrapper) msg.obj;
                    successWrapper.getCallback().onSuccess(successWrapper.getData());
                    break;
                case ERROR_HANDLE:
                    CallbackDataWrapper errorWrapper = (CallbackDataWrapper) msg.obj;
                    errorWrapper.getCallback().onFail((String) errorWrapper.getData());
                    break;
            }
        }
    };


    public void init(String loginName) {
        this.LoginName = loginName;
        try {
            /**
             * configUrl其实是缓存在本地的连接地址
             * 这个缓存本地连接地址是app启动的时候通过http请求去服务端获取的,
             * 每次app启动的时候会拿当前时间与缓存时间比较,超过6小时就再次去服务端获取新的连接地址更新本地缓存
             *///
            String configUrl = UrlsUtils.LOCAL;
            url = TextUtils.isEmpty(configUrl) ? DEF_URL : configUrl;
            ws = new WebSocketFactory().createSocket(url, CONNECT_TIMEOUT)
                    .setFrameQueueSize(FRAME_QUEUE_SIZE)//设置帧队列最大值为5
                    .setMissingCloseFrameAllowed(false)//设置不允许服务端关闭连接却未发送关闭帧
                    .addListener(mListener = new WsListener())//添加回调监听
                    .addListener(wsListListener = new WsListListener())
                    .connectAsynchronously();//异步连接
            setStatus(WsStatus.CONNECTING);
            Logger.t(TAG).d("第一次连接");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录请求
     */
    private void doAuth() {
        sendChatReqRev(Action.SYSTEM, LoginName, "", "", "", "", "KNYS", new ICallback() {
            @Override
            public void onSuccess(Object o) {
                Log.i("onSuccess---", o.toString());
                startHeartbeat();
            }

            @Override
            public void onFail(String msg) {
            }
        });
    }

//    //同步数据
//    private void delaySyncData() {
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                sendReq(Action.SYNC, null, new ICallback() {
//                    @Override
//                    public void onSuccess(Object o) {
//
//                    }
//                    @Override
//                    public void onFail(String msg) {
//
//                    }
//                });
//            }
//        }, 300);
//    }


    /**
     * 开始心跳
     */
    private void startHeartbeat() {
        mHandler.postDelayed(heartbeatTask, HEARTBEAT_INTERVAL);
    }


    /**
     * 取消心跳
     */
    private void cancelHeartbeat() {
        heartbeatFailCount = 0;
        mHandler.removeCallbacks(heartbeatTask);
    }


    /**
     * 心跳线程，当线心跳失败三次之后，重连
     */
    private int heartbeatFailCount = 0;
    private Runnable heartbeatTask = new Runnable() {
        @Override
        public void run() {
            sendReq(Action.HEARTBEAT, null, new ICallback() {
                @Override
                public void onSuccess(Object o) {
                    heartbeatFailCount = 0;
                }

                @Override
                public void onFail(String msg) {
                    heartbeatFailCount++;
                    if (heartbeatFailCount >= 3) {
                        reconnect();
                    }
                }
            });

            mHandler.postDelayed(this, HEARTBEAT_INTERVAL);
        }
    };


    /**
     * 发送聊天消息
     *
     * @param action    动作类型
     * @param receiveId 接收者
     * @param content   消息内容
     * @param callback  消息发送回调
     */
    public void sendChatReq(Action action, String receiveId, String content, String messageid, String MessageType, String sysType, ICallback callback) {
        meassage = new Meassage();
        meassage.setReceiverId(receiveId);
        meassage.setContent(content);
        meassage.setMessageid(messageid);
        meassage.setMessageType(MessageType);
        meassage.setCmd(action.getAction());
        meassage.setSysType(sysType);
        sendReq(action, meassage, callback);
    }

    public void sendChatReqRev(Action action, String senderId, String received, String content, String messageid, String MessageType, String sysType, ICallback callback) {
        meassage = new Meassage();
        meassage.setSenderId(senderId);
        meassage.setReceiverId(received);
        meassage.setContent(content);
        meassage.setMessageid(messageid);
        meassage.setMessageType(MessageType);
        meassage.setSysType(sysType);
        meassage.setCmd(action.getAction());
        sendReq(action, meassage, callback);
    }

    public void sendReq(Action action, Object req, ICallback callback) {
        sendReq(action, req, callback, REQUEST_TIMEOUT);
    }


    public void sendReq(Action action, Object req, ICallback callback, long timeout) {
        sendReq(action, req, callback, timeout, 1);
    }


    /**
     * @param action   Action
     * @param req      请求参数
     * @param callback 回调
     * @param timeout  超时时间
     * @param reqCount 请求次数
     */
    @SuppressWarnings("unchecked")
    private <T> void sendReq(Action action, T req, final ICallback callback, final long timeout, int reqCount) {
        if (!isNetConnect()) {
            callback.onFail("网络不可用");
            return;
        }

        if (WsStatus.AUTH_SUCCESS.equals(getStatus()) || Action.LOGIN.equals(action)
                || Action.CHAT.equals(action) || Action.ENDCHAT.equals(action) || Action.SYSTEM.equals(action)
                || Action.LOGOUT.equals(action)) {
            MessageBean.DataBean messageBean = new MessageBean.DataBean();
            messageBean.setCmd(action.getAction());
            messageBean.setMessageType(((Meassage) req).getMessageType());
            messageBean.setTime(System.currentTimeMillis());
            messageBean.setMessageid(((Meassage) req).getMessageid());
            messageBean.setSysType(((Meassage) req).getSysType());
            if (!action.getAction().equals(Action.HEARTBEAT.getAction()) && !action.getAction().equals(Action.LOGIN.getAction())) {
                messageBean.setContent(((Meassage) req).getContent());
                messageBean.setReceiverId(((Meassage) req).getReceiverId());
            }
            messageBean.setSenderId(LoginName);
            ScheduledFuture timeoutTask = enqueueTimeout(messageBean.getTime(), timeout);//添加超时任务

            IWsCallback tempCallback = new IWsCallback() {

                @Override
                public void onSuccess(Object o) {
                    mHandler.obtainMessage(SUCCESS_HANDLE, new CallbackDataWrapper(callback, o))
                            .sendToTarget();
                }


                @Override
                public void onError(String msg, MessageBean.DataBean request, Action action) {
                    mHandler.obtainMessage(ERROR_HANDLE, new CallbackDataWrapper(callback, msg))
                            .sendToTarget();
                }


                @Override
                public void onTimeout(MessageBean.DataBean request, Action action) {
                    timeoutHandle(request, action, callback, timeout);
                }
            };

            callbacks.put(messageBean.getTime(),
                    new CallbackWrapper(tempCallback, timeoutTask, action, messageBean));

            Logger.t(TAG).d("send text : %s", new Gson().toJson(messageBean));
            ws.sendText(new Gson().toJson(messageBean));
            Log.i("send---", messageBean.toString());
        } else {
            callback.onFail("用户授权失败");
        }
    }


    /**
     * 添加超时任务
     */
    private ScheduledFuture enqueueTimeout(final long seqId, long timeout) {
        return executor.schedule(new Runnable() {
            @Override
            public void run() {
                CallbackWrapper wrapper = callbacks.remove(seqId);
                if (wrapper != null) {
                    Logger.t(TAG).d("(action:%s)第%d次请求超时", wrapper.getAction().getAction(), wrapper.getRequest().getTime());
                    wrapper.getTempCallback().onTimeout(wrapper.getRequest(), wrapper.getAction());
                }
            }
        }, timeout, TimeUnit.MILLISECONDS);
    }


    /**
     * 超时处理
     */
    private void timeoutHandle(MessageBean.DataBean request, Action action, ICallback callback, long timeout) {

        if (request.getReqCount() > 3) {
            Logger.t(TAG).d("(action:%s)连续3次请求超时 执行http请求", action.getAction());
            //走http请求
            init(LoginName);
        } else {
            sendReq(action, request.getTime(), callback, timeout, request.getReqCount() + 1);
            Logger.t(TAG).d("(action:%s)发起第%d次请求", action.getAction(), request.getReqCount());
        }
    }


    /**
     * 继承默认的监听空实现WebSocketAdapter,重写我们需要的方法
     * onTextMessage 收到文字信息
     * onConnected 连接成功
     * onConnectError 连接失败
     * onDisconnected 连接关闭
     */
    class WsListener extends WebSocketAdapter {
        @Override
        public void onTextMessage(WebSocket websocket, String text) throws Exception {
            super.onTextMessage(websocket, text);
            Logger.t(TAG).d("receiverMsg:%s", text);
            //将返回的数据解析成对象
            MessageBean.DataBean messageBean = new Gson().fromJson(text, MessageBean.DataBean.class);
            //判断时自己发的还是别人给自己发的消息
            if (!Action.LOGOUT.getAction().equals(messageBean.getCmd())&&!Action.SYSTEM.getAction().equals(messageBean.getCmd())) {
                if (Action.REVERT.getAction().equals(messageBean.getCmd())
                    || Action.LOGIN.getAction().equals(messageBean.getCmd())
                    || Action.HEARTBEAT.getAction().equals(messageBean.getCmd())) {
                    //找到对应callback
                    CallbackWrapper wrapper = callbacks.remove(messageBean.getTime());
                    if (Action.REVERT.getAction().equals(messageBean.getCmd())) {
                        messageListener.messageListener(messageBean);
                        Log.i("REVERT---", messageBean.getContent());
                    } else if (Action.CHAT.getAction().equals(messageBean.getCmd())) {
                        messageListener.messageListener(messageBean);
                        Log.i("CHAT---", messageBean.getContent());
                    }
                    wrapper.getTempCallback().onSuccess(messageBean);
                } else {
                    //通知
                    messageListener.messageListener(messageBean);
                    Log.i("Notifity---", messageBean.getContent());
                }
            }
        }


        @Override
        public void onConnected(WebSocket websocket, Map<String, List<String>> headers)
                throws Exception {
            super.onConnected(websocket, headers);
            Logger.t(TAG).d("连接成功");
            setStatus(WsStatus.CONNECT_SUCCESS);
            cancelReconnect();//连接成功的时候取消重连,初始化连接次数
            doAuth();
        }


        @Override
        public void onConnectError(WebSocket websocket, WebSocketException exception)
                throws Exception {
            super.onConnectError(websocket, exception);
            Logger.t(TAG).d("连接错误");
            setStatus(WsStatus.CONNECT_FAIL);
            reconnect();//连接错误的时候调用重连方法
        }


        @Override
        public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer)
                throws Exception {
            super.onDisconnected(websocket, serverCloseFrame, clientCloseFrame, closedByServer);
            Logger.t(TAG).d("断开连接");
            setStatus(WsStatus.CONNECT_FAIL);
            reconnect();//连接断开的时候调用重连方法
        }
    }

    class WsListListener extends WebSocketAdapter {
        @Override
        public void onTextMessage(WebSocket websocket, String text) throws Exception {
            super.onTextMessage(websocket, text);
            Logger.t(TAG).d("receiverMsg:%s", text);
            //将返回的数据解析成对象
            DataModel.DataBean.MyMessageBean dataModel = new Gson().fromJson(text, DataModel.DataBean.MyMessageBean.class);
        }


        @Override
        public void onConnected(WebSocket websocket, Map<String, List<String>> headers)
                throws Exception {
            super.onConnected(websocket, headers);
            Logger.t(TAG).d("连接成功");
            setStatus(WsStatus.CONNECT_SUCCESS);
            cancelReconnect();//连接成功的时候取消重连,初始化连接次数
//            doAuth();
        }


        @Override
        public void onConnectError(WebSocket websocket, WebSocketException exception)
                throws Exception {
            super.onConnectError(websocket, exception);
            Logger.t(TAG).d("连接错误");
            setStatus(WsStatus.CONNECT_FAIL);
            reconnect();//连接错误的时候调用重连方法
        }


        @Override
        public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer)
                throws Exception {
            super.onDisconnected(websocket, serverCloseFrame, clientCloseFrame, closedByServer);
            Logger.t(TAG).d("断开连接");
            setStatus(WsStatus.CONNECT_FAIL);
            reconnect();//连接断开的时候调用重连方法
        }
    }

    /**
     * 设置websocket状态
     *
     * @return
     */
    private void setStatus(WsStatus status) {
        this.mStatus = status;
    }


    /**
     * 获取websocket状态
     *
     * @return
     */
    private WsStatus getStatus() {
        return mStatus;
    }


    /**
     * 断开websocket链接
     */
    public void disconnect() {
        if (ws != null) {
            ws.disconnect();
        }
    }


    private int reconnectCount = 0;//重连次数
    private long minInterval = 3000;//重连最小时间间隔
    private long maxInterval = 60000;//重连最大时间间隔


    /**
     * 重连
     */
    public void reconnect() {
        if (!isNetConnect()) {
            reconnectCount = 0;
            Logger.t(TAG).d("重连失败网络不可用");
            return;
        }
        //这里其实应该还有个用户是否登录了的判断 因为当连接成功后我们需要发送用户信息到服务端进行校验
        //由于我们这里是个demo所以省略了
        if (ws != null &&
                !ws.isOpen() &&//当前连接断开了
                getStatus() != WsStatus.CONNECTING) {//不是正在重连状态

            reconnectCount++;
            setStatus(WsStatus.CONNECTING);
            cancelHeartbeat();
            long reconnectTime = minInterval;
            //当重连次数大于3的时候，将地址换成本地缓存的地址
            if (reconnectCount > 3) {
                url = DEF_URL;
                long temp = minInterval * (reconnectCount - 2);
                reconnectTime = temp > maxInterval ? maxInterval : temp;
            }
            Logger.t(TAG).d("准备开始第%d次重连,重连间隔%d -- url:%s", reconnectCount, reconnectTime, url);
            mHandler.postDelayed(mReconnectTask, reconnectTime);
        }
    }


    /**
     * 重连线程
     */
    private Runnable mReconnectTask = new Runnable() {

        @Override
        public void run() {
            try {
                ws = new WebSocketFactory().createSocket(url, CONNECT_TIMEOUT)
                        .setFrameQueueSize(FRAME_QUEUE_SIZE)//设置帧队列最大值为5
                        .setMissingCloseFrameAllowed(false)//设置不允许服务端关闭连接却未发送关闭帧
                        .addListener(mListener = new WsListener())//添加回调监听
                        .addListener(wsListListener = new WsListListener())
                        .connectAsynchronously();//异步连接
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };


    /**
     * 取消重连
     */
    private void cancelReconnect() {
        reconnectCount = 0;
        mHandler.removeCallbacks(mReconnectTask);
    }


    /**
     * 检测是否有网络
     *
     * @return
     */
    private boolean isNetConnect() {
        ConnectivityManager connectivity = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }
}
