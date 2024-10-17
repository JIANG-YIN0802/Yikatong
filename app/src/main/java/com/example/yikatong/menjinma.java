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

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;

public class menjinma extends AppCompatActivity {
    ImageView img_menjinma;
    TextView menjin_title,tv_mj_xuehao,tv_mj_xingming;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menjinma);

        findViews();
        // 从assets加载字体文件
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/jyhphy-2.ttf");
        // 设置字体
        menjin_title.setTypeface(customFont);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        tv_mj_xuehao.setText(bundle.getString("xuehao"));
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
        img_menjinma.setImageBitmap(bitmap);
    }
    private void findViews(){
        menjin_title= findViewById(R.id.menjin_title);
        img_menjinma = findViewById(R.id.img_menjinma);
        tv_mj_xuehao = findViewById(R.id.tv_mj_xuehao);
        tv_mj_xingming = findViewById(R.id.tv_mj_xingming);
    }
}