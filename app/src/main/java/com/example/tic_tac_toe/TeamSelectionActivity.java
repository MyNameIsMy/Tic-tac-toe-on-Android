package com.example.tic_tac_toe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class TeamSelectionActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_selection);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.o_button_selected)
    public void onClickO(){
        intentStarter("o");
    }

    @OnClick(R.id.x_button_selected)
    public void onClickX(){
        intentStarter("x");
    }

    public void intentStarter(String ox){
        Intent intent = new Intent(this, ScreenBeforeGameActivity.class);
        intent.putExtra("your team", ox);
        startActivity(intent);
    }
}