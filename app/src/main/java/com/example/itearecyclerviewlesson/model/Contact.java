package com.example.itearecyclerviewlesson.model;

import android.widget.ImageView;

import com.github.javafaker.Faker;

public class Contact {
    private String name;
    private String surname;
    private String title;
    private String photo;


    public Contact(String name, String surname, String title,String photo) {

        this.name=name;
        this.surname = surname;
        this.title = title;
        this.photo=photo;
    }

    public String getName() {
        return name;
    }



    public String getSurname() {
        return surname;
    }



    public String getTitle() {
        return title;
    }



    public String getPhoto() {
        return photo;
    }


}
