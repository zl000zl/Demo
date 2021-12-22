package com.example.myapp.ui.smartparty.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;

import java.util.ArrayList;
import java.util.List;

public class PhotAdapter extends RecyclerView.Adapter<PhotAdapter.InnerHolder> {
    private Context context;
    private List<Integer> images = new ArrayList<>();
    private String[] content = {"在线学习，谁学谁没学，学多学少，一目了然", "互动大屏，创新会议模式，惊艳现场"};


    public PhotAdapter(Context context) {
        this.context = context;
        images.add(R.drawable.xuexi);
        images.add(R.drawable.daping);
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.wzImage.setImageResource(images.get(position));
        holder.wzContent.setText(""+content[position]);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView wzImage;
        private TextView wzContent;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            wzImage = (ImageView) itemView.findViewById(R.id.wz_image);
            wzContent = (TextView) itemView.findViewById(R.id.wz_content);
        }
    }
}
