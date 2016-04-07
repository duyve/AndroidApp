package com.example.duyve.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/");

        //Check for authentication logic block
        ref.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if(authData == null){
                    Intent loginIntnet = new Intent(SettingsActivity.this, LoginActivity.class);
                    startActivityForResult(loginIntnet, ActivityCode.LOG_IN);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == ActivityCode.LOG_IN)
        {
            if (resultCode == RESULT_CANCELED)
            {
                Intent mainScreen = new Intent(this, MainActivity.class);
                mainScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                mainScreen.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(mainScreen);
            }

        }

    }

    public void onClickChangePassword(View view)
    {

    }
}
