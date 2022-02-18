package com.example.itearecyclerviewlesson.retrofitmodule;

import com.example.itearecyclerviewlesson.model.Contact;

import java.lang.annotation.Target;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiRequest {

    public final String BASE_URL = "https://api.thecatapi.com/";


    @GET("/v1/images/search")
    Call<ApiData> getContactPhoto();

    //here we need to call data that we expect to get
}
