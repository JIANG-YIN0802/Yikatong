package com.example.yikatong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Kapiangunali extends AppCompatActivity {
    ImageView img_kpreturn;
    LinearLayout ll_yuechaxun,ll_chongzhi,ll_guashi,ll_quxiaoguashi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_kapiangunali);
        findViews();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String xingming = bundle.getString("xingming");
        String xuehao = bundle.getString("xuehao");

        ll_yuechaxun.setOnClickListener(new View.OnClickListener() {   //余额查询
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Kapiangunali.this, Yuechaxun.class);
                intent.putExtra("xingming",xingming);
                intent.putExtra("xuehao",xuehao);
                startActivity(intent);
            }
        });

        ll_chongzhi.setOnClickListener(new View.OnClickListener() {   //充值
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Kapiangunali.this, Chongzhi.class);
                intent.putExtra("xingming",xingming);
                intent.putExtra("xuehao",xuehao);
                startActivity(intent);
            }
        });
        ll_guashi.setOnClickListener(new View.OnClickListener() {   //挂失
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Kapiangunali.this, Guashi.class);
                intent.putExtra("xingming",xingming);
                intent.putExtra("xuehao",xuehao);
                startActivity(intent);
            }
        });
        ll_quxiaoguashi.setOnClickListener(new View.OnClickListener() {   //取消挂失
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Kapiangunali.this, Quxiaoguashi.class);
                intent.putExtra("xingming",xingming);
                intent.putExtra("xuehao",xuehao);
                startActivity(intent);
            }
        });
        img_kpreturn.setOnClickListener(new View.OnClickListener() {  //返回
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void findViews(){
        img_kpreturn = findViewById(R.id.img_kpreturn);
        ll_yuechaxun = findViewById(R.id.ll_yuechaxun);
        ll_chongzhi = findViewById(R.id.ll_chongzhi);
        ll_guashi = findViewById(R.id.ll_guashi);
        ll_quxiaoguashi = findViewById(R.id.ll_quxiaoguashi);
    }
}