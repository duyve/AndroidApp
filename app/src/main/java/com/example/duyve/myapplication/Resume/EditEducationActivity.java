package com.example.duyve.myapplication.Resume;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    private ArrayList<FirebaseEducation> educationViews = new ArrayList<>();
    private FirebaseEducation selected;
    private EditText educationName;
    private EditText educationStartDate;
    private EditText educationEndDate;
    private EditText educationCity;
    private EditText educationInfo;
    private Spinner educationState;
    private Button submit;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume_education);
        educationName = (EditText) findViewById(R.id.EditEducationTextName);
        educationStartDate = (EditText) findViewById(R.id.EditEducationTextStartDate);
        educationEndDate = (EditText) findViewById(R.id.EditEducationTextEndDate);
        educationCity = (EditText) findViewById(R.id.EditEducationTextCity);
        educationInfo = (EditText) findViewById(R.id.EditEducationTextInfo);
        educationState = (Spinner) findViewById(R.id.EditEducationSpinnerState);
        submit = (Button) findViewById(R.id.EditEducationButtonSave);
        id = getIntent().getStringExtra("id");


        final Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id + "/education");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                educationViews = new ArrayList<>();
                LinearLayout layout = (LinearLayout) findViewById(R.id.EditEducationLinearLayout);
                layout.removeAllViews();
                for (DataSnapshot education : dataSnapshot.getChildren()) {
                    FirebaseEducation edu = new FirebaseEducation(
                            EditEducationActivity.this,
                            education.getKey(),
                            education.child("name").getValue().toString(),
                            education.child("startDate").getValue().toString(),
                            education.child("endDate").getValue().toString(),
                            education.child("city").getValue().toString(),
                            education.child("state").getValue().toString(),
                            education.child("info").getValue().toString());

                    edu.getLayout().setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            onEducationLongClick(v);
                            return true;
                        }
                    });
                    edu.getLayout().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onEducationClick(v);
                        }
                    });
                    educationViews.add(edu);
                }
                for (int i = 0; i < educationViews.size(); i++) {
                    layout.addView(educationViews.get(i).getLayout(), i);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    public void onEducationClick(View view){
        submit.setText("Edit Education");
        for(int i = 0; i<educationViews.size();i++){
            if(educationViews.get(i).getLayout() == view){
                selected = educationViews.get(i);
            }
        }
        educationName.setText(selected.getSchoolName());
        educationStartDate.setText(selected.getStartDate());
        educationEndDate.setText(selected.getEndDate());
        educationCity.setText(selected.getCity());
        educationInfo.setText(selected.getInfo());

        //Get the state Spinner to automatically select state
        String compareValue = selected.getState();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditEducationActivity.this, R.array.states, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        educationState.setAdapter(adapter);
        if (!compareValue.equals(null)) {
            int spinnerPosition = adapter.getPosition(compareValue);
            educationState.setSelection(spinnerPosition);
        }
    }

    public void onEducationLongClick(View view) {
        final LinearLayout layout = (LinearLayout) view;

        new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this school?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < educationViews.size(); i++) {
                            if(educationViews.get(i) == selected){
                                selected = null;
                                submit.setText("Save new education");
                                EditText[] arr = {educationName, educationCity, educationStartDate, educationEndDate, educationInfo};
                                for(EditText text: arr){
                                    text.setText("");
                                }
                            }
                            if (educationViews.get(i).getLayout() == layout) {
                                layout.setAlpha((float) 0.5);
                                Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id + "/education/" + educationViews.get(i).getId());
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
        Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id + "/education");

        EditText[] arr = {educationName, educationCity, educationStartDate, educationEndDate, educationInfo};
        //Check to make sure they are not empty
        for (EditText text : arr) {
            if (TextUtils.isEmpty(text.getText().toString())) {
                return;
            }
        }
        Map<String, String> map = new HashMap<>();
        map.put("name", educationName.getText().toString());
        map.put("startDate", educationStartDate.getText().toString());
        map.put("endDate", educationEndDate.getText().toString());
        map.put("city", educationCity.getText().toString());
        map.put("state", educationState.getSelectedItem().toString());
        map.put("info", educationInfo.getText().toString());
        if(selected == null){
            ref.push().setValue(map);
        }
        else {
            ref.child(selected.getId()).setValue(map);
            submit.setText("Save new education");
            selected = null;
        }

        for (EditText text : arr) {
            text.setText("");
        }
    }
}