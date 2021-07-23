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

    public void inputScoreOne(int scoreToInput) {
        uiConfig.getCombinations()[0].setOnClickListener(v -> {
            if (scoreToInput > 0 && uiConfig.getIsCombinationActive()[0]&& !resetThrowCounter) {
                totalScore +=scoreToInput;
                uiConfig.setTotalScore(totalScore);
                uiConfig.getCombinationsPoints()[0].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.getCombinations()[0].setEnabled(false);
                uiConfig.setIsCombinationActive(false, 0);

            }

        });
    }

    public void inputScoreTwo(int scoreToInput) {
        uiConfig.getCombinations()[1].setOnClickListener(v -> {
            if (scoreToInput > 0 && uiConfig.getIsCombinationActive()[1]&& !resetThrowCounter) {
                totalScore +=scoreToInput;
                uiConfig.setTotalScore(totalScore);
                uiConfig.getCombinationsPoints()[1].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.getCombinations()[1].setEnabled(false);
                uiConfig.setIsCombinationActive(false, 1);

            }

        });
    }

    public void inputScoreThree(int scoreToInput) {
        uiConfig.getCombinations()[2].setOnClickListener(v -> {
            if (scoreToInput > 0 && uiConfig.getIsCombinationActive()[2]&& !resetThrowCounter) {
                totalScore +=scoreToInput;
                uiConfig.setTotalScore(totalScore);
                uiConfig.getCombinationsPoints()[2].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.getCombinations()[2].setEnabled(false);
                uiConfig.setIsCombinationActive(false, 2);
            }

        });
    }

    public void inputScoreFour(int scoreToInput) {
        uiConfig.getCombinations()[3].setOnClickListener(v -> {
            if (scoreToInput > 0 && uiConfig.getIsCombinationActive()[3]&& !resetThrowCounter) {
                totalScore +=scoreToInput;
                uiConfig.setTotalScore(totalScore);
                uiConfig.getCombinationsPoints()[3].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.getCombinations()[3].setEnabled(false);
                uiConfig.setIsCombinationActive(false, 3);
            }

        });
    }

    public void inputScoreFive(int scoreToInput) {
        uiConfig.getCombinations()[4].setOnClickListener(v -> {
            if (scoreToInput > 0 && uiConfig.getIsCombinationActive()[4]&& !resetThrowCounter) {
                totalScore +=scoreToInput;
                uiConfig.setTotalScore(totalScore);
                uiConfig.getCombinationsPoints()[4].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.getCombinations()[4].setEnabled(false);
                uiConfig.setIsCombinationActive(false, 4);
            }

        });
    }

    public void inputScoreSix(int scoreToInput) {
        uiConfig.getCombinations()[5].setOnClickListener(v -> {
            if (scoreToInput > 0 && uiConfig.getIsCombinationActive()[5]&& !resetThrowCounter) {
                totalScore +=scoreToInput;
                uiConfig.setTotalScore(totalScore);
                uiConfig.getCombinationsPoints()[5].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.getCombinations()[5].setEnabled(false);
                uiConfig.setIsCombinationActive(false, 5);
            }

        });
    }

    public void inputScorePair(int scoreToInput){
        uiConfig.getCombinations()[6].setOnClickListener(v -> {
            if(scoreToInput>0 && uiConfig.getIsCombinationActive()[6]&& !resetThrowCounter){
                totalScore +=scoreToInput;
                uiConfig.setTotalScore(totalScore);
            uiConfig.getCombinationsPoints()[6].setText(scoreToInput + " pkt");
            resetThrowCounter = true;
                uiConfig.getCombinations()[6].setEnabled(false);
            uiConfig.setIsCombinationActive(false, 6);
            }


        });
    }

    public void inputScoreTwoPairs(int scoreToInput){
        uiConfig.getCombinations()[7].setOnClickListener(v -> {
            if(scoreToInput>0 && uiConfig.getIsCombinationActive()[7]&& !resetThrowCounter){
                totalScore +=scoreToInput;
                uiConfig.setTotalScore(totalScore);
                uiConfig.getCombinationsPoints()[7].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.getCombinations()[7].setEnabled(false);
                uiConfig.setIsCombinationActive(false, 7);
            }



        });
    }

    public void inputScoreEvens(int scoreToInput){
        uiConfig.getCombinations()[8].setOnClickListener(v -> {
            if(scoreToInput>0 && uiConfig.getIsCombinationActive()[8] && !resetThrowCounter){
                totalScore +=scoreToInput;
                uiConfig.setTotalScore(totalScore);
                uiConfig.getCombinationsPoints()[8].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.getCombinations()[8].setEnabled(false);
                uiConfig.setIsCombinationActive(false, 8);
            }


        });
    }

    public void inputScoreOdds(int scoreToInput){
        uiConfig.getCombinations()[9].setOnClickListener(v -> {
            if(scoreToInput>0 && uiConfig.getIsCombinationActive()[9] && !resetThrowCounter){
                totalScore +=scoreToInput;
                uiConfig.setTotalScore(totalScore);
                uiConfig.getCombinationsPoints()[9].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.getCombinations()[9].setEnabled(false);
                uiConfig.setIsCombinationActive(false, 9);
            }


        });
    }

    public void inputScoreSmallStraight(int scoreToInput){
        uiConfig.getCombinations()[10].setOnClickListener(v -> {
            if(scoreToInput>0 && uiConfig.getIsCombinationActive()[10] && !resetThrowCounter){
                totalScore +=scoreToInput;
                uiConfig.setTotalScore(totalScore);
                uiConfig.getCombinationsPoints()[10].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.getCombinations()[10].setEnabled(false);
                uiConfig.setIsCombinationActive(false, 10);
            }


        });
    }

    public void inputScoreLargeStraight(int scoreToInput){
        uiConfig.getCombinations()[11].setOnClickListener(v -> {
            if(scoreToInput>0 && uiConfig.getIsCombinationActive()[11] && !resetThrowCounter){
                totalScore +=scoreToInput;
                uiConfig.setTotalScore(totalScore);
                uiConfig.getCombinationsPoints()[11].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.getCombinations()[11].setEnabled(false);
                uiConfig.setIsCombinationActive(false, 11);
            }


        });
    }

    public void inputScoreFullHouse(int scoreToInput){
        uiConfig.getCombinations()[12].setOnClickListener(v -> {
            if(scoreToInput>0 && uiConfig.getIsCombinationActive()[12] && !resetThrowCounter){
                totalScore +=scoreToInput;
                uiConfig.setTotalScore(totalScore);
                uiConfig.getCombinationsPoints()[12].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.getCombinations()[12].setEnabled(false);
                uiConfig.setIsCombinationActive(false, 12);
            }


        });
    }

    public void inputScore4ofAKind(int scoreToInput){
        uiConfig.getCombinations()[13].setOnClickListener(v -> {
            if(scoreToInput>0 && uiConfig.getIsCombinationActive()[13] && !resetThrowCounter){
                totalScore +=scoreToInput;
                uiConfig.setTotalScore(totalScore);
                uiConfig.getCombinationsPoints()[13].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.getCombinations()[13].setEnabled(false);
                uiConfig.setIsCombinationActive(false, 13);
            }


        });
    }

    public void inputScore5ofAKind(int scoreToInput){
        uiConfig.getCombinations()[14].setOnClickListener(v -> {
            if(scoreToInput>0 && uiConfig.getIsCombinationActive()[14] && !resetThrowCounter){
                totalScore +=scoreToInput;
                uiConfig.setTotalScore(totalScore);
                uiConfig.getCombinationsPoints()[14].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.getCombinations()[14].setEnabled(false);
                uiConfig.setIsCombinationActive(false, 14);
            }


        });
    }

    public void inputScoreSos(int scoreToInput){
        uiConfig.getCombinations()[15].setOnClickListener(v -> {
            if(scoreToInput>0 && uiConfig.getIsCombinationActive()[15] && !resetThrowCounter){
                totalScore +=scoreToInput;
                uiConfig.setTotalScore(totalScore);
                uiConfig.getCombinationsPoints()[15].setText(scoreToInput + " pkt");
                resetThrowCounter = true;
                uiConfig.getCombinations()[15].setEnabled(false);
                uiConfig.setIsCombinationActive(false, 15);
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
