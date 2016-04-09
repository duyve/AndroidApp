package com.example.duyve.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText emailView;
    private Firebase firebaseRef;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        emailView = (EditText) findViewById(R.id.EnterEmail);
        firebaseRef = new Firebase("https://sizzling-torch-8367.firebaseio.com/");
        id = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK && requestCode == ActivityCode.FORGOT_PASSWORD)
        {
            if (data.hasExtra("id"))
            {
                if (data.getExtras().getString("id") != null)
                {
                    id = data.getExtras().getString("id");
                    finish();
                }
            }
        }
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
                }

                @Override
                public void onError(FirebaseError firebaseError) {
                    emailView.setError("The email you entered is not associated with a getJobs(); account");
                }
            });
    }
    }

    @Override
    public void finish(){
        Intent intent = new Intent();

        intent.putExtra("id", id);
        setResult(RESULT_OK, intent);

        super.finish();
    }

    public Boolean isValidEmail(String email)
    {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
