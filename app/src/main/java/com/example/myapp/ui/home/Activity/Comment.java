package com.example.myapp.ui.home.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.Package.BaseActivity;
import com.example.myapp.R;
import com.example.myapp.bank.Network;
import com.example.myapp.bank.OkResult;
import com.example.myapp.ui.home.Adapter.CommentAdapter;
import com.example.myapp.ui.home.bean.Comment_list;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class Comment extends BaseActivity {

    private TextView commentSum;
    private RecyclerView commentRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        setTitle("评论列表");
        initView();
        initData();
    }

    private void initData() {
        Network.doGet("/prod-api/press/comments/list", new OkResult() {
            @Override
            public void success(JSONObject jsonObject) {
                commentSum.setText("评论总数："+jsonObject.optString("total"));
                List<Comment_list> comment_lists = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<Comment_list>>(){
                }.getType());
                commentRecycle.setLayoutManager(new LinearLayoutManager(Comment.this));
                commentRecycle.setAdapter(new CommentAdapter(Comment.this,comment_lists));
            }
        });
    }

    private void initView() {
        commentSum = (TextView) findViewById(R.id.comment_sum);
        commentRecycle = (RecyclerView) findViewById(R.id.comment_recycle);
    }
}