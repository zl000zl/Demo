package com.example.myapp.subway.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.Package.OnClickListener;
import com.example.myapp.R;
import com.example.myapp.subway.bean.Subway_xq;
import com.google.gson.Gson;

public class XqAdapter extends RecyclerView.Adapter<XqAdapter.InnerHolder> {
    private Subway_xq subway_xq;
    private OnClickListener onClickListener;


    public Subway_xq getSubway_xq() {
        return subway_xq;
    }

    public void setSubway_xq(Subway_xq subway_xq) {
        this.subway_xq = subway_xq;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_xingq, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.xqAddress.setText(""+subway_xq.getMetroStepList().get(position).getName());
        if (subway_xq.getRunStationsName().equals(subway_xq.getMetroStepList().get(position).getName())){
            //把车辆运行站的名称和你取到的全部站点的名称相比较 相同的就显示
            holder.xqTingkao.setVisibility(View.VISIBLE);
        }else {
            holder.xqTingkao.setVisibility(View.INVISIBLE);
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
        return subway_xq.getMetroStepList().size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView xqTingkao;
        private TextView xqAddress;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            xqTingkao = (ImageView) itemView.findViewById(R.id.xq_tingkao);
            xqAddress = (TextView) itemView.findViewById(R.id.xq_address);
        }
    }
}
