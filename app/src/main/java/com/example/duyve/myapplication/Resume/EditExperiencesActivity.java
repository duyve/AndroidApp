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

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume_experiences);
        id = getIntent().getStringExtra("id");

        final Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id + "/experiences");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                experienceViews = new ArrayList<>();
                LinearLayout layout = (LinearLayout) findViewById(R.id.EditExperiencesLinearLayout);
                layout.removeAllViews();
                for (DataSnapshot experience : dataSnapshot.getChildren()) {
                    LinearLayout experienceLayout = new LinearLayout(EditExperiencesActivity.this);
                    experienceLayout.setOrientation(LinearLayout.VERTICAL);
                    experienceLayout.setBackgroundColor(Color.WHITE);
                    experienceLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onExperienceClick(v);
                        }
                    });

                    TextView nameView = new TextView(EditExperiencesActivity.this);
                    nameView.setTextSize(20);
                    nameView.setTextColor(Color.BLACK);
                    TextView positionView = new TextView(EditExperiencesActivity.this);
                    TextView durationLoactionView = new TextView(EditExperiencesActivity.this);
                    TextView infoView = new TextView(EditExperiencesActivity.this);

                    nameView.setText(experience.child("name").getValue().toString());
                    durationLoactionView.setText(experience.child("city").getValue().toString() + ", " + experience.child("state").getValue().toString() + "  |  " + experience.child("startDate").getValue().toString() + "-" + experience.child("endDate").getValue().toString());
                    positionView.setText("Position: " + experience.child("position").getValue().toString());
                    infoView.setText(experience.child("info").getValue().toString());

                    experienceLayout.addView(nameView, 0);
                    experienceLayout.addView(durationLoactionView, 1);
                    experienceLayout.addView(positionView, 2);
                    experienceLayout.addView(infoView, 3);


                    experienceViews.add(new FirebaseExperience(experience.getKey(), experienceLayout, nameView, durationLoactionView, positionView, infoView));
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

    public void onExperienceClick(View view) {
    final LinearLayout layout = (LinearLayout) view;

    new AlertDialog.Builder(this)
            .setTitle("Delete entry")
            .setMessage("Are you sure you want to delete this experience?")
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    for (int i = 0; i < experienceViews.size(); i++) {
                        if (experienceViews.get(i).getLayout() == layout) {
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

        EditText experienceName = (EditText) findViewById(R.id.EditExperiencesTextName);
        EditText experiencePosition = (EditText) findViewById(R.id.EditExperiencesTextPosition);
        EditText experienceStartDate = (EditText) findViewById(R.id.EditExperiencesTextStartDate);
        EditText experienceEndDate = (EditText) findViewById(R.id.EditExperiencesTextEndDate);
        EditText experienceCity = (EditText) findViewById(R.id.EditExperiencesTextCity);
        EditText experienceInfo = (EditText) findViewById(R.id.EditExperiencesTextInfo);
        Spinner experienceState = (Spinner) findViewById(R.id.EditExperiencesSpinnerState);
        EditText[] arr = {experienceName, experiencePosition, experienceCity, experienceStartDate, experienceEndDate, experienceInfo};
        //Check to make sue they are not empty
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

        ref.push().setValue(map);
        for (EditText text : arr) {
            text.setText("");
        }
    }
}
