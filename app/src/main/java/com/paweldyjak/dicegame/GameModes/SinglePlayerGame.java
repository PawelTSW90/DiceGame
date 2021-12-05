package com.paweldyjak.dicegame.GameModes;

import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.Fragments.FinalResultTwoPlayersFragment;
import java.util.Arrays;

public class SinglePlayerGame implements GameMode{
    private final GameBoardActivity gameBoardActivity;
    private String[] playersNames;
    private int currentPlayerNumber = 2;
    private final int[][] combinationsPointsValues = new int[2][16];
    private final int[][] combinationsSlotsValues = new int[2][16];
    private final int[] totalScore = new int[2];

    public SinglePlayerGame(GameBoardActivity gameBoardActivity, String[] playersNames){
        this.gameBoardActivity = gameBoardActivity;
        this.playersNames = playersNames;
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
    public void setFinalResultScreen() {
        FinalResultTwoPlayersFragment finalResultTwoPlayersFragment = new FinalResultTwoPlayersFragment(gameBoardActivity, this);
        gameBoardActivity.addFragment(finalResultTwoPlayersFragment);
    }

    @Override
    public void setAllCombinationsAsActive() {
        for (int[] row : combinationsSlotsValues)
            Arrays.fill(row, 0);

    }

    @Override
    public void setTotalScore(int score) {
        totalScore[currentPlayerNumber - 1] += score;
    }

    @Override
    public void setCombinationsPointsValues(int score, int combinationNr) {
        this.combinationsPointsValues[currentPlayerNumber - 1][combinationNr] = score;

    }

    @Override
    public void setCombinationsSlots(int combinationsSlotNumber, int slotStatus) {
        combinationsSlotsValues[currentPlayerNumber - 1][combinationsSlotNumber] = slotStatus;

    }

    @Override
    public void setPlayersNames(String[] playersNames) {
        this.playersNames = playersNames;
    }

    @Override
    public void setCurrentPlayerNumber(int currentPlayerNumber) {
        this.currentPlayerNumber = currentPlayerNumber;
    }

    @Override
    public int getCombinationsPointsValues(int playerNumber, int combinationNumber) {
        return combinationsPointsValues[playerNumber - 1][combinationNumber];
    }

    @Override
    public int[][] getCombinationsSlotsValues() {
        return combinationsSlotsValues;
    }

    @Override
    public String[] getPlayersNames() {
        return playersNames;
    }

    @Override
    public int getNumberOfPlayers() {
        return 2;
    }

    @Override
    public int getPlayersTotalScore(int playerNumber) {
        return totalScore[playerNumber];
    }


    @Override
    public int getCurrentPlayerNumber() {
        return currentPlayerNumber;
    }
}
