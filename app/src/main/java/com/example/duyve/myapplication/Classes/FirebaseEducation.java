package com.example.duyve.myapplication.Classes;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FirebaseEducation {
    String id;
    LinearLayout layout;
    TextView schoolNameView;
    TextView durationLocationView;
    TextView infoView;
    String schoolName;
    String startDate;
    String endDate;
    String city;
    String state;
    String info;


    public FirebaseEducation(AppCompatActivity activity, String id, String schoolName, String startDate, String endDate, String city, String state, String info) {
        this.id = id;
        this.layout = new LinearLayout(activity);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.TRANSPARENT);
        this.schoolName = schoolName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.city = city;
        this.state = state;
        this.info = info;
        schoolNameView = new TextView(activity);
        schoolNameView.setTextSize(20);
        schoolNameView.setTextColor(Color.BLACK);
        schoolNameView.setText(schoolName);
        schoolNameView.setPadding(0,10,0,0);
        durationLocationView = new TextView(activity);
        durationLocationView.setText(city + ", " + state + "  |  " + startDate + "-" + endDate);
        durationLocationView.setPadding(20, 5, 0, 0);
        infoView = new TextView(activity);
        infoView.setText(info);
        infoView.setPadding(20,5,0,0);
        layout.addView(schoolNameView, 0);
        layout.addView(durationLocationView, 1);
        layout.addView(infoView, 2);
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

    public TextView getSchoolNameView() {
        return schoolNameView;
    }

    public void setSchoolNameView(TextView schoolNameView) {
        this.schoolNameView = schoolNameView;
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

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
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
}