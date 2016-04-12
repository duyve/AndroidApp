package com.example.duyve.myapplication.Classes;

import android.widget.LinearLayout;
import android.widget.TextView;

public class FireaseReference {
    String id;
    LinearLayout layout;
    TextView name;
    TextView relation;
    TextView email;
    TextView phone;

    public FireaseReference(String id, TextView phone, TextView email, TextView relation, TextView name, LinearLayout layout) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.relation = relation;
        this.name = name;
        this.layout = layout;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TextView getPhone() {
        return phone;
    }

    public void setPhone(TextView phone) {
        this.phone = phone;
    }

    public TextView getEmail() {
        return email;
    }

    public void setEmail(TextView email) {
        this.email = email;
    }

    public TextView getRelation() {
        return relation;
    }

    public void setRelation(TextView relation) {
        this.relation = relation;
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public LinearLayout getLayout() {
        return layout;
    }

    public void setLayout(LinearLayout layout) {
        this.layout = layout;
    }
}