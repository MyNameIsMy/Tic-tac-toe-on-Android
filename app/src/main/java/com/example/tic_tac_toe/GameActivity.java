package com.example.tic_tac_toe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends AppCompatActivity implements GamePresentation {
    Button[][] buttons;
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

        buttons = new Button[3][3];

        buttons[0][0] = (Button)findViewById(R.id.button1);
        buttons[0][1] = (Button)findViewById(R.id.button2);
        buttons[0][2] = (Button)findViewById(R.id.button3);
        buttons[1][0] = (Button)findViewById(R.id.button4);
        buttons[1][1] = (Button)findViewById(R.id.button5);
        buttons[1][2] = (Button)findViewById(R.id.button6);
        buttons[2][0] = (Button)findViewById(R.id.button7);
        buttons[2][1] = (Button)findViewById(R.id.button8);
        buttons[2][2] = (Button)findViewById(R.id.button9);

        enemyTeam = myTeam.equals("x") ? "o" : "x";

        gameCoordinator = new GameCoordinator(getNumberOfTeam(enemyTeam));

        if (myTeam.equals("o"))
            setEnemyMarker(gameCoordinator.coordinatingOfMoveMaking(10));
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

    public void setYourMarker(Button button){
        if (button.getText().equals("")){
            button.setText(myTeam);
            setEnemyMarker(gameCoordinator.coordinatingOfMoveMaking(getMoveIndex(button)));
        }
    }

    private void setEnemyMarker(int move){
        int index = 0;
        for (Button[] bs : buttons){
            for (Button b : bs) {
                if (b.getText().equals("")) {
                    if (index == move)
                        b.setText(enemyTeam);
                    index++;
                }
            }
        }
    }

    private int getNumberOfTeam(String team) {
        return team.equals("x") ? 1 : 2; }

    private int getMoveIndex(Button button){
        int index = 0;
        out:
        for (Button[] bs : buttons){
            for (Button b : bs) {
                if (b.equals(button))
                    break out;
                index++;
            }
        }
        return index;
    }
}

