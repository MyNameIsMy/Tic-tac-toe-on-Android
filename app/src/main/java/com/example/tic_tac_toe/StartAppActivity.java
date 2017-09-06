package com.example.tic_tac_toe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class StartAppActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(StartAppActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        };
        thread.start();
    }
}
