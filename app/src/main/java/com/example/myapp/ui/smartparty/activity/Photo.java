package com.example.myapp.ui.smartparty.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapp.Package.BaseActivity;
import com.example.myapp.R;

public class Photo extends BaseActivity {

    private ImageView photoImage;
    private Button photoBtn;

    public static final int TAKE_PHONE = 1;
    public static final int CHOOSE_PHONE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        setTitle("随手拍");
        initView();
        initData();

    }

    private void initData() {
        photoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (ContextCompat.checkSelfPermission(Photo.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                   ActivityCompat.requestPermissions(Photo.this,new String[]{Manifest.permission.CAMERA},1);
               }else {
                   startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE),999);
               }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       if (resultCode == RESULT_OK){
           photoImage.setImageBitmap((Bitmap) data.getExtras().get("data"));
       }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==1 && grantResults.length >0){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startActivityForResult(new Intent(MediaStore.ACTION_VIDEO_CAPTURE),999);
            }else Toast.makeText(this, "获取权限失败！！！", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        photoImage = (ImageView) findViewById(R.id.photo_image);
        photoBtn = (Button) findViewById(R.id.photo_btn);
    }
}