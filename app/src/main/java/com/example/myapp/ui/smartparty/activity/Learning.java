package com.example.myapp.ui.smartparty.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.myapp.Package.BaseActivity;
import com.example.myapp.R;

public class Learning extends BaseActivity {

    private ImageView zhangjie1;
    private ImageView zhangjie2;
    private ImageView zhangjie3;

    public static int a = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);
        setTitle("党员学习");
        initView();
        initData();
    }

    private void initData() {
        zhangjie1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=1;
                startActivity(new Intent(Learning.this,Learn_video.class));
            }
        });
        zhangjie2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=2;
                startActivity(new Intent(Learning.this,Learn_video.class));
            }
        });
        zhangjie3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=3;
                startActivity(new Intent(Learning.this,Learn_video.class));
            }
        });
    }

    private void initView() {
        zhangjie1 = (ImageView) findViewById(R.id.zhangjie1);
        zhangjie2 = (ImageView) findViewById(R.id.zhangjie2);
        zhangjie3 = (ImageView) findViewById(R.id.zhangjie3);
    }
}