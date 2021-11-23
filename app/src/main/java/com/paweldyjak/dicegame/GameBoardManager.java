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
    private int throwNumber = 0;
    private Sounds sounds;
    private final GameBoardActivity gameBoardActivity;
    private final Random randomValue = new Random();
    private final String opponentUid;


    public GameBoardManager(GameBoardActivity gameBoardActivity, DicesCombinationsChecker dicesCombinationsChecker, UIConfig uiConfig, GameMode gameMode, String opponentUid) {
        this.gameBoardActivity = gameBoardActivity;
        this.dicesCombinationsChecker = dicesCombinationsChecker;
        this.uiConfig = uiConfig;
        this.gameMode = gameMode;
        this.opponentUid = opponentUid;
        sounds = new Sounds(gameBoardActivity);

    }

    public enum CombinationsNames {
        ONES(0), TWOS(1), THREES(2), FOURS(3), FIVES(4), SIXES(5),
        PAIR(6), TWOPAIRS(7), EVENS(8), ODDS(9), SMALLSTRAIGHT(10),
        LARGESTRAIGHT(11), FULLHOUSE(12), FOUROFAKIND(13), FIVEOFAKIND(14), SOS(15);

        private final int combinationNumber;

        CombinationsNames(int combinationNumber) {
            this.combinationNumber = combinationNumber;
        }

        public int getCombinationNumber() {
            return combinationNumber;
        }
    }

    //method configure roll dices button
    public void setRollDicesButton() {
        gameBoardActivity.hideFragment();
        setCombinationsListeners();
        uiConfig.getRollDicesButton().setOnClickListener(v -> {
            if (throwNumber < 3) {
                throwNumber++;
                uiConfig.combinationHighlighter(0, true);
                rollDices();
                uiConfig.showDices(dices, false);
                dicesCombinationsChecker.combinationChecker(dices,throwNumber);
                setDicesRerolling(throwNumber);
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
            uiConfig.clearDicesBorder(false);
            uiConfig.getDicesSlots()[x].setOnClickListener(v -> {
                if (throwNumber < 3) {
                    if (v.getBackground() != null) {
                        v.setBackground(null);
                    } else {
                        uiConfig.setDicesBorder(((ImageView) v));
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

    // method sets combinations listeners
    public void setCombinationsListeners() {
        uiConfig.getCombinationsLayouts()[CombinationsNames.ONES.getCombinationNumber()].setOnClickListener(new CombinationsListener(gameBoardActivity, gameMode, uiConfig, this, dicesCombinationsChecker, sounds, CombinationsNames.ONES.getCombinationNumber()));
        uiConfig.getCombinationsLayouts()[CombinationsNames.TWOS.getCombinationNumber()].setOnClickListener(new CombinationsListener(gameBoardActivity, gameMode, uiConfig, this, dicesCombinationsChecker, sounds, CombinationsNames.TWOS.getCombinationNumber()));
        uiConfig.getCombinationsLayouts()[CombinationsNames.THREES.getCombinationNumber()].setOnClickListener(new CombinationsListener(gameBoardActivity, gameMode, uiConfig, this, dicesCombinationsChecker, sounds, CombinationsNames.THREES.getCombinationNumber()));
        uiConfig.getCombinationsLayouts()[CombinationsNames.FOURS.getCombinationNumber()].setOnClickListener(new CombinationsListener(gameBoardActivity, gameMode, uiConfig, this, dicesCombinationsChecker, sounds, CombinationsNames.FOURS.getCombinationNumber()));
        uiConfig.getCombinationsLayouts()[CombinationsNames.FIVES.getCombinationNumber()].setOnClickListener(new CombinationsListener(gameBoardActivity, gameMode, uiConfig, this, dicesCombinationsChecker, sounds, CombinationsNames.FIVES.getCombinationNumber()));
        uiConfig.getCombinationsLayouts()[CombinationsNames.SIXES.getCombinationNumber()].setOnClickListener(new CombinationsListener(gameBoardActivity, gameMode, uiConfig, this, dicesCombinationsChecker, sounds, CombinationsNames.SIXES.getCombinationNumber()));
        uiConfig.getCombinationsLayouts()[CombinationsNames.PAIR.getCombinationNumber()].setOnClickListener(new CombinationsListener(gameBoardActivity, gameMode, uiConfig, this, dicesCombinationsChecker, sounds, CombinationsNames.PAIR.getCombinationNumber()));
        uiConfig.getCombinationsLayouts()[CombinationsNames.TWOPAIRS.getCombinationNumber()].setOnClickListener(new CombinationsListener(gameBoardActivity, gameMode, uiConfig, this, dicesCombinationsChecker, sounds, CombinationsNames.TWOPAIRS.getCombinationNumber()));
        uiConfig.getCombinationsLayouts()[CombinationsNames.EVENS.getCombinationNumber()].setOnClickListener(new CombinationsListener(gameBoardActivity, gameMode, uiConfig, this, dicesCombinationsChecker, sounds, CombinationsNames.EVENS.getCombinationNumber()));
        uiConfig.getCombinationsLayouts()[CombinationsNames.ODDS.getCombinationNumber()].setOnClickListener(new CombinationsListener(gameBoardActivity, gameMode, uiConfig, this, dicesCombinationsChecker, sounds, CombinationsNames.ODDS.getCombinationNumber()));
        uiConfig.getCombinationsLayouts()[CombinationsNames.SMALLSTRAIGHT.getCombinationNumber()].setOnClickListener(new CombinationsListener(gameBoardActivity, gameMode, uiConfig, this, dicesCombinationsChecker, sounds, CombinationsNames.SMALLSTRAIGHT.getCombinationNumber()));
        uiConfig.getCombinationsLayouts()[CombinationsNames.LARGESTRAIGHT.getCombinationNumber()].setOnClickListener(new CombinationsListener(gameBoardActivity, gameMode, uiConfig, this, dicesCombinationsChecker, sounds, CombinationsNames.LARGESTRAIGHT.getCombinationNumber()));
        uiConfig.getCombinationsLayouts()[CombinationsNames.FULLHOUSE.getCombinationNumber()].setOnClickListener(new CombinationsListener(gameBoardActivity, gameMode, uiConfig, this, dicesCombinationsChecker, sounds, CombinationsNames.FULLHOUSE.getCombinationNumber()));
        uiConfig.getCombinationsLayouts()[CombinationsNames.FOUROFAKIND.getCombinationNumber()].setOnClickListener(new CombinationsListener(gameBoardActivity, gameMode, uiConfig, this, dicesCombinationsChecker, sounds, CombinationsNames.FOUROFAKIND.getCombinationNumber()));
        uiConfig.getCombinationsLayouts()[CombinationsNames.FIVEOFAKIND.getCombinationNumber()].setOnClickListener(new CombinationsListener(gameBoardActivity, gameMode, uiConfig, this, dicesCombinationsChecker, sounds, CombinationsNames.FIVEOFAKIND.getCombinationNumber()));
        uiConfig.getCombinationsLayouts()[CombinationsNames.SOS.getCombinationNumber()].setOnClickListener(new CombinationsListener(gameBoardActivity, gameMode, uiConfig, this, dicesCombinationsChecker, sounds, CombinationsNames.SOS.getCombinationNumber()));

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

    public void setThrowNumber(int throwNumber) {
        this.throwNumber = throwNumber;
    }

    public int getThrowNumber() {
        return throwNumber;
    }

    public void setSounds(Sounds sounds) {
        this.sounds = sounds;
    }
}

