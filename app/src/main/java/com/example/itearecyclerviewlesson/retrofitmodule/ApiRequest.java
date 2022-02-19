package com.example.itearecyclerviewlesson.retrofitmodule;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiRequest {

    public final String BASE_URL = "https://api.thecatapi.com/";


    @GET("/v1/images/search")
    Call<List<ApiData>> getContactPhoto();

    //here we need to call data that we expect to get
}
