package com.example.duyve.myapplication.Classes;

/**
 * Created by duyve on 4/9/2016.
 */
public class Reference {
    private String name;

    private String title;

    private String contact;

    public Reference() {}

    public Reference(String name, String title, String contact) {
        this.name = name;
        this.title = title;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
