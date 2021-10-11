package com.paweldyjak.dicegame.GameModes;

import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.Fragments.FinalResultMorePlayersFragment;
import com.paweldyjak.dicegame.Fragments.FinalResultTwoPlayersFragment;
import com.paweldyjak.dicegame.R;

import java.util.Arrays;

public class HotSeatGame implements GameMode {
    GameBoardActivity gameBoardActivity;
    private String[] playersNames;
    private int numberOfPlayers;
    private int currentPlayerNumber = 6;
    private final int[][] combinationsPointsValues = new int[6][16];
    private final int[][] combinationsSlotsValues = new int[6][16];
    private final boolean[][] isCombinationActive = new boolean[6][16];
    private final int[] totalScore = new int[6];

    public HotSeatGame(GameBoardActivity gameBoardActivity, String[] playersNames) {
        this.gameBoardActivity = gameBoardActivity;
        this.playersNames = playersNames;
    }

    public void setTotalScore(int score) {
        totalScore[currentPlayerNumber - 1] += score;
    }

    public void setAllCombinationsAsActive() {
        for (boolean[] row : isCombinationActive)
            Arrays.fill(row, true);
    }

    public boolean checkIfAllCombinationsAreDone() {
        for (int x = 0; x < getIsCombinationActive().length; x++) {
            if (getIsCombinationActive()[x]) {
                return false;
            }
        }
        return true;
    }

    //generate final screen fragment
    public void setFinalResultScreen() {
        if (numberOfPlayers < 3) {
            FinalResultTwoPlayersFragment finalResultTwoPlayersFragment = new FinalResultTwoPlayersFragment(gameBoardActivity, this);
            gameBoardActivity.replaceFragment(R.id.fragment_layout, finalResultTwoPlayersFragment);

        } else {
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
        return totalScore[playerNumber];

    }

    public int[] getPlayersScore() {
        return totalScore;
    }

    public void setCombinationsSlots(int combinationsSlotNumber, int slotStatus) {
        combinationsSlotsValues[currentPlayerNumber - 1][combinationsSlotNumber] = slotStatus;

    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public int getCombinationsPointsValues(int playerNumber, int combinationNumber) {
        return combinationsPointsValues[playerNumber-1][combinationNumber];
    }

    @Override
    public int[][] getCombinationsSlotsValues() {
        return combinationsSlotsValues;
    }
}
