package com.example.administrator.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

//建立依赖compile 'com.squareup.okhttp3:okhttp:3.10.0'
//在manifest权限申请
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG ="MainActivity" ;
    private Button btnGet,btnPost;
    private TextView tvShowMsg;
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        init();
    }

    private void initView() {
        btnGet=(Button)findViewById(R.id.btn_get);
        btnPost=(Button)findViewById(R.id.btn_post);
        tvShowMsg=(TextView)findViewById(R.id.tv_show_msg);
    }

    private void init() {
        btnPost.setOnClickListener(this);
        btnGet.setOnClickListener(this);
        mHandler=new Handler(){//非UI线程向UI线程传数据，使用handler
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                tvShowMsg.setText((String)msg.obj);
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_get:
                HttpGet();
                break;
            case R.id.btn_post:
                HttpPost();
                break;
        }
    }

    private void HttpGet() {
        HttpUtil.RequestGet(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                  String strRet=response.body().string();
                  Log.i(TAG,"GET方法获取："+strRet);

                  Message msg=mHandler.obtainMessage();
                  msg.obj="GET方法获取："+strRet;
                  mHandler.sendMessage(msg);
            }
        });
    }
    private void HttpPost() {
        HttpUtil.RequestPost(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strRet=response.body().string();
                Log.i(TAG,"POST方法获取："+strRet);

                Message msg=mHandler.obtainMessage();
                msg.obj="POST方法获取："+strRet;
                mHandler.sendMessage(msg);
            }
        });
    }

}
