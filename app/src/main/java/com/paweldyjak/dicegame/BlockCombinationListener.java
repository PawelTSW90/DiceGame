package com.paweldyjak.dicegame;

import android.view.View;

import androidx.core.content.ContextCompat;

import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.GameModes.GameMode;
import com.paweldyjak.dicegame.GameModes.MultiplayerGame;

import java.util.concurrent.Executor;

public class BlockCombinationListener implements View.OnClickListener {
    private final GameBoardActivity gameBoardActivity;
    private final GameMode gameMode;
    private final UIConfig uiConfig;
    private final Sounds sounds;
    private final GameBoardManager gameBoardManager;
    private final int combinationNr;

    public BlockCombinationListener(GameBoardActivity gameBoardActivity, GameMode gameMode, UIConfig uiConfig, GameBoardManager gameBoardManager, Sounds sounds, int combinationNr) {
        this.gameBoardActivity = gameBoardActivity;
        this.gameMode = gameMode;
        this.uiConfig = uiConfig;
        this.sounds = sounds;
        this.gameBoardManager = gameBoardManager;
        this.combinationNr = combinationNr;

    }

    @Override
    public void onClick(View v) {
        Executor executor = ContextCompat.getMainExecutor(gameBoardActivity);
        gameBoardManager.setResetThrowCounter(true);
        gameMode.setCombinationsSlots(combinationNr, 2);
        if (gameMode instanceof MultiplayerGame) {
            ((MultiplayerGame) gameMode).setCombinationsSlotsInDatabase(combinationNr, 2);
            ((MultiplayerGame) gameMode).updateOpponentTurnDatabase();
        }
        gameBoardManager.updatePlayerBoard();
        uiConfig.setDicesVisibility(false);
        executor.execute(() -> {
            try {
                sounds.playEraseCombinationSound();

                Thread.sleep(2000);
                if (gameMode.checkIfAllCombinationsAreDone() && gameMode.getCurrentPlayerNumber() == gameMode.getNumberOfPlayers()) {
                    gameMode.setFinalResultScreen();
                } else {
                    gameBoardManager.changeCurrentPlayer();
                    gameBoardActivity.showNextTurnFragment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}
