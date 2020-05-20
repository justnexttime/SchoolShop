package com.example.schoolshop.entity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("SchoolShop/GoodskindServlet")
    Call<ResponseBody> getjson();

}
