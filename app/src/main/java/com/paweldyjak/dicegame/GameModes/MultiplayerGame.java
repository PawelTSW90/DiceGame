package com.paweldyjak.dicegame.GameModes;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.Fragments.FinalResultTwoPlayersFragment;
import com.paweldyjak.dicegame.R;
import com.paweldyjak.dicegame.UIConfig;

import java.util.Arrays;

public class MultiplayerGame implements GameMode {
    private final UIConfig uiConfig;
    private final GameBoardActivity gameBoardActivity;
    private String[] playersNames;
    private final String opponentUid;
    private final String playerUid = FirebaseAuth.getInstance().getUid();
    private int currentPlayerNumber = 1;
    private int[][] combinationsPointsValues = new int[6][16];
    private int[][] combinationsSlotsValues = new int[6][16];
    private boolean[][] isCombinationActive = new boolean[6][16];
    private int[] totalScore = new int[6];

    public MultiplayerGame(UIConfig uiConfig, GameBoardActivity gameBoardActivity, String[] playersNames, String opponentUID) {
        this.uiConfig = uiConfig;
        this.gameBoardActivity = gameBoardActivity;
        this.playersNames = playersNames;
        this.opponentUid = opponentUID;
    }

    public boolean checkIfAllCombinationsAreDone() {
        for (int x = 0; x < isCombinationActive.length; x++) {
            if (isCombinationActive[currentPlayerNumber - 1][x]) {
                return false;
            }
        }
        return true;
    }

    public void setAllCombinationsAsActive() {

    }


    public void setStartBoardValues() {
        for (int[] row : combinationsPointsValues) {
            Arrays.fill(row, 0);
        }
        for (int[] row : combinationsSlotsValues) {
            Arrays.fill(row, 0);
        }

        for (boolean[] row : isCombinationActive) {
            Arrays.fill(row, true);
        }

        Arrays.fill(totalScore, 0);

    }


    //generate final screen fragment
    public void setFinalResultScreen() {
        FinalResultTwoPlayersFragment finalResultTwoPlayersFragment = new FinalResultTwoPlayersFragment(gameBoardActivity, this);
        gameBoardActivity.replaceFragment(R.id.fragment_layout, finalResultTwoPlayersFragment);

    }

    public boolean[] getIsCombinationActive() {

        return isCombinationActive[currentPlayerNumber - 1];

    }


    public int getCombinationsPointsValues(int playerNumber,int combinationNr) {

        return combinationsPointsValues[playerNumber][combinationNr];

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

    public int[] getPlayersScore() {
        return totalScore;
    }


    public void prepareCombinationsSlots() {

    }

    @Override
    public void setNumberOfPlayers(int numberOfPlayers) {

    }

    @Override
    public int getNumberOfPlayers() {
        return 2;
    }

    @Override
    public String getGameMode() {
        return "MultiplayerMode";
    }

    public void updateOpponentTurnDatabase() {
        FirebaseDatabase.getInstance().getReference().child("users").child(opponentUid).child("multiplayerRoom").child(playerUid).child("opponentTurn").setValue(0);
        FirebaseDatabase.getInstance().getReference().child("users").child(opponentUid).child("multiplayerRoom").child(playerUid).child("opponentTurnStarted").setValue(0);
        FirebaseDatabase.getInstance().getReference().child("users").child(playerUid).child("multiplayerRoom").child(opponentUid).child("opponentTurn").setValue(1);
        FirebaseDatabase.getInstance().getReference().child("users").child(playerUid).child("multiplayerRoom").child(opponentUid).child("opponentTurnStarted").setValue(0);
    }

    public int[][] getCombinationsSlotsValues() {
        return combinationsSlotsValues;
    }

    public void changeCurrentPlayer() {
        if (currentPlayerNumber == 1) {
            currentPlayerNumber = 2;
        } else {
            currentPlayerNumber = 1;
        }

        if (uiConfig.getCurrentPlayerName().getText().equals(playersNames[0])) {
            uiConfig.changeCurrentPlayerName(playersNames[1]);
        } else {
            uiConfig.changeCurrentPlayerName(playersNames[0]);
        }
    }


    public void setCombinationsPointsValues(int score, int combinationNr) {
        combinationsPointsValues[currentPlayerNumber - 1][combinationNr] = score;

    }

    public void setCombinationsPointsInDatabase(int score, int combinationNr) {
        FirebaseDatabase.getInstance().getReference().child("users").child(opponentUid).child("multiplayerRoom").child(playerUid)
                .child("combinationsPoints").child(String.valueOf(combinationNr + 1)).setValue(score);
    }

    public void setTotalScore(int score) {
        totalScore[currentPlayerNumber - 1] += score;
    }

    public void setTotalScoreInDatabase(int score) {
        totalScore[currentPlayerNumber - 1] += score;
        FirebaseDatabase.getInstance().getReference().child("users").child(opponentUid).child("multiplayerRoom")
                .child(playerUid).child("totalScore").setValue(totalScore[currentPlayerNumber - 1]);
    }

    public void setIsCombinationActive(boolean isCombinationActive, int combinationNr) {


        this.isCombinationActive[currentPlayerNumber - 1][combinationNr] = isCombinationActive;

    }

    public void setIsCombinationActiveInDatabase(boolean isCombinationActive, int combinationNr) {
        FirebaseDatabase.getInstance().getReference().child("users").child(opponentUid).child("multiplayerRoom").child(playerUid)
                .child("isCombinationActive").child(String.valueOf(combinationNr + 1)).setValue(isCombinationActive);
    }

    public void setCombinationsSlots(int combinationsSlotNumber, int slotStatus) {
        combinationsSlotsValues[currentPlayerNumber - 1][combinationsSlotNumber] = slotStatus;

    }

    public void setCombinationsSlotsInDatabase(int combinationsSlotNumber, int slotStatus) {
        FirebaseDatabase.getInstance().getReference().child("users").child(opponentUid).child("multiplayerRoom")
                .child(playerUid).child("combinationsSlots").child(String.valueOf(combinationsSlotNumber + 1)).setValue(slotStatus);
    }

    public void updateGameBoard() {

        for (int x = 0; x < uiConfig.getCombinationsSlots().length; x++) {
            if (combinationsSlotsValues[currentPlayerNumber - 1][x] == 1) {
                uiConfig.updateCombinationsSlots(1, x);
            } else if (combinationsSlotsValues[currentPlayerNumber - 1][x] == 2) {
                uiConfig.updateCombinationsSlots(2, x);
            } else {
                uiConfig.updateCombinationsSlots(0, x);
            }
            uiConfig.getCombinationsPoints()[x].setText(combinationsPointsValues[currentPlayerNumber - 1][x] + " " + gameBoardActivity.getString(R.string.points));
            uiConfig.getTotalScore().setText(totalScore[currentPlayerNumber - 1] + " " + gameBoardActivity.getString(R.string.points));


        }
    }
}
