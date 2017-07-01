package com.xiaomomo.okhttp.response;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.xiaomomo.okhttp.exception.OkHttpException;
import com.xiaomomo.okhttp.listener.DeliverHandler;
import com.xiaomomo.okhttp.listener.OkHttpListener;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lihuanxing on 2017/7/1.
 */

public class CommonJsonCallback implements Callback {

    protected final String RESULT_CODE = "code";//若返回有的话，http请求就是成功的
    protected final int RESULT_CODE_VALUE = 0; //正确的响应码
    protected final String ERROT_MSG = "emsg";
    protected final String EMPTY_MSG = "";

    /**
     * 自定义异常类型
     */
    protected final int NETWORK_ERROR = -1;
    protected final int JSON_ERROR = -2;
    protected final int OTHER_ERROR = -3;

    /**
     * 将数据从子线程传递到UI线程
     */
    private Handler mHandler;
    private OkHttpListener listener;
    private Class<?> clazz;


    public CommonJsonCallback(DeliverHandler handler){
        this.listener = handler.httpListener;
        this.clazz = handler.clazz;
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                listener.onFailure(new OkHttpException(NETWORK_ERROR,e));
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String result = response.body().string();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
            }
        });
    }

    /**
     * 处理服务器返回的响应数据
     * @param resultObj
     */
    public void handleResponse(Object resultObj){
        if (resultObj == null && resultObj.toString().trim().equals("")){
            listener.onFailure(new OkHttpException(NETWORK_ERROR,EMPTY_MSG));
            return;
        }

        try{
            JSONObject jsonObject = new JSONObject(resultObj.toString());
            if (jsonObject.has(RESULT_CODE)){
                if(jsonObject.get(RESULT_CODE) == RESULT_CODE_VALUE){//这块我也不是很懂，可能是于服务器定义的一些规则
                    if (clazz == null){
                        listener.onSuccess(jsonObject);
                    }else{
                        //需要将json转化为实体对象
                        Gson gson = new Gson();
                        Object obj = gson.fromJson(jsonObject.toString(),clazz);
                        if (obj != null) {
                            listener.onSuccess(obj);
                        }else{
                            //返回的不是合法的json数据
                            listener.onFailure(new OkHttpException(JSON_ERROR,EMPTY_MSG));
                        }
                    }
                }else{
                    //将服务器返回的异常回调信息返回给应用层
                    listener.onFailure(new OkHttpException(OTHER_ERROR,jsonObject.get(RESULT_CODE)));
                }
            }
        }catch (Exception e){
            listener.onFailure(new OkHttpException(OTHER_ERROR,e.getMessage()));
        }
    }
}
