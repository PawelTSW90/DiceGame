package com.paweldyjak.dicegame.GameModes;


public interface GameMode {

    boolean checkIfAllCombinationsAreDone();

    void setFinalResultScreen();

    void setAllCombinationsAsActive();

    void setTotalScore(int score);

    void setCombinationsPointsValues(int score, int combinationNr);

    void setCombinationsSlots(int combinationsSlotNumber, int slotStatus);

    void setNumberOfPlayers(int numberOfPlayers);

    void setPlayersNames(String[] playersNames);

    void setCurrentPlayerNumber(int currentPlayerNumber);

    int getCombinationsPointsValues(int playerNumber, int combinationNumber);

    int[][] getCombinationsSlotsValues();

    String[] getPlayersNames();

    int getNumberOfPlayers();

    int getPlayersTotalScore(int playerNumber);

    int[] getPlayersScore();

    int getCurrentPlayerNumber();







}
