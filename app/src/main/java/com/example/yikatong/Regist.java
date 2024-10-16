package com.example.yikatong;

import static com.alibaba.fastjson.JSON.parseArray;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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

public class Regist extends AppCompatActivity {
    TextView tv_zc_regist;
    EditText et_zc_xuehao,et_zc_xingming,et_zc_age,et_zc_phone,et_zc_sushe,et_zc_psw1,et_zc_psw2;
    Button bt_zc_chongzhi,bt_zc_quxiao,bt_zc_queding;
    RadioButton zc_sex1,zc_sex2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_regist);
        findViews();

        // 从assets加载字体文件
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/Rainbow-Party-2.ttf");
        // 设置字体
        tv_zc_regist.setTypeface(customFont);

        bt_zc_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(et_zc_xuehao.getText().toString().equals("")) && !(et_zc_psw1.getText().toString().equals(""))){
                    if(et_zc_psw1.getText().toString().equals(et_zc_psw2.getText().toString())){
                        // 创建一个单线程的 ExecutorService
                        ExecutorService executor = Executors.newSingleThreadExecutor();
                        // 提交一个 Runnable 任务到 ExecutorService
                        executor.submit(new Runnable() {
                            @Override
                            public void run() {
                                OkHttpClient okHttpClient = new OkHttpClient();
                                String path = "http://10.201.70.178:8080/demo/add";
                                String xuehao = et_zc_xuehao.getText().toString();
                                String name = et_zc_xingming.getText().toString();
                                String gender;
                                if(zc_sex1.isChecked())
                                    gender = "男";
                                else
                                    gender = "女";
                                String age = et_zc_age.getText().toString();
                                String phone = et_zc_phone.getText().toString();
                                String sushe = et_zc_sushe.getText().toString();
                                String balance = "0";
                                String cardstate = "TRUE";
                                String password = et_zc_psw1.getText().toString();
                                FormBody formBody = new FormBody.Builder()
                                        .add("xuehao", xuehao)
                                        .add("name", name)
                                        .add("gender", gender)
                                        .add("age", age)
                                        .add("phone", phone)
                                        .add("sushe", sushe)
                                        .add("balance", balance)
                                        .add("cardstate", cardstate)
                                        .add("password", password)
                                        .build();
                                Request request = new Request.Builder()
                                        .url(path)
                                        .post(formBody)
                                        .build();
                                try (Response response = okHttpClient.newCall(request).execute()) {
                                    Looper.prepare();
                                    if (Boolean.parseBoolean(response.body().string()))
                                    {
                                        Toast.makeText(Regist.this, "注册成功", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent();
                                        intent.putExtra("userid", et_zc_xuehao.getText().toString());
                                        intent.putExtra("psw",et_zc_psw1.getText().toString());
                                        setResult(1, intent);
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(Regist.this, "注册失败", Toast.LENGTH_SHORT).show();
                                    }
                                    Looper.loop();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });executor.shutdown();
                    }else{
                        Toast.makeText(Regist.this,"两次输入的密码不一致！！！",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(Regist.this,"请输入完整信息！！",Toast.LENGTH_LONG).show();
                }
            }
        });

        bt_zc_chongzhi.setOnClickListener(new View.OnClickListener() {    //重置按钮
            @Override
            public void onClick(View v) {
                et_zc_xuehao.setText("");
                et_zc_xingming.setText("");
                zc_sex1.setChecked(false);
                zc_sex2.setChecked(false);
                et_zc_phone.setText("");
                et_zc_sushe.setText("");
                et_zc_psw1.setText("");
                et_zc_psw2.setText("");
            }
        });

        bt_zc_quxiao.setOnClickListener(new View.OnClickListener() {   //取消按钮
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void findViews(){
        et_zc_xuehao = findViewById(R.id.et_zc_xuehao);
        et_zc_xingming = findViewById(R.id.et_zc_xingming);
        et_zc_age = findViewById(R.id.et_zc_age);
        et_zc_phone = findViewById(R.id.et_zc_phone);
        et_zc_sushe = findViewById(R.id.et_zc_sushe);
        et_zc_psw1 = findViewById(R.id.et_zc_psw1);
        et_zc_psw2 = findViewById(R.id.et_zc_psw2);
        bt_zc_queding = findViewById(R.id.bt_zc_queding);
        bt_zc_chongzhi = findViewById(R.id.bt_zc_chongzhi);
        bt_zc_quxiao = findViewById(R.id.bt_zc_quxiao);
        zc_sex1 = findViewById(R.id.zc_sex1);
        zc_sex2 = findViewById(R.id.zc_sex2);
        tv_zc_regist = findViewById(R.id.tv_zc_regist);
    }
}