package com.example.myapp.start;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapp.Package.BaseActivity;
import com.example.myapp.R;
import com.example.myapp.bank.Network;

public class Port_Activity extends BaseActivity {

    private EditText portEt;
    private Button portBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_port_);
        setTitle("网络设置");
        initView();
        initData();
    }

    private void initData() {
        portBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (portEt.getText().toString().isEmpty()){
                    Toast.makeText(Port_Activity.this, "请输入IP网络地址！！！", Toast.LENGTH_SHORT).show();
                }else {
                    Network.baseUrl = "http://"+portEt.getText().toString().trim();
                    Toast.makeText(Port_Activity.this, "保存成功！！！", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void initView() {
        portEt = (EditText) findViewById(R.id.port_et);
        portBtn = (Button) findViewById(R.id.port_btn);
    }
}