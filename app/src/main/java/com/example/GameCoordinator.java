package com.example;

class GameCoordinator {
    private String[][] bns = new String[3][3];
    private String playerTeam;
    private String botTeam;

    GameCoordinator(String playerTeam, String botTeam, String[][] buttons, GameActivity gameActivity){
        this.playerTeam = playerTeam;
        this.botTeam = botTeam;
        arrayToArray(buttons, bns);
        moveCoordinator(gameActivity);
    }

    private void moveCoordinator(GameActivity act){
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
