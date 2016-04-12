package com.example.duyve.myapplication.Classes;

import android.widget.LinearLayout;
import android.widget.TextView;

public class FirebaseExperience {
    String id;
    LinearLayout layout;
    TextView companyName;
    TextView position;
    TextView durationLocation;
    TextView info;

    public FirebaseExperience(String id, LinearLayout layout, TextView companyName, TextView position, TextView durationLocation, TextView info) {
        this.id = id;
        this.layout = layout;
        this.companyName = companyName;
        this.position = position;
        this.durationLocation = durationLocation;
        this.info = info;
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

    public TextView getCompanyName() {
        return companyName;
    }

    public void setCompanyName(TextView companyName) {
        this.companyName = companyName;
    }

    public TextView getPosition() {
        return position;
    }

    public void setPosition(TextView position) {
        this.position = position;
    }

    public TextView getDurationLocation() {
        return durationLocation;
    }

    public void setDurationLocation(TextView durationLocation) {
        this.durationLocation = durationLocation;
    }

    public TextView getInfo() {
        return info;
    }

    public void setInfo(TextView info) {
        this.info = info;
    }
}