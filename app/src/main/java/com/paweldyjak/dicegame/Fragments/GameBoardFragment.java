package com.paweldyjak.dicegame.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import com.paweldyjak.dicegame.*;
import java.util.Random;


public class GameBoardFragment extends Fragment {
    MainActivity mainActivity;
    private final ScoreInput scoreInput;
    private final UIConfig uiConfig;
    private final DicesCombinationsChecker dicesCombinationsChecker;
    private final RerollDices rerollDices;
    private final int[] dices = new int[5];
    private boolean isFirstThrow = true;
    private int throwNumber = 0;
    private final Sounds sounds;
    View view;
    ImageView rollDicesButton;


    public GameBoardFragment(MainActivity mainActivity, ScoreInput scoreInput, DicesCombinationsChecker dicesCombinationsChecker, UIConfig uiConfig, RerollDices rerollDices) {
        this.mainActivity = mainActivity;
        this.scoreInput = scoreInput;
        this.dicesCombinationsChecker = dicesCombinationsChecker;
        this.uiConfig = uiConfig;
        this.rerollDices = rerollDices;
        sounds = new Sounds(mainActivity);

    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.game_board_screen_fragment, container, false);
        rollDicesButton = view.findViewById(R.id.roll_dices);
        setRollDicesButton();
        uiConfig.setDicesSlots(view);
        uiConfig.setDicesCombinations(view);
        uiConfig.setAllCombinationsAsActive();
        mainActivity.destroyFragment(mainActivity.playerTurnScreenFragment);
        return view;
    }

    //method configure roll dices button
    public void setRollDicesButton() {
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


    // method sets combinations to be checked
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
        for (int x = 0; x < 16; x++) {
            int combinationNr = x;
            if (dicesCombinationsChecker.combinationChecker(x, dices, isFirstThrow, 0) == 0 && uiConfig.getIsCombinationActive()[x]) {
                {
                    uiConfig.getCombinationsTextView()[x].setOnClickListener(v -> {
                        v.setEnabled(false);
                        uiConfig.setIsCombinationActive(false, combinationNr);
                        scoreInput.setResetThrowCounter(true);
                        scoreInput.resetCombinationsListeners();
                        uiConfig.hideDices();
                        if (uiConfig.checkIfAllCombinationsAreDone() && uiConfig.getPlayerNumber() == 2) {
                            uiConfig.setFinalResultScreen();
                        } else {
                            uiConfig.setPlayerTurnWindow();
                        }

                    });

                }
            }
        }
    }
}

