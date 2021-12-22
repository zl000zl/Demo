package com.example.myapp.ui.dashboard.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.Package.OnClickListener;
import com.example.myapp.R;
import com.example.myapp.ui.dashboard.DashboardFragment;


public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.InnerHolder> {
    private String[] serviceType = {"车主服务", "生活服务", "便民服务"};
    private OnClickListener onClickListener;

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_servicetype, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.serviceName.setText(""+serviceType[position]);
        if (position==0) holder.itemView.setBackgroundColor(Color.WHITE);
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
        return serviceType.length;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView serviceName;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            serviceName = (TextView) itemView.findViewById(R.id.service_name);
        }
    }
}
