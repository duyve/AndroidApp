package com.example.duyve.myapplication.MainActivities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duyve.myapplication.Classes.ActivityCode;
import com.example.duyve.myapplication.R;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class LoginActivity extends AppCompatActivity
{
    private EditText emailView,
            passwordView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);
        emailView = (EditText) findViewById(R.id.LoginTextEmail);
        passwordView = (EditText) findViewById(R.id.LoginTextPassword);
        Typeface main = Typeface.createFromAsset(getAssets(), "fonts/Champagne & Limousines Bold.ttf");

        EditText email = (EditText)findViewById(R.id.LoginTextEmail);
        EditText password = (EditText)findViewById(R.id.LoginTextPassword);
        Button login = (Button)findViewById(R.id.LoginButtonSubmit);
        TextView forgot = (TextView)findViewById(R.id.LoginTextForgotPassword);
        TextView signup = (TextView)findViewById(R.id.LoginTextSignup);

        email.setTypeface(main);
        password.setTypeface(main);
        login.setTypeface(main);
        forgot.setTypeface(main);
        signup.setTypeface(main);
    }

    public void switchToForgot(View view){
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        startActivity(intent);
    }

    public void switchToSignup(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivityForResult(intent, ActivityCode.SIGN_UP);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == ActivityCode.SIGN_UP)
        {
            if(resultCode == RESULT_OK)
            {
                finish();
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
            Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/");
            ref.authWithPassword(email, password, new Firebase.AuthResultHandler() {
                @Override
                public void onAuthenticated(AuthData authData) {
                    Toast.makeText(LoginActivity.this, "Logged in Successfully!", Toast.LENGTH_SHORT).show();
                    //Return to Parent call
                    finish();
                    overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
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