package com.paweldyjak.dicegame.Activities;

import android.view.View;
import androidx.core.content.ContextCompat;
import com.paweldyjak.dicegame.GameModes.GameMode;
import com.paweldyjak.dicegame.GameModes.MultiplayerGame;
import com.paweldyjak.dicegame.ScoreInputSetter;
import com.paweldyjak.dicegame.Sounds;
import com.paweldyjak.dicegame.UIConfig;
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
            updatePlayerBoard(scoreToInput, combinationNumber);
            prepareBoardForNextPlayer();
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
                        gameMode.updatePlayerName();
                        gameMode.setOpponentTurn(!gameMode.getOpponentTurn());
                        gameBoardActivity.showNextTurnFragment();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                });
            }

        }
    }

    public void updatePlayerBoard(int scoreToInput, int combinationNumber) {
        Sounds sounds = new Sounds(gameBoardActivity);
        sounds.playCompleteCombinationSound();
        gameMode.setCombinationScore(scoreToInput, combinationNumber);
        String points = gameMode.getCombinationScore(combinationNumber) + " pkt";
        gameMode.setTotalScore(scoreToInput);
        uiConfig.getCombinationsPointsTextView()[combinationNumber].setText(points);
        uiConfig.getCombinationsTextView()[combinationNumber].setEnabled(false);
        gameMode.setIsCombinationActive(false, combinationNumber);
        gameMode.setCombinationsSlots(combinationNumber, 1);
    }

    public void prepareBoardForNextPlayer() {
        uiConfig.clearDicesBorder();
        uiConfig.hideDices();
        gameMode.prepareCombinationsSlots();
        if (gameMode instanceof MultiplayerGame)
            ((MultiplayerGame) gameMode).updateOpponentTurnDatabase();

    }

    public void resetCombinationsListeners() {
        for (int x = 0; x < 15; x++) {

            uiConfig.getCombinationsTextView()[x].setOnClickListener(v -> {

            });
        }
    }
}
