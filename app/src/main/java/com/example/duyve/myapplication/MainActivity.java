package com.example.duyve.myapplication;
//Standard Android imports

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
//Custom imports

public class MainActivity extends AppCompatActivity
{
    private static final int REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickLogin(View view)
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void onClickSignup(View view)
    {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE)
        {
            if (data.hasExtra("id"))
            {
                if (data.getExtras().getString("id") != null)
                {
                    Intent intent = new Intent(this, MainMenuActivity.class);
                    startActivity(intent);
                }
            }
        }
    }
}
