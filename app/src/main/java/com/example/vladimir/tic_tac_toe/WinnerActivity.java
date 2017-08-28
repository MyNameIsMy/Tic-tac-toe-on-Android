package com.example.vladimir.tic_tac_toe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class WinnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        Intent intent = getIntent();
        String winner = intent.getStringExtra("winner");

        TextView textView = (TextView) findViewById(R.id.winnerteam);
        textView.setText("Winner is " + winner + "!");
    }
}
