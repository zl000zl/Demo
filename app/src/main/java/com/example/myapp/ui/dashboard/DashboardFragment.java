package com.example.myapp.ui.dashboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.Package.OnClickListener;
import com.example.myapp.Package.Touch;
import com.example.myapp.R;
import com.example.myapp.bank.Network;
import com.example.myapp.bank.OkResult;
import com.example.myapp.subway.City_subway;
import com.example.myapp.ui.dashboard.adapter.DiaAdapter;
import com.example.myapp.ui.dashboard.adapter.LbAdapter;
import com.example.myapp.ui.dashboard.adapter.ServiceAdapter;
import com.example.myapp.ui.dashboard.bean.Service_list;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class DashboardFragment extends Fragment {

    private SearchView dashSearch;
    private RecyclerView dashList;
    private RecyclerView dashRecycle;
    private TextView dialogContent;
    private RecyclerView dialogRecycle;

    private int currentIndex = 0;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
        Fuwu_list();
        initSearch();
    }

    private void initSearch() {
        dashSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        dashSearch.setIconified(true);
        dashSearch.setSubmitButtonEnabled(true);
        dashSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (Touch.isFastDoubleClick()) {
                    return false;
                } else {
                    get_dialog(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    private void get_dialog(String query) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_dialog, null);
        builder.setView(view);

        Network.doGet("/prod-api/api/service/list?serviceName=" + query, new OkResult() {
            @Override
            public void success(JSONObject jsonObject) {
                List<Service_list> service_lists = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<Service_list>>() {
                }.getType());
                dialogContent = (TextView) view.findViewById(R.id.dialog_content);
                dialogRecycle = (RecyclerView) view.findViewById(R.id.dialog_recycle);

                if (service_lists.size()!=0){
                    DiaAdapter diaAdapter = new DiaAdapter();
                    dialogRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
                    dialogRecycle.setAdapter(diaAdapter);
                    diaAdapter.setService_lists(service_lists);
                    diaAdapter.notifyDataSetChanged();

                    diaAdapter.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view, int position) {
                            switch (service_lists.get(position).getServiceName()) {
                                case "城市地铁":
                                    getActivity().startActivity(new Intent(getActivity(), City_subway.class));
                                    break;
                            }
                        }
                    });
                }else {
                    dialogContent.setVisibility(View.VISIBLE);
                }
            }
        });
        builder.show();
    }

    private void initData() {
        ServiceAdapter serviceAdapter = new ServiceAdapter();
        dashList.setLayoutManager(new LinearLayoutManager(getActivity()));
        dashList.setAdapter(serviceAdapter);
        serviceAdapter.notifyDataSetChanged();

        serviceAdapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                dashList.getChildAt(currentIndex).setBackgroundColor(Color.TRANSPARENT);
                view.setBackgroundColor(Color.WHITE);
                currentIndex = position;
            }
        });
    }

    private void Fuwu_list() {
        dashRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        dashRecycle.setAdapter(new LbAdapter(getActivity()));
    }

    private void initView(View getView) {
        dashSearch = (SearchView) getView.findViewById(R.id.dash_search);
        dashList = (RecyclerView) getView.findViewById(R.id.dash_list);
        dashRecycle = (RecyclerView) getView.findViewById(R.id.dash_recycle);

    }
}