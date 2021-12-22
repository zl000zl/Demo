package com.example.myapp.ui.smartparty;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp.R;
import com.example.myapp.ui.smartparty.adapter.PModuleAdapter;
import com.example.myapp.ui.smartparty.adapter.PhotAdapter;
import com.example.myapp.ui.smartparty.adapter.WshowAdapter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class PartyFragment extends Fragment {


    private Banner partyBanner;
    private RecyclerView partyModule;
    private RecyclerView partyShow;
    private RecyclerView partyStudy;

    private List<Integer> images = new ArrayList<>();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.party_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
        initMoudle();
        initHot();
        initTab();
    }

    private void initHot() {
        partyShow.setLayoutManager(new GridLayoutManager(getActivity(),2));
        partyShow.setAdapter(new PhotAdapter(getActivity()));
    }

    private void initTab() {
        partyStudy.setLayoutManager(new LinearLayoutManager(getActivity()));
        partyStudy.setAdapter(new WshowAdapter(getActivity()));
    }

    private void initMoudle() {
        partyModule.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        partyModule.setAdapter(new PModuleAdapter(getActivity()));
    }

    private void initData() {
        images.add(R.drawable.party1);
        images.add(R.drawable.party2);
        images.add(R.drawable.party3);
        images.add(R.drawable.party4);
        images.add(R.drawable.party5);
        partyBanner.setImages(images).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(imageView).load(path).into(imageView);
            }
        }).start();
    }

    private void initView(View getView) {
        partyBanner = (Banner) getView.findViewById(R.id.party_banner);
        partyModule = (RecyclerView) getView.findViewById(R.id.party_module);
        partyStudy = (RecyclerView) getView.findViewById(R.id.party_study);
        partyShow = (RecyclerView) getView.findViewById(R.id.party_show);
    }
}