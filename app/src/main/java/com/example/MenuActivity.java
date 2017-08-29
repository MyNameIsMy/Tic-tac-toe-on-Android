package com.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tic_tac_toe.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void toTeamSelection(View view){
        Intent intent = new Intent(this, TeamSelectionActivity.class);
        startActivity(intent);
    }
}
