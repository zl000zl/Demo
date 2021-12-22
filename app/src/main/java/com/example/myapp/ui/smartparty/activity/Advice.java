package com.example.myapp.ui.smartparty.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapp.Package.BaseActivity;
import com.example.myapp.R;

import java.util.ArrayList;
import java.util.List;

public class Advice extends BaseActivity {

    private EditText adEt;
    private Button adBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        setTitle("建言献策");
        initView();
        initData();
    }

    private void initData() {
        adBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adEt.setText("");
                Toast.makeText(Advice.this, "发表成功！！！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        adEt = (EditText) findViewById(R.id.ad_et);
        adBtn = (Button) findViewById(R.id.ad_btn);
    }
}