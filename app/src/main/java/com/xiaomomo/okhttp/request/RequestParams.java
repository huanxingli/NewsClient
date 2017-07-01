package com.xiaomomo.okhttp.request;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lihuanxing on 2017/7/1.
 *
 */

public class RequestParams {

    public ConcurrentHashMap<String,String> urlParams = new ConcurrentHashMap<>();
    public ConcurrentHashMap<String,String> fileParams = new ConcurrentHashMap<>();

    public void put(String key, String value){
        if (key != null && value != null){
            urlParams.put(key,value);
        }
    }
}
