package com.example.myapp.ui.home.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp.Package.BaseActivity;
import com.example.myapp.R;
import com.example.myapp.bank.Network;
import com.example.myapp.bank.OkResult;
import com.example.myapp.ui.home.Adapter.NewAdapter;
import com.example.myapp.ui.home.HomeFragment;
import com.example.myapp.ui.home.bean.News;
import com.example.myapp.ui.home.bean.News_xq;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class News_xingq extends BaseActivity {

    private TextView xingqTitle;
    private ImageView xingqImage;
    private TextView xingqContent;
    private RecyclerView tjRecycle;
    private EditText xingqEt;
    private ImageView commentMuch;
    private Button xingqSend;

    private News_xq news_xq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_xingq);
        setTitle("新闻详情");
        initView();
        initData();
        initComment();
        initSend();
        initTuijian();
    }

    private void initTuijian() {
        Network.doGet("/prod-api/press/press/list", new OkResult() {
            @Override
            public void success(JSONObject jsonObject) {
                List<News> news = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<News>>(){
                }.getType());
                NewAdapter newAdapter = new NewAdapter();
                tjRecycle.setLayoutManager(new LinearLayoutManager(News_xingq.this));
                tjRecycle.setAdapter(newAdapter);
                newAdapter.setNews(news.subList(0,3));
                newAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initSend() {
        xingqSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Id = news_xq.getId();
                String content = xingqEt.getText().toString().trim();

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("newsId",Id).put("content",content);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Network.doPost("/prod-api/press/pressComment", jsonObject.toString(), new OkResult() {
                    @Override
                    public void success(JSONObject jsonObject) {
                        if (jsonObject.optInt("code")==200){
                            Toast.makeText(News_xingq.this, "评论成功，请点击更多查看！！！", Toast.LENGTH_SHORT).show();
                            xingqEt.setText("");
                        }else {
                            Toast.makeText(News_xingq.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void initComment() {
        commentMuch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(News_xingq.this, Comment.class));
            }
        });
    }

    private void initData() {
        Network.doGet("/prod-api/press/press/" + HomeFragment.BannerId, new OkResult() {
            @Override
            public void success(JSONObject jsonObject) {
                news_xq = new Gson().fromJson(jsonObject.optString("data"), News_xq.class);
                xingqTitle.setText("" + news_xq.getTitle());
                Glide.with(xingqImage).load(Network.baseUrl + news_xq.getCover()).into(xingqImage);
                xingqContent.setText(Html.fromHtml("内容：" + news_xq.getContent()));
            }
        });
    }

    private void initView() {
        xingqTitle = (TextView) findViewById(R.id.xingq_title);
        xingqImage = (ImageView) findViewById(R.id.xingq_image);
        xingqContent = (TextView) findViewById(R.id.xingq_content);
        tjRecycle = (RecyclerView) findViewById(R.id.tj_recycle);
        xingqEt = (EditText) findViewById(R.id.xingq_et);
        commentMuch = (ImageView) findViewById(R.id.comment_much);
        xingqSend = (Button) findViewById(R.id.xingq_send);
    }
}