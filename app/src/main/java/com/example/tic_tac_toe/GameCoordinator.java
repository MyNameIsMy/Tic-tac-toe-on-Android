package com.example.tic_tac_toe;

class GameCoordinator {
    private String[][] bns = new String[3][3];
    private String playerTeam;
    private String botTeam;

    GameCoordinator(String playerTeam, String botTeam, String[][] buttons, GamePresentation gamePresentation){
        this.playerTeam = playerTeam;
        this.botTeam = botTeam;
        startGame(buttons, gamePresentation);
    }

    void startGame(String[][] buttons, GamePresentation gamePresentation){
        arrayToArray(buttons, bns);
        moveCoordinator(gamePresentation);
    }

    private void moveCoordinator(GamePresentation act){
        if (botTeam.equals(getTeam(turnNumber())))
            act.setTurn(new GameAI(bns, botTeam, playerTeam).moveMaking(turnNumber()));
    }

    private int turnNumber(){
        int turn = 0;
        for (String[] bs : bns){
            for (String button : bs){
                if (!button.equals(""))
                    turn++;
            }
        }
        return turn;
    }

    private String getTeam(int tn){
        return tn % 2 == 0 ? "x" : "o";
    }

    static void arrayToArray(String[][] buttonsFrom, String[][] buttonsTo){
        for (int i = 0; i < 3; i++){
            System.arraycopy(buttonsFrom[i], 0, buttonsTo[i], 0, 3);
        }
    }
}
