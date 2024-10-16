package com.example.yikatong;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Xiaofeijilu extends AppCompatActivity {
    private List<Xiaofei> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xiaofeijilu);

        init();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_xiaofeijilu);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        XiaofeijiluAdapter adapter = new XiaofeijiluAdapter(list);
        recyclerView.setAdapter(adapter);
    }
    private void init(){
        list.add(new Xiaofei("手撕鸡","2024-10-13 12:00","-¥16.9"));
    }
}