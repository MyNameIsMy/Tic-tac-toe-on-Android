package com.example.tic_tac_toe;

import java.util.ArrayList;
import java.util.Collections;

class GameAI{
    private int botTeam;
    private GameField gameField;

    private int[][] mField;
    private int[][] mInitialField;

    GameAI(int botTeam, GameField gameField){
        this.botTeam = botTeam;
        this.gameField = gameField;

        mField = new int[gameField.getSize()][gameField.getSize()];
        mInitialField = new int[gameField.getSize()][gameField.getSize()];
    }

    void makeMove() {
        copyField();

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < gameField.getSize(); i++) {
            for (int j = 0; j < gameField.getSize(); j++) {
                if (mField[i][j] == 0) {
                    mField[i][j] = botTeam;
                    if (getWinner() == 1) {
                        list.add(1);
                        mField[i][j] = 0;
                        continue;
                    }

                    list.add(searchNextMove());
                    mField[i][j] = 0;
                }
            }
        }

        for (int i = 0, index = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (mField[i][j] == 0) {
                    if (index == list.indexOf(getMax(list)))
                        gameField.setField(i, j, botTeam);
                    index++;
                }
            }

        }
    }

    private int searchNextMove(){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (mField[i][j] == 0){
                    mField[i][j] = getTeam(getMoveNumber());
                    if (getWinner() == 1)
                        list.add(1);
                    else if (getWinner() == -1)
                        list.add(-1);
                    else
                        list.add(searchNextMove());
                    mField[i][j] = 0;
                }
            }
        }
        if (botTeam == getTeam(getMoveNumber()))
            return getMax(list);
        else
            return getMin(list);
    }

    private int getTeam(int move) {
        return move % 2 == 0 ? 1 : 2;
    }

    private int getMoveNumber() {
        int mn = 0;
        for (int[] bs : mField){
            for (int b : bs){
                if (b != 0)
                    mn++;
            }
        }
        return mn;
    }


    private void copyField(){
        for (int i = 0; i < gameField.getSize(); i++) {
            for (int j = 0; j < gameField.getSize(); j++) {
                mField[i][j] = gameField.getField(i,j);
                mInitialField[i][j] = mField[i][j];
            }
        }
    }

    private int getWinner(){
        return gameField.getWinner(mField);
    }

    private int getMax(ArrayList<Integer> list){
        if (list.size() > 0){
            return Collections.max(list);
        }
        else return 0;
    }

    private int getMin(ArrayList<Integer> list){
        if (list.size() > 0){
            return Collections.min(list);
        }
        else return 0;
    }
}