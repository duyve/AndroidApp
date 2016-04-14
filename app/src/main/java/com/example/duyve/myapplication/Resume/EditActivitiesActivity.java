package com.example.duyve.myapplication.Resume;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duyve.myapplication.Classes.FirebaseString;
import com.example.duyve.myapplication.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class EditActivitiesActivity extends AppCompatActivity {
    private String id;
    private ArrayList<FirebaseString> activityViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume_activities);
        id = getIntent().getStringExtra("id");
        Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id + "/activities");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                activityViews = new ArrayList<>();
                LinearLayout layout = (LinearLayout) findViewById(R.id.EditActivitiesLinearLayout);
                layout.removeAllViews();
                for (DataSnapshot activity : dataSnapshot.getChildren()) {
                    TextView textView = new TextView(EditActivitiesActivity.this);
                    textView.setText(activity.getValue().toString());
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onActivityClick(v);
                        }
                    });
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

    public void onActivityClick(final View view){
        new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this activity?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        for(int i = 0; i<activityViews.size();i++){
                            if(activityViews.get(i).getTextview() == view){
                                view.setAlpha((float) 0.5);
                                Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id + "/activities/" +activityViews.get(i).getId());
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

    public void onSaveClick(View view){
        Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id + "/activities");
        EditText activity = (EditText) findViewById(R.id.EditActivitiesTextNew);
        if(!TextUtils.isEmpty(activity.getText().toString())){
            Toast.makeText(EditActivitiesActivity.this, "Added Skill Successfully!", Toast.LENGTH_SHORT).show();
            ref.push().setValue(activity.getText().toString());
            activity.setText("");
        }
    }
}
