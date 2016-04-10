package com.example.duyve.myapplication.MainActivities;
//Standard Android imports

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.duyve.myapplication.R;
import com.example.duyve.myapplication.SignupActivity;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
//Custom imports

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Firebase.setAndroidContext(getApplicationContext());

        Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/");

        if (ref.getAuth() != null){
            Intent mainMenu = new Intent(this, MainMenuActivity.class);
            startActivity(mainMenu);
            finish();
        }

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
                Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/");

                //Create authlistener that will go to login page upon losing authorization
                ref.addAuthStateListener(new Firebase.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(AuthData authData) {
                        if(authData == null){
                            Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/");
                            ref.removeAuthStateListener(this);
                            Intent mainScreen = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(mainScreen);
                        }
                    }
                });

                Intent intent = new Intent(this, MainMenuActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
