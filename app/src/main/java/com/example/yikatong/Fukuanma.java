package com.example.yikatong;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.alibaba.fastjson.JSONArray;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Fukuanma extends AppCompatActivity {
    ImageView img_fukuanma;
    TextView fukuan_title,tv_fk_xingming,tv_fk_xuehao,tv_fk_yue;
    List<User> users;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fukuanma);

        findViews();
        // 从assets加载字体文件
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/jyhphy-2.ttf");
        // 设置字体
        fukuan_title.setTypeface(customFont);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        tv_fk_xuehao.setText(bundle.getString("xuehao"));
    }
    private void create_QR_code(String xuehao) throws WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        // 生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
        Hashtable<EncodeHintType, String> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix matrix = new MultiFormatWriter().encode(xuehao, BarcodeFormat.QR_CODE, 240, 240, hints);
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        // 二维矩阵转为一维像素数组,也就是一直横着排了
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = 0xff000000;
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        // 通过像素数组生成bitmap,具体参考api
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        img_fukuanma.setImageBitmap(bitmap);
    }

    private void findViews(){
        fukuan_title = findViewById(R.id.fukuan_title);
        img_fukuanma = findViewById(R.id.img_fukuanma);
        tv_fk_xingming = findViewById(R.id.tv_fk_xingming);
        tv_fk_xuehao = findViewById(R.id.tv_fk_xuehao);
        tv_fk_yue = findViewById(R.id.tv_fk_yue);
    }
}