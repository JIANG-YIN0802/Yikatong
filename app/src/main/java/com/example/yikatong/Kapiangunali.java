package com.example.yikatong;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
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

        Intent intent1 = getIntent();
        Bundle bundle = intent1.getExtras();
        card_xuehao.setText(bundle.getString("xuehao"));


        ll_chongzhi.setOnClickListener(new View.OnClickListener() {   //充值
            @Override
            public void onClick(View v) {
                showChargeDialog();
            }
        });
        ll_guashi.setOnClickListener(new View.OnClickListener() {   //挂失
            @Override
            public void onClick(View v) {
                if(card_state.getText().toString().equals("正常")){
                    showLossDialog(card_name.getText().toString(),card_xuehao.getText().toString());
                }else{
                    showMarkedDialog("您的卡片当前正处于挂失状态，请勿重复挂失");
                }
            }
        });
        ll_quxiaoguashi.setOnClickListener(new View.OnClickListener() {   //取消挂失
            @Override
            public void onClick(View v) {
                if(card_state.getText().toString().equals("挂失")){
                    Intent intent = new Intent(Kapiangunali.this, Card_Loss.class);
                    intent.putExtra("name",card_name.getText().toString());
                    intent.putExtra("xuehao",card_xuehao.getText().toString());
                    intent.putExtra("state","2");
                    startActivity(intent);
                }else{
                    showMarkedDialog("您的卡片当前正处于正常状态");
                }
            }
        });
    }
    public void showChargeDialog(){   //充值面板
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("充值");
        builder.setMessage("请输入充值金额：");
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        EditText input = new EditText(this);
        input.setHint("输入金额");
        layout.addView(input);
        builder.setView(layout);
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 获取用户输入的文本
//                Editable inputText = input.getText();
//                String inputStr = inputText != null ? inputText.toString() : "";
                //数据库操作待处理
                Toast.makeText(Kapiangunali.this,"充值成功",Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showLossDialog(String name, String xuehao){  //挂失面板
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请确认挂失信息");
        builder.setMessage("姓名："+name+"\n"+"学号："+xuehao);
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Kapiangunali.this, Card_Loss.class);
                intent.putExtra("name",card_name.getText().toString());
                intent.putExtra("xuehao",card_xuehao.getText().toString());
                intent.putExtra("state","1");
                startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void showMarkedDialog(String str){   //提示语面板
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage(str);
        builder.setPositiveButton("确定", null);
        AlertDialog dialog = builder.create();
        dialog.show();
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