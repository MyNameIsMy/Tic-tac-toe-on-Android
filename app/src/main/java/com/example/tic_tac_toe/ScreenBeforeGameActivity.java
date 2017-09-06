package com.example.tic_tac_toe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ScreenBeforeGameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_before_game);

        Intent intent = getIntent();
        final String text = intent.getStringExtra("your team");

        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(ScreenBeforeGameActivity.this, GameActivity.class);
                intent.putExtra("your team", text);
                startActivity(intent);
            }
        };

        thread.start();
    }
}
