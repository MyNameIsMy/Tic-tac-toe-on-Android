package com.example.tic_tac_toe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class WinnerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String winner = intent.getStringExtra("winner");

        setWinner(winner);
    }

    @OnClick(R.id.play_again_button)
    public void toMenu(){
        Intent intent = new Intent(this, TeamSelectionActivity.class);
        startActivity(intent);
    }

    private void setWinner(String winner){
        TextView textView = (TextView) findViewById(R.id.winner_team);
        if (winner.equals("draw"))
            textView.setText(R.string.draw);
        else
            textView.setText("Winner is " + winner + "!");
    }
}
