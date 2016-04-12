package com.example.duyve.myapplication.Classes;

import android.widget.LinearLayout;
import android.widget.TextView;

public class FirebaseEducation {
    String id;
    LinearLayout layout;
    TextView schoolName;
    TextView durationLocation;
    TextView info;

    public FirebaseEducation(String id, LinearLayout layout, TextView schoolName, TextView durationLocation, TextView info) {
        this.id = id;
        this.layout = layout;
        this.schoolName = schoolName;
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

    public TextView getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(TextView schoolName) {
        this.schoolName = schoolName;
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