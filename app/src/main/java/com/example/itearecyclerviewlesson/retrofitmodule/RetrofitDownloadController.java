package com.example.itearecyclerviewlesson.retrofitmodule;


import com.example.itearecyclerviewlesson.adapters.ContactsRecyclerAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitDownloadController {

 ContactsRecyclerAdapter contactsRecyclerAdapter;
 List<ProfilePhoto> profilePhotoList;


//Creating reference for X and receiving deserialized data.
 ApiRequest apiRequest = ApiClient.getClient().create(ApiRequest.class);
 Call<List<ApiData>> call = apiRequest.getContactPhoto();


}
