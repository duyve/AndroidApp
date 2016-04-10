package com.example.duyve.myapplication.MainActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.duyve.myapplication.Classes.ActivityCode;
import com.example.duyve.myapplication.R;
import com.example.duyve.myapplication.Resume.EditResumeActivity;
import com.example.duyve.myapplication.Settings.SettingsActivity;

public class MainMenuActivity extends AppCompatActivity {

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }


    public void onEditClick(View view){
        Intent i = new Intent(this, EditResumeActivity.class);
        startActivity(i);
    }

    public void onViewClick(View view){
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
    }

    public void onSettingsClick(View view){
        Intent i = new Intent(this, SettingsActivity.class);
        startActivityForResult(i, ActivityCode.SETTINGS);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == ActivityCode.SETTINGS){
            if(resultCode == RESULT_OK){
                finish();
            }
        }
    }
}