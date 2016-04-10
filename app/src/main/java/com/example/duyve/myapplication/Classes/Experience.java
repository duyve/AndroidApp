package com.example.duyve.myapplication.Classes;

public class Experience {
    private String name;

    private  String position;

    private String startDate;

    private String endDate;

    private String city;

    private String state;

    private String info;

    public Experience() {}

    public Experience(String name, String startDate, String endDate, String city, String state, String info, String position) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.city = city;
        this.state = state;
        this.info = info;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
