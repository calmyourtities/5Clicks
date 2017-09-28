package com.dannyyyy.b5clicks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Gameover extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover);
        /*TextView endmsg = (TextView) findViewById(R.id.endmsg);
        if(Game.GAME_WON) {
            endmsg.setText("Good Job!\nYou found " + Game.topic + " from " + Game.origin + " in " + Game.clicks + " clicks!");
        } else if(!Game.GAME_WON) {
            endmsg.setText("Sorry :(");
        } else {
            System.out.println("win? " + Game.GAME_WON);
            endmsg.setText("We had an error :(");
        }*/
    }
    public void newGame(View v) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
    @Override
    public void onBackPressed() {}
}
