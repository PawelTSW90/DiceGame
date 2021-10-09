package com.paweldyjak.dicegame;

import android.widget.ImageView;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.GameModes.GameMode;
import com.paweldyjak.dicegame.GameModes.HotSeatGame;
import com.paweldyjak.dicegame.GameModes.MultiplayerGame;
import java.util.Random;

public class GameBoardManager {
    private final ScoreInputSetter scoreInputSetter;
    private final UIConfig uiConfig;
    private final OpponentUIConfig opponentUIConfig;
    private final DicesCombinationsChecker dicesCombinationsChecker;
    private final GameMode gameMode;
    private final int[] dices = new int[5];
    private boolean isFirstThrow = true;
    private int throwNumber = 0;
    private final Sounds sounds;
    private final GameBoardActivity gameBoardActivity;
    private final Random randomValue = new Random();


    public GameBoardManager(GameBoardActivity gameBoardActivity, ScoreInputSetter scoreInputSetter, DicesCombinationsChecker dicesCombinationsChecker, UIConfig uiConfig, OpponentUIConfig opponentUIConfig, GameMode gameMode) {
        this.gameBoardActivity = gameBoardActivity;
        this.scoreInputSetter = scoreInputSetter;
        this.dicesCombinationsChecker = dicesCombinationsChecker;
        this.uiConfig = uiConfig;
        this.opponentUIConfig = opponentUIConfig;
        this.gameMode = gameMode;
        sounds = new Sounds(gameBoardActivity);

    }

    //method configure roll dices button
    public void setRollDicesButton() {
        gameBoardActivity.hideFragment();
        uiConfig.getRollDicesButton().setOnClickListener(v -> {

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
                    uiConfig.showDices(dices);
                    setCombinations();
                    throwNumber++;
                    setDicesRerolling(throwNumber);

                }

                if (throwNumber == 3) {
                    setDicesRerolling(throwNumber);
                    blockCombinations();
                    if (gameMode instanceof HotSeatGame) {
                        scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkSOS(dices, throwNumber), 15);
                    } else {
                        scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkSOS(dices, throwNumber), 15);
                    }
                }
            }

        });

    }


    //method generates dices for display
    public void rollDices() {
        sounds.playRollDiceSound();
        boolean rerollAllDices = true;

        for (int x = 0; x < 5; x++) {
            int value = randomValue.nextInt(6 - 1 + 1) + 1;
            if (throwNumber >= 1) {
                if (getSelectedDices()[x]) {
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
        if (gameMode instanceof MultiplayerGame) {
            //upload to database dices values for opponent to display
            opponentUIConfig.updateDatabaseWithDicesValues(dices);
        }

    }

    public void setDicesRerolling(int throwNumber) {

        for (int x = 0; x < 5; x++) {
            uiConfig.clearDicesBorder();
            uiConfig.getDicesSlots()[x].setOnClickListener(v -> {
                if (throwNumber != 3) {
                    if (v.getBackground() != null) {
                        v.setBackground(null);
                    } else {
                        uiConfig.setDicesBorder(((ImageView) v), true);
                    }
                }


            });
        }

    }

    public boolean[] getSelectedDices() {
        boolean[] selectedDices = new boolean[5];
        for (int x = 0; x < 5; x++) {
            if (uiConfig.getDicesSlots()[x].getBackground() != null) {
                selectedDices[x] = true;

            }
        }

        return selectedDices;
    }


    // method sets combinations for checking
    public void setCombinations() {
        if (gameMode instanceof MultiplayerGame) {
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkOne(dices, isFirstThrow), 0);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkTwo(dices, isFirstThrow), 1);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkThree(dices, isFirstThrow), 2);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkFour(dices, isFirstThrow), 3);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkFive(dices, isFirstThrow), 4);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkSix(dices, isFirstThrow), 5);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkPair(dices, isFirstThrow), 6);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkTwoPairs(dices, isFirstThrow), 7);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkEvens(dices, isFirstThrow), 8);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkOdds(dices, isFirstThrow), 9);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkSmallStraight(dices, isFirstThrow), 10);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkLargeStraight(dices, isFirstThrow), 11);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkFullHouse(dices, isFirstThrow), 12);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkFourOfAKind(dices, isFirstThrow), 13);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkFiveOfAKind(dices, isFirstThrow), 14);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkSOS(dices, throwNumber), 15);

        } else {
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkOne(dices, isFirstThrow), 0);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkTwo(dices, isFirstThrow), 1);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkThree(dices, isFirstThrow), 2);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkFour(dices, isFirstThrow), 3);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkFive(dices, isFirstThrow), 4);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkSix(dices, isFirstThrow), 5);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkPair(dices, isFirstThrow), 6);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkTwoPairs(dices, isFirstThrow), 7);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkEvens(dices, isFirstThrow), 8);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkOdds(dices, isFirstThrow), 9);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkSmallStraight(dices, isFirstThrow), 10);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkLargeStraight(dices, isFirstThrow), 11);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkFullHouse(dices, isFirstThrow), 12);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkFourOfAKind(dices, isFirstThrow), 13);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkFiveOfAKind(dices, isFirstThrow), 14);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkSOS(dices, throwNumber), 15);
        }
    }

    // method allows to block one of a combinations after last throw
    public void blockCombinations() {
        for (int x = 0; x < 16; x++) {
            if (dicesCombinationsChecker.combinationChecker(x, dices, isFirstThrow, 0) == 0 && gameMode.getIsCombinationActive()[x]) {
                {
                    uiConfig.getCombinationsText()[x].setOnClickListener(new BlockCombinationListener(gameBoardActivity, gameMode, scoreInputSetter, uiConfig, sounds, x));
                    uiConfig.getCombinationsSlots()[x].setOnClickListener(new BlockCombinationListener(gameBoardActivity, gameMode, scoreInputSetter, uiConfig, sounds, x));

                }
            }
        }
    }

}

