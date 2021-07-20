package com.paweldyjak.dicegame;

import android.content.Context;
import android.content.res.Resources;

//class methods writes score into score table
//score writing enabled when combination is correct, when it's not blocked, no other combination
// has been used during this turn and no combination has been blocked during this turn
public class ScoreInput {
    Context context;
    UIConfig uiConfig;
    private boolean resetThrowCounter = false;


    ScoreInput(Context context, UIConfig uiConfig) {
        this.uiConfig = uiConfig;
        this.context = context;
    }

    public void inputScoreOne(int scoreToInput) {
        uiConfig.getCombinations()[0].setOnClickListener(v -> {
            if (scoreToInput > 0 && uiConfig.getIsCombinationActive()[0]&& !resetThrowCounter) {
                uiConfig.getCombinationsPoints()[0].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.setIsCombinationActive(false, 0);

            }

        });
    }

    public void inputScoreTwo(int scoreToInput) {
        uiConfig.getCombinations()[1].setOnClickListener(v -> {
            if (scoreToInput > 0 && uiConfig.getIsCombinationActive()[1]&& !resetThrowCounter) {
                uiConfig.getCombinationsPoints()[1].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.setIsCombinationActive(false, 1);

            }

        });
    }

    public void inputScoreThree(int scoreToInput) {
        uiConfig.getCombinations()[2].setOnClickListener(v -> {
            if (scoreToInput > 0 && uiConfig.getIsCombinationActive()[2]&& !resetThrowCounter) {
                uiConfig.getCombinationsPoints()[2].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.setIsCombinationActive(false, 2);
            }

        });
    }

    public void inputScoreFour(int scoreToInput) {
        uiConfig.getCombinations()[3].setOnClickListener(v -> {
            if (scoreToInput > 0 && uiConfig.getIsCombinationActive()[3]&& !resetThrowCounter) {
                uiConfig.getCombinationsPoints()[3].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.setIsCombinationActive(false, 3);
            }

        });
    }

    public void inputScoreFive(int scoreToInput) {
        uiConfig.getCombinations()[4].setOnClickListener(v -> {
            if (scoreToInput > 0 && uiConfig.getIsCombinationActive()[4]&& !resetThrowCounter) {
                uiConfig.getCombinationsPoints()[4].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.setIsCombinationActive(false, 4);
            }

        });
    }

    public void inputScoreSix(int scoreToInput) {
        uiConfig.getCombinations()[5].setOnClickListener(v -> {
            if (scoreToInput > 0 && uiConfig.getIsCombinationActive()[5]&& !resetThrowCounter) {
                uiConfig.getCombinationsPoints()[5].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.setIsCombinationActive(false, 5);
            }

        });
    }

    public void inputScorePair(int scoreToInput){
        uiConfig.getCombinations()[6].setOnClickListener(v -> {
            if(scoreToInput>0 && uiConfig.getIsCombinationActive()[6]&& !resetThrowCounter){
            uiConfig.getCombinationsPoints()[6].setText(scoreToInput + " pkt");
            resetThrowCounter = true;
            uiConfig.setIsCombinationActive(false, 6);
            }


        });
    }

    public void inputScoreTwoPairs(int scoreToInput){
        uiConfig.getCombinations()[7].setOnClickListener(v -> {
            if(scoreToInput>0 && uiConfig.getIsCombinationActive()[7]&& !resetThrowCounter){
                uiConfig.getCombinationsPoints()[7].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.setIsCombinationActive(false, 7);
            }


        });
    }

    public void inputScoreEvens(int scoreToInput){
        uiConfig.getCombinations()[8].setOnClickListener(v -> {
            if(scoreToInput>0 && uiConfig.getIsCombinationActive()[8] && !resetThrowCounter){
                uiConfig.getCombinationsPoints()[8].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.setIsCombinationActive(false, 8);
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
