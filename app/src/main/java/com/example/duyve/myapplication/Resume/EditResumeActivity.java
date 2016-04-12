package com.example.duyve.myapplication.Resume;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.duyve.myapplication.Classes.ActivityCode;
import com.example.duyve.myapplication.Classes.FirebaseEducation;
import com.example.duyve.myapplication.Classes.FirebaseExperience;
import com.example.duyve.myapplication.Classes.FirebaseString;
import com.example.duyve.myapplication.Classes.FireaseReference;
import com.example.duyve.myapplication.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class EditResumeActivity extends Activity {
    private String id;
    private ArrayList<FirebaseEducation> eduactionViews;
    private ArrayList<FirebaseExperience> experienceViews;
    private ArrayList<FireaseReference> referenceViews;
    private ArrayList<FirebaseString> skillViews;
    private ArrayList<FirebaseString> activityViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_resume);
        id = getIntent().getStringExtra("id");
        loadHeader();
        loadSkills();
        loadReferences();
        loadExperiences();
        loadEducation();
        loadActivities();
    }

    public void onClick(View view) {

        int request;
        Class<?> intentClass;

        switch (view.getId()) {
            case R.id.EditResumeButtonEditHeader:
                request = ActivityCode.EDIT_HEADER;
                intentClass = EditHeaderActivity.class;
                break;
            case R.id.EditResumeButtonEditEducation:
                request = ActivityCode.EDIT_EDUCATION;
                intentClass = EditEducationActivity.class;
                break;
            case R.id.EditResumeButtonExperience:
                request = ActivityCode.EDIT_EXPERIENCES;
                intentClass = EditExperiencesActivity.class;
                break;
            case R.id.EditResumeButtonActivities:
                request = ActivityCode.EDIT_ACTIVITIES;
                intentClass = EditActivitiesActivity.class;
                break;
            case R.id.EditResumeButtonSkills:
                request = ActivityCode.EDIT_SKILLS;
                intentClass = EditSkillsActivity.class;
                break;
            case R.id.EditResumeButtonReferences:
                request = ActivityCode.EDIT_REFERENCES;
                intentClass = EditReferencesActivity.class;
                break;
            default:
                throw new IllegalStateException();
        }
        Intent i = new Intent(this, intentClass);
        i.putExtra("id", id);
        startActivityForResult(i, request);
    }

    public void loadHeader(){
        Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id +"/header");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LinearLayout layout = (LinearLayout) findViewById(R.id.EditResumeLinearLayoutHeader);
                layout.removeAllViews();

                TextView name = new TextView(EditResumeActivity.this);
                TextView email = new TextView(EditResumeActivity.this);
                TextView phone = new TextView(EditResumeActivity.this);
                TextView address = new TextView(EditResumeActivity.this);
                TextView location = new TextView(EditResumeActivity.this);

                TextView[] arr = {name, email, phone, address, location};

                name.setText("Name: " + dataSnapshot.child("firstName").getValue().toString() + " " + dataSnapshot.child("lastName").getValue().toString());
                email.setText("Email: " + dataSnapshot.child("email").getValue().toString());
                phone.setText("Phone: " + dataSnapshot.child("phone").getValue().toString());
                address.setText("Address: " + dataSnapshot.child("address").getValue().toString());
                location.setText("         " + dataSnapshot.child("city").getValue().toString() + ", " + dataSnapshot.child("state").getValue().toString() + "  " + dataSnapshot.child("zipCode").getValue().toString());

                for(int i = 0; i< arr.length; i++){
                    layout.addView(arr[i],i);
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    public void loadSkills(){
        Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id +"/skills");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                skillViews = new ArrayList<>();
                LinearLayout layout = (LinearLayout) findViewById(R.id.EditResumeLinearLayoutSkills);
                layout.removeAllViews();
                for (DataSnapshot skill : dataSnapshot.getChildren()) {
                    TextView textView = new TextView(EditResumeActivity.this);
                    textView.setText(skill.getValue().toString());
                    skillViews.add(new FirebaseString(skill.getKey(), textView));
                }
                for (int i = 0; i < skillViews.size(); i++) {
                    layout.addView(skillViews.get(i).getTextview(), i);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void loadReferences(){
        final Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id + "/references");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                referenceViews = new ArrayList<>();
                LinearLayout layout = (LinearLayout) findViewById(R.id.EditResumeLinearLayoutReferences);
                layout.removeAllViews();
                for (DataSnapshot reference : dataSnapshot.getChildren()) {
                    LinearLayout referenceLayout = new LinearLayout(EditResumeActivity.this);
                    referenceLayout.setOrientation(LinearLayout.VERTICAL);
                    referenceLayout.setBackgroundColor(Color.WHITE);

                    TextView nameView = new TextView(EditResumeActivity.this);
                    nameView.setTextSize(20);
                    nameView.setTextColor(Color.BLACK);
                    TextView relationView = new TextView(EditResumeActivity.this);
                    TextView emailView = new TextView(EditResumeActivity.this);
                    TextView phoneView = new TextView(EditResumeActivity.this);

                    nameView.setText(reference.child("name").getValue().toString());
                    relationView.setText("Relation: " + reference.child("relation").getValue().toString());
                    emailView.setText("Email: " + reference.child("email").getValue().toString());
                    phoneView.setText("Phone: " + reference.child("phone").getValue().toString());

                    referenceLayout.addView(nameView, 0);
                    referenceLayout.addView(relationView, 1);
                    referenceLayout.addView(emailView, 2);
                    referenceLayout.addView(phoneView,3);


                    referenceViews.add(new FireaseReference(reference.getKey(), nameView, relationView, emailView, phoneView, referenceLayout));
                }
                for (int i = 0; i < referenceViews.size(); i++) {
                    layout.addView(referenceViews.get(i).getLayout(), i);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }

    public void loadActivities(){
        Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id +"/activities");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                activityViews = new ArrayList<>();
                LinearLayout layout = (LinearLayout) findViewById(R.id.EditResumeLinearLayoutActivities);
                layout.removeAllViews();
                for (DataSnapshot activity : dataSnapshot.getChildren()) {
                    TextView textView = new TextView(EditResumeActivity.this);
                    textView.setText(activity.getValue().toString());
                    activityViews.add(new FirebaseString(activity.getKey(), textView));
                }
                for (int i = 0; i < activityViews.size(); i++) {
                    layout.addView(activityViews.get(i).getTextview(), i);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    public void loadEducation(){
        Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id +"/education");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eduactionViews = new ArrayList<>();
                LinearLayout layout = (LinearLayout) findViewById(R.id.EditResumeLinearLayoutEducation);
                layout.removeAllViews();
                for (DataSnapshot eduaction : dataSnapshot.getChildren()) {
                    LinearLayout eduactionLayout = new LinearLayout(EditResumeActivity.this);
                    eduactionLayout.setOrientation(LinearLayout.VERTICAL);
                    eduactionLayout.setBackgroundColor(Color.WHITE);
                    TextView nameView = new TextView(EditResumeActivity.this);
                    nameView.setTextSize(20);
                    nameView.setTextColor(Color.BLACK);
                    TextView durationLoactionView = new TextView(EditResumeActivity.this);
                    TextView infoView = new TextView(EditResumeActivity.this);

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

    public void loadExperiences(){
        final Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id + "/experiences");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                experienceViews = new ArrayList<>();
                LinearLayout layout = (LinearLayout) findViewById(R.id.EditResumeLinearLayoutExperience);
                layout.removeAllViews();
                for (DataSnapshot experience : dataSnapshot.getChildren()) {
                    LinearLayout experienceLayout = new LinearLayout(EditResumeActivity.this);
                    experienceLayout.setOrientation(LinearLayout.VERTICAL);
                    experienceLayout.setBackgroundColor(Color.WHITE);

                    TextView nameView = new TextView(EditResumeActivity.this);
                    nameView.setTextSize(20);
                    nameView.setTextColor(Color.BLACK);
                    TextView positionView = new TextView(EditResumeActivity.this);
                    TextView durationLoactionView = new TextView(EditResumeActivity.this);
                    TextView infoView = new TextView(EditResumeActivity.this);

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

}