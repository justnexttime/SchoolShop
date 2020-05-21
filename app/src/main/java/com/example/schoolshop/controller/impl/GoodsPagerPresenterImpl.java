package com.example.schoolshop.controller.impl;

import android.util.Log;

import com.example.schoolshop.controller.IGoodsPagerPresenter;
import com.example.schoolshop.entity.API;
import com.example.schoolshop.entity.Goods;

import com.example.schoolshop.view.IGoodsPagerCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoodsPagerPresenterImpl implements IGoodsPagerPresenter {
    private GoodsPagerPresenterImpl(){

    }

    public static IGoodsPagerPresenter sInstance = null;

    public static IGoodsPagerPresenter getsInstance(){
        if (sInstance == null) {
            sInstance = new GoodsPagerPresenterImpl();
        }
        return sInstance;
    }



    @Override
    public void getGoodsByTitle(String title) {
        //加载title搜索到的内容
        for (IGoodsPagerCallback callback : callbacks) {
            if (callback.titleget().equals(title)){
                callback.onLoading();
            }
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.79.198.138:8084/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("title", title);
            Log.d("json", "getGoodsByTitle: "+jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<ResponseBody> task = api.getjsonByTitle(""+jsonObject.toString());
        task.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.code();
                if (code == HttpURLConnection.HTTP_OK){
                    try {
                        String s = response.body().string();
                        Log.d("resutlbytitle","result-->"+s);
                        List<Goods> gklist1 = new Gson().fromJson(s,new TypeToken<List<Goods>>(){}.getType());

                        Log.d("resutlbytitle1", "onResponse: "+gklist1.get(0).getGoodsName());
                        //更新ui
                        handlerHomepageResult(gklist1,title);
                    } catch (IOException e) {
                        Log.d("resutlbytitle","resutl-->body失败");
                        e.printStackTrace();

                    }

                }else{
                    handlerNetworkError(title);
                    Log.d("resutlbytitle","resutl-->请求失败"+"code:"+code);
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                handlerNetworkError(title);
                Log.d("resutlbytitle",t.toString());
            }
        });
    }

    private void handlerNetworkError(String title) {

        for (IGoodsPagerCallback callback : callbacks) {
            if (callback.titleget().equals(title)) {
                callback.onNetworkError();
            }
        }
    }

    private void handlerHomepageResult(List<Goods> gklist1, String title) {

        //通知更新数据ui
        for (IGoodsPagerCallback callback:callbacks) {
            if (callback.titleget().equals(title)){
                if (gklist1.size() == 0 || gklist1 == null){
                    callback.onEmpty();
                }else {
                    callback.onContentload(gklist1);
                }
            }
        }
    }

    @Override
    public void LoadMore(String title) {

    }

    @Override
    public void Reload(String title) {

    }

    private  List<IGoodsPagerCallback> callbacks = new ArrayList<>();
    @Override
    public void registerViewCallback(IGoodsPagerCallback callback) {
        if (!callbacks.contains(callback)) {
             callbacks.add(callback);
        }
    }

    @Override
    public void unregisterViewCallback(IGoodsPagerCallback callback) {

    }
}
