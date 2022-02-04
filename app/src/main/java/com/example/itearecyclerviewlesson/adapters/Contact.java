package com.example.itearecyclerviewlesson.adapters;

public class Contact {
    private String name;
    private String surname;
    private String title;


    public Contact(String name, String surname, String title) {
        this.name = name;
        this.surname = surname;
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
