package com.example.yikatong.card_manage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yikatong.R;

public class Guashi extends AppCompatActivity {
    ImageView img_gs_return;
    TextView tv_gs_xingming,tv_gs_xuehao;
    Button bt_guahsi;
    Boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_guashi);


        findViews();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String xingming = bundle.getString("xingming");
        String xuehao = bundle.getString("xuehao");
        tv_gs_xingming.setText(xingming);
        tv_gs_xuehao.setText(xuehao);

        bt_guahsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //查看卡片当前状态，如何判断是挂失还是取消挂失
            }
        });
        img_gs_return.setOnClickListener(new View.OnClickListener() {  //返回键
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void findViews(){
        img_gs_return = findViewById(R.id.img_gs_return);
        tv_gs_xingming = findViewById(R.id.tv_gs_xingming);
        tv_gs_xuehao = findViewById(R.id.tv_gs_xuehao);
        bt_guahsi = findViewById(R.id.bt_guahsi);
    }
}