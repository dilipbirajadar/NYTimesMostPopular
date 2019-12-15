package com.nytimesmostpopular.activity;


import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.nytimesmostpopular.R;
import com.nytimesmostpopular.security.AES;
import com.nytimesmostpopular.utilites.LogUtils;

/**
 * Created by Dilip Birajadar on 2019-12-15.
 */
public class DetailNews extends AppCompatActivity {
    private WebView webView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detal);
        webView = findViewById(R.id.webview);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        url = AES.decrypt(bundle.getString("url"));
        //url = AES.decrypt(getIntent().getStringExtra("url"));
        LogUtils.errorLog("news url:",url);
        webView.loadUrl(url);
    }
}
