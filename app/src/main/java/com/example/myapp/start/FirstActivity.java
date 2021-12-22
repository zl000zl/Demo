package com.example.myapp.start;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapp.R;

public class FirstActivity extends AppCompatActivity {

    private ImageView startImage;
    private TextView startTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        getSupportActionBar().hide();
        initView();
        initData();
    }

    private void initData() {
        Glide.with(startImage).load("http://192.168.1.103:10001/prod-api/profile/upload/image/2021/05/06/d2aeef1a-7c47-46bc-8534-20b3d14cd8eb.png")
                .into(startImage);

        CountDownTimer timer = new CountDownTimer(2000,1000) {
            @Override
            public void onTick(long millisInFuture) {
               startTv.setText(""+millisInFuture / 1000);
            }

            @Override
            public void onFinish() {

            }
        }.start();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(FirstActivity.this,GuideActivity.class));
                finish();
            }
        },1000);
    }

    private void initView() {
        startImage = (ImageView) findViewById(R.id.start_image);
        startTv = (TextView) findViewById(R.id.start_tv);
    }
}