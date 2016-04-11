package com.example.duyve.myapplication.Resume;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.duyve.myapplication.Classes.ActivityCode;
import com.example.duyve.myapplication.Classes.Education;
import com.example.duyve.myapplication.Classes.Experience;
import com.example.duyve.myapplication.Classes.Reference;
import com.example.duyve.myapplication.Classes.User;
import com.example.duyve.myapplication.R;
import com.example.duyve.myapplication.Settings.AboutActivity;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class EditResumeActivity extends Activity {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_resume);
        String id = getIntent().getStringExtra("id");
        user = new User();
        user.setId(id);
        loadUser(user.getId());
    }

    public void onClick(View view){

        int request;
        Class<?> intentClass;

        switch(view.getId()){
            case R.id.EditResumeButtonEditHeader:
                request = ActivityCode.EDIT_HEADER;
                intentClass = EditHeaderActivity.class;
                break;
            case R.id.EditResumeButtonEditEducation:
                request = ActivityCode.EDIT_EDUCATION;
                intentClass = AboutActivity.class;
                break;
            case R.id.EditResumeButtonExperience:
                request = ActivityCode.EDIT_EXPERIENCES;
                intentClass = AboutActivity.class;
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
        i.putExtra("id", user.getId());
        startActivityForResult(i, request);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(resultCode == RESULT_OK){
            switch(requestCode) {
                case ActivityCode.EDIT_HEADER:
                    break;
                case ActivityCode.EDIT_EDUCATION:
                    break;
                case ActivityCode.EDIT_EXPERIENCES:
                    break;
                case ActivityCode.EDIT_ACTIVITIES:
                    break;
                case ActivityCode.EDIT_SKILLS:
                    //Update Skills Section
                    break;
                case ActivityCode.EDIT_REFERENCES:
                    break;
                default:
                    throw new IllegalStateException();
            }
        }
    }

    public void loadUser(String id){
        Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               //GET HEADER ELEMENTS
                DataSnapshot header = dataSnapshot.child("header");
                user.setFirstName( header.child("firstName").getValue().toString());
                user.setLastName( header.child("lastName").getValue().toString());
                user.setEmail( header.child("email").getValue().toString());
                user.setCareerTitle( header.child("careerTitle").getValue().toString());
                user.setAddress( header.child("address").getValue().toString());
                user.setCity( header.child("city").getValue().toString());
                user.setState( header.child("state").getValue().toString());
                user.setPhone( header.child("phone").getValue().toString());
                user.setZip( header.child("zipCode").getValue().toString());

                //GET ACTIVITY ELEMENTS
                Iterable<DataSnapshot> activities = dataSnapshot.child("activities").getChildren();
                for(DataSnapshot activitiy: activities){
                    user.addActivity( activitiy.getValue().toString());
                }

                //GET SKILL ELEMENTS
                Iterable<DataSnapshot> skills = dataSnapshot.child("skills").getChildren();
                for(DataSnapshot skill: skills){
                    user.addSkill( skill.getValue().toString());
                }

                //GET WORK ELEMENTS
                Iterable<DataSnapshot> experiences = dataSnapshot.child("experience").getChildren();
                for(DataSnapshot experience: experiences){
                    String name = experience.child("name").getValue().toString();
                    String startDate = experience.child("startDate").getValue().toString();
                    String endDate = experience.child("endDate").getValue().toString();
                    String city = experience.child("city").getValue().toString();
                    String state = experience.child("state").getValue().toString();
                    String info = experience.child("info").getValue().toString();
                    String position = experience.child("position").getValue().toString();
                    //ADD INFORMATION TO USER
                    user.addExperience(new Experience(name, startDate, endDate, city, state, info, position));
                }

                //ADD EDUCATION ELEMENTS
                Iterable<DataSnapshot> educations = dataSnapshot.child("education").getChildren();

                for(DataSnapshot education: educations){
                    String name = education.child("name").getValue().toString();
                    String startDate = education.child("startDate").getValue().toString();
                    String endDate = education.child("endDate").getValue().toString();
                    String city = education.child("city").getValue().toString();
                    String state = education.child("state").getValue().toString();
                    String info = education.child("info").getValue().toString();
                    //ADD INFORMATION TO USER
                    user.addEducation(new Education(name, startDate, endDate, city, state, info));
                }

                //GET REFERENCE ELEMENTS
                Iterable<DataSnapshot> references = dataSnapshot.child("references").getChildren();
                for(DataSnapshot reference: references){
                    String name = reference.child("name").getValue().toString();
                    String title = reference.child("relation").getValue().toString();
                    String email = reference.child("email").getValue().toString();
                    String phone = reference.child("phone").getValue().toString();


                    //ADD INFORMATION TO USER
                    user.addReference(new Reference(name, title, email, phone));
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                user = null;
            }
        });
    }
}