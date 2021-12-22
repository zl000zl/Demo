package com.example.myapp.subway;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp.Package.BaseActivity;
import com.example.myapp.Package.OnClickListener;
import com.example.myapp.R;
import com.example.myapp.bank.Network;
import com.example.myapp.bank.OkResult;
import com.example.myapp.subway.activity.Subway_xingq;
import com.example.myapp.subway.adapter.StationAdapter;
import com.example.myapp.subway.bean.First_station;
import com.example.myapp.subway.bean.Subway_show;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONObject;

import java.util.List;

public class City_subway extends BaseActivity {

    private Banner subwayTu;
    private TextView subwayAddress;
    private RecyclerView subwayRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_subway);
        setTitle("城市地铁");
        initView();
        initBanner();
        initRecycle();
    }

    private void initRecycle() {
        Network.doGet("/prod-api/api/metro/list?currentName=建国门", new OkResult() {
            @Override
            public void success(JSONObject jsonObject) {
                List<First_station> first_stations = new Gson().fromJson(jsonObject.optString("data"),new TypeToken<List<First_station>>(){
                }.getType());
                subwayAddress.setText(""+first_stations.get(0).getCurrentName());

                StationAdapter stationAdapter = new StationAdapter();
                subwayRecycle.setLayoutManager(new LinearLayoutManager(City_subway.this));
                subwayRecycle.setAdapter(stationAdapter);
                stationAdapter.setFirst_stations(first_stations);
                stationAdapter.notifyDataSetChanged();
                stationAdapter.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Subway_xingq.lineId = first_stations.get(position).getLineId();
                        Subway_xingq.lineName = first_stations.get(position).getLineName();
                        startActivity(new Intent(City_subway.this,Subway_xingq.class));
                    }
                });
            }
        });
    }

    private void initBanner() {
        Network.doGet("/prod-api/api/metro/rotation/list", new OkResult() {
            @Override
            public void success(JSONObject jsonObject) {
                List<Subway_show> subway_shows = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<Subway_show>>(){
                }.getType());
                subwayTu.setImages(subway_shows).setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Subway_show subway_show = (Subway_show)path;
                        Glide.with(context).load(Network.baseUrl+subway_show.getAdvImg()).into(imageView);
                    }
                }).start();
            }
        });
    }

    private void initView() {
        subwayTu = (Banner) findViewById(R.id.subway_tu);
        subwayAddress = (TextView) findViewById(R.id.subway_address);
        subwayRecycle = (RecyclerView) findViewById(R.id.subway_recycle);
    }
}