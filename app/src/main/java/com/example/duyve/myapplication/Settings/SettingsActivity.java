package com.example.duyve.myapplication.Settings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.duyve.myapplication.R;
import com.firebase.client.Firebase;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
    }


    public void onClickChangeEmail(View view)
    {
        Intent i = new Intent(this,ChangeEmailActivity.class);
        startActivity(i);
    }

    public void onClickChangePassword(View view)
    {
        Intent i = new Intent(this,ChangePasswordActivity.class);
        startActivity(i);
    }

    public void onClickAbout(View view)
    {
        Intent i = new Intent(this, AboutActivity.class);
        startActivity(i);
    }

    public void onClickLogout(View view)
    {
        Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/");
        ref.unauth();
        Intent i  = new Intent();
        setResult(RESULT_OK, i);
        finish();
    }

    @Override
    public void finish()
    {
        super.finish();
    }
}
