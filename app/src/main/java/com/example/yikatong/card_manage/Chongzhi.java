package com.example.yikatong.card_manage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yikatong.R;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Chongzhi extends AppCompatActivity {
    ImageView img_cz_return;
    TextView tv_cz_xingming,tv_cz_xuehao;
    EditText et_cz_qian;
    Button bt_cz_queren;
    Boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chongzhi);

        findViews();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String xingming = bundle.getString("xingming");
        String xuehao = bundle.getString("xuehao");
        tv_cz_xingming.setText(xingming);
        tv_cz_xuehao.setText(xuehao);

        bt_cz_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient okHttpClient = new OkHttpClient();
                        String path = "http://10.201.70.178:8080/demo/updateBalance";
                        String xuehao = tv_cz_xuehao.getText().toString();
                        String balance = et_cz_qian.getText().toString();
                        FormBody formBody = new FormBody.Builder()
                                .add("xuehao", xuehao)
                                .add("balance", balance)
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
                                    Toast.makeText(Chongzhi.this, "充值失败", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Chongzhi.this, "充值成功", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }
        });
        img_cz_return.setOnClickListener(new View.OnClickListener() {  //返回
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void findViews(){
        img_cz_return = findViewById(R.id.img_cz_return);
        tv_cz_xingming = findViewById(R.id.tv_cz_xingming);
        tv_cz_xuehao = findViewById(R.id.tv_cz_xuehao);
        et_cz_qian = findViewById(R.id.et_cz_qian);
        bt_cz_queren = findViewById(R.id.bt_cz_queren);
    }
}