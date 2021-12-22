package com.example.myapp.ui.Center;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.myapp.R;
import com.example.myapp.bank.Network;
import com.example.myapp.bank.OkResult;
import com.example.myapp.start.Enter_Activity;
import com.example.myapp.ui.Center.activity.Center_order;
import com.example.myapp.ui.Center.activity.Change_password;
import com.example.myapp.ui.Center.activity.Feedback;
import com.example.myapp.ui.Center.activity.Information;
import com.example.myapp.ui.Center.bean.User;
import com.google.gson.Gson;

import org.json.JSONObject;

public class CenterFragment extends Fragment {

    private TextView centerUser;
    private TextView centerXinxi;
    private TextView centerOrder;
    private TextView centerPassword;
    private TextView centerOpinion;
    private ImageView centerPhoto;
    private Button back;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.center_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
        initClick();
    }

    private void initClick() {
        centerXinxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Information.class));
            }
        });
        centerPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Change_password.class));
            }
        });
        centerOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Center_order.class));
            }
        });
        centerOpinion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Feedback.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                Network.token=null;
                startActivity(new Intent(getActivity(),Enter_Activity.class));
            }
        });
    }

    private void initData() {
        Network.doGet("/prod-api/api/common/user/getInfo", new OkResult() {
            @Override
            public void success(JSONObject jsonObject) {
                User user = new Gson().fromJson(jsonObject.optString("user"), User.class);
                Glide.with(centerPhoto).load(Network.baseUrl + "/prod-api/profile/upload/2021/12/15/c08e28a9-8b7f-4cf6-ab67-06fdc81f245b.jpg").into(centerPhoto);
                centerUser.setText("" + user.getNickName());
            }
        });
    }

    private void initView(View itemView) {
        centerUser = (TextView) itemView.findViewById(R.id.center_user);
        centerXinxi = (TextView) itemView.findViewById(R.id.center_xinxi);
        centerOrder = (TextView) itemView.findViewById(R.id.center_order);
        centerPassword = (TextView) itemView.findViewById(R.id.center_password);
        centerOpinion = (TextView) itemView.findViewById(R.id.center_opinion);
        centerPhoto = (ImageView) itemView.findViewById(R.id.center_photo);
        back = (Button) itemView.findViewById(R.id.back);
    }
}