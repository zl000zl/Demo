package com.example.myapp.ui.smartparty.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.ui.smartparty.activity.KongActivity;


public class ComAdapter extends RecyclerView.Adapter<ComAdapter.InnerHoler> {
    private Context context;


    public ComAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public InnerHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerHoler(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_com, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHoler holder, int position) {
        holder.comContent.setText("内容："+ KongActivity.comment.get(position));
    }

    @Override
    public int getItemCount() {
        return KongActivity.comment.size();
    }

    public class InnerHoler extends RecyclerView.ViewHolder {
        private TextView comContent;
        public InnerHoler(@NonNull View itemView) {
            super(itemView);
            comContent = (TextView) itemView.findViewById(R.id.com_content);
        }
    }
}
