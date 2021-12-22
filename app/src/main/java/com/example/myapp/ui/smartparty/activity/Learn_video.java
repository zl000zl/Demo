package com.example.myapp.ui.smartparty.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.myapp.Package.BaseActivity;
import com.example.myapp.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class Learn_video extends BaseActivity {

    private VideoView videoView;
    private Banner videoBanner;

    private List<Integer> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_video);
        setTitle("视频播放");
        initView();
        initData();
        initBanner();
    }

    private void initBanner() {
        images.add(R.drawable.shengri);
        images.add(R.drawable.yiqing);
        images.add(R.drawable.tuanjie);

        videoBanner.setImages(images).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(imageView).load(path).into(imageView);
            }
        }).start();
    }

    private void initData() {
        MediaController localMediaController = new MediaController(this);
        videoView.setMediaController(localMediaController);
       switch (Learning.a){
           case 1:
               String uri = ("android.resource://"+getPackageName()+"/"+R.raw.video01);
               videoView.setVideoURI(Uri.parse(uri));
               videoView.start();
               break;
           case 2:
               String uri2 = ("android.resource://"+getPackageName()+"/"+R.raw.video02);
               videoView.setVideoURI(Uri.parse(uri2));
               videoView.start();
               break;
           case 3:
               String uri3 = ("android.resource://"+getPackageName()+"/"+R.raw.video03);
               videoView.setVideoURI(Uri.parse(uri3));
               videoView.start();
       }
    }

    private void initView() {
        videoView = (VideoView) findViewById(R.id.videoView);
        videoBanner = (Banner) findViewById(R.id.video_banner);
    }
}