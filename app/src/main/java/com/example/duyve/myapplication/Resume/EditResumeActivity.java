package com.example.duyve.myapplication.Resume;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.duyve.myapplication.Classes.ActivityCode;
import com.example.duyve.myapplication.Classes.Education;
import com.example.duyve.myapplication.Classes.Experience;
import com.example.duyve.myapplication.R;
import com.example.duyve.myapplication.Classes.Reference;
import com.example.duyve.myapplication.Settings.AboutActivity;
import com.example.duyve.myapplication.Classes.User;
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
        String id = new Firebase("https://sizzling-torch-8367.firebaseio.com").getAuth().getUid();
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
                intentClass = AboutActivity.class;
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
                intentClass = AboutActivity.class;
                break;
            case R.id.EditResumeButtonSkills:
                request = ActivityCode.EDIT_EDUCATION;
                intentClass = AboutActivity.class;
                break;
            case R.id.EditResumeButtonReferences:
                request = ActivityCode.EDIT_REFERENCES;
                intentClass = AboutActivity.class;
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
                user.setFirstName((String) header.child("firstName").getValue());
                user.setLastName((String) header.child("lastName").getValue());
                user.setEmail((String) header.child("email").getValue());
                user.setCareerTitle((String) header.child("careerTitle").getValue());
                user.setAddress((String) header.child("address").getValue());
                user.setCity((String) header.child("city").getValue());
                user.setState((String) header.child("state").getValue());
                user.setPhone((String) header.child("phone").getValue());
                user.setZip((String) header.child("zip").getValue());

                //GET ACTIVITY ELEMENTS
                Iterable<DataSnapshot> activities = dataSnapshot.child("activities").getChildren();
                for(DataSnapshot activitiy: activities){
                    user.addActivity((String) activitiy.getValue());
                }

                //GET SKILL ELEMENTS
                Iterable<DataSnapshot> skills = dataSnapshot.child("skills").getChildren();
                for(DataSnapshot skill: skills){
                    user.addSkill((String) skill.getValue());
                }

                //GET WORK ELEMENTS
                Iterable<DataSnapshot> experiences = dataSnapshot.child("experience").getChildren();
                for(DataSnapshot experience: experiences){
                    String name = (String) experience.child("name").getValue();
                    String startDate = (String) experience.child("startDate").getValue();
                    String endDate = (String) experience.child("endDate").getValue();
                    String city = (String) experience.child("city").getValue();
                    String state = (String) experience.child("state").getValue();
                    String info = (String) experience.child("info").getValue();
                    String position = (String) experience.child("position").getValue();
                    //ADD INFORMATION TO USER
                    user.addExperience(new Experience(name, startDate, endDate, city, state, info, position));
                }

                //ADD EDUCATION ELEMENTS
                Iterable<DataSnapshot> educations = dataSnapshot.child("education").getChildren();

                for(DataSnapshot education: educations){
                    String name = (String) education.child("name").getValue();
                    String startDate = (String) education.child("startDate").getValue();
                    String endDate = (String) education.child("endDate").getValue();
                    String city = (String) education.child("city").getValue();
                    String state = (String) education.child("state").getValue();
                    String info = (String) education.child("info").getValue();
                    //ADD INFORMATION TO USER
                    user.addEducation(new Education(name, startDate, endDate, city, state, info));
                }

                //GET REFERENCE ELEMENTS
                Iterable<DataSnapshot> references = dataSnapshot.child("references").getChildren();
                for(DataSnapshot reference: references){
                    String name = (String) reference.child("name").getValue();
                    String title = (String) reference.child("relation").getValue();
                    String email = (String) reference.child("email").getValue();
                    String phone = (String) reference.child("phone").getValue();


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