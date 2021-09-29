package com.paweldyjak.dicegame;

import androidx.core.content.ContextCompat;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.GameModes.GameMode;
import java.util.Random;
import java.util.concurrent.Executor;


public class GameBoardManager {
    private final ScoreInputSetter scoreInputSetter;
    private final UIConfig uiConfig;
    private final OpponentOnlineUIConfig opponentOnlineUIConfig;
    private final DicesCombinationsChecker dicesCombinationsChecker;
    private final RerollDices rerollDices;
    private final GameMode gameMode;
    private final int[] dices = new int[5];
    private boolean isFirstThrow = true;
    private int throwNumber = 0;
    private final Sounds sounds;
    private final GameBoardActivity gameBoardActivity;
    private final Random randomValue = new Random();


    public GameBoardManager(GameBoardActivity gameBoardActivity, ScoreInputSetter scoreInputSetter, DicesCombinationsChecker dicesCombinationsChecker, UIConfig uiConfig, OpponentOnlineUIConfig opponentOnlineUIConfig, RerollDices rerollDices, GameMode gameMode) {
        this.gameBoardActivity = gameBoardActivity;
        this.scoreInputSetter = scoreInputSetter;
        this.dicesCombinationsChecker = dicesCombinationsChecker;
        this.uiConfig = uiConfig;
        this.opponentOnlineUIConfig = opponentOnlineUIConfig;
        this.rerollDices = rerollDices;
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
                    rerollDices.setDicesRerolling(throwNumber);

                }

                if (throwNumber == 3) {
                    rerollDices.setDicesRerolling(throwNumber);
                    blockCombinations();
                    if(gameMode.getGameMode().equals("HotSeatMode")) {
                        scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkSOS(dices, throwNumber), 15);
                    } else{
                        scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkSOS(dices, throwNumber),15);
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
        if(gameMode.getGameMode().equals("MultiplayerMode")) {
            //upload to database dices values for opponent to display
            opponentOnlineUIConfig.updateDatabaseWithDicesValues(dices);
        }

    }


    // method sets combinations for checking
    public void setCombinations() {
        if(gameMode.getGameMode().equals("MultiplayerMode")) {
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
        Executor executor = ContextCompat.getMainExecutor(gameBoardActivity);
        for (int x = 0; x < 16; x++) {
            int combinationNr = x;
            if (dicesCombinationsChecker.combinationChecker(x, dices, isFirstThrow, 0) == 0 && gameMode.getIsCombinationActive()[x]) {
                {
                    uiConfig.getCombinationsText()[x].setOnClickListener(v -> {
                        v.setEnabled(false);
                        gameMode.setIsCombinationActive(false, combinationNr);
                        scoreInputSetter.setResetThrowCounter(true);
                        scoreInputSetter.resetCombinationsListeners();
                        gameMode.setCombinationsSlots(combinationNr, 2);
                        gameMode.prepareCombinationsSlots();
                        uiConfig.setDicesVisibility(false);
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
                    uiConfig.getCombinationsSlots()[x].setOnClickListener(v -> {
                        uiConfig.getCombinationsText()[finalX].setEnabled(false);
                        gameMode.setIsCombinationActive(false, combinationNr);
                        scoreInputSetter.setResetThrowCounter(true);
                        scoreInputSetter.resetCombinationsListeners();
                        gameMode.setCombinationsSlots(combinationNr, 2);
                        gameMode.prepareCombinationsSlots();
                        uiConfig.setDicesVisibility(false);
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

