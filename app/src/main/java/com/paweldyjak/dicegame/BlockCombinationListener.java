package com.paweldyjak.dicegame;

import android.view.View;

import androidx.core.content.ContextCompat;

import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.GameModes.GameMode;
import com.paweldyjak.dicegame.GameModes.HotSeatGame;
import com.paweldyjak.dicegame.GameModes.MultiplayerGame;

import java.util.concurrent.Executor;

public class BlockCombinationListener implements View.OnClickListener {
    private final GameBoardActivity gameBoardActivity;
    private final GameMode gameMode;
    private final ScoreInputSetter scoreInputSetter;
    private final UIConfig uiConfig;
    private final Sounds sounds;
    private final GameBoardManager gameBoardManager;
    private final int combinationNr;

    public BlockCombinationListener(GameBoardActivity gameBoardActivity, GameMode gameMode, ScoreInputSetter scoreInputSetter, UIConfig uiConfig, GameBoardManager gameBoardManager, Sounds sounds, int combinationNr) {
        this.gameBoardActivity = gameBoardActivity;
        this.gameMode = gameMode;
        this.scoreInputSetter = scoreInputSetter;
        this.uiConfig = uiConfig;
        this.sounds = sounds;
        this.gameBoardManager = gameBoardManager;
        this.combinationNr = combinationNr;

    }

    @Override
    public void onClick(View v) {
        Executor executor = ContextCompat.getMainExecutor(gameBoardActivity);
        scoreInputSetter.setResetThrowCounter(true);
        gameMode.setCombinationsSlots(combinationNr, 2);
        if (gameMode instanceof HotSeatGame) {
            gameBoardManager.updatePlayerBoard();
        }
        if (gameMode instanceof MultiplayerGame) {
            updateDatabase();
            ((MultiplayerGame) gameMode).updateOpponentTurnDatabase();
        }
        uiConfig.setDicesVisibility(false);
        resetCombinationsListeners();
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
    }

    public void resetCombinationsListeners() {
        for (int x = 0; x < 15; x++) {

            uiConfig.getCombinationsText()[x].setOnClickListener(v -> {

            });

            uiConfig.getCombinationsSlots()[x].setOnClickListener(v -> {

            });
        }
    }

    public void updateDatabase() {
        ((MultiplayerGame) gameMode).setCombinationsSlotsInDatabase(combinationNr, 2);
        ((MultiplayerGame) gameMode).setIsCombinationActiveInDatabase(false, combinationNr);
    }
}
