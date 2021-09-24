package com.paweldyjak.dicegame.GameModes;

import android.graphics.Color;
import android.view.Gravity;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.Fragments.FinalResultTwoPlayersFragment;
import com.paweldyjak.dicegame.R;
import com.paweldyjak.dicegame.UIConfig;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class MultiplayerGame implements GameMode {
    private final UIConfig uiConfig;
    private final GameBoardActivity gameBoardActivity;
    private String[] playersNames;
    private final String opponentUid;
    private final String playerUid = FirebaseAuth.getInstance().getUid();
    private int currentPlayerNumber = 2;
    private final int[][] playersCombinationsScoreValues = new int[6][16];
    private final int[][] playersCombinationsSlotsValues = new int[6][16];
    private final boolean[][] playersIsCombinationActive = new boolean[6][16];
    private final int[] playersTotalScore = new int[6];
    private boolean opponentTurn = false;

    public MultiplayerGame(UIConfig uiConfig, GameBoardActivity gameBoardActivity, String[] playersNames, String opponentUID) {
        this.uiConfig = uiConfig;
        this.gameBoardActivity = gameBoardActivity;
        this.playersNames = playersNames;
        this.opponentUid = opponentUID;
    }

    public void setAllCombinationsAsActive() {
        for (boolean[] row : playersIsCombinationActive)
            Arrays.fill(row, true);
    }

    public void setTotalScore(int score) {
        String string = gameBoardActivity.getResources().getString(R.string.points);
        playersTotalScore[currentPlayerNumber - 1] += score;
        FirebaseDatabase.getInstance().getReference().child("users").child(opponentUid).child("multiplayerRoom")
                .child(playerUid).child("totalScore").setValue(playersTotalScore[currentPlayerNumber - 1]);
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
        FirebaseDatabase.getInstance().getReference().child("users").child(opponentUid).child("multiplayerRoom").child(playerUid)
                .child("combinationsPoints").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Integer> pointsMap = new LinkedHashMap<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    pointsMap.put(dataSnapshot.getKey(), dataSnapshot.getValue(Integer.class));
                }
                int tmp;
                for (int x = 0; x < 16; x++) {
                    tmp = x + 1;
                    String tmpValue = String.valueOf(tmp);
                    uiConfig.getCombinationsPointsTextView()[x].setText(pointsMap.get(tmpValue) + " " + gameBoardActivity.getString(R.string.points));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        FirebaseDatabase.getInstance().getReference().child("users").child(playerUid).child("multiplayerRoom")
                .child(opponentUid).child("totalScore").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int totalScore;
                totalScore = snapshot.getValue(Integer.class);
                uiConfig.getTotalScoreTextView().setText(totalScore + " " + gameBoardActivity.getString(R.string.points));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    //generate final screen fragment
    public void setFinalResultScreen() {
        FinalResultTwoPlayersFragment finalResultTwoPlayersFragment = new FinalResultTwoPlayersFragment(gameBoardActivity, this);
        gameBoardActivity.replaceFragment(R.id.fragment_layout, finalResultTwoPlayersFragment);

    }

    public boolean[] getIsCombinationActive() {

        return playersIsCombinationActive[currentPlayerNumber - 1];

    }

    public void setIsCombinationActive(boolean isCombinationActive, int combinationNr) {
        FirebaseDatabase.getInstance().getReference().child("users").child(opponentUid).child("multiplayerRoom").child(playerUid)
                .child("isCombinationActive").child(String.valueOf(combinationNr + 1)).setValue(isCombinationActive);

        this.playersIsCombinationActive[currentPlayerNumber - 1][combinationNr] = isCombinationActive;

    }

    public int getCombinationScore(int combinationNr) {

        return playersCombinationsScoreValues[currentPlayerNumber - 1][combinationNr];

    }

    public void setCombinationScore(int score, int combinationNr) {
        FirebaseDatabase.getInstance().getReference().child("users").child(opponentUid).child("multiplayerRoom").child(playerUid)
                .child("combinationsPoints").child(String.valueOf(combinationNr + 1)).setValue(score);

        playersCombinationsScoreValues[currentPlayerNumber - 1][combinationNr] = score;

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

    public int[] getPlayersScore() {
        return playersTotalScore;
    }

    public void setCombinationsSlots(int combinationsSlotNumber, int slotStatus) {
        FirebaseDatabase.getInstance().getReference().child("users").child(opponentUid).child("multiplayerRoom")
                .child(playerUid).child("combinationsSlots").child(String.valueOf(combinationsSlotNumber + 1)).setValue(slotStatus);
        playersCombinationsSlotsValues[currentPlayerNumber - 1][combinationsSlotNumber] = slotStatus;


    }

    public void prepareCombinationsSlots() {
        for (int x = 0; x < uiConfig.getCombinationsSlots().length; x++) {
            if (playersCombinationsSlotsValues[currentPlayerNumber - 1][x] == 1) {
                int[] green = {27, 182, 33};
                uiConfig.prepareCombinationsSlotForNextPlayer(x, "\u2713", Gravity.CENTER, 16, green, false);
                uiConfig.getCombinationsPointsTextView()[x].setEnabled(true);
            } else if (playersCombinationsSlotsValues[currentPlayerNumber - 1][x] == 2) {
                int[] red = {140, 17,16};
                uiConfig.prepareCombinationsSlotForNextPlayer(x, "X", Gravity.CENTER, 16, red, false);
                uiConfig.getCombinationsPointsTextView()[x].setEnabled(false);
            } else {
                uiConfig.prepareCombinationsSlotForNextPlayer(0, null, 0, 0, null, true);
            }


        }
    }

    @Override
    public void setNumberOfPlayers(int numberOfPlayers) {

    }

    @Override
    public int getNumberOfPlayers() {
        return 2;
    }

    @Override
    public boolean getOpponentTurn() {
        return opponentTurn;
    }

    @Override
    public void setOpponentTurn(boolean opponentTurn) {
        this.opponentTurn = opponentTurn;
    }

    @Override
    public String getGameMode() {
        return "MultiplayerMode";
    }

    @Override
    public void updatePlayerName() {
            if(currentPlayerNumber==1){
                uiConfig.setCurrentPlayerName(playersNames[1]);
               currentPlayerNumber = 2;
            } else{
                uiConfig.setCurrentPlayerName(playersNames[0]);
                currentPlayerNumber = 1;
            }


    }

    public void updateOpponentTurnDatabase(){
        FirebaseDatabase.getInstance().getReference().child("users").child(opponentUid).child("multiplayerRoom").child(playerUid).child("opponentTurn").setValue(0);
        FirebaseDatabase.getInstance().getReference().child("users").child(opponentUid).child("multiplayerRoom").child(playerUid).child("opponentTurnStarted").setValue(0);
    }


}
