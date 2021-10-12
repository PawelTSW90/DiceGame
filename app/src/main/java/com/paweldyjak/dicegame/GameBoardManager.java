package com.paweldyjak.dicegame;

import android.widget.ImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.GameModes.GameMode;
import com.paweldyjak.dicegame.GameModes.HotSeatGame;
import com.paweldyjak.dicegame.GameModes.MultiplayerGame;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameBoardManager {
    private final String playerUid = FirebaseAuth.getInstance().getUid();
    private final ScoreInputSetter scoreInputSetter;
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


    public GameBoardManager(GameBoardActivity gameBoardActivity, ScoreInputSetter scoreInputSetter, DicesCombinationsChecker dicesCombinationsChecker, UIConfig uiConfig, GameMode gameMode, String opponentUid) {
        this.gameBoardActivity = gameBoardActivity;
        this.scoreInputSetter = scoreInputSetter;
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
                //disable SoS blocking
                if (gameMode instanceof HotSeatGame) {
                    scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkSOS(dices, throwNumber), 15, this);
                } else {
                    scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkSOS(dices, throwNumber), 15, this);
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
            updateDatabaseWithDicesValues(dices);
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


    // method checks which combinations are available
    public void setCombinations() {
        if (gameMode instanceof MultiplayerGame) {
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkOne(dices, isFirstThrow), 0, this);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkTwo(dices, isFirstThrow), 1, this);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkThree(dices, isFirstThrow), 2, this);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkFour(dices, isFirstThrow), 3, this);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkFive(dices, isFirstThrow), 4, this);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkSix(dices, isFirstThrow), 5, this);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkPair(dices, isFirstThrow), 6, this);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkTwoPairs(dices, isFirstThrow), 7, this);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkEvens(dices, isFirstThrow), 8, this);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkOdds(dices, isFirstThrow), 9, this);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkSmallStraight(dices, isFirstThrow), 10, this);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkLargeStraight(dices, isFirstThrow), 11, this);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkFullHouse(dices, isFirstThrow), 12, this);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkFourOfAKind(dices, isFirstThrow), 13, this);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkFiveOfAKind(dices, isFirstThrow), 14, this);
            scoreInputSetter.updateDatabasePlayerScore(dicesCombinationsChecker.checkSOS(dices, throwNumber), 15, this);

        } else {
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkOne(dices, isFirstThrow), 0, this);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkTwo(dices, isFirstThrow), 1, this);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkThree(dices, isFirstThrow), 2, this);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkFour(dices, isFirstThrow), 3, this);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkFive(dices, isFirstThrow), 4, this);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkSix(dices, isFirstThrow), 5, this);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkPair(dices, isFirstThrow), 6, this);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkTwoPairs(dices, isFirstThrow), 7, this);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkEvens(dices, isFirstThrow), 8, this);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkOdds(dices, isFirstThrow), 9, this);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkSmallStraight(dices, isFirstThrow), 10, this);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkLargeStraight(dices, isFirstThrow), 11, this);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkFullHouse(dices, isFirstThrow), 12, this);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkFourOfAKind(dices, isFirstThrow), 13, this);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkFiveOfAKind(dices, isFirstThrow), 14, this);
            scoreInputSetter.updatePlayerScore(dicesCombinationsChecker.checkSOS(dices, throwNumber), 15, this);
        }
    }

    // method allows to block combinations after last throw
    public void blockCombinations() {
        for (int x = 0; x < 16; x++) {
            if (dicesCombinationsChecker.combinationChecker(x, dices, isFirstThrow, 0) == 0 && gameMode.getIsCombinationActive()[x]) {
                {
                    uiConfig.getCombinationsText()[x].setOnClickListener(new BlockCombinationListener(gameBoardActivity, gameMode, scoreInputSetter, uiConfig, this, sounds, x));
                    uiConfig.getCombinationsSlots()[x].setOnClickListener(new BlockCombinationListener(gameBoardActivity, gameMode, scoreInputSetter, uiConfig, this, sounds, x));

                }
            }
        }
    }
    public void changeCurrentPlayer() {
        int numberOfPlayers = gameMode.getNumberOfPlayers();
        if(numberOfPlayers>gameMode.getCurrentPlayerNumber()){
            gameMode.setCurrentPlayerNumber(gameMode.getCurrentPlayerNumber()+1);
            uiConfig.changeCurrentPlayerName(gameMode.getPlayersNames()[gameMode.getCurrentPlayerNumber()-1]);
        } else{
            gameMode.setCurrentPlayerNumber(1);
            uiConfig.changeCurrentPlayerName(gameMode.getPlayersNames()[0]);
        }
    }

    public void updatePlayerBoard() {
        String string = gameBoardActivity.getResources().getString(R.string.points);
        for (int x = 0; x < 16; x++) {
            uiConfig.updateCombinationsUI(gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][x], x);
            uiConfig.getCombinationsPoints()[x].setText(gameMode.getCombinationsPointsValues(gameMode.getCurrentPlayerNumber(), x) + " " + string);
        }

        uiConfig.getTotalScore().setText(gameMode.getPlayersTotalScore(gameMode.getCurrentPlayerNumber() - 1) + " " + string);


    }

    public void updateDatabaseWithDicesValues(int[] dicesValues) {
        Map<String, Integer> dices = new HashMap<>();
        for (int x = 0; x < 5; x++) {
            dices.put(String.valueOf(x + 1), dicesValues[x]);
        }

        FirebaseDatabase.getInstance().getReference().child("users").child(opponentUid).child("multiplayerRoom").child(playerUid).child("dices").setValue(dices);

    }

}

