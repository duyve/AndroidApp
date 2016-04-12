package com.example.duyve.myapplication.Classes;

import android.widget.TextView;

public class FirebaseString {
    String id;
    TextView theString;

    public FirebaseString(String id, TextView theString) {
        this.id = id;
        this.theString = theString;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TextView getTextview() {
        return theString;
    }

    public void setTextView(TextView theString) {
        this.theString = theString;
    }
}
