package com.paweldyjak.dicegame;

import androidx.core.content.ContextCompat;
import java.util.concurrent.Executor;

/*class methods writes score into score table
score writing enabled when combination is correct, when it's not blocked, no other combination
 has been used during this turn and no combination has been blocked during this turn*/
public class ScoreInput {

    private final UIConfig uiConfig;
    private final MainActivity mainActivity;
    private boolean resetThrowCounter = false;
    private final Sounds sounds;

    public ScoreInput(MainActivity mainActivity,UIConfig uiConfig) {
        this.mainActivity = mainActivity;
        this.uiConfig = uiConfig;
        sounds = new Sounds(mainActivity);
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

    public void inputScore(int scoreToInput, int combinationNr) {
        Executor executor = ContextCompat.getMainExecutor(mainActivity);
        uiConfig.getCombinationsTextView()[combinationNr].setOnClickListener(v -> {

            if (scoreToInput > 0 && uiConfig.getIsCombinationActive()[combinationNr] && !resetThrowCounter) {
                sounds.playCombinationTickSound();
                uiConfig.setCombinationScore(scoreToInput, combinationNr);
                uiConfig.setTotalScore(scoreToInput);
                uiConfig.clearDicesBorder();
                uiConfig.getCombinationsPointsTextView()[combinationNr].setText(uiConfig.getCombinationScore(combinationNr) + " pkt");
                uiConfig.getCombinationsTextView()[combinationNr].setEnabled(false);
                uiConfig.setIsCombinationActive(false, combinationNr);
                if (!uiConfig.checkIfAllCombinationsAreDone() || uiConfig.getPlayerNumber()==1) {
                    executor.execute(() -> {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        resetThrowCounter = true;
                        resetCombinationsListeners();
                        uiConfig.hideDices();
                        mainActivity.showFragment(true);
                    });


                } else if(uiConfig.getPlayerNumber()==2 && uiConfig.checkIfAllCombinationsAreDone()){
                    executor.execute(() -> {
                        try {
                            Thread.sleep(2000);
                            uiConfig.setFinalResultScreen();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    });
                }

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



}
