package com.example.duyve.myapplication.Settings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duyve.myapplication.R;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText newPasswordView,
            passwordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_password);
        newPasswordView = (EditText) findViewById(R.id.ChangePasswordTextNewPassword);
        passwordView = (EditText) findViewById(R.id.ChangePasswordTextCurrentPassword);
    }

    public void onClickChangePassword(View view)
    {
        newPasswordView.setError(null);
        passwordView.setError(null);
        final String newPassword = newPasswordView.getText().toString();
        final String password = passwordView.getText().toString();

        if(!isValidPassword(newPassword))
        {
            newPasswordView.setError("Please enter a valid email");
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
            final String ee = ref.getAuth().getProviderData().get("email").toString();
            ref.changePassword(ref.getAuth().getProviderData().get("email").toString(), password, newPassword, new Firebase.ResultHandler() {
                @Override
                public void onSuccess() {
                    Toast.makeText(ChangePasswordActivity.this, "Password Changed Sccessfully!", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onError(FirebaseError firebaseError) {
                    switch (firebaseError.getCode()) {
                        case FirebaseError.INVALID_EMAIL:
                            Toast.makeText(ChangePasswordActivity.this, "Email entered was incorrect", Toast.LENGTH_SHORT).show();
                            break;
                        case FirebaseError.INVALID_PASSWORD:
                            Toast.makeText(ChangePasswordActivity.this,  ee + "Password was incorrect", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(ChangePasswordActivity.this, "There was an error changing your password", Toast.LENGTH_SHORT).show();
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
