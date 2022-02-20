package com.example.itearecyclerviewlesson.retrofitmodule;

import com.google.gson.annotations.SerializedName;

public class ProfilePhoto {

    @SerializedName("profilePhoto")
    private String profilePhotoUrl;


    public ProfilePhoto(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }
}
