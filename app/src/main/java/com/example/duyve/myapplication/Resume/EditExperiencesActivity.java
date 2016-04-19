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
import com.example.duyve.myapplication.Classes.FirebaseExperience;
import com.example.duyve.myapplication.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditExperiencesActivity extends AppCompatActivity {

    private String id;
    private ArrayList<FirebaseExperience> experienceViews = new ArrayList<>();
    private FirebaseExperience selected;
    private EditText experienceName;
    private EditText experiencePosition;
    private EditText experienceStartDate;
    private EditText experienceEndDate;
    private EditText experienceCity;
    private EditText experienceInfo;
    private Spinner experienceState;
    private Button submit;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume_experiences);
        experienceName = (EditText) findViewById(R.id.EditExperiencesTextName);
        experiencePosition = (EditText) findViewById(R.id.EditExperiencesTextPosition);
        experienceStartDate = (EditText) findViewById(R.id.EditExperiencesTextStartDate);
        experienceEndDate = (EditText) findViewById(R.id.EditExperiencesTextEndDate);
        experienceCity = (EditText) findViewById(R.id.EditExperiencesTextCity);
        experienceInfo = (EditText) findViewById(R.id.EditExperiencesTextInfo);
        experienceState = (Spinner) findViewById(R.id.EditExperiencesSpinnerState);
        submit = (Button) findViewById(R.id.EditExperiencesButtonSave);
        id = getIntent().getStringExtra("id");

        final Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id + "/experiences");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                experienceViews = new ArrayList<>();
                LinearLayout layout = (LinearLayout) findViewById(R.id.EditExperiencesLinearLayout);
                layout.removeAllViews();
                for (DataSnapshot experience : dataSnapshot.getChildren()) {

                    FirebaseExperience exp = new FirebaseExperience(
                            EditExperiencesActivity.this,
                            experience.getKey(),
                            experience.child("position").getValue().toString(),
                            experience.child("name").getValue().toString(),
                            experience.child("startDate").getValue().toString(),
                            experience.child("endDate").getValue().toString(),
                            experience.child("city").getValue().toString(),
                            experience.child("state").getValue().toString(),
                            experience.child("info").getValue().toString());

                    exp.getLayout().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onExperienceClick(v);
                        }
                    });

                    exp.getLayout().setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            onExperienceLongClick(v);
                            return true;
                        }
                    });
                    experienceViews.add(exp);
                }
                for (int i = 0; i < experienceViews.size(); i++) {
                    layout.addView(experienceViews.get(i).getLayout(), i);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void onExperienceClick(View view){
        submit.setText("Edit Education");
        for(int i = 0; i<experienceViews.size();i++){
            if(experienceViews.get(i).getLayout() == view){
                selected = experienceViews.get(i);
            }
        }
        experienceName.setText(selected.getCompanyName());
        experiencePosition.setText(selected.getPosition());
        experienceStartDate.setText(selected.getStartDate());
        experienceEndDate.setText(selected.getEndDate());
        experienceCity.setText(selected.getCity());
        experienceInfo.setText(selected.getInfo());

        //Get the state Spinner to automatically select state
        String compareValue = selected.getState();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditExperiencesActivity.this, R.array.states, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        experienceState.setAdapter(adapter);
        if (!compareValue.equals(null)) {
            int spinnerPosition = adapter.getPosition(compareValue);
            experienceState.setSelection(spinnerPosition);
        }
    }

    public void onExperienceLongClick(View view) {
    final LinearLayout layout = (LinearLayout) view;

    new AlertDialog.Builder(this)
            .setTitle("Delete entry")
            .setMessage("Are you sure you want to delete this experience?")
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    for (int i = 0; i < experienceViews.size(); i++) {
                        if(experienceViews.get(i) == selected){
                            selected = null;
                            submit.setText("Save new experience");
                            EditText[] arr = {experienceName, experiencePosition, experienceCity, experienceStartDate, experienceEndDate, experienceInfo};
                            for(EditText text: arr){
                                text.setText("");
                            }
                        }
                        if (experienceViews.get(i).getLayout() == layout) {
                            layout.setAlpha((float) 0.5);
                            Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id + "/experiences/" + experienceViews.get(i).getId());
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
        Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id + "/experiences");

        EditText[] arr = {experienceName, experiencePosition, experienceCity, experienceStartDate, experienceEndDate, experienceInfo};
        //Check to make sure they are not empty
        for (EditText text : arr) {
            if (TextUtils.isEmpty(text.getText().toString())) {
                return;
            }
        }
        Map<String, String> map = new HashMap<>();
        map.put("name", experienceName.getText().toString());
        map.put("position", experiencePosition.getText().toString());
        map.put("startDate", experienceStartDate.getText().toString());
        map.put("endDate", experienceEndDate.getText().toString());
        map.put("city", experienceCity.getText().toString());
        map.put("state", experienceState.getSelectedItem().toString());
        map.put("info", experienceInfo.getText().toString());

        if(selected == null){
            ref.push().setValue(map);
        }
        else {
            ref.child(selected.getId()).setValue(map);
            submit.setText("Save new experience");
            selected = null;
        }
        for (EditText text : arr) {
            text.setText("");
        }
    }
}
