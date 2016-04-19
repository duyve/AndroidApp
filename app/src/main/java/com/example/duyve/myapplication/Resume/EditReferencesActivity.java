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

import com.example.duyve.myapplication.Classes.FirebaseReference;
import com.example.duyve.myapplication.Classes.FirebaseExperience;
import com.example.duyve.myapplication.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditReferencesActivity extends AppCompatActivity {

    private String id;
    private ArrayList<FirebaseReference> referenceViews = new ArrayList<>();
    private FirebaseReference selected;
    private EditText referenceName;
    private EditText referenceRelation;
    private EditText referenceEmail;
    private EditText referencePhone;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume_references);
        referenceName = (EditText) findViewById(R.id.EditReferencesTextName);
        referenceRelation = (EditText) findViewById(R.id.EditReferencesTextRelaton);
        referenceEmail = (EditText) findViewById(R.id.EditReferencesTextEmail);
        referencePhone = (EditText) findViewById(R.id.EditReferencesTextPhone);
        submit = (Button) findViewById(R.id.EditReferencesButtonSave);
        id = getIntent().getStringExtra("id");


        final Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id + "/references");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                referenceViews = new ArrayList<>();
                LinearLayout layout = (LinearLayout) findViewById(R.id.EditReferencesLinearLayout);
                layout.removeAllViews();
                for (DataSnapshot reference : dataSnapshot.getChildren()) {

                    FirebaseReference ref = new FirebaseReference(
                            EditReferencesActivity.this,
                            reference.getKey(),
                            reference.child("name").getValue().toString(),
                            reference.child("phone").getValue().toString(),
                            reference.child("email").getValue().toString(),
                            reference.child("relation").getValue().toString());
                    ref.getLayout().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onReferenceClick(v);
                        }
                    });
                    ref.getLayout().setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            onReferenceLongClick(v);
                            return true;
                        }
                    });

                    referenceViews.add(ref);
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

    public void onReferenceClick(View view){
        submit.setText("Edit Reference");
        for(int i = 0; i<referenceViews.size();i++){
            if(referenceViews.get(i).getLayout() == view){
                selected = referenceViews.get(i);
            }
        }
        referenceName.setText(selected.getName());
        referenceRelation.setText(selected.getRelation());
        referencePhone.setText(selected.getPhone());
        referenceEmail.setText(selected.getEmail());
    }

    public void onReferenceLongClick(View view){
        final LinearLayout layout = (LinearLayout) view;
        new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this reference?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        for(int i = 0; i<referenceViews.size();i++){
                            if(referenceViews.get(i) == selected){
                                selected = null;
                                submit.setText("Save new reference");
                                EditText[] arr = {referenceName, referenceRelation, referenceEmail, referencePhone};
                                for(EditText text: arr){
                                    text.setText("");
                                }
                            }
                            if(referenceViews.get(i).getLayout() == layout){
                                layout.setAlpha((float) 0.5);
                                Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id + "/references/" +referenceViews.get(i).getId());
                                ref.removeValue();
                            }
                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void onSaveClick(View view){
        Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id + "/references");

        EditText[] arr = {referenceName, referenceRelation, referenceEmail, referencePhone};
        //Check to make sure they are not empty
        for(EditText text: arr){
            if(TextUtils.isEmpty(text.getText().toString())){
                return;
            }
        }
        Map<String,String> map = new HashMap<>();
        map.put("name", referenceName.getText().toString());
        map.put("relation", referenceRelation.getText().toString());
        map.put("email", referenceEmail.getText().toString());
        map.put("phone", referencePhone.getText().toString());

        if(selected == null){
            ref.push().setValue(map);
        }
        else {
            ref.child(selected.getId()).setValue(map);
            submit.setText("Save new reference");
            selected = null;
        }
        for(EditText text: arr){
            text.setText("");
        }
    }

}
