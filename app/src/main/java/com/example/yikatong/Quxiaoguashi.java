package com.example.yikatong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Quxiaoguashi extends AppCompatActivity {
    ImageView img_qxgs_return;
    TextView tv_qxgs_xingming,tv_qxgs_xuehao;
    Button bt_qxgs_queren;
    Boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quxiaoguashi);


        findViews();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String xingming = bundle.getString("xingming");
        String xuehao = bundle.getString("xuehao");
        tv_qxgs_xingming.setText(xingming);
        tv_qxgs_xuehao.setText(xuehao);

        bt_qxgs_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient okHttpClient = new OkHttpClient();
                        String path = "http://10.201.70.178:8080/demo/updateCardstate";
                        String xuehao = tv_qxgs_xuehao.getText().toString();
                        String state = "TRUE";
                        FormBody formBody = new FormBody.Builder()
                                .add("xuehao", xuehao)
                                .add("cardstate", state)
                                .build();
                        Request request = new Request.Builder()
                                .url(path)
                                .post(formBody)
                                .build();
                        try (Response response = okHttpClient.newCall(request).execute()) {
                            flag = Boolean.parseBoolean(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!flag) {
                                    Toast.makeText(Quxiaoguashi.this, "取消挂失失败", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Quxiaoguashi.this, "取消挂失成功", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }
        });
        img_qxgs_return.setOnClickListener(new View.OnClickListener() {   //返回
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void findViews(){
        img_qxgs_return = findViewById(R.id.img_qxgs_return);
        tv_qxgs_xingming = findViewById(R.id.tv_qxgs_xingming);
        tv_qxgs_xuehao = findViewById(R.id.tv_qxgs_xuehao);
        bt_qxgs_queren = findViewById(R.id.bt_qxgs_queren);
    }
}