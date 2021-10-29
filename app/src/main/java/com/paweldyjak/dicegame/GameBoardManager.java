package com.paweldyjak.dicegame;

import android.widget.ImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.GameModes.GameMode;
import com.paweldyjak.dicegame.GameModes.MultiplayerGame;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameBoardManager {
    private final String playerUid = FirebaseAuth.getInstance().getUid();
    private final UIConfig uiConfig;
    private final DicesCombinationsChecker dicesCombinationsChecker;
    private final GameMode gameMode;
    private final int[] dices = new int[5];
    private boolean isFirstThrow = true;
    private int throwNumber = 0;
    private final Sounds sounds;
    private final GameBoardActivity gameBoardActivity;
    private final Random randomValue = new Random();
    private final String opponentUid;
    private boolean resetThrowCounter = false;


    public GameBoardManager(GameBoardActivity gameBoardActivity, DicesCombinationsChecker dicesCombinationsChecker, UIConfig uiConfig, GameMode gameMode, String opponentUid) {
        this.gameBoardActivity = gameBoardActivity;
        this.dicesCombinationsChecker = dicesCombinationsChecker;
        this.uiConfig = uiConfig;
        this.gameMode = gameMode;
        this.opponentUid = opponentUid;
        sounds = new Sounds(gameBoardActivity);

    }

    //method configure roll dices button
    public void setRollDicesButton() {
        gameBoardActivity.hideFragment();
        uiConfig.getRollDicesButton().setOnClickListener(v -> {
            if (throwNumber < 4) {
                uiConfig.combinationHighlighter(0, true);
            }
            if (getResetThrowCounter()) {
                throwNumber = 0;
                isFirstThrow = true;
                setResetThrowCounter(false);
            }
            if (throwNumber < 3) {
                if (throwNumber > 0) {
                    isFirstThrow = false;
                }
                rollDices();
                uiConfig.showDices(dices);
                setCombinationsListeners();
                throwNumber++;
                setDicesRerolling(throwNumber);
            }

            if (throwNumber == 3) {
                setDicesRerolling(throwNumber);
                blockCombinations();
                throwNumber++;
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
            updateDatabaseWithDicesValues(dices);
        }

    }

    public void setDicesRerolling(int throwNumber) {

        for (int x = 0; x < 5; x++) {
            uiConfig.clearDicesBorder();
            uiConfig.getDicesSlots()[x].setOnClickListener(v -> {
                if (throwNumber < 3) {
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


    // method checks which combinations are available
    public void setCombinationsListeners() {
        uiConfig.getCombinationsLayouts()[0].setOnClickListener(new ScoreInputListener(gameBoardActivity, gameMode, uiConfig, this, resetThrowCounter, dicesCombinationsChecker.checkOne(dices, isFirstThrow), 0, gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][0]));
        uiConfig.getCombinationsLayouts()[1].setOnClickListener(new ScoreInputListener(gameBoardActivity, gameMode, uiConfig, this, resetThrowCounter, dicesCombinationsChecker.checkTwo(dices, isFirstThrow), 1, gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][1]));
        uiConfig.getCombinationsLayouts()[2].setOnClickListener(new ScoreInputListener(gameBoardActivity, gameMode, uiConfig, this, resetThrowCounter, dicesCombinationsChecker.checkThree(dices, isFirstThrow), 2, gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][2]));
        uiConfig.getCombinationsLayouts()[3].setOnClickListener(new ScoreInputListener(gameBoardActivity, gameMode, uiConfig, this, resetThrowCounter, dicesCombinationsChecker.checkFour(dices, isFirstThrow), 3, gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][3]));
        uiConfig.getCombinationsLayouts()[4].setOnClickListener(new ScoreInputListener(gameBoardActivity, gameMode, uiConfig, this, resetThrowCounter, dicesCombinationsChecker.checkFive(dices, isFirstThrow), 4, gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][4]));
        uiConfig.getCombinationsLayouts()[5].setOnClickListener(new ScoreInputListener(gameBoardActivity, gameMode, uiConfig, this, resetThrowCounter, dicesCombinationsChecker.checkSix(dices, isFirstThrow), 5, gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][5]));
        uiConfig.getCombinationsLayouts()[6].setOnClickListener(new ScoreInputListener(gameBoardActivity, gameMode, uiConfig, this, resetThrowCounter, dicesCombinationsChecker.checkPair(dices, isFirstThrow), 6, gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][6]));
        uiConfig.getCombinationsLayouts()[7].setOnClickListener(new ScoreInputListener(gameBoardActivity, gameMode, uiConfig, this, resetThrowCounter, dicesCombinationsChecker.checkTwoPairs(dices, isFirstThrow), 7, gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][7]));
        uiConfig.getCombinationsLayouts()[8].setOnClickListener(new ScoreInputListener(gameBoardActivity, gameMode, uiConfig, this, resetThrowCounter, dicesCombinationsChecker.checkEvens(dices, isFirstThrow), 8, gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][8]));
        uiConfig.getCombinationsLayouts()[9].setOnClickListener(new ScoreInputListener(gameBoardActivity, gameMode, uiConfig, this, resetThrowCounter, dicesCombinationsChecker.checkOdds(dices, isFirstThrow), 9, gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][9]));
        uiConfig.getCombinationsLayouts()[10].setOnClickListener(new ScoreInputListener(gameBoardActivity, gameMode, uiConfig, this, resetThrowCounter, dicesCombinationsChecker.checkSmallStraight(dices, isFirstThrow), 10, gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][10]));
        uiConfig.getCombinationsLayouts()[11].setOnClickListener(new ScoreInputListener(gameBoardActivity, gameMode, uiConfig, this, resetThrowCounter, dicesCombinationsChecker.checkLargeStraight(dices, isFirstThrow), 11, gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][11]));
        uiConfig.getCombinationsLayouts()[12].setOnClickListener(new ScoreInputListener(gameBoardActivity, gameMode, uiConfig, this, resetThrowCounter, dicesCombinationsChecker.checkFullHouse(dices, isFirstThrow), 12, gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][12]));
        uiConfig.getCombinationsLayouts()[13].setOnClickListener(new ScoreInputListener(gameBoardActivity, gameMode, uiConfig, this, resetThrowCounter, dicesCombinationsChecker.checkFourOfAKind(dices, isFirstThrow), 13, gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][13]));
        uiConfig.getCombinationsLayouts()[14].setOnClickListener(new ScoreInputListener(gameBoardActivity, gameMode, uiConfig, this, resetThrowCounter, dicesCombinationsChecker.checkFiveOfAKind(dices, isFirstThrow), 14, gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][14]));
        uiConfig.getCombinationsLayouts()[15].setOnClickListener(new ScoreInputListener(gameBoardActivity, gameMode, uiConfig, this, resetThrowCounter, dicesCombinationsChecker.checkSOS(dices, throwNumber), 15, gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][15]));

    }

    // method allows to block combinations after last throw
    public void blockCombinations() {
        for (int x = 0; x < 16; x++) {
            if (dicesCombinationsChecker.combinationChecker(x, dices, isFirstThrow, 0) == 0 && gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][x] == 0) {
                {
                    if(gameBoardActivity.isBlockConfirmationOn()){
                        int finalX = x;
                        uiConfig.getCombinationsLayouts()[x].setOnClickListener(v -> setBlockCombinationConfirmation(finalX));
                    } else {
                        uiConfig.getCombinationsLayouts()[x].setOnClickListener(new BlockCombinationListener(gameBoardActivity, gameMode, uiConfig, this, sounds, x));
                    }
                }
            }
        }
        uiConfig.getCombinationsLayouts()[15].setOnClickListener(new ScoreInputListener(gameBoardActivity, gameMode, uiConfig, this, resetThrowCounter, dicesCombinationsChecker.checkSOS(dices, throwNumber), 15, gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][15]));
        uiConfig.getCombinationsLayouts()[15].setOnClickListener(new ScoreInputListener(gameBoardActivity, gameMode, uiConfig, this, resetThrowCounter, dicesCombinationsChecker.checkSOS(dices, throwNumber), 15, gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][15]));


    }

    public void changeCurrentPlayer() {
        int numberOfPlayers = gameMode.getNumberOfPlayers();
        if (numberOfPlayers > gameMode.getCurrentPlayerNumber()) {
            gameMode.setCurrentPlayerNumber(gameMode.getCurrentPlayerNumber() + 1);
            uiConfig.changeCurrentPlayerName(gameMode.getPlayersNames()[gameMode.getCurrentPlayerNumber() - 1]);
        } else {
            gameMode.setCurrentPlayerNumber(1);
            uiConfig.changeCurrentPlayerName(gameMode.getPlayersNames()[0]);
        }
    }

    public void updatePlayerBoard() {
        for (int x = 0; x < 16; x++) {
            uiConfig.updateCombinationsUI(gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][x], x);
            uiConfig.getCombinationsPoints()[x].setText(gameBoardActivity.getResources().getString(R.string.points_value, gameMode.getCombinationsPointsValues(gameMode.getCurrentPlayerNumber(), x)));
        }

        uiConfig.setTotalScore(gameMode.getPlayersTotalScore(gameMode.getCurrentPlayerNumber() - 1));


    }

    public void updateDatabaseWithDicesValues(int[] dicesValues) {
        Map<String, Integer> dices = new HashMap<>();
        for (int x = 0; x < 5; x++) {
            dices.put(String.valueOf(x + 1), dicesValues[x]);
        }

        FirebaseDatabase.getInstance().getReference().child("users").child(opponentUid).child("multiplayerRoom").child(playerUid).child("dices").setValue(dices);

    }

    public boolean getResetThrowCounter() {
        return resetThrowCounter;
    }

    public void setResetThrowCounter(boolean resetThrowCounter) {
        this.resetThrowCounter = resetThrowCounter;
    }

    public void setBlockCombinationConfirmation(int combinationNumber) {
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
        uiConfig.showBlockCombinationQuestion(true);
        uiConfig.getBlockCombinationNoButton().setOnClickListener(v -> {
            uiConfig.gameBoardEnableController(true, uiConfig.getGameBoardLayout());
            uiConfig.showBlockCombinationQuestion(false);
        });
        uiConfig.getBlockCombinationYesButton().setOnClickListener(new BlockCombinationListener(gameBoardActivity, gameMode, uiConfig, this, sounds, combinationNumber));


    }

}

