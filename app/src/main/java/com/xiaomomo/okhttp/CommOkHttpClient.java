package com.xiaomomo.okhttp;

import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by lihuanxing on 2017/7/1.
 * 请求发送，参数配置，https支持
 */

public class CommOkHttpClient {

    private static final long TIME_OUT = 30;//设置超时时间为30s
    private static OkHttpClient okHttpClient;

    static {
        //创建client构建者
        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();

        //为client构建者填充超时时间
        okhttpBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okhttpBuilder.readTimeout(TIME_OUT,TimeUnit.SECONDS);
        okhttpBuilder.writeTimeout(TIME_OUT,TimeUnit.SECONDS);

        //支持重定向
        okhttpBuilder.followRedirects(true);

        //支持https
        okhttpBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        //创建信任管理器
        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }};

        //创建加密上下文
        final SSLContext sslContext;
        try {
            //与服务器类型保持一致
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext
                    .getSocketFactory();
            okhttpBuilder.sslSocketFactory(sslSocketFactory);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //创建client对象
        okHttpClient = okhttpBuilder.build();

    }

    /**
     * 发送具体的http/https请求
     * @param request
     * @param commentCallback
     * @return
     */
    public static Call sendRequest(Request request, Callback commentCallback){
        Call call = okHttpClient.newCall(request);
        call.enqueue(commentCallback);
        return call;
    }
}
