package com.example.yikatong;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSONArray;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TextView tv_login_regist,tv_login_forgetpsw,tv_login_agreement,tv_login_privacy,tv_welcome;
    EditText et_login_id,et_login_psw;
//    List<User> users;
    Button bt_login;
    CheckBox cb_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        findViews();

        // 从assets加载字体文件
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/Rainbow-Party-2.ttf");
        // 设置字体
        tv_welcome.setTypeface(customFont);


        bt_login.setOnClickListener(new View.OnClickListener() {  //登录（待完善）
            @Override
            public void onClick(View view) {
                if(!(et_login_id.getText().toString().trim().isEmpty() || et_login_psw.getText().toString().trim().isEmpty()) ){
                    if(cb_login.isChecked()){
                        /*
                         * 在检查完输入框的内容不为空后应该还要请求服务器检查数据库中是否有该用户信息，待完成
                         * */
                        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, PersonalActivity.class);
                        intent.putExtra("xuehao",et_login_id.getText().toString());
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this,"请阅读并同意用户协议与隐私政策",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "请完整填写学号或密码信息", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*bt_login.setOnClickListener(new View.OnClickListener() {   //登录
            @Override
            public void onClick(View v) {
                if(cb_login.isChecked()){
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    executor.submit(new Runnable() {
                        @Override
                        public void run() {
                            OkHttpClient okHttpClient = new OkHttpClient();
                            String path = "http://10.201.70.178:8080/demo/getByXuehaoAndPassword";
                            String xuehao = et_login_id.getText().toString();
                            String psw = et_login_psw.getText().toString();
                            FormBody formBody = new FormBody.Builder()
                                    .add("xuehao", xuehao)
                                    .add("password", psw)
                                    .build();
                            Request request = new Request.Builder()
                                    .url(path)
                                    .post(formBody)
                                    .build();
                            users = null;
                            try (Response response = okHttpClient.newCall(request).execute()) {
                                users = JSONArray.parseArray(response.body().string(), User.class);
                            } catch (IOException e) {
                                e.printStackTrace();
                                return;
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (users.size() == 0) {
                                        Toast.makeText(MainActivity.this, "用户不存在或密码错误", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MainActivity.this, PersonalActivity.class);
                                        intent.putExtra("xuehao",et_login_id.getText().toString());
                                        startActivity(intent);
                                        // 在这里可以执行登录成功后的其他操作，比如跳转到其他页面
                                    }
                                }
                            });
                        }
                    });

                }else{
                    Toast.makeText(MainActivity.this,"请阅读并同意用户协议与隐私政策",Toast.LENGTH_LONG).show();
                }
            }
        });*/


        tv_login_regist.setOnClickListener(new View.OnClickListener() {  //用户注册
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Regist.class);
                startActivityForResult(intent,1);
            }
        });

        tv_login_forgetpsw.setOnClickListener(new View.OnClickListener() {  //忘记密码
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Wangjimima.class);
                startActivityForResult(intent,2);
            }
        });

        tv_login_agreement.setOnClickListener(new View.OnClickListener() {  //用户协议
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Yonghufuwuxiwyi.class);
                startActivity(intent);
            }
        });

        tv_login_privacy.setOnClickListener(new View.OnClickListener() {  //隐私协议
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Yonghufuwuxiwyi.class);
                startActivity(intent);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){   //从注册/重置的Activity获取数据
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){  //从用户注册界面返回
            if(resultCode == 1){  //注册成功返回
                if(data != null){
                    et_login_id.setText(data.getStringExtra("userid"));
                    et_login_psw.setText(data.getStringExtra("psw"));
                }
            }
        }else if(requestCode == 2){     //忘记密码界面返回
            if(resultCode == 1){
                if(data != null){
                    et_login_id.setText(data.getStringExtra("userid"));
                    et_login_psw.setText(data.getStringExtra("psw"));
                }
            }else{
                Toast.makeText(MainActivity.this,"取消密码重置",Toast.LENGTH_LONG).show();
            }
        }
    }
    @SuppressLint("WrongViewCast")
    private void findViews(){
        tv_login_regist = findViewById(R.id.tv_login_regist);
        tv_login_forgetpsw = findViewById(R.id.tv_login_forgetpsw);
        tv_login_agreement = findViewById(R.id.tv_login_agreement);
        tv_login_privacy = findViewById(R.id.tv_login_privacy);
        et_login_id = findViewById(R.id.et_login_id);
        et_login_psw = findViewById(R.id.et_login_psw);
        bt_login = findViewById(R.id.bt_login);
        cb_login = findViewById(R.id.cb_login);
        tv_welcome = findViewById(R.id.tv_welcome);
    }
}