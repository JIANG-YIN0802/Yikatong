package com.example.yikatong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class CircleImageView extends AppCompatImageView {

    private Paint mPaint;
    private Path mPath;
    public CircleImageView(@NonNull Context context) {
        super(context);
        init();
    }
    public CircleImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public CircleImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        int radius = Math.min(width, height) / 2;
        // 清除默认的矩形区域
        canvas.clipPath(mPath);
        // 重新计算路径
        mPath.reset();
        mPath.addCircle(width / 2, height / 2, radius, Path.Direction.CW);
        // 绘制圆角矩形
        canvas.clipPath(mPath);
        super.onDraw(canvas);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int width = getWidth();
        int height = getHeight();
        int radius = Math.min(width, height) / 2;
        mPath.addCircle(width / 2, height / 2, radius, Path.Direction.CW);
    }
}
