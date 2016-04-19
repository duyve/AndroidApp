package com.example.duyve.myapplication.Settings;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.duyve.myapplication.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_about);

        TextView creators = (TextView)findViewById(R.id.textView3);
        TextView tristan = (TextView)findViewById(R.id.textView4);
        TextView willem = (TextView)findViewById(R.id.textView5);
        TextView ryan = (TextView)findViewById(R.id.textView6);
        TextView ethan = (TextView)findViewById(R.id.textView7);
        TextView info = (TextView)findViewById(R.id.textView8);
        TextView version = (TextView)findViewById(R.id.textView2);

        Typeface main = Typeface.createFromAsset(getAssets(), "fonts/Champagne & Limousines.ttf");

        creators.setTypeface(main);
        tristan.setTypeface(main);
        willem.setTypeface(main);
        ryan.setTypeface(main);
        ethan.setTypeface(main);
        info.setTypeface(main);
        version.setTypeface(main);
    }
}
