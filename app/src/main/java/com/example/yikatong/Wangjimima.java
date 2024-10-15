package com.example.yikatong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.alibaba.fastjson.JSONArray;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Wangjimima extends AppCompatActivity {
    EditText et_wj_xuehao,et_wj_psw1,et_wj_psw2;
    Button bt_wj_queding,bt_wj_quxiao;
    Boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_wangjimima);

        findViews();

        bt_wj_queding.setOnClickListener(new View.OnClickListener() {   //确定重置
            @Override
            public void onClick(View v) {
                if(!(et_wj_xuehao.getText().toString().equals("")) && !(et_wj_psw1.getText().toString().equals(""))){
                    if(et_wj_psw1.getText().toString().equals(et_wj_psw2.getText().toString())){
                        ExecutorService executor = Executors.newSingleThreadExecutor();
                        executor.submit(new Runnable() {
                            @Override
                            public void run() {
                                OkHttpClient okHttpClient = new OkHttpClient();
                                String path = "http://10.201.70.178:8080/demo/updatePassword";
                                String xuehao = et_wj_xuehao.getText().toString();
                                String psw = et_wj_psw1.getText().toString();
                                FormBody formBody = new FormBody.Builder()
                                        .add("xuehao", xuehao)
                                        .add("password", psw)
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
                                            Toast.makeText(Wangjimima.this, "重置密码失败", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(Wangjimima.this, "重置密码成功", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent();
                                            intent.putExtra("userid", et_wj_xuehao.getText().toString());
                                            intent.putExtra("psw",et_wj_psw1.getText().toString());
                                            setResult(1, intent);
                                            finish();
                                        }
                                    }
                                });
                            }
                        });
                    }else{
                        Toast.makeText(Wangjimima.this,"两次输入的密码不一致！！！",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(Wangjimima.this,"请输入完整信息！！",Toast.LENGTH_LONG).show();
                }
            }
        });
        bt_wj_quxiao.setOnClickListener(new View.OnClickListener() {    //取消并返回
            @Override
            public void onClick(View v) {
                setResult(2);
                finish();
            }
        });
    }
    private void findViews(){
        et_wj_xuehao = findViewById(R.id.et_wj_xuehao);
        et_wj_psw1 = findViewById(R.id.et_wj_psw1);
        et_wj_psw2 = findViewById(R.id.et_wj_psw2);
        bt_wj_queding = findViewById(R.id.bt_wj_queding);
        bt_wj_quxiao = findViewById(R.id.bt_wj_quxiao);
    }
}