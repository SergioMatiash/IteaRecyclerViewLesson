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

    public void setName(String name) {
        this.name = name;
    }



    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
