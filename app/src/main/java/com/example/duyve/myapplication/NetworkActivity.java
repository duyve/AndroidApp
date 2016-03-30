package com.example.duyve.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class NetworkActivity extends AppCompatActivity {
    protected Firebase myFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://sizzling-torch-8367.firebaseio.com/");
    }

    public void onSendClick(View view){
        EditText message = (EditText) findViewById(R.id.editText);
        myFirebaseRef.child("message").setValue(message.getText().toString());
    }

}
