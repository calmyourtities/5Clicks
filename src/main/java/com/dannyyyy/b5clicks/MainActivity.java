package com.dannyyyy.b5clicks;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void startGame(View v) throws IOException{
        Game.origin = ((EditText) findViewById(R.id.origin)).getText().toString();
        Game.topic = ((EditText) findViewById(R.id.topic)).getText().toString();
        //check for randomness \_(`-`)_/
        if (((EditText) findViewById(R.id.origin)).getText().toString().equals("random")) {
            String returnURL = getFinalURL("http://en.m.wikipedia.com/wiki/Special:Random");
            Game.origin = returnURL.substring(31, returnURL.length()-1).replace("/", "").replace("_", " ").replace("(", "").replace(")", "").replace("%27", "'");
        }
        if (((EditText) findViewById(R.id.topic)).getText().toString().toLowerCase().equals("random")) {
            Log.d("comparison alart", "it's random");

            /*final WebView webView = new WebView(this);
            webView.setVisibility(View.GONE);
            webView.loadUrl("http://en.m.wikipedia.com/wiki/Special:Random");
            webView.reload();
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            Log.d("comparison alart", "loaded page: " + webView.getUrl());
            webView.setWebViewClient(new WebViewClient() {
                public void onPageFinished(WebView view, String url) {
                    Log.d("comparison alart", "loaded page: " + webView.getUrl());
                    if (!webView.getUrl().contains("Special:Random")) {
                        Game.topic = webView.getUrl();
                    }
                }
            });*/
            String returnURL = getFinalURL("http://en.m.wikipedia.com/wiki/Special:Random");
            Game.topic = returnURL.substring(31, returnURL.length()-1).replace("/", "").replace("_", " ").replace("(", "").replace(")", "").replace("%27", "'");

        } else {
            Log.d("comparison alart", ((EditText) findViewById(R.id.topic)).getText().toString().toLowerCase() + "does not equal \"random\"");
        }
        Intent myIntent = new Intent(this, Game.class);
        startActivity(myIntent);
    }
    public static String getFinalURL(String url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setInstanceFollowRedirects(false);
        con.connect();
        con.getInputStream();

        if (con.getResponseCode() == HttpURLConnection.HTTP_MOVED_PERM || con.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP) {
            String redirectUrl = con.getHeaderField("Location");
            return getFinalURL(redirectUrl);
        }
        return url;
    }

}