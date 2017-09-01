package com.example.tic_tac_toe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends AppCompatActivity implements GamePresentation {
    Button[][] field;
    String myTeam;
    String enemyTeam;
    GameCoordinator gameCoordinator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        myTeam = intent.getStringExtra("your team");

        field = new Button[3][3];

        field[0][0] = (Button)findViewById(R.id.button1);
        field[0][1] = (Button)findViewById(R.id.button2);
        field[0][2] = (Button)findViewById(R.id.button3);
        field[1][0] = (Button)findViewById(R.id.button4);
        field[1][1] = (Button)findViewById(R.id.button5);
        field[1][2] = (Button)findViewById(R.id.button6);
        field[2][0] = (Button)findViewById(R.id.button7);
        field[2][1] = (Button)findViewById(R.id.button8);
        field[2][2] = (Button)findViewById(R.id.button9);

        enemyTeam = myTeam.equals("x") ? "o" : "x";

        gameCoordinator = new GameCoordinator(getNumberOfTeam(enemyTeam), getNumberOfTeam(myTeam), this);

        gameCoordinator.coordinatingOfGame();

        setFieldOfButtons();
    }

    @OnClick(R.id.button1)
    public void onClick1(Button button){
        setYourMarker(button);
    }

    @OnClick(R.id.button2)
    public void onClick2(Button button){
        setYourMarker(button);
    }

    @OnClick(R.id.button3)
    public void onClick3(Button button){
        setYourMarker(button);
    }

    @OnClick(R.id.button4)
    public void onClick4(Button button){
        setYourMarker(button);
    }

    @OnClick(R.id.button5)
    public void onClick5(Button button){
        setYourMarker(button);
    }

    @OnClick(R.id.button6)
    public void onClick6(Button button){
        setYourMarker(button);
    }

    @OnClick(R.id.button7)
    public void onClick7(Button button){
        setYourMarker(button);
    }

    @OnClick(R.id.button8)
    public void onClick8(Button button){
        setYourMarker(button);
    }

    @OnClick(R.id.button9)
    public void onClick9(Button button){
        setYourMarker(button);
    }

    private void setYourMarker(Button button){
        if (button.getText().equals("")){
            setField(button);
            gameCoordinator.coordinatingOfGame();
            setFieldOfButtons();
        }
    }

    private void setField(Button button){
        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if (field[i][j].equals(button))
                    gameCoordinator.setField(i, j, getNumberOfTeam(myTeam));
            }
        }
    }

    private void setFieldOfButtons(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                field[i][j].setText(getMarkerFromNumber(gameCoordinator.getField(i, j)));
            }
        }
    }

    private int getNumberOfTeam(String team) {
        return team.equals("x") ? 1 : 2;
    }

    private String getMarkerFromNumber(int number){
        if (number == 2)
            return "o";
        if (number == 1)
            return "x";
        else
            return "";
    }

    public void toWinnerActivity(int team){
        String winner = team == 1 ? enemyTeam : myTeam;
        Intent intent = new Intent(this, WinnerActivity.class);
        intent.putExtra("winner", winner);
        startActivity(intent);
    }
}

