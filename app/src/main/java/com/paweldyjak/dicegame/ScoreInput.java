package com.paweldyjak.dicegame;
import android.content.Context;


public class ScoreInput {
    Context context;
    UIConfig uiConfig;
    private boolean resetThrowCounter = false;
    private DicesChecker dicesChecker;


    ScoreInput(Context context, UIConfig uiConfig, DicesChecker dicesChecker){
        this.uiConfig = uiConfig;
        this.context = context;
        this.dicesChecker = dicesChecker;
    }

    public void InputScoreOne(int scoreToInput) {
        uiConfig.getCombinations()[0].setOnClickListener(v -> {
            if(scoreToInput>0 && !dicesChecker.getIsCombinationDone()[0]) {
                uiConfig.getCombinationsPoints()[0].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                dicesChecker.setIsCombinationDone(true, 0);
            }
        });
    }

    public void InputScoreTwo(int scoreToInput) {
        uiConfig.getCombinations()[1].setOnClickListener(v -> {
            if(scoreToInput>0 && !dicesChecker.getIsCombinationDone()[1]) {
                uiConfig.getCombinationsPoints()[1].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                dicesChecker.setIsCombinationDone(true, 1);
            }
        });
    }

    public void InputScoreThree(int scoreToInput) {
        uiConfig.getCombinations()[2].setOnClickListener(v -> {
            if(scoreToInput>0 && !dicesChecker.getIsCombinationDone()[2]) {
                uiConfig.getCombinationsPoints()[2].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                dicesChecker.setIsCombinationDone(true, 2);
            }
        });
    }

    public void InputScoreFour(int scoreToInput) {
        uiConfig.getCombinations()[3].setOnClickListener(v -> {
            if(scoreToInput>0 && !dicesChecker.getIsCombinationDone()[3]) {
                uiConfig.getCombinationsPoints()[3].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                dicesChecker.setIsCombinationDone(true, 3);
            }
        });
    }

    public void InputScoreFive(int scoreToInput) {
        uiConfig.getCombinations()[4].setOnClickListener(v -> {
            if(scoreToInput>0 && !dicesChecker.getIsCombinationDone()[4]) {
                uiConfig.getCombinationsPoints()[4].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                dicesChecker.setIsCombinationDone(true, 4);
            }
        });
    }

    public void InputScoreSix(int scoreToInput) {
        uiConfig.getCombinations()[5].setOnClickListener(v -> {
            if(scoreToInput>0 && !dicesChecker.getIsCombinationDone()[5]) {
                uiConfig.getCombinationsPoints()[5].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                dicesChecker.setIsCombinationDone(true, 5);
            }
        });
    }

    public boolean getResetThrowCounter(){
        return resetThrowCounter;
    }

    public void setResetThrowCounter(boolean resetThrowCounter){
        this.resetThrowCounter = resetThrowCounter;
    }
}
