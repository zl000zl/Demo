package com.example.myapp.ui.dashboard.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp.Package.OnClickListener;
import com.example.myapp.R;
import com.example.myapp.bank.Network;
import com.example.myapp.bank.OkResult;
import com.example.myapp.subway.City_subway;
import com.example.myapp.ui.dashboard.bean.Service_list;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class LbAdapter extends RecyclerView.Adapter<LbAdapter.InnerHolder> {
    private String[] fuwuType = {"车主服务", "生活服务", "便民服务"};
    private Context context;

    public LbAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_big, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.bigTv.setText(""+fuwuType[position]);
        Network.doGet("/prod-api/api/service/list?serviceType="+fuwuType[position], new OkResult() {
            @Override
            public void success(JSONObject jsonObject) {
                List<Service_list> service_lists = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<Service_list>>(){
                }.getType());
                BigAdapter bigAdapter = new BigAdapter();
                holder.bigRecycle.setLayoutManager(new GridLayoutManager(context,3));
                holder.bigRecycle.setAdapter(bigAdapter);
                bigAdapter.setService_lists(service_lists);
                bigAdapter.notifyDataSetChanged();

                bigAdapter.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        switch (service_lists.get(position).getServiceName()){
                            case "城市地铁":
                                context.startActivity(new Intent(context, City_subway.class));
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return fuwuType.length;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {


        private TextView bigTv;
        private RecyclerView bigRecycle;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);


            bigTv = (TextView) itemView.findViewById(R.id.big_tv);
            bigRecycle = (RecyclerView) itemView.findViewById(R.id.big_recycle);
        }
    }
}
