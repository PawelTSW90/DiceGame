package com.paweldyjak.dicegame;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.GameModes.GameMode;

/*class methods writes score into score table
score writing enabled when combination is correct, when it's not blocked, no other combination
 has been used during this turn and no combination has been blocked during this turn*/
public class ScoreInputSetter {

    private final UIConfig uiConfig;
    private final GameBoardActivity gameBoardActivity;
    private final GameMode gameMode;
    private boolean resetThrowCounter = false;

    public ScoreInputSetter(GameBoardActivity gameBoardActivity, UIConfig uiConfig, GameMode gameMode) {
        this.gameBoardActivity = gameBoardActivity;
        this.uiConfig = uiConfig;
        this.gameMode = gameMode;
    }
    /*method inputs score for a specified combination. Combinations list:
    combination nr 0 = 1
    combination nr 1 = 2
    combination nr 2 = 3
    combination nr 3 = 4
    combination nr 4 = 5
    combination nr 5 = 6
    combination nr 6 = pair
    combination nr 7 = pairs
    combination nr 8 = evens
    combination nr 9 = odds
    combination nr 10 = small straight
    combination nr 11 = large straight
    combination nr 12 = full house
    combination nr 13 = 4 of a kind
    combination nr 14 = 5 of a kind
    combination nr 15 = Sos
    */

    public void updatePlayerScore(int scoreToInput, int combinationNumber, GameBoardManager gameBoardManager) {

        uiConfig.getCombinationsText()[combinationNumber].setOnClickListener(new ScoreInputListener(gameBoardActivity, gameMode, this, uiConfig,gameBoardManager, resetThrowCounter, scoreToInput, combinationNumber, gameMode.getIsCombinationActive()[combinationNumber]));
        uiConfig.getCombinationsSlots()[combinationNumber].setOnClickListener(new ScoreInputListener(gameBoardActivity, gameMode, this, uiConfig,gameBoardManager, resetThrowCounter, scoreToInput, combinationNumber, gameMode.getIsCombinationActive()[combinationNumber]));


    }

    public void updateDatabasePlayerScore(int scoreToInput, int combinationNumber, GameBoardManager gameBoardManager) {
        //create database variable increased by one
        int databaseCombinationNumber = combinationNumber+1;
        FirebaseDatabase.getInstance().getReference().child("users").child(uiConfig.getOpponentUid()).child("multiplayerRoom").child(uiConfig.getPlayerUid())
                .child("isCombinationActive").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (dataSnapshot.getKey().equals(String.valueOf(databaseCombinationNumber))) {
                        if (dataSnapshot.getValue(Boolean.class)) {
                            uiConfig.getCombinationsText()[combinationNumber].setOnClickListener(new ScoreInputListener(gameBoardActivity, gameMode, ScoreInputSetter.this, uiConfig,gameBoardManager, resetThrowCounter, scoreToInput, combinationNumber, true));
                            uiConfig.getCombinationsSlots()[combinationNumber].setOnClickListener(new ScoreInputListener(gameBoardActivity, gameMode, ScoreInputSetter.this, uiConfig,gameBoardManager, resetThrowCounter, scoreToInput, combinationNumber, true));

                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public boolean getResetThrowCounter() {
        return resetThrowCounter;
    }

    public void setResetThrowCounter(boolean resetThrowCounter) {
        this.resetThrowCounter = resetThrowCounter;
    }

}
