package com.example.duyve.myapplication.Resume;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.duyve.myapplication.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class EditHeaderActivity extends AppCompatActivity {

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume_header);
        id = getIntent().getStringExtra("id");

        Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id + "/header");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                EditText firstName = (EditText) findViewById(R.id.EditHeaderTextFirstName);
                EditText lastName = (EditText) findViewById(R.id.EditHeaderTextLastName);
                EditText email = (EditText) findViewById(R.id.EditHeaderTextEmail);
                EditText phone = (EditText) findViewById(R.id.EditHeaderTextPhone);
                EditText address = (EditText) findViewById(R.id.EditHeaderTextAddress);
                EditText city = (EditText) findViewById(R.id.EditHeaderTextCity);
                Spinner state = (Spinner) findViewById(R.id.EditHeaderSpinnerState);
                EditText zipcode = (EditText) findViewById(R.id.EditHeaderTextZipcode);

                firstName.setText(dataSnapshot.child("firstName").getValue().toString());
                lastName.setText(dataSnapshot.child("lastName").getValue().toString());
                email.setText(dataSnapshot.child("email").getValue().toString());
                phone.setText(dataSnapshot.child("phone").getValue().toString());
                address.setText(dataSnapshot.child("address").getValue().toString());
                city.setText(dataSnapshot.child("city").getValue().toString());
                //Get the state Spinner to automatically select state
                String compareValue = dataSnapshot.child("state").getValue().toString();
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditHeaderActivity.this, R.array.states, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                state.setAdapter(adapter);
                if (!compareValue.equals(null)) {
                    int spinnerPosition = adapter.getPosition(compareValue);
                    state.setSelection(spinnerPosition);
                }

                zipcode.setText(dataSnapshot.child("zipCode").getValue().toString());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void onClickSave(View view){
        EditText firstName = (EditText) findViewById(R.id.EditHeaderTextFirstName);
        EditText lastName = (EditText) findViewById(R.id.EditHeaderTextLastName);
        EditText email = (EditText) findViewById(R.id.EditHeaderTextEmail);
        EditText phone = (EditText) findViewById(R.id.EditHeaderTextPhone);
        EditText address = (EditText) findViewById(R.id.EditHeaderTextAddress);
        EditText city = (EditText) findViewById(R.id.EditHeaderTextCity);
        Spinner state = (Spinner) findViewById(R.id.EditHeaderSpinnerState);
        EditText zipcode = (EditText) findViewById(R.id.EditHeaderTextZipcode);

        Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id + "/header");
        ref.child("firstName").setValue(firstName.getText().toString());
        ref.child("lastName").setValue(lastName.getText().toString());
        ref.child("email").setValue(email.getText().toString());
        ref.child("phone").setValue(phone.getText().toString());
        ref.child("address").setValue(address.getText().toString());
        ref.child("city").setValue(city.getText().toString());
        ref.child("state").setValue(state.getSelectedItem().toString());
        ref.child("zipCode").setValue(zipcode.getText().toString());
        finish();
    }
}
