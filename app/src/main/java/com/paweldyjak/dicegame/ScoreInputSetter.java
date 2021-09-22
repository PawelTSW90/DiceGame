package com.paweldyjak.dicegame;
import android.os.Build;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.GameModes.GameMode;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/*class methods writes score into score table
score writing enabled when combination is correct, when it's not blocked, no other combination
 has been used during this turn and no combination has been blocked during this turn*/
public class ScoreInputSetter {

    private final UIConfig uiConfig;
    private final GameBoardActivity gameBoardActivity;
    private final GameMode gameMode;
    private boolean resetThrowCounter = false;
    private final Sounds sounds;

    public ScoreInputSetter(GameBoardActivity gameBoardActivity, UIConfig uiConfig, GameMode gameMode) {
        this.gameBoardActivity = gameBoardActivity;
        this.uiConfig = uiConfig;
        this.gameMode = gameMode;
        sounds = new Sounds(gameBoardActivity);
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

    public void updatePlayerScore(int scoreToInput, int combinationNumber) {
        Executor executor = ContextCompat.getMainExecutor(gameBoardActivity);
        uiConfig.getCombinationsTextView()[combinationNumber].setOnClickListener(v -> {

            if (scoreToInput > 0 && gameMode.getIsCombinationActive()[combinationNumber] && !resetThrowCounter) {
                sounds.playCompleteCombinationSound();
                gameMode.setCombinationScore(scoreToInput, combinationNumber);
                String points = gameMode.getCombinationScore(combinationNumber) + " pkt";
                gameMode.setTotalScore(scoreToInput);
                uiConfig.clearDicesBorder();
                uiConfig.hideDices();
                uiConfig.getCombinationsPointsTextView()[combinationNumber].setText(points);
                uiConfig.getCombinationsTextView()[combinationNumber].setEnabled(false);
                gameMode.setIsCombinationActive(false, combinationNumber);
                gameMode.setCombinationsSlots(combinationNumber, 1);
                gameMode.prepareCombinationsSlots();
                if (gameMode.getCurrentPlayerNumber() == gameMode.getNumberOfPlayers() && gameMode.checkIfAllCombinationsAreDone()) {
                    executor.execute(() -> {
                        try {
                            Thread.sleep(2000);
                            gameMode.setFinalResultScreen();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    });
                } else {
                    executor.execute(() -> {
                        try {
                            Thread.sleep(2000);
                            resetThrowCounter = true;
                            resetCombinationsListeners();
                            gameBoardActivity.showNextTurnFragment();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    });
                }

            }


        });
        uiConfig.getCombinationsSlots()[combinationNumber].setOnClickListener(v -> {
            if (scoreToInput > 0 && gameMode.getIsCombinationActive()[combinationNumber] && !resetThrowCounter) {
                sounds.playCompleteCombinationSound();
                gameMode.setCombinationScore(scoreToInput, combinationNumber);
                String points = gameMode.getCombinationScore(combinationNumber) + " pkt";
                gameMode.setTotalScore(scoreToInput);
                uiConfig.clearDicesBorder();
                uiConfig.hideDices();
                uiConfig.getCombinationsPointsTextView()[combinationNumber].setText(points);
                uiConfig.getCombinationsTextView()[combinationNumber].setEnabled(false);
                gameMode.setIsCombinationActive(false, combinationNumber);
                gameMode.setCombinationsSlots(combinationNumber, 1);
                gameMode.prepareCombinationsSlots();
                if (gameMode.getCurrentPlayerNumber() == gameMode.getNumberOfPlayers() && gameMode.checkIfAllCombinationsAreDone()) {
                    executor.execute(() -> {
                        try {
                            Thread.sleep(2000);
                            gameMode.setFinalResultScreen();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    });
                } else {
                    executor.execute(() -> {
                        try {
                            Thread.sleep(2000);
                            resetThrowCounter = true;
                            resetCombinationsListeners();
                            gameMode.setOpponentTurn(!gameMode.getOpponentTurn());
                            gameBoardActivity.showNextTurnFragment();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    });
                }

            }
        });


    }

    public void updateDatabasePlayerScore(int scoreToInput, int combinationNumber){
        FirebaseDatabase.getInstance().getReference().child("users").child(uiConfig.getOpponentUid()).child("multiplayerRoom").child(uiConfig.getPlayerUid())
                .child("isCombinationActive").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    if(dataSnapshot.getKey().equals(String.valueOf(combinationNumber))){
                        if(dataSnapshot.getValue(Boolean.class)){
                            uiConfig.getCombinationsTextView()[combinationNumber].setOnClickListener(v -> {
                                if(scoreToInput>0 && !resetThrowCounter){
                                    sounds.playCompleteCombinationSound();
                                    gameMode.setCombinationScore(scoreToInput, combinationNumber);
                                    String points = gameMode.getCombinationScore(combinationNumber) + " pkt";
                                    gameMode.setTotalScore(scoreToInput);
                                    uiConfig.clearDicesBorder();
                                    uiConfig.hideDices();
                                    uiConfig.getCombinationsPointsTextView()[combinationNumber].setText(points);
                                    uiConfig.getCombinationsTextView()[combinationNumber].setEnabled(false);
                                    gameMode.setIsCombinationActive(false, combinationNumber);
                                    gameMode.setCombinationsSlots(combinationNumber, 1);
                                    gameMode.prepareCombinationsSlots();
                                }
                            });
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

    public void resetCombinationsListeners() {
        for (int x = 0; x < 15; x++) {

            uiConfig.getCombinationsTextView()[x].setOnClickListener(v -> {

            });
        }
    }

    /*@RequiresApi(api = Build.VERSION_CODES.N)
    public void updateScoreDatabase(int combinationNumber, int points){
        Map<String, Integer> combinationPointsValues = new HashMap<>();
        for(int x = 0; x<16; x++){
            combinationPointsValues.put(String.valueOf(x + 1), 0);
        }
        combinationPointsValues.replace(String.valueOf(combinationNumber), points);
        FirebaseDatabase.getInstance().getReference().child("users").child(uiConfig.getOpponentUid()).child("multiplayerRoom").child(uiConfig.getPlayerUid())
                .child("combinationsPoints").setValue(combinationPointsValues);
    }*/
}
