package com.paweldyjak.dicegame.GameModes;
import android.graphics.Color;
import android.view.Gravity;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.Fragments.FinalResultMorePlayersFragment;
import com.paweldyjak.dicegame.Fragments.FinalResultTwoPlayersFragment;
import com.paweldyjak.dicegame.R;
import com.paweldyjak.dicegame.UIConfig;

import java.util.Arrays;

public class HotSeatGame implements GameMode {
    GameBoardActivity gameBoardActivity;
    private final UIConfig uiConfig;
    private String[] playersNames;
    private int numberOfPlayers;
    private int currentPlayerNumber = 6;
    private final int[][] combinationsPointsValues = new int[6][16];
    private final int[][] combinationsSlotsValues = new int[6][16];
    private final boolean[][] isCombinationActive = new boolean[6][16];
    private final int[] totalScore = new int[6];

    public HotSeatGame(UIConfig uiConfig, GameBoardActivity gameBoardActivity, String[] playersNames) {
        this.uiConfig = uiConfig;
        this.gameBoardActivity = gameBoardActivity;
        this.playersNames = playersNames;
    }

    public void setAllCombinationsAsActive() {
        for (boolean[] row : isCombinationActive)
            Arrays.fill(row, true);
    }

    public void setTotalScore(int score) {
        String string = gameBoardActivity.getResources().getString(R.string.points);

        totalScore[currentPlayerNumber - 1] += score;
        uiConfig.getTotalScore().setText(totalScore[currentPlayerNumber - 1] + " " + string);
    }

    public boolean checkIfAllCombinationsAreDone() {
        for (int x = 0; x < getIsCombinationActive().length; x++) {
            if (getIsCombinationActive()[x]) {
                return false;
            }
        }
        return true;
    }

    public void updateGameBoard() {
        String string = gameBoardActivity.getResources().getString(R.string.points);
        for (int x = 0; x < 16; x++) {
            uiConfig.getCombinationsText()[x].setEnabled(isCombinationActive[currentPlayerNumber - 1][x]);
            uiConfig.getCombinationsPoints()[x].setText(combinationsPointsValues[currentPlayerNumber - 1][x] + " " + string);
            uiConfig.getTotalScore().setText(totalScore[currentPlayerNumber - 1] + " " + string);
        }

    }

    //generate final screen fragment
    public void setFinalResultScreen() {
        if (numberOfPlayers < 3) {
            FinalResultTwoPlayersFragment finalResultTwoPlayersFragment = new FinalResultTwoPlayersFragment(gameBoardActivity, this);
            gameBoardActivity.replaceFragment(R.id.fragment_layout, finalResultTwoPlayersFragment);

        } else{
            FinalResultMorePlayersFragment finalResultMorePlayersFragment = new FinalResultMorePlayersFragment(gameBoardActivity, this);
            gameBoardActivity.replaceFragment(R.id.fragment_layout, finalResultMorePlayersFragment);
        }
    }

    public boolean[] getIsCombinationActive() {

        return isCombinationActive[currentPlayerNumber - 1];

    }

    public void setIsCombinationActive(boolean isCombinationActive, int combinationNr) {

        this.isCombinationActive[currentPlayerNumber - 1][combinationNr] = isCombinationActive;

    }

    public int getCombinationScore(int combinationNr) {

        return combinationsPointsValues[currentPlayerNumber - 1][combinationNr];

    }

    public void setCombinationsPointsValues(int score, int combinationNr) {
        this.combinationsPointsValues[currentPlayerNumber - 1][combinationNr] = score;

    }

    public int getCurrentPlayerNumber() {
        return currentPlayerNumber;
    }

    public void setCurrentPlayerNumber(int currentPlayerNumber) {
        this.currentPlayerNumber = currentPlayerNumber;
    }

    public String[] getPlayersNames() {
        return playersNames;
    }

    public void setPlayersNames(String[] playersNames) {
        this.playersNames = playersNames;
    }

    public int getPlayersTotalScore(int playerNumber) {
        return totalScore[playerNumber - 1];

    }

    public int[] getPlayersScore(){
        return totalScore;
    }

    public void setCombinationsSlots(int combinationsSlotNumber, int slotStatus) {
        combinationsSlotsValues[currentPlayerNumber - 1][combinationsSlotNumber] = slotStatus;


    }

    public void prepareCombinationsSlots() {
        for (int x = 0; x < uiConfig.getCombinationsSlots().length; x++) {
            if (combinationsSlotsValues[currentPlayerNumber - 1][x] == 1) {
                uiConfig.getCombinationsSlots()[x].setText("\u2713");
                uiConfig.getCombinationsSlots()[x].setGravity(Gravity.CENTER);
                uiConfig.getCombinationsSlots()[x].setTextSize(16);
                uiConfig.getCombinationsSlots()[x].setTextColor(Color.rgb(27, 182, 33));
                uiConfig.getCombinationsPoints()[x].setEnabled(true);
            } else if (combinationsSlotsValues[currentPlayerNumber - 1][x] == 2) {
                uiConfig.getCombinationsSlots()[x].setText("X");
                uiConfig.getCombinationsSlots()[x].setGravity(Gravity.CENTER);
                uiConfig.getCombinationsSlots()[x].setTextSize(16);
                uiConfig.getCombinationsSlots()[x].setTextColor(Color.rgb(140, 17, 17));
                uiConfig.getCombinationsPoints()[x].setEnabled(false);
            } else {
                uiConfig.getCombinationsSlots()[x].setText("");
                uiConfig.getCombinationsPoints()[x].setEnabled(true);
            }


        }
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    @Override
    public String getGameMode() {
        return "HotSeatMode";
    }



}
