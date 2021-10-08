package com.paweldyjak.dicegame;

import android.view.View;

import androidx.core.content.ContextCompat;

import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.GameModes.GameMode;
import com.paweldyjak.dicegame.GameModes.HotSeatGame;
import com.paweldyjak.dicegame.GameModes.MultiplayerGame;

import java.util.concurrent.Executor;

public class ScoreInputListener implements View.OnClickListener {

    private final GameBoardActivity gameBoardActivity;
    private final GameMode gameMode;
    private final ScoreInputSetter scoreInputSetter;
    private final UIConfig uiConfig;
    private final boolean resetThrowCounter;
    private final int scoreToInput;
    private final int combinationNumber;
    private final boolean isCombinationActive;

    public ScoreInputListener(GameBoardActivity gameBoardActivity, GameMode gameMode, ScoreInputSetter scoreInputSetter, UIConfig uiConfig, boolean resetThrowCounter, int scoreToInput, int combinationNumber, boolean isCombinationActive) {
        this.gameBoardActivity = gameBoardActivity;
        this.gameMode = gameMode;
        this.scoreInputSetter = scoreInputSetter;
        this.uiConfig = uiConfig;
        this.resetThrowCounter = resetThrowCounter;
        this.scoreToInput = scoreToInput;
        this.combinationNumber = combinationNumber;
        this.isCombinationActive = isCombinationActive;
    }

    @Override
    public void onClick(View v) {
        Executor executor = ContextCompat.getMainExecutor(gameBoardActivity);
        if (scoreToInput > 0 && isCombinationActive && !resetThrowCounter) {
            updateBoardValues(scoreToInput, combinationNumber);
            gameMode.updateGameBoard();
            prepareBoardForNextPlayer();
            if (gameMode instanceof MultiplayerGame) {

                ((MultiplayerGame) gameMode).updateOpponentTurnDatabase();
            }
            if (gameMode.getCurrentPlayerNumber() == gameMode.getNumberOfPlayers() && gameMode.checkIfAllCombinationsAreDone()) {
                executor.execute(() -> {
                    try {
                        Thread.sleep(2000);
                        gameMode.setFinalResultScreen();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                });
            } else {
                executor.execute(() -> {
                    try {
                        Thread.sleep(2000);
                        scoreInputSetter.setResetThrowCounter(true);
                        resetCombinationsListeners();
                        if (gameMode instanceof MultiplayerGame) {
                            ((MultiplayerGame) gameMode).changeCurrentPlayer();
                        }
                        gameBoardActivity.showNextTurnFragment();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                });
            }

        }
    }

    public void updateBoardValues(int scoreToInput, int combinationNumber) {
        Sounds sounds = new Sounds(gameBoardActivity);
        sounds.playCompleteCombinationSound();
        if (gameMode instanceof MultiplayerGame) {
            ((MultiplayerGame) gameMode).setCombinationsSlotsInDatabase(combinationNumber, 1);
            ((MultiplayerGame) gameMode).setCombinationsPointsInDatabase(scoreToInput, combinationNumber);
            ((MultiplayerGame) gameMode).setTotalScoreInDatabase(scoreToInput);
            ((MultiplayerGame) gameMode).setIsCombinationActiveInDatabase(false, combinationNumber);

        }
        gameMode.setCombinationsPointsValues(scoreToInput, combinationNumber);
        gameMode.setCombinationsSlots(combinationNumber, 1);
        int totalScore = gameMode.getPlayersTotalScore(gameMode.getCurrentPlayerNumber()) + scoreToInput;
        gameMode.setTotalScore(totalScore);
        gameMode.setIsCombinationActive(false, combinationNumber);

    }

    public void prepareBoardForNextPlayer() {
        uiConfig.clearDicesBorder();
        uiConfig.setDicesVisibility(false);
        if (gameMode instanceof HotSeatGame) {
            ((HotSeatGame) gameMode).prepareCombinationsSlots();
        }


    }

    public void resetCombinationsListeners() {
        for (int x = 0; x < 15; x++) {

            uiConfig.getCombinationsText()[x].setOnClickListener(v -> {

            });

            uiConfig.getCombinationsSlots()[x].setOnClickListener(v -> {

            });
        }
    }
}
