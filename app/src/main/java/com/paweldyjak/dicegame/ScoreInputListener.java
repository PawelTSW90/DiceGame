package com.paweldyjak.dicegame;

import android.view.View;

import androidx.core.content.ContextCompat;

import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.GameModes.GameMode;
import com.paweldyjak.dicegame.GameModes.MultiplayerGame;

import java.util.concurrent.Executor;

public class ScoreInputListener implements View.OnClickListener {

    private final GameBoardActivity gameBoardActivity;
    private final GameMode gameMode;
    private final UIConfig uiConfig;
    private final GameBoardManager gameBoardManager;
    private final boolean resetThrowCounter;
    private final int scoreToInput;
    private final int combinationNumber;
    private final int combinationSlotStatus;

    public ScoreInputListener(GameBoardActivity gameBoardActivity, GameMode gameMode, UIConfig uiConfig, GameBoardManager gameBoardManager, boolean resetThrowCounter, int scoreToInput, int combinationNumber, int combinationSlotStatus) {
        this.gameBoardActivity = gameBoardActivity;
        this.gameMode = gameMode;
        this.uiConfig = uiConfig;
        this.resetThrowCounter = resetThrowCounter;
        this.scoreToInput = scoreToInput;
        this.combinationNumber = combinationNumber;
        this.combinationSlotStatus = combinationSlotStatus;
        this.gameBoardManager = gameBoardManager;
    }

    @Override
    public void onClick(View v) {
        Executor executor = ContextCompat.getMainExecutor(gameBoardActivity);
        if (scoreToInput > 0 && combinationSlotStatus == 0 && !resetThrowCounter) {
            uiConfig.combinationHighlighter(0, true);
            updateBoardValues(scoreToInput, combinationNumber);
            uiConfig.setDicesVisibility(false, false);
            if (gameMode instanceof MultiplayerGame) {
                ((MultiplayerGame) gameMode).updateOpponentTurnDatabase();
            }
            executor.execute(() -> {
                try {
                    Thread.sleep(2000);
                    if (gameMode.getCurrentPlayerNumber() == gameMode.getNumberOfPlayers() && gameMode.checkIfAllCombinationsAreDone()) {
                        gameMode.setFinalResultScreen();
                    } else {
                        resetCombinationsListeners();
                        gameBoardManager.setResetThrowCounter(true);
                        gameBoardManager.changeCurrentPlayer();
                        gameBoardActivity.showNextTurnFragment();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }

    }


    public void updateBoardValues(int scoreToInput, int combinationNumber) {
        Sounds sounds = new Sounds(gameBoardActivity);
        sounds.playCompleteCombinationSound();

        if (gameMode instanceof MultiplayerGame) {
            ((MultiplayerGame) gameMode).setCombinationsSlotsInDatabase(combinationNumber, 1);
            ((MultiplayerGame) gameMode).setCombinationsPointsInDatabase(scoreToInput, combinationNumber);
            ((MultiplayerGame) gameMode).setTotalScoreInDatabase(scoreToInput);

        }
        gameMode.setCombinationsPointsValues(scoreToInput, combinationNumber);
        gameMode.setCombinationsSlots(combinationNumber, 1);
        gameMode.setTotalScore(scoreToInput);
        int totalScore = gameMode.getPlayersTotalScore(gameMode.getCurrentPlayerNumber() - 1);
        uiConfig.setTotalScore(totalScore);
        gameBoardManager.updatePlayerBoard();

    }

    public void resetCombinationsListeners() {
        for (int x = 0; x < 16; x++) {
            uiConfig.getCombinationsLayouts()[x].setOnClickListener(v -> {

            });
        }
    }


}
