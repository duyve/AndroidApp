package com.example.duyve.myapplication.Resume;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.duyve.myapplication.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class EditActivitiesActivity extends AppCompatActivity {
    private String id;
    private ArrayList<Activity> activityViews = new ArrayList<>();

    private class Activity{
        String id;
        TextView activity;

        public Activity(String id, TextView activity) {
            this.id = id;
            this.activity = activity;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public TextView getActivity() {
            return activity;
        }

        public void setActivity(TextView activity) {
            this.activity = activity;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_resume_activities);
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
                    activityViews.add(new Activity(activity.getKey(), textView));
                }
                for (int i = 0; i < activityViews.size(); i++) {
                    layout.addView(activityViews.get(i).getActivity(), i);
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
                            if(activityViews.get(i).getActivity() == view){
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
        ref.push().setValue(activity.getText().toString());
    }
}
