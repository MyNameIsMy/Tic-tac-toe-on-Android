package com.example.tic_tac_toe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends Activity {

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
