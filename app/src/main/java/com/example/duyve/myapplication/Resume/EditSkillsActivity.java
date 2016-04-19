package com.example.duyve.myapplication.Resume;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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

public class EditSkillsActivity extends AppCompatActivity {
    private String id;
    private ArrayList<FirebaseString> skillViews = new ArrayList<>();
    private FirebaseString selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume_skills);
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
                    textView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            onSkillLongClick(v);
                            return true;
                        }
                    });
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onSkillClick(v);
                        }
                    });
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

    public void onSkillClick(View view){

        EditText skill = (EditText) findViewById(R.id.EditSkillsTextNew);
        Button submit = (Button) findViewById(R.id.EditSkillsButtonSave);
        submit.setText("Edit Skill");
        for(int i = 0; i<skillViews.size();i++){
            if(skillViews.get(i).getTextview() == view){
                selected = skillViews.get(i);
            }
        }
        skill.setText(selected.getTextview().getText().toString());

    }

    public void onSkillLongClick(final View view){
        new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this skill?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        for(int i = 0; i<skillViews.size();i++){
                            if(skillViews.get(i) == selected){
                                selected = null;
                                Button button = (Button) findViewById(R.id.EditSkillsButtonSave);
                                button.setText("Save new skill");
                                EditText skill = (EditText) findViewById(R.id.EditSkillsTextNew);
                                skill.setText("");
                            }
                            if(skillViews.get(i).getTextview() == view){
                                view.setAlpha((float) 0.5);
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
        if(!TextUtils.isEmpty(skill.getText().toString())){
            if(selected == null){
                Toast.makeText(EditSkillsActivity.this, "Added Skill Successfully!", Toast.LENGTH_SHORT).show();
                ref.push().setValue(skill.getText().toString());
            }
            else{
                Toast.makeText(EditSkillsActivity.this, "Edited Skill Successfully!", Toast.LENGTH_SHORT).show();
                ref.child(selected.getId()).setValue(skill.getText().toString());
                Button button = (Button) view;
                button.setText("Save new skill");
                selected = null;
            }
            skill.setText("");
        }
    }
}
