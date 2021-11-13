package com.paweldyjak.dicegame;

import android.view.View;
import androidx.core.content.ContextCompat;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.GameModes.GameMode;
import com.paweldyjak.dicegame.GameModes.MultiplayerGame;
import java.util.concurrent.Executor;

public class CombinationsListener implements View.OnClickListener {

    private final GameBoardActivity gameBoardActivity;
    private final GameMode gameMode;
    private final UIConfig uiConfig;
    private final GameBoardManager gameBoardManager;
    private final DicesCombinationsChecker dicesCombinationsChecker;
    private final Sounds sounds;
    private final boolean resetThrowCounter;
    private final int combinationNumber;
    private final int combinationSlotStatus;
    private final Executor executor;
    private boolean blockConfirmationButtonsSet = false;

    public CombinationsListener(GameBoardActivity gameBoardActivity, GameMode gameMode, UIConfig uiConfig, GameBoardManager gameBoardManager, DicesCombinationsChecker dicesCombinationsChecker, Sounds sounds, boolean resetThrowCounter, int combinationNumber, int combinationSlotStatus) {
        this.gameBoardActivity = gameBoardActivity;
        this.gameMode = gameMode;
        this.uiConfig = uiConfig;
        this.gameBoardManager = gameBoardManager;
        this.dicesCombinationsChecker = dicesCombinationsChecker;
        this.sounds = sounds;
        this.resetThrowCounter = resetThrowCounter;
        this.combinationNumber = combinationNumber;
        this.combinationSlotStatus = combinationSlotStatus;
        executor = ContextCompat.getMainExecutor(gameBoardActivity);
    }

    @Override
    public void onClick(View v) {
        //set buttons for block combination confirmation
        setBlockConfirmationButtons();

        //set score input listener
        int scoreToInput = dicesCombinationsChecker.getAvailableCombinationsValues().get(combinationNumber);
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
                        gameBoardManager.setResetThrowCounter(true);
                        dicesCombinationsChecker.resetAvailableCombinationsValues();
                        gameBoardManager.changeCurrentPlayer();
                        gameBoardActivity.showNextTurnFragment();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });

            //set block combination listener
        } else if(scoreToInput==0 && combinationSlotStatus == 0 && gameBoardManager.getThrowNumber()==3){
            if (gameBoardActivity.isBlockConfirmationOn()) {
                    uiConfig.showBlockCombinationQuestion(true);
                    switch (combinationNumber) {
                        case 0:
                            uiConfig.getBlockCombinationTextView().setText(gameBoardActivity.getResources().getString(R.string.block_combination_question, gameBoardActivity.getResources().getString(R.string.ones)));
                            break;
                        case 1:
                            uiConfig.getBlockCombinationTextView().setText(gameBoardActivity.getResources().getString(R.string.block_combination_question, gameBoardActivity.getResources().getString(R.string.twos)));
                            break;
                        case 2:
                            uiConfig.getBlockCombinationTextView().setText(gameBoardActivity.getResources().getString(R.string.block_combination_question, gameBoardActivity.getResources().getString(R.string.threes)));
                            break;
                        case 3:
                            uiConfig.getBlockCombinationTextView().setText(gameBoardActivity.getResources().getString(R.string.block_combination_question, gameBoardActivity.getResources().getString(R.string.fours)));
                            break;
                        case 4:
                            uiConfig.getBlockCombinationTextView().setText(gameBoardActivity.getResources().getString(R.string.block_combination_question, gameBoardActivity.getResources().getString(R.string.fives)));
                            break;
                        case 5:
                            uiConfig.getBlockCombinationTextView().setText(gameBoardActivity.getResources().getString(R.string.block_combination_question, gameBoardActivity.getResources().getString(R.string.sixes)));
                            break;
                        case 6:
                            uiConfig.getBlockCombinationTextView().setText(gameBoardActivity.getResources().getString(R.string.block_combination_question, gameBoardActivity.getResources().getString(R.string.pair)));
                            break;
                        case 7:
                            uiConfig.getBlockCombinationTextView().setText(gameBoardActivity.getResources().getString(R.string.block_combination_question, gameBoardActivity.getResources().getString(R.string.two_pairs)));
                            break;
                        case 8:
                            uiConfig.getBlockCombinationTextView().setText(gameBoardActivity.getResources().getString(R.string.block_combination_question, gameBoardActivity.getResources().getString(R.string.evens)));
                            break;
                        case 9:
                            uiConfig.getBlockCombinationTextView().setText(gameBoardActivity.getResources().getString(R.string.block_combination_question, gameBoardActivity.getResources().getString(R.string.odds)));
                            break;
                        case 10:
                            uiConfig.getBlockCombinationTextView().setText(gameBoardActivity.getResources().getString(R.string.block_combination_question, gameBoardActivity.getResources().getString(R.string.small_straight)));
                            break;
                        case 11:
                            uiConfig.getBlockCombinationTextView().setText(gameBoardActivity.getResources().getString(R.string.block_combination_question, gameBoardActivity.getResources().getString(R.string.large_straight)));
                            break;
                        case 12:
                            uiConfig.getBlockCombinationTextView().setText(gameBoardActivity.getResources().getString(R.string.block_combination_question, gameBoardActivity.getResources().getString(R.string.full_house)));
                            break;
                        case 13:
                            uiConfig.getBlockCombinationTextView().setText(gameBoardActivity.getResources().getString(R.string.block_combination_question, gameBoardActivity.getResources().getString(R.string.four_of_a_kind)));
                            break;
                        case 14:
                            uiConfig.getBlockCombinationTextView().setText(gameBoardActivity.getResources().getString(R.string.block_combination_question, gameBoardActivity.getResources().getString(R.string.five_of_a_kind)));
                            break;
                    }
            } else{
                blockCombination();
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

        }
        gameMode.setCombinationsPointsValues(scoreToInput, combinationNumber);
        gameMode.setCombinationsSlots(combinationNumber, 1);
        gameMode.setTotalScore(scoreToInput);
        int totalScore = gameMode.getPlayersTotalScore(gameMode.getCurrentPlayerNumber() - 1);
        uiConfig.setTotalScore(totalScore);
        gameBoardManager.updatePlayerBoard();

    }
        //block combination listener
    public void blockCombination() {
            uiConfig.showBlockCombinationQuestion(false);
            gameBoardManager.setResetThrowCounter(true);
            gameMode.setCombinationsSlots(combinationNumber, 2);
            uiConfig.combinationHighlighter(0, true);
            if (gameMode instanceof MultiplayerGame) {
                ((MultiplayerGame) gameMode).setCombinationsSlotsInDatabase(combinationNumber, 2);
                ((MultiplayerGame) gameMode).updateOpponentTurnDatabase();
            }
            gameBoardManager.updatePlayerBoard();
            uiConfig.setDicesVisibility(false, false);
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

        public void setBlockConfirmationButtons(){
        if(!blockConfirmationButtonsSet) {
            uiConfig.getBlockCombinationNoButton().setOnClickListener(v1 -> {
                uiConfig.gameBoardEnableController(true, uiConfig.getGameBoardLayout());
                uiConfig.showBlockCombinationQuestion(false);
            });

            uiConfig.getBlockCombinationYesButton().setOnClickListener(v1 -> {
                uiConfig.showBlockCombinationQuestion(false);
                blockCombination();
            });
            blockConfirmationButtonsSet = true;
        }
        }

}
