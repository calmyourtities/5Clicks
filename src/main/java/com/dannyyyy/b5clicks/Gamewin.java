package com.dannyyyy.b5clicks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Gamewin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamewin);
    }
    public void newGame(View v) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
    @Override
    public void onBackPressed() {}
}
