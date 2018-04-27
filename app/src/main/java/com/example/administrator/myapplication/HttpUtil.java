package com.example.administrator.myapplication;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

//功能类中用静态方法
public class HttpUtil {
    public static void RequestGet(Callback callback){
        //创建OkHttpClient对象
        OkHttpClient okHttpClient=new OkHttpClient();
        //创建Request对象
        Request request=new Request.Builder()
                .url("http://10.0.2.2/get.php?key=get")
                .get()
                .build();
        //通过OkttpClient的newCall方法创建Call对象
        Call call=okHttpClient.newCall(request);
        //调用Call的enqueue方法执行异步网络请求
        call.enqueue(callback);
    }


    public static void RequestPost(Callback callback){
        OkHttpClient okHttpClient=new OkHttpClient();
        FormBody formBody=new FormBody.Builder().add("key","post").build();
        Request request=new Request.Builder()
                .url("http://10.0.2.2/post.php")
                .post(formBody)
                .build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(callback);
    }
}
