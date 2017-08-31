package com.example.tic_tac_toe;

import java.util.ArrayList;

class GameAI{
    private int botTeam;
    private int playerTeam;
    private int[][] buttons = new int[3][3];

    GameAI(int botTeam){
        this.botTeam = botTeam;
        playerTeam = botTeam == 1 ? 2 : 1;
    }

    int moveMaking(int enemyTurn){
        setEnemyMarker(enemyTurn);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == 0){
                    buttons[i][j] = getTeam(getMoveNumber());
                    if (getWinner() == 1) {
                        list.add(1);
                        buttons[i][j] = 0;
                        continue;
                    }
                    list.add(moveSearching());
                    buttons[i][j] = 0;
                }
            }
        }
        setYourMarker(list.indexOf(getMax(list)));
        System.out.println(list.toString());
        showField();
        return list.indexOf(getMax(list));
    }

    private int moveSearching(){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (buttons[i][j] == 0){
                    buttons[i][j] = getTeam(getMoveNumber());
                    if (getWinner() == 1)
                        list.add(1);
                    else if (getWinner() == -1)
                        list.add(-1);
                    else
                        list.add(moveSearching());
                    buttons[i][j] = 0;
                }
            }
        }
        if (botTeam == getTeam(getMoveNumber()))
            return getMax(list);
        else
            return getMin(list);
    }

    private int getTeam(int tn){
        return tn % 2 == 0 ? 1 : 2;}

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

    private int getWinner(){
        for (int i = 0; i < 3; i++){
            int bt = 0;
            int pt = 0;
            for (int j = 0; j < 3; j++){
                if (buttons[i][j] == botTeam)
                    bt++;
                if (buttons[i][j] == playerTeam)
                    pt++;
            }
            if (bt == 3)
                return 1;
            if (pt == 3)
                return -1;
        }

        for (int i = 0; i < 3; i++){
            int bt = 0;
            int pt = 0;
            for (int j = 0; j < 3; j++){
                if (buttons[j][i] == botTeam)
                    bt++;
                if (buttons[j][i] == playerTeam)
                    pt++;
            }
            if (bt == 3)
                return 1;
            if (pt == 3)
                return -1;
        }

        int bt = 0;
        int pt = 0;
        for (int i = 0; i < 3; i++){
            if (buttons[i][i] == botTeam)
                bt++;
            if (buttons[i][i] == playerTeam)
                pt++;
        }
        if (bt == 3)
            return 1;
        if (pt == 3)
            return -1;

        bt = 0;
        pt = 0;
        for (int i = 0; i < 3; i++){
            if (buttons[i][2-i] == botTeam)
                bt++;
            if (buttons[i][2-i] == playerTeam)
                pt++;
        }
        if (bt == 3)
            return 1;
        if (pt == 3)
            return -1;
        return 0;
    }

    private int getMoveNumber(){
        int mn = 0;
        for (int[] bs : buttons){
            for (int b : bs){
                if (b != 0)
                    mn++;
            }
        }
        return mn;
    }

    private void setEnemyMarker(int move){
        for (int i = 0, index = 0; i < 3; i++){
            for (int j = 0; j < 3; j++, index++) {
                if (index == move)
                    buttons[i][j] = playerTeam;
            }
        }
    }

    private void showField(){
        for (int[] bs : buttons){
            for (int button : bs){
                System.out.print(button + " ");
            }
            System.out.println();
        }
    }

    private void setYourMarker(int move){
        for (int i = 0, index = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == 0){
                    if (index == move)
                        buttons[i][j] = botTeam;
                    index++;
                }
            }
        }
    }
}