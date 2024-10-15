package com.example.yikatong;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.alibaba.fastjson.JSONArray;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class personal_info extends AppCompatActivity {
    ImageView img_info_touxiang,img_info_return;
    TextView tv_info_xingming,tv_info_xuehao,tv_info_xingbie,tv_info_phone,tv_info_sushe;
    String xingming,xingbie,phone,sushe;
    Button bt_info_huantouxiang;
    int flag = 0;
    String img_url;
    List<User> users;
    public static final int CHOOSE_PHOTO = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal_info);

        findViews();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        tv_info_xuehao.setText(bundle.getString("xuehao"));

//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        executor.submit(new Runnable() {
//            @Override
//            public void run() {
//                OkHttpClient okHttpClient = new OkHttpClient();
//                String path = "http://10.201.70.178:8080/demo/getByXuehao";
//                String xuehao = tv_info_xuehao.getText().toString();
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
//                        if (users == null || users.isEmpty()) {
//                            Toast.makeText(personal_info.this, "信息获取失败", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(personal_info.this, "信息获取成功", Toast.LENGTH_SHORT).show();
//                            User user = users.get(0);
//                            xingming = user.getName().toString();
//                            xingbie = user.getGender().toString();
//                            phone = user.getPhone();
//                            sushe = user.getSushe();
//                            tv_info_xingming.setText(xingming);
//                            tv_info_xingbie.setText(xingbie);
//                            tv_info_phone.setText(phone);
//                            tv_info_sushe.setText(sushe);
//                        }
//                    }
//                });
//            }
//        });

        img_info_return.setOnClickListener(new View.OnClickListener() {   //返回:如果换了头像就返回头像照片所在的路径
            @Override
            public void onClick(View v) {
                if(flag != 0){
                    Intent intent = new Intent();
                    intent.putExtra("url", img_url);
                    setResult(1, intent);
                    finish();
                }
                finish();
            }
        });

        bt_info_huantouxiang.setOnClickListener(new View.OnClickListener() {  //相册换头像
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(personal_info.this,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.
                        PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(personal_info.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE} , 1);
                }else{
                    flag = 1;
                    openAlbum();
                }
            }
        });
}

    private void openAlbum(){     //打开相册
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CHOOSE_PHOTO){
            if(resultCode ==RESULT_OK){
                if(Build.VERSION.SDK_INT >= 19){
                    handleImageOnKitKat(data);
                }else{
                    handleImageBeforeKitKat(data);
                }
            }
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data){
        String imagePath = null;
        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(this,uri)){
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contenturi = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath = getImagePath(contenturi, null);
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())){
            imagePath = getImagePath(uri, null);
        }else if("file".equalsIgnoreCase(uri.getScheme())){
            imagePath = uri.getPath();
        }
        img_url = imagePath;
        displayImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }
    @SuppressLint("Range")
    private  String getImagePath(Uri uri, String selection){
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath){
        if(imagePath != null){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            img_info_touxiang.setImageBitmap(bitmap);
        }else{
            Toast.makeText(this,"failed to get image",Toast.LENGTH_SHORT).show();
        }
    }
    private void findViews(){
        img_info_touxiang = findViewById(R.id.img_info_touxiang);
        tv_info_xingming = findViewById(R.id.tv_info_xingming);
        tv_info_xuehao = findViewById(R.id.tv_info_xuehao);
        tv_info_xingbie = findViewById(R.id.tv_info_xingbie);
        tv_info_phone = findViewById(R.id.tv_info_phone);
        tv_info_sushe = findViewById(R.id.tv_info_sushe);
        bt_info_huantouxiang = findViewById(R.id.bt_info_huantouxiang);
        img_info_return = findViewById(R.id.img_info_return);
    }
}