package com.example.yikatong;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class PersonalActivity extends AppCompatActivity {
    TextView tv_person,person_name, person_xuehao;
    LinearLayout person_gerenxinxi, person_fukuanma, person_menjinma, person_xiaofeijilu, person_kapianguanli, person_tushujieyue;
    com.example.yikatong.CircleImageView person_touxiang;
    List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal);
        findViews();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        person_xuehao.setText(bundle.getString("xuehao"));

        // 从assets加载字体文件
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/jyhphy-2.ttf");
        // 设置字体
        tv_person.setTypeface(customFont);

        person_gerenxinxi.setOnClickListener(new View.OnClickListener() {   //个人信息查看
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalActivity.this, personal_info.class);
                intent.putExtra("xuehao",person_xuehao.getText().toString());
                startActivity(intent);
            }
        });
        person_fukuanma.setOnClickListener(new View.OnClickListener() {    //付款码
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalActivity.this, Fukuanma.class);
                intent.putExtra("xuehao",person_xuehao.getText().toString());
                startActivity(intent);
            }
        });
        person_menjinma.setOnClickListener(new View.OnClickListener() {     //门禁码
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalActivity.this, menjinma.class);
                intent.putExtra("xuehao",person_xuehao.getText().toString());
                startActivity(intent);
            }
        });
        person_xiaofeijilu.setOnClickListener(new View.OnClickListener() {  //消费记录
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalActivity.this, Xiaofeijilu.class);
                intent.putExtra("xuehao",person_xuehao.getText().toString());
                startActivity(intent);
            }
        });
        person_kapianguanli.setOnClickListener(new View.OnClickListener() {    //卡片管理
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalActivity.this, Kapiangunali.class);
                intent.putExtra("xuehao",person_xuehao.getText().toString());
                startActivity(intent);
            }
        });
        person_tushujieyue.setOnClickListener(new View.OnClickListener() {  //图书借阅
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalActivity.this, Tushujieyuejilu.class);
                intent.putExtra("xuehao",person_xuehao.getText().toString());
                startActivity(intent);
            }
        });
        /*Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        person_id.setText(bundle.getString("xuehao"));   //将学号位置设置为登录的学号*/

//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        executor.submit(new Runnable() {
//            @Override
//            public void run() {
//                OkHttpClient okHttpClient = new OkHttpClient();
//                String path = "http://10.201.70.178:8080/demo/getByXuehao";
//                String xuehao = tv_person_xuehao.getText().toString();
//                FormBody formBody = new FormBody.Builder().add("xuehao", xuehao).build();
//                Request request = new Request.Builder()
//                        .url(path)
//                        .post(formBody)
//                        .build();
//                users = null;
//                try (Response response = okHttpClient.newCall(request).execute()) {
//                    if (!response.isSuccessful()) {
//                        throw new IOException("Unexpected code " + response);
//                    }
//                    users = JSONArray.parseArray(response.body().string(), User.class);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if(users != null && !users.isEmpty()) {
//                            User user = users.get(0);
//                            tv_person_name.setText(user.getName().toString());
//                        }
//                    }
//                });
//            }
//        });

//        person_fukuanma.setOnClickListener(new View.OnClickListener() {   //付款码
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(PersonalActivity.this, Fukuanma.class);
//                intent.putExtra("xuehao",tv_person_xuehao.getText().toString());
//                startActivity(intent);
//            }
//        });

//        person_menjinma.setOnClickListener(new View.OnClickListener() {   //门禁码
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(PersonalActivity.this, menjinma.class);
//                intent.putExtra("xingming",tv_person_name.getText().toString());
//                intent.putExtra("xuehao",tv_person_xuehao.getText().toString());
//                startActivity(intent);
//            }
//        });

//        person_kapianguanli.setOnClickListener(new View.OnClickListener() {   //卡片管理
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(PersonalActivity.this, Kapiangunali.class);
//                intent.putExtra("xingming",tv_person_name.getText().toString());
//                intent.putExtra("xuehao",tv_person_xuehao.getText().toString());
//                startActivity(intent);
//            }
//        });

//        person_xiaofeijilu.setOnClickListener(new View.OnClickListener() {   //消费记录
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(PersonalActivity.this, com.example.yikatong.Xiaofeijilu.class);
//                startActivity(intent);
//            }
//        });
//
//        person_tushujieyue.setOnClickListener(new View.OnClickListener() {   //图书借阅记录
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(PersonalActivity.this, Tushujieyuejilu.class);
//                startActivity(intent);
//            }
//        });
//
//
//    }
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 1){  //从用户注册界面返回
//            if(resultCode == 1){  //注册成功返回
//                if(data != null){
//                    String url = data.getStringExtra("url");
//                    displayImage(url);
//                }
//            }
//        }
//    }
//    private void displayImage(String imagePath){
//        if(imagePath != null){
//            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
//            person_touxiang.setImageBitmap(bitmap);
//        }else{
//            Toast.makeText(this,"failed to get image",Toast.LENGTH_SHORT).show();
//        }
    }

    private void findViews() {
        tv_person = findViewById(R.id.tv_person);
        person_name = findViewById(R.id.person_name);
        person_xuehao = findViewById(R.id.tv_person_xuehao);
        person_gerenxinxi = findViewById(R.id.person_gerenxinxi);
        person_fukuanma = findViewById(R.id.person_fukuanma);
        person_menjinma = findViewById(R.id.person_menjinma);
        person_xiaofeijilu = findViewById(R.id.person_xiaofeijilu);
        person_kapianguanli = findViewById(R.id.person_kapianguanli);
        person_tushujieyue = findViewById(R.id.person_tushujieyue);
        person_touxiang = findViewById(R.id.person_touxiang);
    }
}