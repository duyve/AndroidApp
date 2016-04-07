package com.example.duyve.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditResumeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_resume);
    }

    public void onClick(View view){
        TextView text;
        int request;
        switch(view.getId()){
            case R.id.EditResumeButtonEditHeader:
                text = (TextView) findViewById(R.id.EditResumeTextEducationInfo);
                request = ActivityCode.EDIT_HEADER;
                break;
            case R.id.EditResumeButtonEditEducation:
                text = (TextView) findViewById(R.id.EditResumeButtonEditEducation);
                request = 2;
                break;
            case R.id.EditResumeButtonExperience:
                text = (TextView) findViewById(R.id.EditResumeTextExperienceInfo);
                request = 3;
                break;
            case R.id.EditResumeButtonActivities:
                text = (TextView) findViewById(R.id.EditResumeTextActivitiesInfo);
                request = 4;
                break;
            case R.id.EditResumeButtonSkills:
                text = (TextView) findViewById(R.id.EditReumeTextSkillsInfo);
                request = 5;
                break;
            case R.id.EditResumeButtonReferences:
                text = (TextView) findViewById(R.id.EditResumeTextReferencesInfo);
                request = 6;
                break;
            default:
                throw new IllegalStateException();
        }
        String value = text.getText().toString();
        Intent i = new Intent(this, EditSectionActivity.class);
        i.putExtra("yourkey", value);
        startActivityForResult(i, request);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == ActivityCode.LOG_IN)
        {
            if (resultCode == RESULT_CANCELED)
            {
                Intent mainScreen = new Intent(this, MainActivity.class);
                mainScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                mainScreen.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(mainScreen);
            }

        }

        if(resultCode == RESULT_OK){
            TextView editedText;
            switch(requestCode) {
                case 1:
                    editedText = (TextView) findViewById(R.id.EditResumeTextHeaderInfo);
                    break;
                case 2:
                    editedText = (TextView) findViewById(R.id.EditResumeTextExperienceInfo);
                    break;
                case 3:
                    editedText = (TextView) findViewById(R.id.EditResumeTextExperienceInfo);
                    break;
                case 4:
                    editedText = (TextView) findViewById(R.id.EditResumeTextActivitiesInfo);
                    break;
                case 5:
                    editedText = (TextView) findViewById(R.id.EditReumeTextSkillsInfo);
                    break;
                case 6:
                    editedText = (TextView) findViewById(R.id.EditResumeTextReferencesInfo);
                    break;
                default:
                    throw new IllegalStateException();
            }
            if(data.hasExtra("returnkey")){
                String result = data.getExtras().getString("returnkey");
                if(result != null && result.length() > 0){
                    editedText.setText(result);
                }
            }
        }
    }

    public static class EditSectionActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.edit_resume_section);
            Bundle extras = getIntent().getExtras();
            String value = extras.getString("yourkey");
            EditText text = (EditText) findViewById(R.id.sectionEditText);
            text.setText(value);
        }
        public void onClick(View view){
            Intent i2 = new Intent();
            EditText text = (EditText) findViewById(R.id.sectionEditText);
            String back = text.getText().toString();
            i2.putExtra("returnkey", back);
            setResult(RESULT_OK, i2);
            super.finish();
        }
    }
}