package com.example.duyve.myapplication.MainActivities;

public class Reference {
    private String name;

    private String title;

    private String email;

    private String phone;

    public Reference() {}

    public Reference(String name, String title, String email, String phone) {
        this.name = name;
        this.title = title;
        this.email = email;
        this.phone = phone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
