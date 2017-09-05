package com.example.tic_tac_toe;

class GameCoordinator implements GameField {
    private int[][] field = new int[3][3];
    private GameAI gameAI;
    private int botTeam;
    private int playerTeam;
    private GamePresentation presentation;

    GameCoordinator(int botTeam, int playerTeam, GamePresentation presentation){
        this.botTeam = botTeam;
        this.playerTeam = playerTeam;
        this.presentation = presentation;
        gameAI = new GameAI(botTeam, this);
    }

    void coordinatingOfGame(){
        if (getWinner() != 0 || getDraw())
            presentation.toWinnerActivity(getWinner());
        if (botTeam == getTeam(getMoveNumber()))
            gameAI.moveMaking();
        if (getWinner() != 0 || getDraw())
            presentation.toWinnerActivity(getWinner());
    }

    public int getField(int v, int h) {
        return field[v][h];
    }

    public void setField(int v, int h, int marker) {
        field[v][h] = marker;
    }

    public int getWinner(){
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

    public int getMoveNumber(){
        int mn = 0;
        for (int[] bs : field){
            for (int b : bs){
                if (b != 0)
                    mn++;
            }
        }
        return mn;
    }

    public int getTeam(int tn){
        return tn % 2 == 0 ? 1 : 2;
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
