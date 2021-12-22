package com.example.myapp.ui.smartparty.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapp.R;

import java.util.ArrayList;
import java.util.List;

public class KongActivity extends AppCompatActivity {
    public static List<String> comment = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kong);
        initData();
    }

    public static void initData() {
        comment.add("很帮！！！");
    }
}