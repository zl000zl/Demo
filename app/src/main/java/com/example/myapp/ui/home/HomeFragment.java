package com.example.myapp.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp.Package.OnClickListener;
import com.example.myapp.Package.Touch;
import com.example.myapp.Park.ParkActivity;
import com.example.myapp.R;
import com.example.myapp.bank.Network;
import com.example.myapp.bank.OkResult;
import com.example.myapp.subway.City_subway;
import com.example.myapp.ui.home.Activity.News_xingq;
import com.example.myapp.ui.home.Activity.Search_content;
import com.example.myapp.ui.home.Adapter.FuwuAdapter;
import com.example.myapp.ui.home.Adapter.HotnewsAdapter;
import com.example.myapp.ui.home.Adapter.NewAdapter;
import com.example.myapp.ui.home.bean.Fuwu;
import com.example.myapp.ui.home.bean.News;
import com.example.myapp.ui.home.bean.News_type;
import com.example.myapp.ui.home.bean.Slideshow;
import com.example.myapp.ui.smartparty.activity.KongActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HomeFragment extends Fragment {

    private SearchView oneSearch;

    public static String Content;
    //公共的变量 自己搜索的内容与请求的内容相比较

    private Banner oneBanner;
    private RecyclerView oneRecycleView;
    private RecyclerView hotRecycleView;
    private TabLayout oneTab;
    private RecyclerView newsRecycleView;

    public static int BannerId;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        KongActivity.initData();
        Auto_login();
        initView(view);
        initSearchView();
        initBanner();
        initRecycle();
        initHotrecycle();
        initTab();
    }

    private void initTab() {
        LinearLayout linearLayout = (LinearLayout) oneTab.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerPadding(25);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.tab_line));
        Network.doGet("/prod-api/press/category/list", new OkResult() {
            @Override
            public void success(JSONObject jsonObject) {
                List<News_type> news_types = new Gson().fromJson(jsonObject.optString("data"),new TypeToken<List<News_type>>(){
                }.getType());

                for (News_type data: news_types){
                    oneTab.addTab(oneTab.newTab().setText(data.getName()).setTag(data.getId()));
                }
            }
        });
        oneTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

    private void loadData(Object typeId) {
        Network.doGet("/prod-api/press/press/list?type=" + typeId, new OkResult() {
            @Override
            public void success(JSONObject jsonObject) {
                List<News> news = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<News>>(){
                }.getType());
                NewAdapter newAdapter = new NewAdapter();
                newsRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
                newsRecycleView.setAdapter(newAdapter);
                newAdapter.setNews(news);
                newAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initHotrecycle() {
        Network.doGet("/prod-api/press/press/list?hot=Y", new OkResult() {
            @Override
            public void success(JSONObject jsonObject) {
                List<News> news = new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<News>>(){
                }.getType());

                HotnewsAdapter hotnewsAdapter = new HotnewsAdapter();
                hotRecycleView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                hotRecycleView.setAdapter(hotnewsAdapter);
                hotnewsAdapter.setNews(news);
                hotnewsAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initRecycle() {
        Comparator<Fuwu> comparator = new Comparator<Fuwu>() {
            @Override
            public int compare(Fuwu fuwu, Fuwu t1) {
                if (fuwu.getId() != t1.getId()) {
                    return t1.getId() - fuwu.getId();
                }else {
                    return fuwu.getId() - t1.getId();
                }
            }
        };
        Network.doGet("/prod-api/api/service/list", new OkResult() {
            @Override
            public void success(JSONObject jsonObject) {
                List<Fuwu> fuwus = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Fuwu>>() {
                }.getType());

                Collections.sort(fuwus,comparator);

                FuwuAdapter fuwuAdapter = new FuwuAdapter();
                oneRecycleView.setLayoutManager(new GridLayoutManager(getActivity(), 5));
                oneRecycleView.setAdapter(fuwuAdapter);

                fuwuAdapter.setFuwus(fuwus);
                fuwuAdapter.notifyDataSetChanged();
                fuwuAdapter.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        switch (position){
                            case 9:
                                Navigation.findNavController(view).navigate(R.id.navigation_dashboard);
                                break;
                        }
                        switch (fuwus.get(position).getServiceName()){
                            case "停哪儿":
                                startActivity(new Intent(getActivity(), ParkActivity.class));
                                break;
                        }
                    }
                });
            }
        });
    }

    private void initBanner() {
        Network.doGet("/prod-api/api/rotation/list?type=2", new OkResult() {
            @Override
            public void success(JSONObject jsonObject) {
                List<Slideshow> slideshows = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Slideshow>>() {
                }.getType());
                oneBanner.setImages(slideshows).setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Slideshow slideshow = (Slideshow) path;
                        Glide.with(context).load(Network.baseUrl + slideshow.getAdvImg()).into(imageView);
                    }
                });
                oneBanner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        BannerId = slideshows.get(position).getTargetId();
                        switch (position){
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

    private void initSearchView() {
        oneSearch.setIconified(true);
        oneSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        oneSearch.setSubmitButtonEnabled(true);
        oneSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Content = query;
                if (Touch.isFastDoubleClick()){
                    return false;
                }else {
                    startActivity(new Intent(getActivity(), Search_content.class));
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    private void Auto_login() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", "test01").put("password", "123456");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Network.doPost("/prod-api/api/login", jsonObject.toString(), new OkResult() {
            @Override
            public void success(JSONObject jsonObject) {
                if (jsonObject.optInt("code") == 200) {
                    Network.token = jsonObject.optString("token");
                    Toast.makeText(getActivity(), "登录成功！！！", Toast.LENGTH_SHORT).show();
                } else {
                }
            }
        });
    }

    private void initView(View getView) {
        oneSearch = (SearchView) getView.findViewById(R.id.one_search);
        oneBanner = (Banner) getView.findViewById(R.id.one_banner);
        oneRecycleView = (RecyclerView) getView.findViewById(R.id.one_recycleView);
        hotRecycleView = (RecyclerView) getView.findViewById(R.id.hot_recycleView);
        oneTab = (TabLayout) getView.findViewById(R.id.one_tab);
        newsRecycleView = (RecyclerView) getView.findViewById(R.id.news_recycleView);
    }
}