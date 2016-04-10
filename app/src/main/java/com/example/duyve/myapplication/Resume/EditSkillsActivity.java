package com.example.duyve.myapplication.Resume;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class EditSkillsActivity extends AppCompatActivity {
    private String id;
    private ArrayList<Skill> skillViews = new ArrayList<>();

    private class Skill{
        String id;
        TextView skill;

        public Skill(String id, TextView skill) {
            this.id = id;
            this.skill = skill;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public TextView getSkill() {
            return skill;
        }

        public void setSkill(TextView skill) {
            this.skill = skill;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_resume_section);
        id = getIntent().getStringExtra("id");
        Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id + "/skills");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                skillViews = new ArrayList<>();
                LinearLayout layout = (LinearLayout) findViewById(R.id.EditSkillsLinearLayout);
                layout.removeAllViews();
                for (DataSnapshot skill : dataSnapshot.getChildren()) {
                    TextView textView = new TextView(EditSkillsActivity.this);
                    textView.setText(skill.getValue().toString());
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onSkillClick(v);
                        }
                    });
                    skillViews.add(new Skill(skill.getKey(), textView));
                }
                for (int i = 0; i < skillViews.size(); i++) {
                    layout.addView(skillViews.get(i).getSkill(), i);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void onSkillClick(final View view){
        new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this skill?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        for(int i = 0; i<skillViews.size();i++){
                            if(skillViews.get(i).getSkill() == view){
                                Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id + "/skills/" +skillViews.get(i).getId());
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
        Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id + "/skills");
        EditText skill = (EditText) findViewById(R.id.EditSkillsTextNew);
        ref.push().setValue(skill.getText().toString());
    }
}
