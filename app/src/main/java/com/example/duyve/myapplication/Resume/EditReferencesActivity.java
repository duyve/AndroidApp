package com.example.duyve.myapplication.Resume;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    private ArrayList<Reference> referenceViews = new ArrayList<>();

    private class Reference {
        String id;
        LinearLayout layout;
        TextView name;
        TextView relation;
        TextView email;
        TextView phone;

        public Reference(String id, TextView phone, TextView email, TextView relation, TextView name, LinearLayout layout) {
            this.id = id;
            this.phone = phone;
            this.email = email;
            this.relation = relation;
            this.name = name;
            this.layout = layout;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public TextView getPhone() {
            return phone;
        }

        public void setPhone(TextView phone) {
            this.phone = phone;
        }

        public TextView getEmail() {
            return email;
        }

        public void setEmail(TextView email) {
            this.email = email;
        }

        public TextView getRelation() {
            return relation;
        }

        public void setRelation(TextView relation) {
            this.relation = relation;
        }

        public TextView getName() {
            return name;
        }

        public void setName(TextView name) {
            this.name = name;
        }

        public LinearLayout getLayout() {
            return layout;
        }

        public void setLayout(LinearLayout layout) {
            this.layout = layout;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_resume_references);
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


                    referenceViews.add(new Reference(reference.getKey(), nameView, relationView, emailView, phoneView, referenceLayout));
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
