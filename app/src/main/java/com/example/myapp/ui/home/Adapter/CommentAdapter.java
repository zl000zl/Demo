package com.example.myapp.ui.home.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp.R;
import com.example.myapp.bank.Network;
import com.example.myapp.ui.home.bean.Comment_list;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.InnerHolder> {
    private Context context;
    private List<Comment_list> comment_lists;


    public CommentAdapter(Context context, List<Comment_list> comment_lists) {
        this.context = context;
        this.comment_lists = comment_lists;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commnet, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.userName.setText("用户名："+comment_lists.get(position).getUserName());
        holder.userDate.setText("发表："+comment_lists.get(position).getCommentDate());
        holder.userContent.setText("内容："+comment_lists.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return comment_lists.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView userName;
        private TextView userDate;
        private TextView userContent;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            userName = (TextView) itemView.findViewById(R.id.user_name);
            userDate = (TextView) itemView.findViewById(R.id.user_date);
            userContent = (TextView) itemView.findViewById(R.id.user_content);
        }
    }
}
