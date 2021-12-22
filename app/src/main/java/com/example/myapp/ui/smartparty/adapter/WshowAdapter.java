package com.example.myapp.ui.smartparty.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.ui.smartparty.activity.Mic_xingqing;

import java.util.ArrayList;
import java.util.List;

public class WshowAdapter extends RecyclerView.Adapter<WshowAdapter.InnerHolder> {
    private Context context;
    private List<Integer> images = new ArrayList<>();
    private String[] content = {"高效督促党员自觉参与学习", "高效传达组织声音给基层", "思想汇报不再只是面对面"
            , "聚点构面，聚力打造党建工作新舞台", "条块联动，“四化”绘制党务工作新景象", "融合融入，“着力”引领党建工作新风尚"};


    public WshowAdapter(Context context) {
        this.context = context;
        images.add(R.drawable.a1);
        images.add(R.drawable.a2);
        images.add(R.drawable.a3);
        images.add(R.drawable.a4);
        images.add(R.drawable.a5);
        images.add(R.drawable.a6);
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wshow, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.wshowImage.setImageResource(images.get(position));
        holder.wshowContent.setText(""+content[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Mic_xingqing.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView wshowImage;
        private TextView wshowContent;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            wshowImage = (ImageView) itemView.findViewById(R.id.wshow_image);
            wshowContent = (TextView) itemView.findViewById(R.id.wshow_content);
        }
    }
}
