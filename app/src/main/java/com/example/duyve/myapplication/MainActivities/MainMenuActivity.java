package com.example.duyve.myapplication.MainActivities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.duyve.myapplication.Classes.ActivityCode;
import com.example.duyve.myapplication.R;
import com.example.duyve.myapplication.Resume.EditResumeActivity;
import com.example.duyve.myapplication.Resume.ViewResumeActivity;
import com.example.duyve.myapplication.Settings.SettingsActivity;
import com.firebase.client.Firebase;

public class MainMenuActivity extends AppCompatActivity {

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/");
        id = ref.getAuth().getUid();
        Typeface main = Typeface.createFromAsset(getAssets(), "fonts/Champagne & Limousines Bold.ttf");

        Button edit = (Button)findViewById(R.id.MainMenuButtonEditResume);
        Button view = (Button)findViewById(R.id.MainMenuButtonViewResume);
        Button settings = (Button)findViewById(R.id.MainMenuButtonSettings);

        edit.setTypeface(main);
        view.setTypeface(main);
        settings.setTypeface(main);
    }


    public void onEditClick(View view){
        Intent i = new Intent(this, EditResumeActivity.class);
        i.putExtra("id", id);
        startActivity(i);
    }

    public void onViewClick(View view){
        Intent i = new Intent(this, ViewResumeActivity.class);
        i.putExtra("id", id);
        startActivity(i);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
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