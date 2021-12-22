package com.example.myapp.bank;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Network {
    public static String token = null;
    public static String baseUrl = "http://192.168.1.103:10001";
    private static final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        @NotNull
        @Override
        public Response intercept(@NotNull Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            if (token!=null)
                builder.addHeader("Authorization",token);
            return chain.proceed(builder.build());
        }
    }).build();
    private static final MediaType JSON = MediaType.Companion.parse("application/json;charset=utf-8");

    public static void doGet(String path,OkResult okResult){
        Request request = new Request.Builder().url(baseUrl+path).get().build();
        client.newCall(request).enqueue(new OkCallback(okResult));
    }

    public static void doPost(String path,String params,OkResult okResult){
        Request request = new Request.Builder().url(baseUrl+path).post(RequestBody.create(params,JSON)).build();
        client.newCall(request).enqueue(new OkCallback(okResult));
    }

    public static void doPut(String path,String params,OkResult okResult){
        Request request = new Request.Builder().url(baseUrl+path).put(RequestBody.create(params,JSON)).build();
        client.newCall(request).enqueue(new OkCallback(okResult));
    }

    public static void doAddress(Context context, TextView textView){
        CityPickerView cityPicker = new CityPickerView();
        cityPicker.init(context);
        CityConfig cityConfig = new CityConfig.Builder().build();
        cityPicker.setConfig(cityConfig);
        cityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                textView.setText(province+"-"+city+"-"+district);
            }

            @Override
            public void onCancel() {
                super.onCancel();
            }
        });
    }

    private static final Handler MHANDLER = new Handler(Looper.myLooper());
    static class OkCallback implements Callback{
        private final OkResult okResult;

        public OkCallback(OkResult okResult) {
            this.okResult = okResult;
        }

        @Override
        public void onFailure(@NotNull Call call, @NotNull IOException e) {
            e.printStackTrace();
            MHANDLER.post(new Runnable() {
                @Override
                public void run() {
                }
            });
        }

        @Override
        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
            String string = Objects.requireNonNull(response.body().string());
            MHANDLER.post(()->{
                try {
                    okResult.success(new JSONObject(string));
                } catch (JSONException e) {
                }
            });
        }
    }

}
