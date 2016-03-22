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
        TextView text = (TextView) findViewById(R.id.textView2);
        String value = text.getText().toString();
        Intent i = new Intent(this, editHeaderActivity.class);
        i.putExtra("yourkey", value);
        startActivityForResult(i, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int returnCode, Intent data){
        if(requestCode == 1 && returnCode == 2){
            TextView editedText = (TextView) findViewById(R.id.textView2);
            if(data.hasExtra("returnkey")){
                String result = data.getExtras().getString("returnkey");
                if(result != null && result.length() > 0){
                    editedText.setText(result);
                }
            }
        }
    }

    public static class editHeaderActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_edit_header);
            Bundle extras = getIntent().getExtras();
            String value = extras.getString("yourkey");
            EditText text = (EditText) findViewById(R.id.headEditText);
            text.setText(value);
        }
        public void onClick(View view){
            Intent i2 = new Intent();
            EditText text = (EditText) findViewById(R.id.headEditText);
            String back = text.getText().toString();
            i2.putExtra("returnkey", back);
            setResult(2, i2);
            super.finish();
        }
    }
}
