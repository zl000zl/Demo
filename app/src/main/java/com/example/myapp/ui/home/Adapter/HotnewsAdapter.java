package com.example.myapp.ui.home.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp.Package.OnClickListener;
import com.example.myapp.R;
import com.example.myapp.bank.Network;
import com.example.myapp.ui.home.bean.News;

import java.util.List;

public class HotnewsAdapter extends RecyclerView.Adapter<HotnewsAdapter.InnerHolder> {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotnews, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Glide.with(holder.hotnewsImage).load(Network.baseUrl+news.get(position).getCover()).into(holder.hotnewsImage);
        holder.hotnewsTitle.setText(""+news.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView hotnewsImage;
        private TextView hotnewsTitle;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            hotnewsImage = (ImageView) itemView.findViewById(R.id.hotnews_image);
            hotnewsTitle = (TextView) itemView.findViewById(R.id.hotnews_title);
        }
    }
}
