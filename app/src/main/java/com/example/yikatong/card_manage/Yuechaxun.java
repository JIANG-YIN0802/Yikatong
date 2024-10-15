package com.example.yikatong.card_manage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSONArray;
import com.example.yikatong.R;
import com.example.yikatong.User;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Yuechaxun extends AppCompatActivity {
    ImageView img_yue_return;
    TextView tv_yue_xingming,tv_yue_xuehao,tv_yue_yue;
    List<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_yuechaxun);

        findViews();


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String xingming = bundle.getString("xingming");
        String xuehao = bundle.getString("xuehao");
        tv_yue_xingming.setText(xingming);
        tv_yue_xuehao.setText(xuehao);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                String path = "http://10.201.70.178:8080/demo/getByXuehao";
                String xuehao = tv_yue_xuehao.getText().toString();
                FormBody formBody = new FormBody.Builder().add("xuehao", xuehao).build();
                Request request = new Request.Builder()
                        .url(path)
                        .post(formBody)
                        .build();
                users = null;
                try (Response response = okHttpClient.newCall(request).execute()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }
                    users = JSONArray.parseArray(response.body().string(), User.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        User user = users.get(0);
                        tv_yue_yue.setText(user.getBalance().toString());
                    }
                });
            }
        });
        img_yue_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void findViews(){
        img_yue_return = findViewById(R.id.img_yue_return);
        tv_yue_xingming = findViewById(R.id.tv_yue_xingming);
        tv_yue_xuehao = findViewById(R.id.tv_yue_xuehao);
        tv_yue_yue = findViewById(R.id.tv_yue_yue);
    }
}