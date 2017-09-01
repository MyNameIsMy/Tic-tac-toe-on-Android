package com.example.tic_tac_toe;

interface GameField {
    int getField(int v, int h);

    void setField(int v, int h, int marker);

    int getWinner();

    int getTeam(int tn);

    int getMoveNumber();
}
