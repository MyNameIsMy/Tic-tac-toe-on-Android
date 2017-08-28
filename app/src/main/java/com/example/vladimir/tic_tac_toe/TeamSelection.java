package com.example.vladimir.tic_tac_toe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class TeamSelection extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.tic-tac-toe.MESSAGE";
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
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(EXTRA_MESSAGE, ox);
        startActivity(intent);
    }
}