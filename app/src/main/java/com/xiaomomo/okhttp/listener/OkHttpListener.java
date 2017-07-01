package com.xiaomomo.okhttp.listener;

/**
 * Created by lihuanxing on 2017/7/1.
 * 自定义监听接口
 */

public interface OkHttpListener {

    /**
     * 网络请求成功回调事件
     * @param responseObj
     */
    public void onSuccess(Object responseObj);

    /**
     * 网络请求失败回调事件
     * @param errorObj
     */
    public void onFailure(Object errorObj);

}
