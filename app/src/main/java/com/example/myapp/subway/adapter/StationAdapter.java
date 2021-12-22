package com.example.myapp.subway.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.Package.OnClickListener;
import com.example.myapp.R;
import com.example.myapp.subway.bean.First_station;

import java.util.List;

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.InnerHolder> {
    private List<First_station> first_stations;
    private OnClickListener onClickListener;


    public List<First_station> getFirst_stations() {
        return first_stations;
    }

    public void setFirst_stations(List<First_station> first_stations) {
        this.first_stations = first_stations;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_swstation, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.stationLine.setText("线路："+first_stations.get(position).getLineName());
        holder.stationTime.setText(""+first_stations.get(position).getReachTime()+"分钟后");
        holder.stationName.setText("下一站："+first_stations.get(position).getNextStep().getName());
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
        return first_stations.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView stationLine;
        private TextView stationTime;
        private TextView stationName;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            stationLine = (TextView) itemView.findViewById(R.id.station_line);
            stationTime = (TextView) itemView.findViewById(R.id.station_time);
            stationName = (TextView) itemView.findViewById(R.id.station_name);
        }
    }
}
