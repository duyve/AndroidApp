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

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
//Custom imports

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/");
        ref.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData != null){
                    Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public void onClickLogin(View view)
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, ActivityCode.LOG_IN);
    }

    public void onClickSignup(View view)
    {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivityForResult(intent, ActivityCode.SIGN_UP);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == ActivityCode.LOG_IN || requestCode == ActivityCode.SIGN_UP)
        {
            if(resultCode == RESULT_OK)
            {
                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }
    }
}
