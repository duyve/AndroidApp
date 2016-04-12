package com.example.duyve.myapplication.Resume;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.duyve.myapplication.Classes.FirebaseEducation;
import com.example.duyve.myapplication.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditEducationActivity extends AppCompatActivity {
    private String id;
    private ArrayList<FirebaseEducation> eduactionViews = new ArrayList<>();

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_resume_education);
        id = getIntent().getStringExtra("id");

        final Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id + "/education");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eduactionViews = new ArrayList<>();
                LinearLayout layout = (LinearLayout) findViewById(R.id.EditEducationLinearLayout);
                layout.removeAllViews();
                for (DataSnapshot eduaction : dataSnapshot.getChildren()) {
                    LinearLayout eduactionLayout = new LinearLayout(EditEducationActivity.this);
                    eduactionLayout.setOrientation(LinearLayout.VERTICAL);
                    eduactionLayout.setBackgroundColor(Color.WHITE);
                    eduactionLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onEducationClick(v);
                        }
                    });

                    TextView nameView = new TextView(EditEducationActivity.this);
                    nameView.setTextSize(20);
                    nameView.setTextColor(Color.BLACK);
                    TextView durationLoactionView = new TextView(EditEducationActivity.this);
                    TextView infoView = new TextView(EditEducationActivity.this);

                    nameView.setText(eduaction.child("name").getValue().toString());
                    durationLoactionView.setText(eduaction.child("city").getValue().toString() + ", " + eduaction.child("state").getValue().toString() + "  |  " + eduaction.child("startDate").getValue().toString() + "-" + eduaction.child("endDate").getValue().toString());
                    infoView.setText(eduaction.child("info").getValue().toString());

                    eduactionLayout.addView(nameView, 0);
                    eduactionLayout.addView(durationLoactionView, 1);
                    eduactionLayout.addView(infoView, 2);


                    eduactionViews.add(new FirebaseEducation(eduaction.getKey(), eduactionLayout, nameView, durationLoactionView, infoView));
                }
                for (int i = 0; i < eduactionViews.size(); i++) {
                    layout.addView(eduactionViews.get(i).getLayout(), i);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    public void onEducationClick(View view) {
        final LinearLayout layout = (LinearLayout) view;

        new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this school?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < eduactionViews.size(); i++) {
                            if (eduactionViews.get(i).getLayout() == layout) {
                                Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id + "/eduaction/" + eduactionViews.get(i).getId());
                                ref.removeValue();
                            }
                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void onSaveClick(View view) {
        Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id + "/eduactions");

        EditText eduactionName = (EditText) findViewById(R.id.EditEducationTextName);
        EditText eduactionStartDate = (EditText) findViewById(R.id.EditEducationTextStartDate);
        EditText eduactionEndDate = (EditText) findViewById(R.id.EditEducationTextEndDate);
        EditText eduactionCity = (EditText) findViewById(R.id.EditEducationTextCity);
        EditText eduactionInfo = (EditText) findViewById(R.id.EditEducationTextInfo);
        Spinner eduactionState = (Spinner) findViewById(R.id.EditEducationSpinnerState);
        EditText[] arr = {eduactionName, eduactionCity, eduactionStartDate, eduactionEndDate, eduactionInfo};
        //Check to make sue they are not empty
        for (EditText text : arr) {
            if (TextUtils.isEmpty(text.getText().toString())) {
                return;
            }
        }
        Map<String, String> map = new HashMap<>();
        map.put("name", eduactionName.getText().toString());
        map.put("startDate", eduactionStartDate.getText().toString());
        map.put("endDate", eduactionEndDate.getText().toString());
        map.put("city", eduactionCity.getText().toString());
        map.put("state", eduactionState.getSelectedItem().toString());
        map.put("info", eduactionInfo.getText().toString());

        ref.push().setValue(map);
        for (EditText text : arr) {
            text.setText("");
        }
    }
}