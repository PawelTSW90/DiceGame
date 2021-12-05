package com.paweldyjak.dicegame.GameModes;

import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.Fragments.FinalResultMorePlayersFragment;
import com.paweldyjak.dicegame.Fragments.FinalResultTwoPlayersFragment;

import java.util.Arrays;

public class HotSeatGame implements GameMode {
    private final GameBoardActivity gameBoardActivity;
    private String[] playersNames;
    private int numberOfPlayers;
    private int currentPlayerNumber = 6;
    private final int[][] combinationsPointsValues = new int[6][16];
    private final int[][] combinationsSlotsValues = new int[6][16];
    private final int[] totalScore = new int[6];

    public HotSeatGame(GameBoardActivity gameBoardActivity, String[] playersNames) {
        this.gameBoardActivity = gameBoardActivity;
        this.playersNames = playersNames;
    }

    @Override
    public void setTotalScore(int score) {
        totalScore[currentPlayerNumber - 1] += score;
    }

    @Override
    public void setAllCombinationsAsActive() {
        for (int[] row : combinationsSlotsValues)
            Arrays.fill(row, 0);

    }

    @Override
    public boolean checkIfAllCombinationsAreDone() {
        for (int x = 0; x < 16; x++) {
            if (getCombinationsSlotsValues()[getCurrentPlayerNumber() - 1][x] == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    //generate final screen fragment
    public void setFinalResultScreen() {
        if (numberOfPlayers < 3) {
            FinalResultTwoPlayersFragment finalResultTwoPlayersFragment = new FinalResultTwoPlayersFragment(gameBoardActivity, this);
            gameBoardActivity.addFragment(finalResultTwoPlayersFragment);

        } else {
            FinalResultMorePlayersFragment finalResultMorePlayersFragment = new FinalResultMorePlayersFragment(gameBoardActivity, this);
            gameBoardActivity.addFragment(finalResultMorePlayersFragment);
        }
    }

    @Override
    public void setCombinationsPointsValues(int score, int combinationNr) {
        this.combinationsPointsValues[currentPlayerNumber - 1][combinationNr] = score;

    }

    @Override
    public int getCurrentPlayerNumber() {
        return currentPlayerNumber;
    }

    @Override
    public void setCurrentPlayerNumber(int currentPlayerNumber) {
        this.currentPlayerNumber = currentPlayerNumber;
    }

    @Override
    public String[] getPlayersNames() {
        return playersNames;
    }

    @Override
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    @Override
    public void setPlayersNames(String[] playersNames) {
        this.playersNames = playersNames;
    }

    @Override
    public int getPlayersTotalScore(int playerNumber) {
        return totalScore[playerNumber];

    }

    @Override
    public void setCombinationsSlots(int combinationsSlotNumber, int slotStatus) {
        combinationsSlotsValues[currentPlayerNumber - 1][combinationsSlotNumber] = slotStatus;

    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    @Override
    public int getCombinationsPointsValues(int playerNumber, int combinationNumber) {
        return combinationsPointsValues[playerNumber - 1][combinationNumber];
    }

    @Override
    public int[][] getCombinationsSlotsValues() {
        return combinationsSlotsValues;
    }

}
