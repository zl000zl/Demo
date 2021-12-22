package com.example.myapp.subway.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.Package.BaseActivity;
import com.example.myapp.Package.OnClickListener;
import com.example.myapp.R;
import com.example.myapp.bank.Network;
import com.example.myapp.bank.OkResult;
import com.example.myapp.subway.adapter.XqAdapter;
import com.example.myapp.subway.bean.Subway_xq;
import com.example.myapp.ui.home.bean.News;
import com.google.gson.Gson;

import org.json.JSONObject;

public class Subway_xingq extends BaseActivity {
    public static String lineName;
    public static int lineId;

    private TextView xqStart;
    private TextView xqEnd;
    private TextView xqTime;
    private TextView xqJuli;
    private RecyclerView xqRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subway_xingq);
        setTitle("" + lineName);
        initView();
        initData();
    }

    private void initData() {
        Network.doGet("/prod-api/api/metro/line/"+lineId, new OkResult() {
            @Override
            public void success(JSONObject jsonObject) {
               Subway_xq subway_xq = new Gson().fromJson(jsonObject.optString("data"),Subway_xq.class);
                xqStart.setText(""+subway_xq.getFirst());
                xqEnd.setText(""+subway_xq.getEnd());
                xqTime.setText(""+subway_xq.getRemainingTime()+"分钟");
                xqJuli.setText(""+subway_xq.getStationsNumber()+"站"+subway_xq.getKm()+"Km");

                XqAdapter xqAdapter = new XqAdapter();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Subway_xingq.this);
                linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                xqRecycle.setLayoutManager(linearLayoutManager);
                xqRecycle.setAdapter(xqAdapter);
                xqAdapter.setSubway_xq(subway_xq);
                xqAdapter.notifyDataSetChanged();
                xqAdapter.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        startActivity(new Intent(Subway_xingq.this,Subway_map.class));
                    }
                });
            }
        });
    }

    private void initView() {
        xqStart = (TextView) findViewById(R.id.xq_start);
        xqEnd = (TextView) findViewById(R.id.xq_end);
        xqTime = (TextView) findViewById(R.id.xq_time);
        xqJuli = (TextView) findViewById(R.id.xq_juli);
        xqRecycle = (RecyclerView) findViewById(R.id.xq_recycle);
    }
}