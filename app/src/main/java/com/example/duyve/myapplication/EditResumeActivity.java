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
        setContentView(R.layout.activity_edit_resume);
    }

    public void onClick(View view){
        TextView text;
        int request;
        switch(view.getId()){
            case R.id.button:
                text = (TextView) findViewById(R.id.textView2);
                request = 1;
                break;
            case R.id.button2:
                text = (TextView) findViewById(R.id.textView4);
                request = 2;
                break;
            case R.id.button3:
                text = (TextView) findViewById(R.id.textView6);
                request = 3;
                break;
            case R.id.button4:
                text = (TextView) findViewById(R.id.textView8);
                request = 4;
                break;
            case R.id.button5:
                text = (TextView) findViewById(R.id.textView10);
                request = 5;
                break;
            case R.id.button6:
                text = (TextView) findViewById(R.id.textView12);
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
    public void onActivityResult(int requestCode, int returnCode, Intent data){
        if(returnCode == RESULT_OK){
            TextView editedText;
            switch(requestCode) {
                case 1:
                    editedText = (TextView) findViewById(R.id.textView2);
                    break;
                case 2:
                    editedText = (TextView) findViewById(R.id.textView4);
                    break;
                case 3:
                    editedText = (TextView) findViewById(R.id.textView6);
                    break;
                case 4:
                    editedText = (TextView) findViewById(R.id.textView8);
                    break;
                case 5:
                    editedText = (TextView) findViewById(R.id.textView10);
                    break;
                case 6:
                    editedText = (TextView) findViewById(R.id.textView12);
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
            setContentView(R.layout.activity_edit_section);
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