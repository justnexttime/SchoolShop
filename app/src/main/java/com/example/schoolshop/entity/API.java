package com.example.schoolshop.entity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {
    @GET("SchoolShop/GoodskindServlet")
    Call<ResponseBody> getjson();

    @POST("SchoolShop/GoodsSearchBykinds")
    Call<ResponseBody> getjsonByTitle(@Body String content );

}
