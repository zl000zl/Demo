package com.example.myapp.ui.smartparty.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapp.Package.BaseActivity;
import com.example.myapp.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class Activity_bm extends BaseActivity {

    private Banner bmBanner;
    private EditText bmEt2;
    private EditText bmEt1;
    private EditText bmEt3;
    private EditText bmEt4;
    private Button bmBtn;

    private List<Integer> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bm);
        setTitle("活动报名");
        initView();
        initData();
    }

    private void initData() {
        images.add(R.drawable.b1);
        images.add(R.drawable.b2);
        images.add(R.drawable.b3);
        images.add(R.drawable.b4);
        images.add(R.drawable.b5);
        images.add(R.drawable.b6);

        bmBanner.setImages(images).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(imageView).load(path).into(imageView);
            }
        }).start();
        bmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bmEt1.setText("");
                bmEt2.setText("");
                bmEt3.setText("");
                bmEt4.setText("");
                Toast.makeText(Activity_bm.this, "报名成功！！！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        bmBanner = (Banner) findViewById(R.id.bm_banner);
        bmEt2 = (EditText) findViewById(R.id.bm_et2);
        bmEt1 = (EditText) findViewById(R.id.bm_et1);
        bmEt3 = (EditText) findViewById(R.id.bm_et3);
        bmEt4 = (EditText) findViewById(R.id.bm_et4);
        bmBtn = (Button) findViewById(R.id.bm_btn);
    }
}