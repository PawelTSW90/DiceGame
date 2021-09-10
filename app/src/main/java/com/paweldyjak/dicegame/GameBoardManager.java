package com.paweldyjak.dicegame;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.Fragments.PlayerTurnScreenFragment;
import com.paweldyjak.dicegame.GameModes.GameMode;
import java.util.Random;
import java.util.concurrent.Executor;


public class GameBoardManager {
    private final Context context;
    private final ScoreInputSetter scoreInputSetter;
    private final UIConfig uiConfig;
    private final DicesCombinationsChecker dicesCombinationsChecker;
    private final RerollDices rerollDices;
    private final GameMode gameMode;
    private final int[] dices = new int[5];
    private boolean isFirstThrow = true;
    private int throwNumber = 0;
    private final Sounds sounds;
    private final GameBoardActivity gameBoardActivity;


    public GameBoardManager(GameBoardActivity gameBoardActivity, Context context, ScoreInputSetter scoreInputSetter, DicesCombinationsChecker dicesCombinationsChecker, UIConfig uiConfig, RerollDices rerollDices, GameMode gameMode) {
        this.context = context;
        this.gameBoardActivity = gameBoardActivity;
        this.scoreInputSetter = scoreInputSetter;
        this.dicesCombinationsChecker = dicesCombinationsChecker;
        this.uiConfig = uiConfig;
        this.rerollDices = rerollDices;
        this.gameMode = gameMode;
        sounds = new Sounds(context);
        PlayerTurnScreenFragment playerTurnScreenFragment = new PlayerTurnScreenFragment(gameBoardActivity, uiConfig, gameMode);
        gameBoardActivity.replaceFragment(R.id.fragment_layout, playerTurnScreenFragment);
        gameBoardActivity.setPlayerTurnScreen(playerTurnScreenFragment);
        gameBoardActivity.hideFragment();

    }


    //method configure roll dices button
    public void setRollDicesButton() {
        gameBoardActivity.hideFragment();
        ImageView rollDicesButton = ((Activity) context).findViewById(R.id.roll_dices);
        rollDicesButton.setOnClickListener(v -> {

            if (!gameMode.checkIfAllCombinationsAreDone()) {

                if (scoreInputSetter.getResetThrowCounter()) {
                    throwNumber = 0;
                    isFirstThrow = true;
                    scoreInputSetter.setResetThrowCounter(false);

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
                    scoreInputSetter.setScoreInputForViews(dicesCombinationsChecker.checkSOS(dices, throwNumber), 15);
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
        scoreInputSetter.setScoreInputForViews(dicesCombinationsChecker.checkOne(dices, isFirstThrow), 0);
        scoreInputSetter.setScoreInputForViews(dicesCombinationsChecker.checkTwo(dices, isFirstThrow), 1);
        scoreInputSetter.setScoreInputForViews(dicesCombinationsChecker.checkThree(dices, isFirstThrow), 2);
        scoreInputSetter.setScoreInputForViews(dicesCombinationsChecker.checkFour(dices, isFirstThrow), 3);
        scoreInputSetter.setScoreInputForViews(dicesCombinationsChecker.checkFive(dices, isFirstThrow), 4);
        scoreInputSetter.setScoreInputForViews(dicesCombinationsChecker.checkSix(dices, isFirstThrow), 5);
        scoreInputSetter.setScoreInputForViews(dicesCombinationsChecker.checkPair(dices, isFirstThrow), 6);
        scoreInputSetter.setScoreInputForViews(dicesCombinationsChecker.checkTwoPairs(dices, isFirstThrow), 7);
        scoreInputSetter.setScoreInputForViews(dicesCombinationsChecker.checkEvens(dices, isFirstThrow), 8);
        scoreInputSetter.setScoreInputForViews(dicesCombinationsChecker.checkOdds(dices, isFirstThrow), 9);
        scoreInputSetter.setScoreInputForViews(dicesCombinationsChecker.checkSmallStraight(dices, isFirstThrow), 10);
        scoreInputSetter.setScoreInputForViews(dicesCombinationsChecker.checkLargeStraight(dices, isFirstThrow), 11);
        scoreInputSetter.setScoreInputForViews(dicesCombinationsChecker.checkFullHouse(dices, isFirstThrow), 12);
        scoreInputSetter.setScoreInputForViews(dicesCombinationsChecker.checkFourOfAKind(dices, isFirstThrow), 13);
        scoreInputSetter.setScoreInputForViews(dicesCombinationsChecker.checkFiveOfAKind(dices, isFirstThrow), 14);
        scoreInputSetter.setScoreInputForViews(dicesCombinationsChecker.checkSOS(dices, throwNumber), 15);
    }

    // method allows to block one of a combinations after last throw
    public void blockCombinations() {
        Executor executor = ContextCompat.getMainExecutor(gameBoardActivity);
        for (int x = 0; x < 16; x++) {
            int combinationNr = x;
            if (dicesCombinationsChecker.combinationChecker(x, dices, isFirstThrow, 0) == 0 && gameMode.getIsCombinationActive()[x]) {
                {
                    uiConfig.getCombinationsTextView()[x].setOnClickListener(v -> {
                        v.setEnabled(false);
                        gameMode.setIsCombinationActive(false, combinationNr);
                        scoreInputSetter.setResetThrowCounter(true);
                        scoreInputSetter.resetCombinationsListeners();
                        gameMode.setCombinationsSlots(combinationNr, 2);
                        gameMode.prepareCombinationsSlots();
                        uiConfig.hideDices();
                        if (gameMode.checkIfAllCombinationsAreDone() && gameMode.getCurrentPlayerNumber() == gameMode.getNumberOfPlayers()) {
                            executor.execute(() -> {
                                try {
                                    sounds.playEraseCombinationSound();
                                    Thread.sleep(2000);
                                    gameMode.setFinalResultScreen();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            });

                        } else {
                            executor.execute(() -> {
                                try {
                                    sounds.playEraseCombinationSound();
                                    Thread.sleep(2000);
                                    gameBoardActivity.showNextTurnFragment();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            });

                        }

                    });

                    int finalX = x;
                    uiConfig.getCombinationsSlots()[x].setOnClickListener(v->{
                        uiConfig.getCombinationsTextView()[finalX].setEnabled(false);
                        gameMode.setIsCombinationActive(false, combinationNr);
                        scoreInputSetter.setResetThrowCounter(true);
                        scoreInputSetter.resetCombinationsListeners();
                        gameMode.setCombinationsSlots(combinationNr, 2);
                        gameMode.prepareCombinationsSlots();
                        uiConfig.hideDices();
                        if (gameMode.checkIfAllCombinationsAreDone() && gameMode.getCurrentPlayerNumber() == gameMode.getNumberOfPlayers()) {
                            executor.execute(() -> {
                                try {
                                    sounds.playEraseCombinationSound();
                                    Thread.sleep(2000);
                                    gameMode.setFinalResultScreen();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            });

                        } else {
                            executor.execute(() -> {
                                try {
                                    sounds.playEraseCombinationSound();
                                    Thread.sleep(2000);
                                    gameBoardActivity.showNextTurnFragment();
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

