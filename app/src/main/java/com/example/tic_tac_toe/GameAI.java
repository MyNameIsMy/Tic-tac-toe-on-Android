package com.example.tic_tac_toe;

import java.util.ArrayList;

class GameAI{
    private String[][] textValueOfButtons;
    private String botTeam;
    private String playerTeam;

    GameAI(String[][] buttons , String botTeam, String playerTeam){
        this.botTeam = botTeam;
        this.playerTeam = playerTeam;
        textValueOfButtons = new String[3][3];
        GameCoordinator.arrayToArray(buttons, textValueOfButtons);
    }

    int moveMaking(int tn) {
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
        return list.indexOf(getMax(list));
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
}