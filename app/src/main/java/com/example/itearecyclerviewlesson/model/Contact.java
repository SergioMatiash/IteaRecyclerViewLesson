package com.example.itearecyclerviewlesson.model;

import android.widget.ImageView;

import com.github.javafaker.Faker;

public class Contact {
    private String name;
    private String surname;
    private String title;
    private String photo;
    private int position;


    public Contact(String name, String surname, String title,String photo, int position) {

        this.name=name;
        this.surname = surname;
        this.title = title;
        this.photo=photo;
        this.position = position;
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

    public int getPosition() {
        return position;
    }
}
