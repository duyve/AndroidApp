package com.example.duyve.myapplication.Resume;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.duyve.myapplication.R;

public class ViewResumeActivity extends AppCompatActivity{

    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        id = getIntent().getStringExtra("id");
        setContentView(R.layout.resume_view);
        WebView webview = (WebView) findViewById(R.id.ViewResumeWebView);
        WebSettings s = webview.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        webview.loadUrl("https://sizzling-torch-8367.firebaseapp.com?username=" + id);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "https://sizzling-torch-8367.firebaseapp.com?username=" + id);
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Check out my Resume!");
            startActivity(Intent.createChooser(intent, "Share"));
            return true;
    }
}
