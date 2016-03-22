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
        intent.putExtra("loginKeySend", false);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE)
        {
            if (data.hasExtra("loginKeyReceive"))
            {
                Boolean successful = data.getExtras().getBoolean("loginKeyReceive");
                if (successful)
                {
                    Intent intent = new Intent(this, EditResumeActivity.class);
                    startActivity(intent);
                }
                    // Replace with new logic
                    //Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
