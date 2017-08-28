package com.example.vladimir.tic_tac_toe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends AppCompatActivity {
    Button[][] buttons;
    String myTeam;
    String enemyTeam;
    ArrayList<BotIntelligence> parentBotList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        myTeam = intent.getStringExtra(TeamSelection.EXTRA_MESSAGE);

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

        if(myTeam.equals("x"))
            enemyTeam = "o";
        else {
            enemyTeam = "x";
            new BotIntelligence(buttons, enemyTeam, myTeam, turnNumber());
        }
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
        if (button.getText().equals(""))
            button.setText(myTeam);
        else
            return;
        if (winChecking(buttons)){
            toWinnerActivity();
            return;
        }

        new BotIntelligence(buttons, enemyTeam, myTeam, turnNumber());
        if (winChecking(buttons))
            toWinnerActivity();
    }

    private void toWinnerActivity(){
        Intent intent = new Intent(this, WinnerActivity.class);
        intent.putExtra("winner", getTeam(turnNumber() - 1));
        startActivity(intent);
    }

    private int turnNumber(){
        int turn = 0;
        for (Button[] bs : buttons){
            for (Button button : bs){
                if (!button.getText().equals(""))
                    turn++;
            }
        }
        return turn;
    }

    private String getTeam(int tn){
        return tn % 2 == 0 ? "x" : "o";
    }

    private boolean winChecking(Button[][] buttons){
        for (int i = 0; i < 3; i++){
            if(!buttons[i][0].getText().equals(""))
                if (buttons[i][0].getText().equals(buttons[i][1].getText())
                        && buttons[i][0].getText().equals(buttons[i][2].getText()))
                    return true;
        }

        for (int i = 0; i < 3; i++){
            if(!buttons[0][i].getText().equals(""))
                if (buttons[0][i].getText().equals(buttons[1][i].getText())
                        && buttons[0][i].getText().equals(buttons[2][i].getText()))
                    return true;
        }

        if(!buttons[0][0].getText().equals(""))
            if (buttons[0][0].getText().equals(buttons[1][1].getText())
                    && buttons[0][0].getText().equals(buttons[2][2].getText()))
                return true;

        if (!buttons[0][2].getText().equals(""))
            if(buttons[0][2].getText().equals(buttons[1][1].getText())
                    && buttons[0][2].getText().equals(buttons[2][0].getText()))
                return true;

        return false;
    }
}

class BotIntelligence{
    private String[][] textValueOfButtons;
    private Button[][] buttons;
    private String botTeam;
    private String playerTeam;

    BotIntelligence(Button[][] buttons, String botTeam, String playerTeam, int turnNumber){
        this.buttons = buttons;
        this.botTeam = botTeam;
        this.playerTeam = playerTeam;
        textValueOfButtons = new String[3][3];
        setTextValueOfButtons();
        moveMaking(turnNumber);
    }

    private void moveMaking(int tn) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (textValueOfButtons[i][j].equals("")) {
                    textValueOfButtons[i][j] = botTeam;
                    if (winChecking(botTeam) == 1) {
                        list.add(1);
                        textValueOfButtons[i][j] = "";
                        continue;
                    }
                    list.add(moveSearching(tn + 1));
                    System.out.println(moveSearching(tn + 1));
                    textValueOfButtons[i][j] = "";
                }
            }
        }

        for (int i = 0, t = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")){
                    if (t == list.indexOf(getMax(list)))
                        buttons[i][j].setText(botTeam);
                    t++;
                }
            }
        }
    }

    private int moveSearching(int tn){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (textValueOfButtons[i][j].equals("")){
                    textValueOfButtons[i][j] = getTeam(tn);
                    if (winChecking(botTeam) == 1)
                        list.add(1);
                    else if (winChecking(playerTeam) == -1)
                        list.add(-1);
                    else {
                        if (tn < 8)
                            list.add(moveSearching(tn + 1));
                    }
                    textValueOfButtons[i][j] = "";
                }
            }
        }
        if (getTeam(tn).equals(botTeam))
            return getMax(list);
        else
            return getMin(list);
    }

    private int getMax(ArrayList<Integer> list){
        if (list.size() > 0){
            int max = list.get(0);
            for (int i : list)
                if (max < i)
                    max = i;
            return max;
        }
        else return 0;
    }

    private int getMin(ArrayList<Integer> list){
        if (list.size() > 0){
            int min = list.get(0);
            for (int i : list)
                if (min > i)
                    min = i;
            return min;
        }
        else return 0;
    }

    private String getTeam(int tn){
        return tn % 2 == 0 ? "x" : "o";
    }

    private int winChecking(String team){
        for (int i = 0; i < 3; i++){
            int countOfTeamMarker = 0;
            for (int j = 0; j < 3; j++){
                if (textValueOfButtons[i][j].equals(team))
                    countOfTeamMarker++;
            }
            if (countOfTeamMarker == 3) {
                if (team.equals(botTeam))
                    return 1;
                else
                    return -1;
            }
        }


        for (int i = 0; i < 3; i++){
            int countOfTeamMarker = 0;
            for (int j = 0; j < 3; j++){
                if (textValueOfButtons[j][i].equals(team))
                    countOfTeamMarker++;
            }
            if (countOfTeamMarker == 3) {
                if (team.equals(botTeam))
                    return 1;
                else
                    return -1;
            }
        }


        int countOfTeamMarker = 0;
        for (int i = 0; i < 3; i++){
            if (textValueOfButtons[i][i].equals(team))
                countOfTeamMarker++;
        }
        if (countOfTeamMarker == 3) {
            if (team.equals(botTeam))
                return 1;
            else
                return -1;
        }


        countOfTeamMarker = 0;
        for (int i = 0; i < 3; i++){
            if (textValueOfButtons[i][2 - i].equals(team))
                countOfTeamMarker++;
        }
        if (countOfTeamMarker == 3) {
            if (team.equals(botTeam))
                return 1;
            else
                return -1;
        }
        return 0;
    }

    private void setTextValueOfButtons(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                textValueOfButtons[i][j] = buttons[i][j].getText().toString();
            }
        }
    }
}

