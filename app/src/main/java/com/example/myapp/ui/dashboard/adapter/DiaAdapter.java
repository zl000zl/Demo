package com.example.myapp.ui.dashboard.adapter;

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
import com.example.myapp.ui.dashboard.bean.Service_list;

import java.util.List;

public class DiaAdapter extends RecyclerView.Adapter<DiaAdapter.InnerHolder> {
    private List<Service_list> service_lists;
    private OnClickListener onClickListener;


    public List<Service_list> getService_lists() {
        return service_lists;
    }

    public void setService_lists(List<Service_list> service_lists) {
        this.service_lists = service_lists;
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
        return new InnerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dia, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Glide.with(holder.diaImage).load(Network.baseUrl+service_lists.get(position).getImgUrl()).into(holder.diaImage);
        holder.diaName.setText(""+service_lists.get(position).getServiceName());
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
        return service_lists.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView diaImage;
        private TextView diaName;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            diaImage = (ImageView) itemView.findViewById(R.id.dia_image);
            diaName = (TextView) itemView.findViewById(R.id.dia_name);
        }
    }
}
