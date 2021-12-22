package com.example.myapp.ui.home.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.Package.BaseActivity;
import com.example.myapp.R;
import com.example.myapp.bank.Network;
import com.example.myapp.bank.OkResult;
import com.example.myapp.ui.home.Adapter.NewAdapter;
import com.example.myapp.ui.home.HomeFragment;
import com.example.myapp.ui.home.bean.News;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class Search_content extends BaseActivity {

    private RecyclerView searchRecycle;
    private TextView searchTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_content);
        setTitle("搜索内容");
        initView();
        initData();
    }

    private void initData() {
        Network.doGet("/prod-api/press/press/list?title=" + HomeFragment.Content, new OkResult() {
            @Override
            public void success(JSONObject jsonObject) {
                List<News> news = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<News>>(){
                }.getType());
                if (news.size()!=0){
                    NewAdapter newAdapter = new NewAdapter();
                    searchRecycle.setLayoutManager(new LinearLayoutManager(Search_content.this));
                    searchRecycle.setAdapter(newAdapter);
                    newAdapter.setNews(news);
                    newAdapter.notifyDataSetChanged();
                }else {
                    searchRecycle.setVisibility(View.GONE);
                    searchTitle.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initView() {
        searchRecycle = (RecyclerView) findViewById(R.id.search_recycle);
        searchTitle = (TextView) findViewById(R.id.search_title);
    }
}