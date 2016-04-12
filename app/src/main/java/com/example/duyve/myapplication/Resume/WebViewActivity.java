package com.example.duyve.myapplication.Resume;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.duyve.myapplication.R;

public class WebViewActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        WebView webview = new WebView(this);
        String id = getIntent().getStringExtra("id");
        setContentView(webview);
        WebSettings s = webview.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        webview.loadUrl("https://sizzling-torch-8367.firebaseapp.com?username=" + id);
    }
}
