package com.paweldyjak.dicegame.GameModes;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.Fragments.FinalResultTwoPlayersFragment;
import java.util.Arrays;

public class MultiplayerGame implements GameMode {
    private final GameBoardActivity gameBoardActivity;
    private String[] playersNames;
    private final String opponentUid;
    private final String playerUid = FirebaseAuth.getInstance().getUid();
    private int currentPlayerNumber = 1;
    private final int[][] combinationsPointsValues = new int[6][16];
    private final int[][] combinationsSlotsValues = new int[6][16];
    private final int[] totalScore = new int[6];

    public MultiplayerGame(GameBoardActivity gameBoardActivity, String[] playersNames, String opponentUID) {
        this.gameBoardActivity = gameBoardActivity;
        this.playersNames = playersNames;
        this.opponentUid = opponentUID;
    }

    public boolean checkIfAllCombinationsAreDone() {
        for (int x = 0; x < 16; x++) {
            if (getCombinationsSlotsValues()[getCurrentPlayerNumber()-1][x]==0) {
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

        Arrays.fill(totalScore, 0);

    }


    //generate final screen fragment
    public void setFinalResultScreen() {
        FinalResultTwoPlayersFragment finalResultTwoPlayersFragment = new FinalResultTwoPlayersFragment(gameBoardActivity, this);
        gameBoardActivity.addFragment(finalResultTwoPlayersFragment);

    }

    public int getCurrentPlayerNumber() {
        return currentPlayerNumber;
    }

    public void setCurrentPlayerNumber(int currentPlayerNumber) {
        this.currentPlayerNumber = currentPlayerNumber;
    }

    @Override
    public int getCombinationsPointsValues(int playerNumber, int combinationNumber) {
        return combinationsPointsValues[playerNumber-1][combinationNumber];
    }

    public String[] getPlayersNames() {
        return playersNames;
    }

    @Override
    public int getNumberOfPlayers() {
        return 2;
    }

    public void setPlayersNames(String[] playersNames) {
        this.playersNames = playersNames;
    }

    public int getPlayersTotalScore(int playerNumber) {
        return totalScore[playerNumber];

    }

    public int[][] getCombinationsSlotsValues() {
        return combinationsSlotsValues;
    }

    //set values in device
    public void setCombinationsPointsValues(int score, int combinationNr) {
        combinationsPointsValues[currentPlayerNumber - 1][combinationNr] = score;

    }

    public void setTotalScore(int score) {
        totalScore[currentPlayerNumber - 1] += score;
    }

    public void setCombinationsSlots(int combinationsSlotNumber, int slotStatus) {
        combinationsSlotsValues[currentPlayerNumber - 1][combinationsSlotNumber] = slotStatus;

    }

    //set values in database
    public void setCombinationsSlotsInDatabase(int combinationsSlotNumber, int slotStatus) {
        FirebaseDatabase.getInstance().getReference().child("users").child(opponentUid).child("multiplayerRoom")
                .child(playerUid).child("combinationsSlots").child(String.valueOf(combinationsSlotNumber + 1)).setValue(slotStatus);
    }

    public void setTotalScoreInDatabase(int score) {
        score += totalScore[currentPlayerNumber-1];
        FirebaseDatabase.getInstance().getReference().child("users").child(opponentUid).child("multiplayerRoom")
                .child(playerUid).child("totalScore").setValue(score);
    }

    public void setCombinationsPointsInDatabase(int score, int combinationNr) {
        FirebaseDatabase.getInstance().getReference().child("users").child(opponentUid).child("multiplayerRoom").child(playerUid)
                .child("combinationsPoints").child(String.valueOf(combinationNr + 1)).setValue(score);
    }
    public void updateOpponentTurnDatabase() {
        FirebaseDatabase.getInstance().getReference().child("users").child(opponentUid).child("multiplayerRoom").child(playerUid).child("opponentTurn").setValue(0);
        FirebaseDatabase.getInstance().getReference().child("users").child(opponentUid).child("multiplayerRoom").child(playerUid).child("opponentTurnStarted").setValue(0);
    }

}
