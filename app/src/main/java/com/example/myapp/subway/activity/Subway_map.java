package com.example.myapp.subway.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapp.Package.BaseActivity;
import com.example.myapp.R;
import com.example.myapp.bank.Network;
import com.example.myapp.bank.OkResult;
import com.example.myapp.subway.bean.Map;
import com.google.gson.Gson;

import org.json.JSONObject;

public class Subway_map extends BaseActivity {

    private ImageView subwayMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subway_map);
        setTitle("线路总览");
        initView();
        initData();
    }

    private void initData() {
        Network.doGet("/prod-api/api/metro/city", new OkResult() {
            @Override
            public void success(JSONObject jsonObject) {
                Map map = new Gson().fromJson(jsonObject.optString("data"),Map.class);
                Glide.with(subwayMap).load(Network.baseUrl+map.getImgUrl()).into(subwayMap);
            }
        });
    }

    private void initView() {
        subwayMap = (ImageView) findViewById(R.id.subway_map);
    }
}