package com.example.duyve.myapplication.Resume;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.duyve.myapplication.R;

public class WebViewActivity extends AppCompatActivity{

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        WebView webview = new WebView(this);
        id= getIntent().getStringExtra("id");
        setContentView(webview);
        WebSettings s = webview.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setAllowUniversalAccessFromFileURLs(true);
        s.setAllowFileAccessFromFileURLs(true);
        webview.loadUrl("https://sizzling-torch-8367.firebaseapp.com?username=" + id);
    }
}
