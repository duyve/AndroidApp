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

public class SignupActivity extends AppCompatActivity
{
    private static final int REQUEST_CODE = 10;

    private EditText emailView,
            passwordView;
    private TextView loginView;
    private String id;
    private Firebase firebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Firebase.setAndroidContext(this);
        emailView = (EditText) findViewById(R.id.SignupTextEmail);
        passwordView = (EditText) findViewById(R.id.SignupTextPassword);
        loginView = (TextView) findViewById(R.id.LoginTextSignup);

        id = null;
        firebaseRef = new Firebase("https://sizzling-torch-8367.firebaseio.com/");
    }

    public void switchToLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
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

    public void loginAfterSignup(String email, String password)
    {
        firebaseRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Toast.makeText(SignupActivity.this, "Logged in Successfully!", Toast.LENGTH_SHORT).show();
                id = authData.getUid();
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
            firebaseRef.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
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
