package com.example.duyve.myapplication.Classes;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FirebaseReference {
    String id;
    LinearLayout layout;
    TextView nameView;
    TextView relationView;
    TextView emailView;
    TextView phoneView;
    String name;
    String relation;
    String email;
    String phone;

    public FirebaseReference(AppCompatActivity activity, String id, String name, String phone, String email, String relation) {
        this.id = id;
        this.layout = new LinearLayout(activity);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.TRANSPARENT);
        this.phone = phone;
        this.email = email;
        this.relation = relation;
        this.name = name;
        this.nameView = new TextView(activity);
        nameView.setText(name);
        nameView.setTextSize(20);
        nameView.setTextColor(Color.BLACK);
        nameView.setPadding(0, 10, 0, 0);
        this.relationView = new TextView(activity);
        relationView.setText("Relation: " + relation);
        relationView.setPadding(20, 5, 0, 0);
        this.emailView = new TextView(activity);
        emailView.setText("Email: " + email);
        emailView.setPadding(20, 5, 0, 0);
        this.phoneView = new TextView(activity);
        phoneView.setText("Phone: " + phone);
        phoneView.setPadding(20,5,0,0);


        layout.addView(nameView,0);
        layout.addView(relationView, 1);
        layout.addView(phoneView, 2);
        layout.addView(emailView, 3);

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

    public TextView getNameView() {
        return nameView;
    }

    public void setNameView(TextView nameView) {
        this.nameView = nameView;
    }

    public TextView getRelationView() {
        return relationView;
    }

    public void setRelationView(TextView relationView) {
        this.relationView = relationView;
    }

    public TextView getEmailView() {
        return emailView;
    }

    public void setEmailView(TextView emailView) {
        this.emailView = emailView;
    }

    public TextView getPhoneView() {
        return phoneView;
    }

    public void setPhoneView(TextView phoneView) {
        this.phoneView = phoneView;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
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