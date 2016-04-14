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
import android.widget.TextView;

import com.example.duyve.myapplication.Classes.FireaseReference;
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
    private ArrayList<FireaseReference> referenceViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume_references);
        id = getIntent().getStringExtra("id");

        final Firebase ref = new Firebase("https://sizzling-torch-8367.firebaseio.com/users/" + id + "/references");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                referenceViews = new ArrayList<>();
                LinearLayout layout = (LinearLayout) findViewById(R.id.EditReferencesLinearLayout);
                layout.removeAllViews();
                for (DataSnapshot reference : dataSnapshot.getChildren()) {
                    LinearLayout referenceLayout = new LinearLayout(EditReferencesActivity.this);
                    referenceLayout.setOrientation(LinearLayout.VERTICAL);
                    referenceLayout.setBackgroundColor(Color.WHITE);
                    referenceLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onReferenceClick(v);
                        }
                    });

                    TextView nameView = new TextView(EditReferencesActivity.this);
                    nameView.setTextSize(20);
                    nameView.setTextColor(Color.BLACK);
                    TextView relationView = new TextView(EditReferencesActivity.this);
                    TextView emailView = new TextView(EditReferencesActivity.this);
                    TextView phoneView = new TextView(EditReferencesActivity.this);

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

    public void onReferenceClick(View view){
        final LinearLayout layout = (LinearLayout) view;
        new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this reference?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        for(int i = 0; i<referenceViews.size();i++){
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

        EditText referenceName = (EditText) findViewById(R.id.EditReferencesTextName);
        EditText referenceRelation = (EditText) findViewById(R.id.EditReferencesTextRelaton);
        EditText referenceEmail = (EditText) findViewById(R.id.EditReferencesTextEmail);
        EditText referencePhone = (EditText) findViewById(R.id.EditReferencesTextPhone);
        EditText[] arr = {referenceName, referenceRelation, referenceEmail, referencePhone};
        //Check to make sue they are not empty
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

        ref.push().setValue(map);
        for(EditText text: arr){
            text.setText("");
        }
    }

}
