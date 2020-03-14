package com.example.nodecomunication;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface  {

    @POST("/login")
    Call<loginResult> executeResult(@Body HashMap<String,String> map);


}
