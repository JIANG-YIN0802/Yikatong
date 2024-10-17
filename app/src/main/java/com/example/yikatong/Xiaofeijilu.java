package com.example.yikatong;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Xiaofeijilu extends AppCompatActivity {
    TextView consume_title;
    private List<Xiaofei> list = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xiaofeijilu);

        consume_title = findViewById(R.id.consume_title);
        // 从assets加载字体文件
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/jyhphy-2.ttf");
        // 设置字体
        consume_title.setTypeface(customFont);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String xuehao = bundle.getString("xuehao");

        init();
        RecyclerView recyclerView = findViewById(R.id.rv_xiaofeijilu);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        XiaofeijiluAdapter adapter = new XiaofeijiluAdapter(list);
        recyclerView.setAdapter(adapter);
    }
    private void init(){
        list.add(new Xiaofei("手撕鸡","2024-10-13 12:00","-¥16.9"));
        list.add(new Xiaofei("黄焖鸡","2024-10-15 12:10","-¥12"));
    }
}