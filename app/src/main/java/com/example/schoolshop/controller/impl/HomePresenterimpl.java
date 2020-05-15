package com.example.schoolshop.controller.impl;


import android.util.Log;

import com.example.schoolshop.entity.API;
import com.example.schoolshop.entity.Goodskind;
import com.example.schoolshop.view.IHomeCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;



public class HomePresenterimpl implements IHomePresenter {
    private IHomeCallback mCallback;

    @Override
    public void getGoodskinds() {
        if (mCallback!=null){
            mCallback.onLoading();
        }
        //加载分类数据
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.79.198.138:8084/")
                .build();

        API api = retrofit.create(API.class);
        Call<ResponseBody> task = api.getjson();
        task.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.code();
                if(code == HttpURLConnection.HTTP_OK){
                    try {
                        String s = response.body().string();
                        List<Goodskind> gklist1 = new Gson().fromJson(s,new TypeToken<List<Goodskind>>(){}.getType());
                        ArrayList<String> gklist2 = new ArrayList<String>();
                        for (int i = 0; i<gklist1.size();i++){
                            gklist2.add(gklist1.get(i).getGoodskind());
                        }

                        if(mCallback!=null){
                            if (gklist1 == null|| gklist1.size() ==0 ){
                                mCallback.onEmpty();
                            }else{

                                mCallback.onGoodskindLoaded(gklist2);
                            }


                        }

                        Log.d("kinds","result--->"+gklist2.toString());
                    } catch (IOException e) {
                    e.printStackTrace();
                }
                }else{

                    mCallback.onNetworkError();
                    Log.d("kinds","resutl-->请求失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("kinds",t.toString());
                mCallback.onNetworkError();
            }
        });



    }

    @Override
    public void registerCallback(IHomeCallback iHomeCallback) {
        this.mCallback = iHomeCallback;
    }

    @Override
    public void unregisterCallback(IHomeCallback iHomeCallback) {
        mCallback = null;
    }


}
