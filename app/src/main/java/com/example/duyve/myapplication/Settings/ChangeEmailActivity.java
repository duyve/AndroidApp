package com.example.duyve.myapplication.Settings;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duyve.myapplication.R;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class ChangeEmailActivity extends AppCompatActivity {

    private EditText oldEmailView,
            newEmailView,
            passwordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.settings_email);
        oldEmailView = (EditText) findViewById(R.id.ChangeEmailTextOldEmail);
        newEmailView = (EditText) findViewById(R.id.ChangeEmailTextNewEmail);
        passwordView = (EditText) findViewById(R.id.ChangeEmailTextPassword);

        EditText old = (EditText)findViewById(R.id.ChangeEmailTextOldEmail);
        EditText newer = (EditText)findViewById(R.id.ChangeEmailTextNewEmail);
        EditText pass = (EditText)findViewById(R.id.ChangeEmailTextPassword);
        Button change = (Button)findViewById(R.id.ChangeEmailButtonSubmit);
        Typeface main = Typeface.createFromAsset(getAssets(), "fonts/Champagne & Limousines Bold.ttf");

        old.setTypeface(main);
        newer.setTypeface(main);
        pass.setTypeface(main);
        change.setTypeface(main);
    }

    public void onClickChangeEmail(View view)
    {
        oldEmailView.setError(null);
        newEmailView.setError(null);
        passwordView.setError(null);
        final String oldEmail = oldEmailView.getText().toString();
        final String newEmail = newEmailView.getText().toString();
        final String password = passwordView.getText().toString();

        if(!isValidEmail(oldEmail))
        {
            oldEmailView.setError("Please enter a valid email");
        }
        else if(!isValidEmail(newEmail))
        {
            newEmailView.setError("Please enter a valid email");
        }
        else if(!isValidPassword(password))
        {
            passwordView.setError("Please enter a valid password");
        }

        /*
         * Update user email if both are valid
         */
        else
        {
            Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/");
            ref.changeEmail(oldEmail, password, newEmail, new Firebase.ResultHandler() {
                @Override
                public void onSuccess() {
                    Toast.makeText(ChangeEmailActivity.this, "Email Changed Sccessfully!", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onError(FirebaseError firebaseError) {
                    switch (firebaseError.getCode()) {
                        case FirebaseError.EMAIL_TAKEN:
                            Toast.makeText(ChangeEmailActivity.this, "New email is already taken", Toast.LENGTH_SHORT).show();
                            break;
                        case FirebaseError.INVALID_PASSWORD:
                            Toast.makeText(ChangeEmailActivity.this, "Password was incorrect", Toast.LENGTH_SHORT).show();
                            break;
                        case FirebaseError.INVALID_EMAIL:
                            Toast.makeText(ChangeEmailActivity.this, "Email entered was not a valid email", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(ChangeEmailActivity.this, "There was an error changing your email", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        }
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
