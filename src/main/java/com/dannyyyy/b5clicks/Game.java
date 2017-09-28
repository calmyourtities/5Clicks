package com.dannyyyy.b5clicks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class Game extends AppCompatActivity {

    public static String[] map = new String[5];
    public int mapLength;
    public String mapAsString = "";
    public static int clicks = 0;
    public static boolean GAME_WON;
    public static String origin = "United_States";
    public static String topic = "Jesus";
    public static final String ROOTURL = "http://en.m.wikipedia.com/wiki/";
    String lastURL = ROOTURL;
    WebView wv;
    WebSettings wvs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        wv = (WebView) findViewById(R.id.webview);
        ((TextView) findViewById(R.id.goals)).setText("Get from " + origin + " to " + topic);
        reset();
        startGame();
    }

    public void startGame() {
        final TextView cc = (TextView) findViewById(R.id.clickCount);
        wv.loadUrl(ROOTURL + origin);
        wvs = (WebSettings) wv.getSettings();
        wvs.setJavaScriptEnabled(true);
        cc.setText("");
        wv.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                /*wv.evaluateJavascript("(function(){alert('hallo'); document.getElementById(\"searchIcon\").href = \"javascript:alert('nope')\";return 'yay';})()", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String s) {
                        Log.d("LogName", s); // Prints: "this"
                    }
                });*/
                Log.d("GameInfo", "Page finished loading: " + url);
                Log.d("GameInfo", "Map: " + map[0] + ", " + map[1] + ", " + map[2] + ", " + map[3] + ", " + map[4]);
                if (lastURL != url) {
                    Log.d("GameInfo", "LastURL: " + lastURL + " url: " + url);
                    mapAsString += url + "\n";
                    Log.d("GameInfo", "New url");
                    lastURL = url;
                    clicks++;
                    if(((clicks - 5) / 2) > 0) { //because that's the equation for clicks
                        Log.d("dbgc1", "clicks = " + ((clicks - 5) / 2) + ", url = " + wv.getUrl());
                        if((clicks - 5) / 2 < 5) {
                            map[(clicks - 5) / 2] = wv.getUrl();
                        }
                    }
                    cc.setText("Clicks: " + (clicks - 5) / 2);
                    if (wv.getUrl().toLowerCase().contains("search")) { //check for cheaters ;}
                        Log.d("dbgc2", "attempting to load url at map[" + (((clicks - 5) / 2) - 1) + "]");
                        wv.loadUrl(ROOTURL + origin);
                        return;
                    }
                    if (wv.getUrl().toLowerCase().contains(topic.toLowerCase())) {
                        Log.d("GameInfo", "Good job!");
                        GAME_WON = true;
                        Intent myIntent = new Intent(getApplicationContext(), Gamewin.class);
                        startActivity(myIntent);
                        finish();
                        Log.d("GameInfo", "You won!");
                    } else if (((clicks - 5) / 2) >= 5) { //starts off with 5 for some reason, and two per click
                        Log.d("GameInfo", "Aww");
                        GAME_WON = false;
                        Intent myIntent = new Intent(getApplicationContext(), Gameover.class);
                        startActivity(myIntent);
                        finish();
                        Log.d("GameInfo", "You lost");
                    }
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
    }

    public void reset() {
        map = new String[5];
        mapAsString = "";
        clicks = 0;
        lastURL = "";
        wv.loadUrl(ROOTURL + topic);
        mapLength = 0;

    }
}