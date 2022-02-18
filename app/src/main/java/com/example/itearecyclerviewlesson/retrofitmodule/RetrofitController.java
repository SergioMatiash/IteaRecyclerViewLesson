package com.example.itearecyclerviewlesson.retrofitmodule;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitController implements ApiRequest{

    static final String BASE_URL = "https://api.thecatapi.com/";
    Context context;


    public void start () {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //make a builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        //get client & call object for the request
        //interface instance
       ApiRequest apiRequest= retrofit.create(ApiRequest.class);
       Call<ApiData> call = apiRequest.getContactPhoto();

       call.enqueue(new Callback<ApiData>() {
           @Override
           public void onResponse(Call<ApiData> call, Response<ApiData> response) {

               //checking if response is successful
               if (response.code()== 200) {
                   Toast.makeText(context,"sucess",Toast.LENGTH_LONG).show();
               }
               else {
                   Toast.makeText(context,"check out the problems",Toast.LENGTH_LONG).show();
               }

           }

           @Override
           public void onFailure(Call<ApiData> call, Throwable t) {

           }
       });



    }



    @Override
    public Call<ApiData>getContactPhoto() {
        return null;
        //need to be overridden
    }
}
