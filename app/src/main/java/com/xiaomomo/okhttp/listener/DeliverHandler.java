package com.xiaomomo.okhttp.listener;

/**
 * Created by lihuanxing on 2017/7/1.
 */

public class DeliverHandler {

    public OkHttpListener httpListener = null;
    public Class<?> clazz;

    public DeliverHandler(OkHttpListener listener){
        this.httpListener = listener;
    }

    public DeliverHandler(OkHttpListener listener, Class<?> clazz){
        this.httpListener = listener;
        this.clazz = clazz;
    }

}
