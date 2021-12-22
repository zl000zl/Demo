package com.example.myapp.ui.smartparty.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp.Package.OnClickListener;
import com.example.myapp.R;
import com.example.myapp.bank.Network;
import com.example.myapp.ui.smartparty.activity.Activity_bm;
import com.example.myapp.ui.smartparty.activity.Advice;
import com.example.myapp.ui.smartparty.activity.Dynamic;
import com.example.myapp.ui.smartparty.activity.Learning;
import com.example.myapp.ui.smartparty.activity.Photo;

import java.util.ArrayList;
import java.util.List;

public class PModuleAdapter extends RecyclerView.Adapter<PModuleAdapter.InnerHolder> {
    private List<Integer> module_images = new ArrayList<>();
    private String[] title = {"党建动态","党员学习","组织活动","建言献策","随手拍"};
    private Context context;

    public PModuleAdapter(Context context) {
        this.context = context;
        module_images.add(R.drawable.dongtai);
        module_images.add(R.drawable.activity);
        module_images.add(R.drawable.xiance);
        module_images.add(R.drawable.lean);
        module_images.add(R.drawable.pai);
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pmodule, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.pImage.setImageResource(module_images.get(position));
        holder.pName.setText(title[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (title[position]){
                    case "党建动态":
                        context.startActivity(new Intent(context, Dynamic.class));
                        break;
                    case "党员学习":
                        context.startActivity(new Intent(context, Learning.class));
                        break;
                    case "组织活动":
                        context.startActivity(new Intent(context, Activity_bm.class));
                        break;
                    case "建言献策":
                        context.startActivity(new Intent(context, Advice.class));
                        break;
                    case "随手拍":
                        context.startActivity(new Intent(context, Photo.class));
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView pImage;
        private TextView pName;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            pImage = (ImageView) itemView.findViewById(R.id.p_image);
            pName = (TextView) itemView.findViewById(R.id.p_name);
        }
    }
}
