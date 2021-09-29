package com.paweldyjak.dicegame.GameModes;


public interface GameMode {

    boolean checkIfAllCombinationsAreDone();

    void updateGameBoard();

    void setFinalResultScreen();

    void prepareCombinationsSlots();


    void setAllCombinationsAsActive();

    void setTotalScore(int score);

    void setIsCombinationActive(boolean isCombinationActive, int combinationNr);

    void setCombinationsPointsValues(int score, int combinationNr);

    void setCombinationsSlots(int combinationsSlotNumber, int slotStatus);

    void setNumberOfPlayers(int numberOfPlayers);

    void setPlayersNames(String[] playersNames);

    void setCurrentPlayerNumber(int currentPlayerNumber);

    String getGameMode();

    String[] getPlayersNames();

    int getNumberOfPlayers();

    int getPlayersTotalScore(int playerNumber);

    int[] getPlayersScore();

    boolean[] getIsCombinationActive();

    int getCurrentPlayerNumber();







}
