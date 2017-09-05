package com.example.tic_tac_toe;

import java.util.ArrayList;

class GameAI{
    private int botTeam;
    private GameField gameField;

    GameAI(int botTeam, GameField gameField){
        this.botTeam = botTeam;
        this.gameField = gameField;
    }

    void moveMaking(){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (getField(i, j) == 0){
                    setField(i, j, getTeam(getMoveNumber()));
                    if (getWinner() == 1) {
                        list.add(1);
                        setField(i, j, 0);
                        continue;
                    }
                    list.add(moveSearching());
                    setField(i, j, 0);
                }
            }
        }
        for (int i = 0, index = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (getField(i, j) == 0){
                    if (index == list.indexOf(getMax(list)))
                        setField(i, j, botTeam);
                    index++;
                }
            }
        }
    }

    private int moveSearching(){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (getField(i, j) == 0){
                    setField(i, j, getTeam(getMoveNumber()));
                    if (getWinner() == 1)
                        list.add(1);
                    else if (getWinner() == -1)
                        list.add(-1);
                    else
                        list.add(moveSearching());
                    setField(i, j, 0);
                }
            }
        }
        if (botTeam == getTeam(getMoveNumber()))
            return getMax(list);
        else
            return getMin(list);
    }

    private int getTeam(int tn){
        return gameField.getTeam(tn);
    }

    private int getMoveNumber(){
        return gameField.getMoveNumber();
    }

    private int getField(int v, int h){
        return gameField.getField(v, h);
    }

    private void setField(int v, int h, int marker){
        gameField.setField(v, h, marker);
    }

    private int getWinner(){
        return gameField.getWinner();
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
}