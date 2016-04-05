package com.example.duyve.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void onEditClick(View view){
        Intent i = new Intent(this, EditResumeActivity.class);
        startActivity(i);
    }

    public void onViewClick(View view){
        Intent i =new Intent(this, SettingsActivity.class);
        startActivity(i);
    }

    public void onSettingsClick(View view){
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
    }
}