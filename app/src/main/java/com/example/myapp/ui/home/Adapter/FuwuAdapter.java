package com.example.myapp.ui.home.Adapter;

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
import com.example.myapp.ui.home.bean.Fuwu;

import java.util.List;

public class FuwuAdapter extends RecyclerView.Adapter<FuwuAdapter.InnerHolder> {
    private List<Fuwu> fuwus;
    private OnClickListener onClickListener;


    public List<Fuwu> getFuwus() {
        return fuwus;
    }

    public void setFuwus(List<Fuwu> fuwus) {
        this.fuwus = fuwus;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fuwu, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        if (position==9){
            holder.fuwuImage.setImageResource(R.drawable.fuwu);
            holder.fuwuTitle.setText("更多服务");
        }else {
            Glide.with(holder.fuwuImage).load(Network.baseUrl+fuwus.get(position).getImgUrl()).into(holder.fuwuImage);
            holder.fuwuTitle.setText(""+fuwus.get(position).getServiceName());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener!=null){
                    onClickListener.onClick(view,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView fuwuImage;
        private TextView fuwuTitle;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            fuwuImage = (ImageView) itemView.findViewById(R.id.fuwu_image);
            fuwuTitle = (TextView) itemView.findViewById(R.id.fuwu_title);
        }
    }
}
