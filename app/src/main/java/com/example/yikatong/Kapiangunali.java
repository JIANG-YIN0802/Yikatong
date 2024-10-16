package com.example.yikatong;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Kapiangunali extends AppCompatActivity {
    TextView card_title,card_name,card_xuehao,card_money,card_state;
    LinearLayout ll_chongzhi,ll_guashi,ll_quxiaoguashi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_kapiangunali);
        findViews();

        // 从assets加载字体文件
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/jyhphy-2.ttf");
        // 设置字体
        card_title.setTypeface(customFont);
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        String xingming = bundle.getString("xingming");
//        String xuehao = bundle.getString("xuehao");


        ll_chongzhi.setOnClickListener(new View.OnClickListener() {   //充值
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Kapiangunali.this, Chongzhi.class);
//                intent.putExtra("xingming",xingming);
//                intent.putExtra("xuehao",xuehao);
                startActivity(intent);
            }
        });
        ll_guashi.setOnClickListener(new View.OnClickListener() {   //挂失
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Kapiangunali.this, Guashi.class);
//                intent.putExtra("xingming",xingming);
//                intent.putExtra("xuehao",xuehao);
                startActivity(intent);
            }
        });
        ll_quxiaoguashi.setOnClickListener(new View.OnClickListener() {   //取消挂失
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Kapiangunali.this, Quxiaoguashi.class);
//                intent.putExtra("xingming",xingming);
//                intent.putExtra("xuehao",xuehao);
                startActivity(intent);
            }
        });
    }
    private void findViews(){
        card_title = findViewById(R.id.card_title);
        card_name = findViewById(R.id.card_name);
        card_xuehao = findViewById(R.id.card_xuehao);
        card_money = findViewById(R.id.card_money);
        card_state = findViewById(R.id.card_state);
        ll_chongzhi = findViewById(R.id.ll_chongzhi);
        ll_guashi = findViewById(R.id.ll_guashi);
        ll_quxiaoguashi = findViewById(R.id.ll_quxiaoguashi);
    }
}