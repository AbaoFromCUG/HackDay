package com.cuger.demon.demon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GameActivity extends AppCompatActivity {
    WebView gameWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameWebView= (WebView) findViewById(R.id.game_webview);
        gameWebView.getSettings().setJavaScriptEnabled(true);
        gameWebView.loadUrl("http://139.198.14.233/game/");
        gameWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

        });

    }
}
