package com.example.myapp.Park;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapp.Package.BaseActivity;
import com.example.myapp.R;

public class ParkActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park);
        setTitle("停车场");
    }
}