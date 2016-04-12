package com.example.duyve.myapplication.MainActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duyve.myapplication.R;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText emailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_forgot);
        emailView = (EditText) findViewById(R.id.EnterEmail);
    }

    public void getNewPassword(View view){
        final String email =  emailView.getText().toString();

        if(!isValidEmail(email))
        {
            emailView.setError("The email you entered is not associated with a getJobs(); account");
        }
        else{
            Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/");
            ref.resetPassword(email, new Firebase.ResultHandler() {
                @Override
                public void onSuccess() {
                    Toast.makeText(ForgotPasswordActivity.this, "Password Reset Email Sent", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onError(FirebaseError firebaseError) {
                    emailView.setError("An error happened");
                }
            });
    }
    }

    public Boolean isValidEmail(String email)
    {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
