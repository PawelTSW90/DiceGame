package com.paweldyjak.dicegame;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import java.util.Random;


public class Dices {
    private final Context context;
    private final ScoreInput scoreInput;
    private final UIConfig uiConfig;
    private final DicesScoreChecker dicesScoreChecker;
    private final int[] dices = new int[6];
    private boolean isFirstThrow = true;
    private int throwNumber = 0;


    Dices(Context context, ScoreInput scoreInput, DicesScoreChecker dicesScoreChecker, UIConfig uiConfig) {
        this.context = context;
        this.scoreInput = scoreInput;
        this.dicesScoreChecker = dicesScoreChecker;
        this.uiConfig = uiConfig;


    }

    //method configure roll dices button
    public void setRollDicesButton() {
        ImageView rollDicesButton = ((Activity) context).findViewById(R.id.roll_dices);
        rollDicesButton.setOnClickListener(v -> {

            if (scoreInput.getResetThrowCounter()) {
                throwNumber = 0;
                isFirstThrow = true;
                scoreInput.setResetThrowCounter(false);

            }
            if (throwNumber < 3) {
                if (throwNumber > 0) {
                    isFirstThrow = false;
                }
                rollDices();
                showDices();
                setCombinations();
                throwNumber++;

            }

            if (throwNumber == 3) {
                if (!dicesScoreChecker.checkForAvailableCombination(dices, isFirstThrow)) {
                    blockCombinations();

                }
            }

        });
    }

        //method generates dices for display
    public void rollDices() {
        Sounds sounds = new Sounds(context);
        sounds.playRollDiceSound();
        Random randomValue = new Random();
        for (int x = 0; x < 6; x++) {
            int value = randomValue.nextInt(6 - 1 + 1) + 1;
            dices[x] = value;
        }


    }

    //method shows dices
    public void showDices() {
        for (int x = 0; x < 6; x++) {
            uiConfig.getDicesSlots()[x].setImageResource(0);
        }

        int valueToDisplay;
        for (int x = 0; x < 6; x++) {
            valueToDisplay = dices[x];

            for (int y = 0; y < 6; y++) {
                if (uiConfig.getDicesSlots()[y].getDrawable() != null) {

                } else {
                    switch (valueToDisplay) {
                        case 1:
                            uiConfig.getDicesSlots()[y].setImageResource(R.drawable.dice1);
                            y = 5;
                            break;
                        case 2:
                            uiConfig.getDicesSlots()[y].setImageResource(R.drawable.dice2);
                            y = 5;
                            break;
                        case 3:
                            uiConfig.getDicesSlots()[y].setImageResource(R.drawable.dice3);
                            y = 5;
                            break;
                        case 4:
                            uiConfig.getDicesSlots()[y].setImageResource(R.drawable.dice4);
                            y = 5;
                            break;
                        case 5:
                            uiConfig.getDicesSlots()[y].setImageResource(R.drawable.dice5);
                            y = 5;
                            break;
                        case 6:
                            uiConfig.getDicesSlots()[y].setImageResource(R.drawable.dice6);
                            y = 5;
                            break;
                    }
                }
            }
        }

    }


    // method sets combinations to be checked
    public void setCombinations() {
        scoreInput.InputScoreOne(dicesScoreChecker.checkOne(dices, isFirstThrow));
        scoreInput.InputScoreTwo(dicesScoreChecker.checkTwo(dices, isFirstThrow));
        scoreInput.InputScoreThree(dicesScoreChecker.checkThree(dices, isFirstThrow));
        scoreInput.InputScoreFour(dicesScoreChecker.checkFour(dices, isFirstThrow));
        scoreInput.InputScoreFive(dicesScoreChecker.checkFive(dices, isFirstThrow));
        scoreInput.InputScoreSix(dicesScoreChecker.checkSix(dices, isFirstThrow));
    }
    // method blocks chose dices combination when none has been thrown in last throw
    public void blockCombinations() {
        uiConfig.getCombinations()[0].setOnClickListener(v -> {
            if (!dicesScoreChecker.checkForAvailableCombination(dices, isFirstThrow) && uiConfig.getIsCombinationActive()[0]) {
                v.setEnabled(false);
                uiConfig.setIsCombinationActive(false, 0);
                scoreInput.setResetThrowCounter(true);
                setCombinations();
            }
        });

        uiConfig.getCombinations()[1].setOnClickListener(v -> {
            if (!dicesScoreChecker.checkForAvailableCombination(dices, isFirstThrow) && uiConfig.getIsCombinationActive()[1]) {
                v.setEnabled(false);
                uiConfig.setIsCombinationActive(false, 1);
                scoreInput.setResetThrowCounter(true);
                setCombinations();
            }
        });

        uiConfig.getCombinations()[2].setOnClickListener(v -> {
            if (!dicesScoreChecker.checkForAvailableCombination(dices, isFirstThrow) && uiConfig.getIsCombinationActive()[2]) {
                v.setEnabled(false);
                uiConfig.setIsCombinationActive(false, 2);
                scoreInput.setResetThrowCounter(true);
                setCombinations();
            }
        });

        uiConfig.getCombinations()[3].setOnClickListener(v -> {
            if (!dicesScoreChecker.checkForAvailableCombination(dices, isFirstThrow) && uiConfig.getIsCombinationActive()[3]) {
                v.setEnabled(false);
                uiConfig.setIsCombinationActive(false, 3);
                scoreInput.setResetThrowCounter(true);
                setCombinations();
            }
        });

        uiConfig.getCombinations()[4].setOnClickListener(v -> {
            if (!dicesScoreChecker.checkForAvailableCombination(dices, isFirstThrow) && uiConfig.getIsCombinationActive()[4]) {
                v.setEnabled(false);
                uiConfig.setIsCombinationActive(false, 4);
                scoreInput.setResetThrowCounter(true);
                setCombinations();
            }
        });

        uiConfig.getCombinations()[5].setOnClickListener(v -> {
            if (!dicesScoreChecker.checkForAvailableCombination(dices, isFirstThrow) && uiConfig.getIsCombinationActive()[5]) {
                v.setEnabled(false);
                uiConfig.setIsCombinationActive(false, 5);
                scoreInput.setResetThrowCounter(true);
                setCombinations();
            }
        });

    }


}

