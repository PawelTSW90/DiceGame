package com.paweldyjak.dicegame.GameModes;

public class SinglePlayerGame implements GameMode{

    @Override
    public boolean checkIfAllCombinationsAreDone() {
        return false;
    }

    @Override
    public void setFinalResultScreen() {

    }

    @Override
    public void setAllCombinationsAsActive() {

    }

    @Override
    public void setTotalScore(int score) {

    }

    @Override
    public void setCombinationsPointsValues(int score, int combinationNr) {

    }

    @Override
    public void setCombinationsSlots(int combinationsSlotNumber, int slotStatus) {

    }

    @Override
    public void setNumberOfPlayers(int numberOfPlayers) {

    }

    @Override
    public void setPlayersNames(String[] playersNames) {

    }

    @Override
    public void setCurrentPlayerNumber(int currentPlayerNumber) {

    }

    @Override
    public int getCombinationsPointsValues(int playerNumber, int combinationNumber) {
        return 0;
    }

    @Override
    public int[][] getCombinationsSlotsValues() {
        return new int[0][];
    }

    @Override
    public String[] getPlayersNames() {
        return new String[0];
    }

    @Override
    public int getNumberOfPlayers() {
        return 0;
    }

    @Override
    public int getPlayersTotalScore(int playerNumber) {
        return 0;
    }

    @Override
    public int[] getPlayersScore() {
        return new int[0];
    }

    @Override
    public int getCurrentPlayerNumber() {
        return 0;
    }
}
