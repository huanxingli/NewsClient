package com.xiaomomo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.xiaomomo.okhttp.CommOkHttpClient;
import com.xiaomomo.okhttp.listener.DeliverHandler;
import com.xiaomomo.okhttp.listener.OkHttpListener;
import com.xiaomomo.okhttp.request.CommentRequest;
import com.xiaomomo.okhttp.response.CommonJsonCallback;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.xiaomomo", appContext.getPackageName());
        CommOkHttpClient.sendRequest(CommentRequest.postRequest("http://wwww.baidu.com",null),
                new CommonJsonCallback(new DeliverHandler(new OkHttpListener() {
                    @Override
                    public void onSuccess(Object responseObj) {

                    }

                    @Override
                    public void onFailure(Object errorObj) {

                    }
                })));

        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

    }
}
