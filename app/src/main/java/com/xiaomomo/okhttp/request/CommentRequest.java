package com.xiaomomo.okhttp.request;


import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * Created by lihuanxing on 2017/7/1.
 * 接收参数，生成Request对象
 */

public class CommentRequest {

    /**
     * 创建post请求的Request
     * @param url
     * @param params
     * @return
     */
    public static Request postRequest(String url,RequestParams params){
        FormBody.Builder formBuilder = new FormBody.Builder();
        //添加参数
        if (params != null){
            for (Map.Entry<String,String> entry : params.urlParams.entrySet()){
                formBuilder.add(entry.getKey(),entry.getValue());
            }
        }
        FormBody formBody = formBuilder.build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        return request;

    }

    /**
     * 创建get请求的Request
     * @param url
     * @param params
     * @return
     */
    public static Request getRequest(String url, RequestParams params){
        StringBuilder stringBuilder = new StringBuilder(url).append("?");
        if (params != null){
            for (Map.Entry<String ,String> entry : params.urlParams.entrySet()){
                stringBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        Request request = new Request.Builder()
                .url(stringBuilder.substring(0,stringBuilder.length()-1))
                .get()
                .build();
        return request;

    }
}
