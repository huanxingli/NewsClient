package com.xiaomomo.okhttp.exception;

/**
 * Created by lihuanxing on 2017/7/1.
 * 自定义的异常类，将异常信息返回给应用层
 */

public class OkHttpException extends Exception{

    private static final long serialVersionUID = 1L;

    private int code;
    private Object emsg;

    public OkHttpException(int code , Object emsg){
        this.code = code;
        this.emsg = emsg;
    }

    public int getCode(){
        return this.code;
    }

    public Object getObject(){
        return this.emsg;
    }

}
