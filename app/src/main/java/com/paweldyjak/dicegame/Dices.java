package com.paweldyjak.dicegame;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import java.util.Random;


public class Dices {
    private final Context context;
    private final ScoreInput scoreInput;
    private final UIConfig uiConfig;
    private final DicesScoreChecker dicesScoreChecker;
    private final int[] dices = new int[5];
    private boolean isFirstThrow = true;
    private int throwNumber = 0;
    private final Sounds sounds;


    Dices(Context context, ScoreInput scoreInput, DicesScoreChecker dicesScoreChecker, UIConfig uiConfig) {
        this.context = context;
        this.scoreInput = scoreInput;
        this.dicesScoreChecker = dicesScoreChecker;
        this.uiConfig = uiConfig;
        sounds = new Sounds(context);


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
                blockCombinations();
                scoreInput.inputScoreSos(dicesScoreChecker.checkSOS(dices, throwNumber));
            }
        });
    }

    //method generates dices for display
    public void rollDices() {
        sounds.playRollDiceSound();
        Random randomValue = new Random();
        for (int x = 0; x < 5; x++) {
            int value = randomValue.nextInt(6 - 1 + 1) + 1;
            dices[x] = value;
        }


    }

    //method shows dices
    public void showDices() {
        for (int x = 0; x < 5; x++) {
            uiConfig.getDicesSlots()[x].setImageResource(0);
        }

        int valueToDisplay;
        for (int x = 0; x < 5; x++) {
            valueToDisplay = dices[x];

            for (int y = 0; y < 5; y++) {
                if (uiConfig.getDicesSlots()[y].getDrawable() == null) {


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
        scoreInput.inputScoreOne(dicesScoreChecker.checkOne(dices, isFirstThrow));
        scoreInput.inputScoreTwo(dicesScoreChecker.checkTwo(dices, isFirstThrow));
        scoreInput.inputScoreThree(dicesScoreChecker.checkThree(dices, isFirstThrow));
        scoreInput.inputScoreFour(dicesScoreChecker.checkFour(dices, isFirstThrow));
        scoreInput.inputScoreFive(dicesScoreChecker.checkFive(dices, isFirstThrow));
        scoreInput.inputScoreSix(dicesScoreChecker.checkSix(dices, isFirstThrow));
        scoreInput.inputScorePair(dicesScoreChecker.checkPair(dices, isFirstThrow));
        scoreInput.inputScoreTwoPairs(dicesScoreChecker.checkTwoPairs(dices, isFirstThrow));
        scoreInput.inputScoreEvens(dicesScoreChecker.checkEvens(dices, isFirstThrow));
        scoreInput.inputScoreOdds(dicesScoreChecker.checkOdds(dices, isFirstThrow));
        scoreInput.inputScoreSmallStraight(dicesScoreChecker.checkSmallStraight(dices, isFirstThrow));
        scoreInput.inputScoreLargeStraight(dicesScoreChecker.checkLargeStraight(dices, isFirstThrow));
        scoreInput.inputScoreFullHouse(dicesScoreChecker.checkFullHouse(dices, isFirstThrow));
        scoreInput.inputScore4ofAKind(dicesScoreChecker.check4OfAKind(dices, isFirstThrow));
        scoreInput.inputScore5ofAKind(dicesScoreChecker.check4OfAKind(dices, isFirstThrow));
        scoreInput.inputScoreSos(dicesScoreChecker.checkSOS(dices, throwNumber));

    }

    // method allows to block one of a combinations after last throw
    public void blockCombinations() {
        if (dicesScoreChecker.checkOne(dices, isFirstThrow) == 0 && uiConfig.getIsCombinationActive()[0]) {
            uiConfig.getCombinations()[0].setOnClickListener(v -> {
                v.setEnabled(false);
                uiConfig.setIsCombinationActive(false, 0);
                scoreInput.setResetThrowCounter(true);
                setCombinations();

            });
        }
        if (dicesScoreChecker.checkTwo(dices, isFirstThrow) == 0 && uiConfig.getIsCombinationActive()[1]) {
            uiConfig.getCombinations()[1].setOnClickListener(v -> {
                v.setEnabled(false);
                uiConfig.setIsCombinationActive(false, 1);
                scoreInput.setResetThrowCounter(true);
                setCombinations();

            });
        }
        if (dicesScoreChecker.checkThree(dices, isFirstThrow) == 0 && uiConfig.getIsCombinationActive()[2]) {
            uiConfig.getCombinations()[2].setOnClickListener(v -> {
                v.setEnabled(false);
                uiConfig.setIsCombinationActive(false, 2);
                scoreInput.setResetThrowCounter(true);
                setCombinations();

            });
        }
        if (dicesScoreChecker.checkFour(dices, isFirstThrow) == 0 && uiConfig.getIsCombinationActive()[3]) {
            uiConfig.getCombinations()[3].setOnClickListener(v -> {
                v.setEnabled(false);
                uiConfig.setIsCombinationActive(false, 3);
                scoreInput.setResetThrowCounter(true);
                setCombinations();

            });
        }
        if (dicesScoreChecker.checkFive(dices, isFirstThrow) == 0 && uiConfig.getIsCombinationActive()[4]) {
            uiConfig.getCombinations()[4].setOnClickListener(v -> {
                v.setEnabled(false);
                uiConfig.setIsCombinationActive(false, 4);
                scoreInput.setResetThrowCounter(true);
                setCombinations();

            });
        }
        if (dicesScoreChecker.checkSix(dices, isFirstThrow) == 0 && uiConfig.getIsCombinationActive()[5]) {
            uiConfig.getCombinations()[5].setOnClickListener(v -> {
                v.setEnabled(false);
                uiConfig.setIsCombinationActive(false, 5);
                scoreInput.setResetThrowCounter(true);
                setCombinations();

            });
        }
        if (dicesScoreChecker.checkPair(dices, isFirstThrow) == 0 && uiConfig.getIsCombinationActive()[6]) {
            uiConfig.getCombinations()[6].setOnClickListener(v -> {
                v.setEnabled(false);
                uiConfig.setIsCombinationActive(false, 6);
                scoreInput.setResetThrowCounter(true);
                setCombinations();

            });
        }
        if (dicesScoreChecker.checkTwoPairs(dices, isFirstThrow) == 0 && uiConfig.getIsCombinationActive()[7]) {
            uiConfig.getCombinations()[7].setOnClickListener(v -> {
                v.setEnabled(false);
                uiConfig.setIsCombinationActive(false, 7);
                scoreInput.setResetThrowCounter(true);
                setCombinations();

            });
        }
        if (dicesScoreChecker.checkEvens(dices, isFirstThrow) == 0 && uiConfig.getIsCombinationActive()[8]) {
            uiConfig.getCombinations()[8].setOnClickListener(v -> {
                v.setEnabled(false);
                uiConfig.setIsCombinationActive(false, 8);
                scoreInput.setResetThrowCounter(true);
                setCombinations();

            });
        }
        if (dicesScoreChecker.checkOdds(dices, isFirstThrow) == 0 && uiConfig.getIsCombinationActive()[9]) {
            uiConfig.getCombinations()[9].setOnClickListener(v -> {
                v.setEnabled(false);
                uiConfig.setIsCombinationActive(false, 9);
                scoreInput.setResetThrowCounter(true);
                setCombinations();

            });
        }

        if (dicesScoreChecker.checkSmallStraight(dices, isFirstThrow) == 0 && uiConfig.getIsCombinationActive()[10]) {
            uiConfig.getCombinations()[10].setOnClickListener(v -> {
                v.setEnabled(false);
                uiConfig.setIsCombinationActive(false, 10);
                scoreInput.setResetThrowCounter(true);
                setCombinations();

            });
        }

        if (dicesScoreChecker.checkLargeStraight(dices, isFirstThrow) == 0 && uiConfig.getIsCombinationActive()[11]) {
            uiConfig.getCombinations()[11].setOnClickListener(v -> {
                v.setEnabled(false);
                uiConfig.setIsCombinationActive(false, 11);
                scoreInput.setResetThrowCounter(true);
                setCombinations();

            });
        }

        if (dicesScoreChecker.checkFullHouse(dices, isFirstThrow) == 0 && uiConfig.getIsCombinationActive()[12]) {
            uiConfig.getCombinations()[12].setOnClickListener(v -> {
                v.setEnabled(false);
                uiConfig.setIsCombinationActive(false, 12);
                scoreInput.setResetThrowCounter(true);
                setCombinations();

            });
        }

        if (dicesScoreChecker.check4OfAKind(dices, isFirstThrow) == 0 && uiConfig.getIsCombinationActive()[13]) {
            uiConfig.getCombinations()[13].setOnClickListener(v -> {
                v.setEnabled(false);
                uiConfig.setIsCombinationActive(false, 13);
                scoreInput.setResetThrowCounter(true);
                setCombinations();

            });
        }

        if (dicesScoreChecker.check5ofAKind(dices, isFirstThrow) == 0 && uiConfig.getIsCombinationActive()[14]) {
            uiConfig.getCombinations()[14].setOnClickListener(v -> {
                v.setEnabled(false);
                uiConfig.setIsCombinationActive(false, 14);
                scoreInput.setResetThrowCounter(true);
                setCombinations();

            });
        }

    }


}

