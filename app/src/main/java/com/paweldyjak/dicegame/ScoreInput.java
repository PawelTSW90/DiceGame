package com.paweldyjak.dicegame;

import android.content.Context;

//class methods writes score into score table
//score writing enabled when combination is correct, when it's not blocked, no other combination
// has been used during this turn and no combination has been blocked during this turn
public class ScoreInput {
    Context context;
    UIConfig uiConfig;
    private boolean resetThrowCounter = false;
    private int totalScore = 0;

    ScoreInput(Context context, UIConfig uiConfig) {
        this.uiConfig = uiConfig;
        this.context = context;
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
        uiConfig.getCombinations()[combinationNr].setOnClickListener(v -> {
            if (scoreToInput > 0 && uiConfig.getIsCombinationActive()[combinationNr] && !resetThrowCounter) {
                uiConfig.setScoreValues(scoreToInput, combinationNr);
                totalScore += scoreToInput;
                uiConfig.setTotalScore(totalScore);
                uiConfig.getCombinationsPoints()[combinationNr].setText(uiConfig.getScoreValues(combinationNr) + " pkt");
                uiConfig.getCombinations()[combinationNr].setEnabled(false);
                uiConfig.setIsCombinationActive(false, combinationNr);
                if (!uiConfig.checkIfAllCombinationsAreDone()) {
                    resetThrowCounter = true;
                }
                uiConfig.hideDices();
                resetCombinationsListeners();
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


            uiConfig.getCombinations()[x].setOnClickListener(v -> {

            });
        }
    }


}
