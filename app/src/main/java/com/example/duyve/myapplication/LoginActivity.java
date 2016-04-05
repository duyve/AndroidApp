package com.example.duyve.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class LoginActivity extends AppCompatActivity
{
    private static final int REQUEST_CODE = 10;

    private EditText emailView,
            passwordView;
    private TextView signupView;
    private String id;
    private Firebase firebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this);
        emailView = (EditText) findViewById(R.id.LoginTextEmail);
        passwordView = (EditText) findViewById(R.id.LoginTextPassword);
        signupView = (TextView) findViewById(R.id.LoginTextSignup);

        id = null;
        firebaseRef = new Firebase("https://sizzling-torch-8367.firebaseio.com/");
    }

    public void switchToForgot(View view){
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void switchToSignup(View view) {
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
                    id = data.getExtras().getString("id");
                    finish();
                }
            }
        }
    }

    public void loginAccount(View view)
    {
        emailView.setError(null);
        passwordView.setError(null);
        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();

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
            firebaseRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {
                @Override
                public void onAuthenticated(AuthData authData) {
                    Toast.makeText(LoginActivity.this, "Logged in Successfully!", Toast.LENGTH_SHORT).show();
                    id = authData.getUid();
                    //Return to Parent call
                    finish();
                }

                @Override
                public void onAuthenticationError(FirebaseError firebaseError) {
                    switch (firebaseError.getCode()){
                        case FirebaseError.INVALID_PASSWORD:
                            Toast.makeText(LoginActivity.this, "Password Incorrect", Toast.LENGTH_SHORT).show();
                            break;
                        case FirebaseError.INVALID_EMAIL:
                            Toast.makeText(LoginActivity.this, "Account with given email does not exist", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(LoginActivity.this, "There was an error signing into your account", Toast.LENGTH_SHORT).show();
                            break;
                    }
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

    public Boolean isValidPassword(String password)
    {
        //TODO add additional requirements for password
        return !(TextUtils.isEmpty(password) || password.length() < 4);
    }
}