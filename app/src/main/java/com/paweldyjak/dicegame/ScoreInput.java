package com.paweldyjak.dicegame;

import android.content.Context;
import android.content.res.Resources;

//class methods writes score into score table
public class ScoreInput {
    Context context;
    UIConfig uiConfig;
    private boolean resetThrowCounter = false;


    ScoreInput(Context context, UIConfig uiConfig) {
        this.uiConfig = uiConfig;
        this.context = context;
    }

    public void InputScoreOne(int scoreToInput) {
        uiConfig.getCombinations()[0].setOnClickListener(v -> {
            if (scoreToInput > 0 && uiConfig.getIsCombinationActive()[0]) {
                uiConfig.getCombinationsPoints()[0].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.setIsCombinationActive(false, 0);

            }

        });
    }

    public void InputScoreTwo(int scoreToInput) {
        uiConfig.getCombinations()[1].setOnClickListener(v -> {
            if (scoreToInput > 0 && uiConfig.getIsCombinationActive()[1]) {
                uiConfig.getCombinationsPoints()[1].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.setIsCombinationActive(false, 1);

            }

        });
    }

    public void InputScoreThree(int scoreToInput) {
        uiConfig.getCombinations()[2].setOnClickListener(v -> {
            if (scoreToInput > 0 && uiConfig.getIsCombinationActive()[2]) {
                uiConfig.getCombinationsPoints()[2].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.setIsCombinationActive(false, 2);
            }

        });
    }

    public void InputScoreFour(int scoreToInput) {
        uiConfig.getCombinations()[3].setOnClickListener(v -> {
            if (scoreToInput > 0 && uiConfig.getIsCombinationActive()[3]) {
                uiConfig.getCombinationsPoints()[3].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.setIsCombinationActive(false, 3);
            }

        });
    }

    public void InputScoreFive(int scoreToInput) {
        uiConfig.getCombinations()[4].setOnClickListener(v -> {
            if (scoreToInput > 0 && uiConfig.getIsCombinationActive()[4]) {
                uiConfig.getCombinationsPoints()[4].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.setIsCombinationActive(false, 4);
            }

        });
    }

    public void InputScoreSix(int scoreToInput) {
        uiConfig.getCombinations()[5].setOnClickListener(v -> {
            if (scoreToInput > 0 && uiConfig.getIsCombinationActive()[5]) {
                uiConfig.getCombinationsPoints()[5].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.setIsCombinationActive(false, 5);
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
