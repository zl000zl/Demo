package com.example.myapp.ui.home.Adapter;

import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp.Package.OnClickListener;
import com.example.myapp.R;
import com.example.myapp.bank.Network;
import com.example.myapp.ui.home.bean.News;

import java.util.List;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.InnerHolder> {
    private List<News> news;
    private OnClickListener onClickListener;

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new InnerHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Glide.with(holder.newsImage).load(Network.baseUrl+news.get(position).getCover()).into(holder.newsImage);
        holder.newsTitle.setText(""+news.get(position).getTitle());
        holder.newsContent.setText(Html.fromHtml(""+news.get(position).getContent(),1));
        if (news.get(position).getCommentNum()==null){
            holder.newsSum.setText("评论总数："+"0");
        }else {
            holder.newsSum.setText("评论总数："+news.get(position).getCommentNum());
        }
        holder.newsDate.setText("发布时间："+news.get(position).getPublishDate());
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView newsImage;
        private TextView newsTitle;
        private TextView newsContent;
        private TextView newsSum;
        private TextView newsDate;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            newsImage = (ImageView) itemView.findViewById(R.id.news_image);
            newsTitle = (TextView) itemView.findViewById(R.id.news_title);
            newsContent = (TextView) itemView.findViewById(R.id.news_content);
            newsSum = (TextView) itemView.findViewById(R.id.news_sum);
            newsDate = (TextView) itemView.findViewById(R.id.news_date);
        }
    }
}
