package com.example.myapp.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp.R;
import com.example.myapp.bank.Network;
import com.example.myapp.bank.OkResult;
import com.example.myapp.ui.home.Activity.News_xingq;
import com.example.myapp.ui.home.Adapter.NewAdapter;
import com.example.myapp.ui.home.HomeFragment;
import com.example.myapp.ui.home.bean.News;
import com.example.myapp.ui.home.bean.News_type;
import com.example.myapp.ui.home.bean.Slideshow;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONObject;

import java.util.List;

public class NotificationsFragment extends Fragment {

    private Banner notiBanner;
    private RecyclerView notiRecycle;
    private TabLayout notiTab;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initBanner();
        initTablayout();
    }

    private void initTablayout() {
        LinearLayout linearLayout = (LinearLayout)notiTab.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerPadding(25);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.tab_line));
        Network.doGet("/prod-api/press/category/list", new OkResult() {
            @Override
            public void success(JSONObject jsonObject) {
                List<News_type> news_types = new Gson().fromJson(jsonObject.optString("data"),new TypeToken<List<News_type>>(){
                }.getType());

                for (News_type data: news_types){
                    notiTab.addTab(notiTab.newTab().setText(data.getName()).setTag(data.getId()));
                }
            }
        });
        notiTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String TypeId = tab.getTag().toString();
                loadData(TypeId);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void loadData(String typeId) {
        Network.doGet("/prod-api/press/press/list?type=" + typeId, new OkResult() {
            @Override
            public void success(JSONObject jsonObject) {
                List<News> news = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<News>>(){
                }.getType());
                NewAdapter newAdapter = new NewAdapter();
                notiRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
                notiRecycle.setAdapter(newAdapter);
                newAdapter.setNews(news);
                newAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initBanner() {
        Network.doGet("/prod-api/api/rotation/list?type=2", new OkResult() {
            @Override
            public void success(JSONObject jsonObject) {
                List<Slideshow> slideshows = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Slideshow>>() {
                }.getType());
                notiBanner.setImages(slideshows).setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Slideshow slideshow = (Slideshow) path;
                        Glide.with(imageView).load(Network.baseUrl + slideshow.getAdvImg()).into(imageView);
                    }
                });
                notiBanner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        HomeFragment.BannerId = slideshows.get(position).getTargetId();
                        switch (position) {
                            case 0:
                            case 1:
                            case 2:
                                startActivity(new Intent(getActivity(), News_xingq.class));
                                break;
                        }
                    }
                }).start();
            }
        });
    }

    private void initView(View getView) {
        notiBanner = (Banner) getView.findViewById(R.id.noti_banner);
        notiRecycle = (RecyclerView) getView.findViewById(R.id.noti_recycle);
        notiTab = (TabLayout) getView.findViewById(R.id.noti_tab);
    }
}