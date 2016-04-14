package com.example.duyve.myapplication.Classes;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FirebaseExperience {
    String id;
    LinearLayout layout;
    TextView companyNameView;
    TextView durationLocationView;
    TextView infoView;
    TextView positionView;
    String companyName;
    String position;
    String startDate;
    String endDate;
    String city;
    String state;
    String info;


    public FirebaseExperience(AppCompatActivity activity, String id, String position, String companyName, String startDate, String endDate, String city, String state, String info) {
        this.id = id;
        this.layout = new LinearLayout(activity);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.TRANSPARENT);
        this.position = position;
        this.companyName = companyName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.city = city;
        this.state = state;
        this.info = info;
        companyNameView = new TextView(activity);
        companyNameView.setTextSize(20);
        companyNameView.setTextColor(Color.BLACK);
        companyNameView.setText(companyName);
        companyNameView.setPadding(0,10,0,0);
        durationLocationView = new TextView(activity);
        durationLocationView.setText(city + ", " + state + "  |  " + startDate + "-" + endDate);
        durationLocationView.setPadding(20,5,0,0);
        infoView = new TextView(activity);
        infoView.setText(info);
        infoView.setPadding(20,5,0,0);
        positionView = new TextView(activity);
        positionView.setText("Position: " + position);
        positionView.setPadding(20,5,0,0);
        layout.addView(companyNameView, 0);
        layout.addView(positionView, 1);
        layout.addView(durationLocationView, 2);
        layout.addView(infoView, 3);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LinearLayout getLayout() {
        return layout;
    }

    public void setLayout(LinearLayout layout) {
        this.layout = layout;
    }

    public TextView getDurationLocationView() {
        return durationLocationView;
    }

    public void setDurationLocationView(TextView durationLocationView) {
        this.durationLocationView = durationLocationView;
    }

    public TextView getInfoView() {
        return infoView;
    }

    public void setInfoView(TextView infoView) {
        this.infoView = infoView;
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

    public TextView getCompanyNameView() {
        return companyNameView;
    }

    public void setCompanyNameView(TextView companyNameView) {
        this.companyNameView = companyNameView;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}