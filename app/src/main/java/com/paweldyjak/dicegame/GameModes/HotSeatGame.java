package com.paweldyjak.dicegame.GameModes;
import android.graphics.Color;
import android.view.Gravity;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.Fragments.FinalResultScreenFragment;
import com.paweldyjak.dicegame.R;
import com.paweldyjak.dicegame.UIConfig;

import java.util.Arrays;

public class HotSeatGame {
    GameBoardActivity gameBoardActivity;
    private final UIConfig uiConfig;
    private String[] playersNames;
    private int numberOfPlayers;
    private int currentPlayerNumber = 6;
    private final int[][] playersCombinationsScoreValues = new int[6][16];
    private final int[][] playersCombinationsSlotsValues = new int[6][16];
    private final boolean[][] playersIsCombinationActive = new boolean[6][16];
    private final int[] playersTotalScore = new int[6];

    public HotSeatGame(UIConfig uiConfig, GameBoardActivity gameBoardActivity, String[] playersNames) {
        this.uiConfig = uiConfig;
        this.gameBoardActivity = gameBoardActivity;
        this.playersNames = playersNames;
    }

    public void setAllCombinationsAsActive() {
        for (boolean[] row : playersIsCombinationActive)
            Arrays.fill(row, true);
    }

    public void setTotalScore(int score) {
        String string = gameBoardActivity.getResources().getString(R.string.points);

        playersTotalScore[currentPlayerNumber - 1] += score;
        uiConfig.getTotalScoreTextView().setText(playersTotalScore[currentPlayerNumber - 1] + " " + string);
    }

    public boolean checkIfAllCombinationsAreDone() {
        for (int x = 0; x < getIsCombinationActive().length; x++) {
            if (getIsCombinationActive()[x]) {
                return false;
            }
        }
        return true;
    }

    public void prepareScoreBoard() {
        String string = gameBoardActivity.getResources().getString(R.string.points);
        for (int x = 0; x < 16; x++) {
            uiConfig.getCombinationsTextView()[x].setEnabled(playersIsCombinationActive[currentPlayerNumber - 1][x]);
            uiConfig.getCombinationsPointsTextView()[x].setText(playersCombinationsScoreValues[currentPlayerNumber - 1][x] + " " + string);
            uiConfig.getTotalScoreTextView().setText(playersTotalScore[currentPlayerNumber - 1] + " " + string);
        }

    }

    //generate final screen fragment
    public void setFinalResultScreen() {
        FinalResultScreenFragment finalResultScreenFragment = new FinalResultScreenFragment(gameBoardActivity,this);
        gameBoardActivity.replaceFragment(R.id.fragment_layout, finalResultScreenFragment);

    }

    public boolean[] getIsCombinationActive() {

        return playersIsCombinationActive[currentPlayerNumber - 1];

    }

    public void setIsCombinationActive(boolean isCombinationActive, int combinationNr) {

        this.playersIsCombinationActive[currentPlayerNumber - 1][combinationNr] = isCombinationActive;

    }

    public int getCombinationScore(int combinationNr) {

        return playersCombinationsScoreValues[currentPlayerNumber - 1][combinationNr];

    }

    public void setCombinationScore(int score, int combinationNr) {
        this.playersCombinationsScoreValues[currentPlayerNumber - 1][combinationNr] = score;

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
        return playersTotalScore[playerNumber - 1];

    }

    public void setCombinationsSlots(int combinationsSlotNumber, int slotStatus) {
        playersCombinationsSlotsValues[currentPlayerNumber - 1][combinationsSlotNumber] = slotStatus;


    }

    public void prepareCombinationsSlots() {
        for (int x = 0; x < uiConfig.getCombinationsSlots().length; x++) {
            if (playersCombinationsSlotsValues[currentPlayerNumber - 1][x] == 1) {
                uiConfig.getCombinationsSlots()[x].setText("\u2713");
                uiConfig.getCombinationsSlots()[x].setGravity(Gravity.CENTER);
                uiConfig.getCombinationsSlots()[x].setTextSize(16);
                uiConfig.getCombinationsSlots()[x].setTextColor(Color.rgb(27, 182, 33));
                uiConfig.getCombinationsPointsTextView()[x].setEnabled(true);
            } else if (playersCombinationsSlotsValues[currentPlayerNumber - 1][x] == 2) {
                uiConfig.getCombinationsSlots()[x].setText("X");
                uiConfig.getCombinationsSlots()[x].setGravity(Gravity.CENTER);
                uiConfig.getCombinationsSlots()[x].setTextSize(16);
                uiConfig.getCombinationsSlots()[x].setTextColor(Color.rgb(140, 17, 17));
                uiConfig.getCombinationsPointsTextView()[x].setEnabled(false);
            } else {
                uiConfig.getCombinationsSlots()[x].setText("");
                uiConfig.getCombinationsPointsTextView()[x].setEnabled(true);
            }


        }
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }


}
