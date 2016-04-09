package com.example.duyve.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by Ethan on 4/9/2016.
 */
public class EditWorkActivity extends AppCompatActivity{

    private EditText job;
    private EditText location;
    private EditText start;
    private EditText end;
    private EditText description;
    private ArrayList<View> work;
    private ArrayList<User.Work> experience;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        /**
         * if user work information exists add views with work info
         * else add empty view
         * outside of if-else add optional add work experience button which when clicked adds empty view
         */

        for(){
            experience.add(User.experience.get(i));
        }
        if (User.experience.size() != null) {

        }
    }
}
