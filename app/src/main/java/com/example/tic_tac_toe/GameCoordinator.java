package com.example.tic_tac_toe;

<<<<<<< HEAD
=======
interface GameField {
    int getField(int v, int h);

    void setField(int x, int y, int marker);

    int getWinner(int[][] field);

    int getSize();
}


>>>>>>> a2a6c9507b0f6042c7571fc75211e884e9383569
class GameCoordinator implements GameField {
    private int[][] mField = new int[getSize()][getSize()];
    private GameAI gameAI;
    private int botTeam;
    private int playerTeam;
    private int move;
    private GamePresentation presentation;

    GameCoordinator(int botTeam, int playerTeam, GamePresentation presentation){
        this.botTeam = botTeam;
        this.playerTeam = playerTeam;
        this.presentation = presentation;
        gameAI = new GameAI(botTeam, this);
        move = 0;

        if (getTeam() == botTeam) {
            nextMove();
        }
    }

    public int getSize() {
        return 3;
    }

<<<<<<< HEAD
    void coordinatingOfGame(){
        if (getWinner() != 0 || getDraw())
            presentation.toWinnerActivity(getWinner());
        if (botTeam == getTeam(getMoveNumber()))
            gameAI.moveMaking();
        if (getWinner() != 0 || getDraw())
            presentation.toWinnerActivity(getWinner());
=======
    private void nextMove(){
        if (botTeam == getTeam())
            gameAI.makeMove();
>>>>>>> a2a6c9507b0f6042c7571fc75211e884e9383569
    }

    public int getField(int v, int h) {
        return mField[v][h];
    }

    public void setField(int x, int y, int marker) {
        mField[x][y] = marker;
        presentation.updateField(x, y, marker);

        move++;

        int winner = getWinner(mField);

        if (winner != 0)
            presentation.toWinnerActivity(winner);
        else
            nextMove();
    }

    public int getWinner(int[][] field){
        for (int i = 0; i < 3; i++){
            int bt = 0;
            int pt = 0;
            for (int j = 0; j < 3; j++){
                if (field[i][j] == botTeam)
                    bt++;
                if (field[i][j] == playerTeam)
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
                if (field[j][i] == botTeam)
                    bt++;
                if (field[j][i] == playerTeam)
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
            if (field[i][i] == botTeam)
                bt++;
            if (field[i][i] == playerTeam)
                pt++;
        }
        if (bt == 3)
            return 1;
        if (pt == 3)
            return -1;

        bt = 0;
        pt = 0;
        for (int i = 0; i < 3; i++){
            if (field[i][2-i] == botTeam)
                bt++;
            if (field[i][2-i] == playerTeam)
                pt++;
        }
        if (bt == 3)
            return 1;
        if (pt == 3)
            return -1;
        return 0;
    }

    public int getTeam(){
        return move % 2 == 0 ? 1 : 2;
    }

    private boolean getDraw(){
        for (int[] fd : field){
            for (int f : fd){
                if (f == 0)
                    return false;
            }
        }
        return true;
    }
}
