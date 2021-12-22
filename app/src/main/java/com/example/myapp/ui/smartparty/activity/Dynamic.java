package com.example.myapp.ui.smartparty.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.Package.BaseActivity;
import com.example.myapp.R;
import com.example.myapp.ui.smartparty.adapter.WshowAdapter;

public class Dynamic extends BaseActivity {

    private RecyclerView micRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);
        setTitle("党建动态");
        initView();
        initMic();
    }

    private void initMic() {
        micRecycle.setLayoutManager(new LinearLayoutManager(Dynamic.this));
        micRecycle.setAdapter(new WshowAdapter(Dynamic.this));
    }

    private void initView() {
        micRecycle = (RecyclerView) findViewById(R.id.mic_recycle);
    }
}