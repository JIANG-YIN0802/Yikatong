package com.example.yikatong;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSONArray;
import com.example.yikatong.book.Tushujieyuejilu;
import com.example.yikatong.card_manage.Kapiangunali;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PersonalActivity extends AppCompatActivity {
    TextView person_name,person_id;
    ImageView person_set,person_touxiang;
    LinearLayout person_fukuanma,person_menjinma,person_xiaofeijilu,person_kapianguanli,person_tushujieyuejilu;
    List<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal);
        findViews();

        /*Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        person_id.setText(bundle.getString("xuehao"));   //将学号位置设置为登录的学号*/

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                String path = "http://10.201.70.178:8080/demo/getByXuehao";
                String xuehao = person_id.getText().toString();
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
                        if(users != null && !users.isEmpty()) {
                            User user = users.get(0);
                            person_name.setText(user.getName().toString());
                        }
                    }
                });
            }
        });

        person_name.setOnClickListener(new View.OnClickListener() {    //点名字查看个人资料，码为 1
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalActivity.this, personal_info.class);
                intent.putExtra("xuehao",person_id.getText().toString());
                startActivityForResult(intent, 1);
            }
        });

        person_fukuanma.setOnClickListener(new View.OnClickListener() {   //付款码
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalActivity.this, Fukuanma.class);
                intent.putExtra("xuehao",person_id.getText().toString());
                startActivity(intent);
            }
        });

        person_menjinma.setOnClickListener(new View.OnClickListener() {   //门禁码
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalActivity.this, menjinma.class);
                intent.putExtra("xingming",person_name.getText().toString());
                intent.putExtra("xuehao",person_id.getText().toString());
                startActivity(intent);
            }
        });

        person_kapianguanli.setOnClickListener(new View.OnClickListener() {   //卡片管理
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalActivity.this, Kapiangunali.class);
                intent.putExtra("xingming",person_name.getText().toString());
                intent.putExtra("xuehao",person_id.getText().toString());
                startActivity(intent);
            }
        });

        person_xiaofeijilu.setOnClickListener(new View.OnClickListener() {   //消费记录
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalActivity.this, com.example.yikatong.Xiaofeijilu.class);
                startActivity(intent);
            }
        });

        person_tushujieyuejilu.setOnClickListener(new View.OnClickListener() {   //图书借阅记录
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalActivity.this, Tushujieyuejilu.class);
                startActivity(intent);
            }
        });


    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){  //从用户注册界面返回
            if(resultCode == 1){  //注册成功返回
                if(data != null){
                    String url = data.getStringExtra("url");
                    displayImage(url);
                }
            }
        }
    }
    private void displayImage(String imagePath){
        if(imagePath != null){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            person_touxiang.setImageBitmap(bitmap);
        }else{
            Toast.makeText(this,"failed to get image",Toast.LENGTH_SHORT).show();
        }
    }
    private void findViews(){
        person_name = findViewById(R.id.person_name);
        person_id = findViewById(R.id.person_id);
        person_set = findViewById(R.id.person_set);
        person_fukuanma = findViewById(R.id.person_fukuanma);
        person_menjinma = findViewById(R.id.person_menjinma);
        person_xiaofeijilu = findViewById(R.id.person_xiaofeijilu);
        person_kapianguanli = findViewById(R.id.person_kapianguanli);
        person_tushujieyuejilu = findViewById(R.id.person_tushujieyuejilu);
        person_touxiang = findViewById(R.id.person_touxiang);
    }
}