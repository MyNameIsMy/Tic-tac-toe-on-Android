package com.example.tic_tac_toe;


class GameCoordinator implements GameLogic{
    private GameAI gameAI;

    GameCoordinator(int botTeam){
        gameAI = new GameAI(botTeam);
    }

    public int coordinatingOfMoveMaking(int yourTurn){
        return gameAI.moveMaking(yourTurn);
    }
}
