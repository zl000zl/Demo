package com.example.myapp.ui.smartparty.activity;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.Package.BaseActivity;
import com.example.myapp.R;
import com.example.myapp.ui.smartparty.adapter.ComAdapter;

public class Party_comment extends BaseActivity {

    private RecyclerView comRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_comment);
        setTitle("评论详情");
        initView();
    }

    private void initView() {
        comRecycle = (RecyclerView) findViewById(R.id.com_recycle);
        comRecycle.setLayoutManager(new LinearLayoutManager(Party_comment.this));
        comRecycle.setAdapter(new ComAdapter(Party_comment.this));
    }
}