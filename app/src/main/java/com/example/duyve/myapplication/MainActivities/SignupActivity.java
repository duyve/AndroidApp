package com.example.duyve.myapplication.MainActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duyve.myapplication.Classes.ActivityCode;
import com.example.duyve.myapplication.R;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class SignupActivity extends AppCompatActivity
{

    private EditText emailView,
            passwordView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_signup);
        emailView = (EditText) findViewById(R.id.SignupTextEmail);
        passwordView = (EditText) findViewById(R.id.SignupTextPassword);

    }

    public void switchToLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, ActivityCode.LOG_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == ActivityCode.LOG_IN)
        {
            if(resultCode == RESULT_OK)
            {
                finish();
            }
        }
    }

    public void loginAfterSignup(String email, String password)
    {
        Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/");
        ref.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Toast.makeText(SignupActivity.this, "Logged in Successfully!", Toast.LENGTH_SHORT).show();
                //Return to Parent call
                finish();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                switch (firebaseError.getCode()){
                    case FirebaseError.INVALID_PASSWORD:
                        Toast.makeText(SignupActivity.this, "Password Incorrect", Toast.LENGTH_SHORT).show();
                        break;
                    case FirebaseError.INVALID_EMAIL:
                        Toast.makeText(SignupActivity.this, "Account with given email does not exist", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(SignupActivity.this, "There was an error signing into your account", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    public void signupAccount(View view)
    {
        emailView.setError(null);
        passwordView.setError(null);
        final String email = emailView.getText().toString();
        final String password = passwordView.getText().toString();

        if(!isValidEmail(email))
        {
            emailView.setError("Please enter a valid email");
        }
        else if(!isValidPassword(password))
        {
            passwordView.setError("Please enter a valid password");
        }
        /*
         * Create a user account if email and password are valid
         */
        else
        {
            Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/");
            ref.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
                @Override
                public void onSuccess(Map<String, Object> result) {
                    Toast.makeText(SignupActivity.this, "Created Successfully!", Toast.LENGTH_SHORT).show();
                    //Log in after creating account
                    loginAfterSignup(email, password);
                }

                @Override
                public void onError(FirebaseError firebaseError) {
                    switch (firebaseError.getCode()){
                        case FirebaseError.EMAIL_TAKEN:
                            Toast.makeText(SignupActivity.this, "Email is taken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(SignupActivity.this, "There was an error creating your account", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        }
    }

    @Override
    public void finish(){
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        super.finish();
    }

    public Boolean isValidEmail(String email)
    {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public Boolean isValidPassword(String password)
    {
        //TODO add additional requirements for password
        return !(TextUtils.isEmpty(password) || password.length() < 4);
    }
}
