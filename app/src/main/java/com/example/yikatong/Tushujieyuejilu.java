package com.example.yikatong;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Tushujieyuejilu extends AppCompatActivity {
    TextView book_title;
    TabLayout tabLayout;
    ViewPager viewPager;
    List<Fragment> fgList = new ArrayList<>();
    List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tushujieyuejilu);

        book_title = findViewById(R.id.book_title);
        // 从assets加载字体文件
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/jyhphy-2.ttf");
        // 设置字体
        book_title.setTypeface(customFont);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String xuehao = bundle.getString("xuehao");

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        fgList.add(new TushujieyueFragment1());
        fgList.add(new TushujieyueFragment2());

        list.add("当前借阅");            	//往tab底部文字的list里添加文字
        list.add("借阅历史");

        FragmentAdapter fragmentAdapter = new FragmentAdapter(fgList, list, getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapter);    //为ViewPger添加适配器
        tabLayout.setupWithViewPager(viewPager); 	//把TabLayout与ViewPager绑定起来
    }
}