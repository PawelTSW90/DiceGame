package com.paweldyjak.dicegame;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;


public class Dices {
    private final Context context;
    private final ScoreInput scoreInput;
    private final UIConfig uiConfig;
    private final DicesScoreChecker dicesScoreChecker;
    private final RerollDices rerollDices;
    private final int[] dices = new int[5];
    private boolean isFirstThrow = true;
    private int throwNumber = 0;
    private final Sounds sounds;


    Dices(Context context, ScoreInput scoreInput, DicesScoreChecker dicesScoreChecker, UIConfig uiConfig, RerollDices rerollDices) {
        this.context = context;
        this.scoreInput = scoreInput;
        this.dicesScoreChecker = dicesScoreChecker;
        this.uiConfig = uiConfig;
        this.rerollDices = rerollDices;
        sounds = new Sounds(context);

    }

    //method configure roll dices button
    public void setRollDicesButton() {
        ImageView rollDicesButton = ((Activity) context).findViewById(R.id.roll_dices);
        rollDicesButton.setOnClickListener(v -> {

            if (uiConfig.checkIfAllCombinationsAreDone()) {
            } else {
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
                    rerollDices.setDicesRerolling(throwNumber);

                }

                if (throwNumber == 3) {
                    rerollDices.setDicesRerolling(throwNumber);
                    blockCombinations();
                    scoreInput.inputScore(dicesScoreChecker.checkSOS(dices, throwNumber), 15);
                }
            }

        });

    }

    //method generates dices for display
    public void rollDices() {


        sounds.playRollDiceSound();
        boolean rerollAllDices = true;
        Random randomValue = new Random();
        for (int x = 0; x < 5; x++) {
            int value = randomValue.nextInt(6 - 1 + 1) + 1;
            if (throwNumber >= 1) {
                if (rerollDices.getSelectedDices()[x]) {
                    dices[x] = value;
                    rerollAllDices = false;
                }

            } else {
                dices[x] = value;
            }
        }
        if (throwNumber >= 1 && rerollAllDices) {
            for (int x = 0; x < 5; x++) {
                int value = randomValue.nextInt(6 - 1 + 1) + 1;
                dices[x] = value;
            }
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
        scoreInput.inputScore(dicesScoreChecker.checkOne(dices, isFirstThrow), 0);
        scoreInput.inputScore(dicesScoreChecker.checkTwo(dices, isFirstThrow), 1);
        scoreInput.inputScore(dicesScoreChecker.checkThree(dices, isFirstThrow), 2);
        scoreInput.inputScore(dicesScoreChecker.checkFour(dices, isFirstThrow), 3);
        scoreInput.inputScore(dicesScoreChecker.checkFive(dices, isFirstThrow), 4);
        scoreInput.inputScore(dicesScoreChecker.checkSix(dices, isFirstThrow), 5);
        scoreInput.inputScore(dicesScoreChecker.checkPair(dices, isFirstThrow), 6);
        scoreInput.inputScore(dicesScoreChecker.checkTwoPairs(dices, isFirstThrow), 7);
        scoreInput.inputScore(dicesScoreChecker.checkEvens(dices, isFirstThrow), 8);
        scoreInput.inputScore(dicesScoreChecker.checkOdds(dices, isFirstThrow), 9);
        scoreInput.inputScore(dicesScoreChecker.checkSmallStraight(dices, isFirstThrow), 10);
        scoreInput.inputScore(dicesScoreChecker.checkLargeStraight(dices, isFirstThrow), 11);
        scoreInput.inputScore(dicesScoreChecker.checkFullHouse(dices, isFirstThrow), 12);
        scoreInput.inputScore(dicesScoreChecker.checkFourOfAKind(dices, isFirstThrow), 13);
        scoreInput.inputScore(dicesScoreChecker.checkFiveOfAKind(dices, isFirstThrow), 14);
        scoreInput.inputScore(dicesScoreChecker.checkSOS(dices, throwNumber), 15);
    }

    // method allows to block one of a combinations after last throw
    public void blockCombinations() {
        for (int x = 0; x < 15; x++) {
            int combinationNr = x;
            if (dicesScoreChecker.callCheckCombinationMethod(x, dices, isFirstThrow, 0) == 0 && uiConfig.getIsCombinationActive()[x]) {
                {
                    uiConfig.getCombinationsTextView()[x].setOnClickListener(v -> {
                        v.setEnabled(false);
                        uiConfig.setIsCombinationActive(false, combinationNr);
                        scoreInput.setResetThrowCounter(true);
                        scoreInput.resetCombinationsListeners();
                        uiConfig.hideDices();
                        uiConfig.setPlayerTurnWindow();

                    });

                }
            }
        }
    }
}

