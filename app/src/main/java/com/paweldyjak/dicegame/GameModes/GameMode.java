package com.paweldyjak.dicegame.GameModes;


public interface GameMode {

    void setAllCombinationsAsActive();

    void setTotalScore(int score);

    boolean checkIfAllCombinationsAreDone();

    void prepareScoreBoard();

    void setFinalResultScreen();

    boolean[] getIsCombinationActive();

    void setIsCombinationActive(boolean isCombinationActive, int combinationNr);

    int getCombinationScore(int combinationNr);

    void setCombinationScore(int score, int combinationNr);

    int getCurrentPlayerNumber();

    void setCurrentPlayerNumber(int currentPlayerNumber);

    String[] getPlayersNames();

    void setPlayersNames(String[] playersNames);

    int getPlayersTotalScore(int playerNumber);

    int[] getPlayersScore();

    void setCombinationsSlots(int combinationsSlotNumber, int slotStatus);

    void prepareCombinationsSlots();

    void setNumberOfPlayers(int numberOfPlayers);

    int getNumberOfPlayers();

    boolean getOpponentTurn();

    void setOpponentTurn(boolean opponentTurn);

    String getGameMode();
}
