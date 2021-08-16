package com.paweldyjak.dicegame;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.Fragments.PlayerTurnScreen;

import java.util.Random;
import java.util.concurrent.Executor;


public class GameBoard {
    private final Context context;
    private final ScoreInput scoreInput;
    private final UIConfig uiConfig;
    private final DicesCombinationsChecker dicesCombinationsChecker;
    private final RerollDices rerollDices;
    private final int[] dices = new int[5];
    private boolean isFirstThrow = true;
    private int throwNumber = 0;
    private final Sounds sounds;
    private final GameBoardActivity gameBoardActivity;


    public GameBoard(GameBoardActivity gameBoardActivity, Context context, ScoreInput scoreInput, DicesCombinationsChecker dicesCombinationsChecker, UIConfig uiConfig, RerollDices rerollDices) {
        this.context = context;
        this.gameBoardActivity = gameBoardActivity;
        this.scoreInput = scoreInput;
        this.dicesCombinationsChecker = dicesCombinationsChecker;
        this.uiConfig = uiConfig;
        this.rerollDices = rerollDices;
        sounds = new Sounds(context);
        PlayerTurnScreen playerTurnScreen = new PlayerTurnScreen(gameBoardActivity, uiConfig);
        gameBoardActivity.replaceFragment(R.id.fragment_layout, playerTurnScreen);
        gameBoardActivity.setPlayerTurnScreen(playerTurnScreen);
        gameBoardActivity.showNewTurnScreen(false);

    }


    //method configure roll dices button
    public void setRollDicesButton() {
        gameBoardActivity.showNewTurnScreen(false);
        ImageView rollDicesButton = ((Activity) context).findViewById(R.id.roll_dices);
        rollDicesButton.setOnClickListener(v -> {

            if (!uiConfig.checkIfAllCombinationsAreDone()) {

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
                    scoreInput.inputScore(dicesCombinationsChecker.checkSOS(dices, throwNumber), 15);
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


    // method sets combinations for checking
    public void setCombinations() {
        scoreInput.inputScore(dicesCombinationsChecker.checkOne(dices, isFirstThrow), 0);
        scoreInput.inputScore(dicesCombinationsChecker.checkTwo(dices, isFirstThrow), 1);
        scoreInput.inputScore(dicesCombinationsChecker.checkThree(dices, isFirstThrow), 2);
        scoreInput.inputScore(dicesCombinationsChecker.checkFour(dices, isFirstThrow), 3);
        scoreInput.inputScore(dicesCombinationsChecker.checkFive(dices, isFirstThrow), 4);
        scoreInput.inputScore(dicesCombinationsChecker.checkSix(dices, isFirstThrow), 5);
        scoreInput.inputScore(dicesCombinationsChecker.checkPair(dices, isFirstThrow), 6);
        scoreInput.inputScore(dicesCombinationsChecker.checkTwoPairs(dices, isFirstThrow), 7);
        scoreInput.inputScore(dicesCombinationsChecker.checkEvens(dices, isFirstThrow), 8);
        scoreInput.inputScore(dicesCombinationsChecker.checkOdds(dices, isFirstThrow), 9);
        scoreInput.inputScore(dicesCombinationsChecker.checkSmallStraight(dices, isFirstThrow), 10);
        scoreInput.inputScore(dicesCombinationsChecker.checkLargeStraight(dices, isFirstThrow), 11);
        scoreInput.inputScore(dicesCombinationsChecker.checkFullHouse(dices, isFirstThrow), 12);
        scoreInput.inputScore(dicesCombinationsChecker.checkFourOfAKind(dices, isFirstThrow), 13);
        scoreInput.inputScore(dicesCombinationsChecker.checkFiveOfAKind(dices, isFirstThrow), 14);
        scoreInput.inputScore(dicesCombinationsChecker.checkSOS(dices, throwNumber), 15);
    }

    // method allows to block one of a combinations after last throw
    public void blockCombinations() {
        Executor executor = ContextCompat.getMainExecutor(gameBoardActivity);
        for (int x = 0; x < 16; x++) {
            int combinationNr = x;
            if (dicesCombinationsChecker.combinationChecker(x, dices, isFirstThrow, 0) == 0 && uiConfig.getIsCombinationActive()[x]) {
                {
                    uiConfig.getCombinationsTextView()[x].setOnClickListener(v -> {
                        v.setEnabled(false);
                        uiConfig.setIsCombinationActive(false, combinationNr);
                        scoreInput.setResetThrowCounter(true);
                        scoreInput.resetCombinationsListeners();
                        uiConfig.setCombinationsSlots(combinationNr, 2);
                        uiConfig.prepareCombinationsSlots();
                        uiConfig.hideDices();
                        if (uiConfig.checkIfAllCombinationsAreDone() && uiConfig.getCurrentPlayerNumber() == uiConfig.getNumberOfPlayers()) {
                            executor.execute(() -> {
                                try {
                                    sounds.playCrossOutCombinationSound();
                                    Thread.sleep(2000);
                                    uiConfig.setFinalResultScreen();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            });

                        } else {
                            executor.execute(() -> {
                                try {
                                    sounds.playCrossOutCombinationSound();
                                    Thread.sleep(2000);
                                    gameBoardActivity.showNewTurnScreen(true);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            });

                        }

                    });

                }
            }
        }
    }
}

