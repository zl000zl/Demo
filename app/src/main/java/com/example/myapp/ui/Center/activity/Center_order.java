package com.example.myapp.ui.Center.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.Package.BaseActivity;
import com.example.myapp.R;

public class Center_order extends BaseActivity {

    private TextView nonPayment;
    private TextView havePayment;
    private RecyclerView centerRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_order);
        setTitle("我的订单");
        initView();
        initData();
    }

    private void initData() {
        havePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nonPayment.setBackgroundColor(Color.WHITE);
                nonPayment.setTextColor(Color.rgb(33,150,243));

                havePayment.setBackgroundColor(Color.rgb(33,150,243));
                havePayment.setTextColor(Color.WHITE);
            }
        });
        nonPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                havePayment.setBackgroundColor(Color.WHITE);
                havePayment.setTextColor(Color.rgb(33,150,243));

                nonPayment.setBackgroundColor(Color.rgb(33,150,243));
                nonPayment.setTextColor(Color.WHITE);
            }
        });
    }

    private void initView() {
        nonPayment = (TextView) findViewById(R.id.non_payment);
        havePayment = (TextView) findViewById(R.id.have_payment);
        centerRecycle = (RecyclerView) findViewById(R.id.center_recycle);
    }
}