package com.paweldyjak.dicegame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.paweldyjak.dicegame.Fragments.FinalResultScreen;

import java.util.Arrays;

public class UIConfig {
    private final Context context;
    private final MainActivity mainActivity;
    private final ImageView[] dicesSlots = new ImageView[5];
    private final TextView[] combinationsTextView = new TextView[16];
    private final TextView[] combinationsPointsTextView = new TextView[16];
    private final TextView[] combinationsSlots = new TextView[16];
    private TextView currentPlayerName;
    private TextView totalScoreTextView;
    private String[] playersNames;
    private int numberOfPlayers;
    private int currentPlayerNumber = 6;
    private final int[][] playersCombinationsScore = new int[6][16];
    private final int[][] playersCombinationsSlots = new int[6][16];
    private final boolean[][] playersIsCombinationActive = new boolean[6][16];
    private final int[] playersTotalScore = new int[6];


    public UIConfig(MainActivity mainActivity, Context context, String[] playersNames) {
        this.context = context;
        this.playersNames = playersNames;
        this.mainActivity = mainActivity;


    }

    public void setComponents() {
        dicesSlots[0] = (((Activity) context).findViewById(R.id.diceSlot1));
        dicesSlots[1] = (((Activity) context).findViewById(R.id.diceSlot2));
        dicesSlots[2] = (((Activity) context).findViewById(R.id.diceSlot3));
        dicesSlots[3] = (((Activity) context).findViewById(R.id.diceSlot4));
        dicesSlots[4] = (((Activity) context).findViewById(R.id.diceSlot5));
        combinationsTextView[0] = ((Activity) context).findViewById(R.id.textView_1);
        combinationsPointsTextView[0] = ((Activity) context).findViewById(R.id.textView_1_pts);
        combinationsSlots[0] = ((Activity) context).findViewById(R.id.textView_1_slot);
        combinationsTextView[1] = ((Activity) context).findViewById(R.id.textView_2);
        combinationsPointsTextView[1] = ((Activity) context).findViewById(R.id.textView_2_pts);
        combinationsSlots[1] = ((Activity) context).findViewById(R.id.textView_2_slot);
        combinationsTextView[2] = ((Activity) context).findViewById(R.id.textView_3);
        combinationsPointsTextView[2] = ((Activity) context).findViewById(R.id.textView_3_pts);
        combinationsSlots[2] = ((Activity) context).findViewById(R.id.textView_3_slot);
        combinationsTextView[3] = ((Activity) context).findViewById(R.id.textView_4);
        combinationsPointsTextView[3] = ((Activity) context).findViewById(R.id.textView_4_pts);
        combinationsSlots[3] = ((Activity) context).findViewById(R.id.textView_4_slot);
        combinationsTextView[4] = ((Activity) context).findViewById(R.id.textView_5);
        combinationsPointsTextView[4] = ((Activity) context).findViewById(R.id.textView_5_pts);
        combinationsSlots[4] = ((Activity) context).findViewById(R.id.textView_5_slot);
        combinationsTextView[5] = ((Activity) context).findViewById(R.id.textView_6);
        combinationsPointsTextView[5] = ((Activity) context).findViewById(R.id.textView_6_pts);
        combinationsSlots[5] = ((Activity) context).findViewById(R.id.textView_6_slot);
        combinationsTextView[6] = ((Activity) context).findViewById(R.id.textView_pair);
        combinationsPointsTextView[6] = ((Activity) context).findViewById(R.id.textView_pair_pts);
        combinationsSlots[6] = ((Activity) context).findViewById(R.id.textView_pair_slot);
        combinationsTextView[7] = ((Activity) context).findViewById(R.id.textView_2pairs);
        combinationsPointsTextView[7] = ((Activity) context).findViewById(R.id.textView_2pairs_pts);
        combinationsSlots[7] = ((Activity) context).findViewById(R.id.textView_2pairs_slot);
        combinationsTextView[8] = ((Activity) context).findViewById(R.id.textView_evens);
        combinationsPointsTextView[8] = ((Activity) context).findViewById(R.id.textView_evens_pts);
        combinationsSlots[8] = ((Activity) context).findViewById(R.id.textView_evens_slot);
        combinationsTextView[9] = ((Activity) context).findViewById(R.id.textView_odds);
        combinationsPointsTextView[9] = ((Activity) context).findViewById(R.id.textView_odds_pts);
        combinationsSlots[9] = ((Activity) context).findViewById(R.id.textView_odds_slot);
        combinationsTextView[10] = ((Activity) context).findViewById(R.id.textView_smallStraight);
        combinationsPointsTextView[10] = ((Activity) context).findViewById(R.id.textView_smallStraight_pts);
        combinationsSlots[10] = ((Activity) context).findViewById(R.id.textView_smallStraight_slot);
        combinationsTextView[11] = ((Activity) context).findViewById(R.id.textView_largeStraight);
        combinationsPointsTextView[11] = ((Activity) context).findViewById(R.id.textView_largeStraight_pts);
        combinationsSlots[11] = ((Activity) context).findViewById(R.id.textView_largeStraight_slot);
        combinationsTextView[12] = ((Activity) context).findViewById(R.id.textView_fullHouse);
        combinationsPointsTextView[12] = ((Activity) context).findViewById(R.id.textView_fullHouse_pts);
        combinationsSlots[12] = ((Activity) context).findViewById(R.id.textView_fullHouse_slot);
        combinationsTextView[13] = ((Activity) context).findViewById(R.id.textView_4ofAKind);
        combinationsPointsTextView[13] = ((Activity) context).findViewById(R.id.textView_4ofAKind_pts);
        combinationsSlots[13] = ((Activity) context).findViewById(R.id.textView_4ofAKind_slot);
        combinationsTextView[14] = ((Activity) context).findViewById(R.id.textView_5ofAKind);
        combinationsPointsTextView[14] = ((Activity) context).findViewById(R.id.textView_5ofAKind_pts);
        combinationsSlots[14] = ((Activity) context).findViewById(R.id.textView_5ofAKind_slot);
        combinationsTextView[15] = ((Activity) context).findViewById(R.id.textView_sos);
        combinationsPointsTextView[15] = ((Activity) context).findViewById(R.id.textView_sos_pts);
        combinationsSlots[15] = ((Activity) context).findViewById(R.id.textView_sos_slot);
        totalScoreTextView = new TextView(context);
        totalScoreTextView = ((Activity) context).findViewById(R.id.textView_score_pts);
        currentPlayerName = ((Activity) context).findViewById(R.id.player_name_textView);

    }

    public void setAllCombinationsAsActive() {
        for (boolean[] row: playersIsCombinationActive)
            Arrays.fill(row, true);
    }


    public void setTotalScore(int score) {
        String string = mainActivity.getResources().getString(R.string.points);
        switch (currentPlayerNumber) {
            case 1:
                playersTotalScore[0] += score;
                totalScoreTextView.setText(playersTotalScore[0] + " " + string);
                break;
            case 2:
                playersTotalScore[1] += score;
                totalScoreTextView.setText(playersTotalScore[1] + " " + string);
                break;

            case 3:
                playersTotalScore[2] += score;
                totalScoreTextView.setText(playersTotalScore[2] + " " + string);
                break;

            case 4:
                playersTotalScore[3] += score;
                totalScoreTextView.setText(playersTotalScore[3] + " " + string);
                break;

            case 5:
                playersTotalScore[4] += score;
                totalScoreTextView.setText(playersTotalScore[4] + " " + string);
                break;

            case 6:
                playersTotalScore[5] += score;
                totalScoreTextView.setText(playersTotalScore[5] + " " + string);
                break;
        }

    }

    public void hideDices() {
        for (int x = 0; x < getDicesSlots().length; x++) {
            getDicesSlots()[x].setImageResource(0);
        }
    }

    public boolean checkIfAllCombinationsAreDone() {
        for (int x = 0; x < getIsCombinationActive().length; x++) {
            if (getIsCombinationActive()[x]) {
                return false;
            }
        }
        return true;
    }


    public void prepareScoreBoard() {
        String string = mainActivity.getResources().getString(R.string.points);
        for (int x = 0; x < 16; x++) {
            switch (currentPlayerNumber) {
                case 1:
                    getCombinationsTextView()[x].setEnabled(playersIsCombinationActive[0][x]);
                    combinationsPointsTextView[x].setText(playersCombinationsScore[0][x] + " " + string);
                    totalScoreTextView.setText(playersTotalScore[0] + " " + string);
                    break;

                case 2:
                    getCombinationsTextView()[x].setEnabled(playersIsCombinationActive[1][x]);
                    combinationsPointsTextView[x].setText(playersCombinationsScore[1][x] + " " + string);
                    totalScoreTextView.setText(playersTotalScore[1] + " " + string);
                    break;

                case 3:
                    getCombinationsTextView()[x].setEnabled(playersIsCombinationActive[2][x]);
                    combinationsPointsTextView[x].setText(playersCombinationsScore[2][x] + " " + string);
                    totalScoreTextView.setText(playersTotalScore[2] + " " + string);
                    break;

                case 4:
                    getCombinationsTextView()[x].setEnabled(playersIsCombinationActive[3][x]);
                    combinationsPointsTextView[x].setText(playersCombinationsScore[3][x] + " " + string);
                    totalScoreTextView.setText(playersTotalScore[3] + " " + string);
                    break;

                case 5:
                    getCombinationsTextView()[x].setEnabled(playersIsCombinationActive[4][x]);
                    combinationsPointsTextView[x].setText(playersCombinationsScore[4][x] + " " + string);
                    totalScoreTextView.setText(playersTotalScore[4] + " " + string);
                    break;

                case 6:
                    getCombinationsTextView()[x].setEnabled(playersIsCombinationActive[5][x]);
                    combinationsPointsTextView[x].setText(playersCombinationsScore[5][x] + " " + string);
                    totalScoreTextView.setText(playersTotalScore[5] + " " + string);
                    break;


            }


        }

    }

    public void configureUI() {
        setComponents();
        setAllCombinationsAsActive();
    }

    //generate final screen fragment
    public void setFinalResultScreen() {
        FinalResultScreen finalResultScreen = new FinalResultScreen(mainActivity, context, this);
        mainActivity.replaceFragment(R.id.fragment_layout, finalResultScreen);

    }


    //setters and getters
    public ImageView[] getDicesSlots() {
        return dicesSlots;
    }

    public TextView[] getCombinationsPointsTextView() {
        return combinationsPointsTextView;
    }

    public TextView[] getCombinationsTextView() {
        return combinationsTextView;
    }

    public boolean[] getIsCombinationActive() {
        switch (currentPlayerNumber) {
            case 1:
                return playersIsCombinationActive[0];
            case 2:
                return playersIsCombinationActive[1];
            case 3:
                return playersIsCombinationActive[2];
            case 4:
                return playersIsCombinationActive[3];
            case 5:
                return playersIsCombinationActive[4];
            case 6:
                return playersIsCombinationActive[5];
        }
        return null;

    }

    public void setIsCombinationActive(boolean isCombinationActive, int combinationNr) {
        switch (currentPlayerNumber) {
            case 1:
                this.playersIsCombinationActive[0][combinationNr] = isCombinationActive;
                break;
            case 2:
                this.playersIsCombinationActive[1][combinationNr] = isCombinationActive;
                break;
            case 3:
                this.playersIsCombinationActive[2][combinationNr] = isCombinationActive;
                break;
            case 4:
                this.playersIsCombinationActive[3][combinationNr] = isCombinationActive;
                break;
            case 5:
                this.playersIsCombinationActive[4][combinationNr] = isCombinationActive;
                break;
            case 6:
                this.playersIsCombinationActive[5][combinationNr] = isCombinationActive;
                break;

        }


    }

    public int getCombinationScore(int combinationNr) {

        switch (currentPlayerNumber) {
            case 1:
                return playersCombinationsScore[0][combinationNr];
            case 2:
                return playersCombinationsScore[1][combinationNr];
            case 3:
                return playersCombinationsScore[2][combinationNr];
            case 4:
                return playersCombinationsScore[3][combinationNr];
            case 5:
                return playersCombinationsScore[4][combinationNr];
            case 6:
                return playersCombinationsScore[5][combinationNr];
        }
        return 0;


    }

    public void setCombinationScore(int score, int combinationNr) {
        switch (currentPlayerNumber) {

            case 1:
                this.playersCombinationsScore[0][combinationNr] = score;
                break;
            case 2:
                this.playersCombinationsScore[1][combinationNr] = score;
                break;
            case 3:
                this.playersCombinationsScore[2][combinationNr] = score;
                break;
            case 4:
                this.playersCombinationsScore[3][combinationNr] = score;
                break;
            case 5:
                this.playersCombinationsScore[4][combinationNr] = score;
                break;
            case 6:
                this.playersCombinationsScore[5][combinationNr] = score;
                break;
        }


    }

    public void setDicesBorder(ImageView dice, boolean setBorder) {
        Drawable dicesBorder = ResourcesCompat.getDrawable(context.getResources(), R.drawable.dices_border, null);
        if (setBorder) {
            dice.setBackground(dicesBorder);
        } else {
            dice.setBackground(null);
        }
    }

    public void clearDicesBorder() {
        for (ImageView dices : dicesSlots) {
            dices.setBackground(null);
        }
    }

    public int getCurrentPlayerNumber() {
        return currentPlayerNumber;
    }

    public void setCurrentPlayerNumber(int currentPlayerNumber) {
        this.currentPlayerNumber = currentPlayerNumber;
    }

    public String[] getPlayersNames() {
        return playersNames;
    }

    public void setPlayersNames(String[] playersNames) {
        this.playersNames = playersNames;
    }

    public int getPlayersTotalScore(int playerNumber) {
        switch (playerNumber) {
            case 1:
                return playersTotalScore[0];
            case 2:
                return playersTotalScore[1];
            case 3:
                return playersTotalScore[2];
            case 4:
                return playersTotalScore[3];
            case 5:
                return playersTotalScore[4];
            case 6:
                return playersTotalScore[5];
        }
        return 0;


    }

    public TextView getCurrentPlayerName() {
        return currentPlayerName;
    }

    public void setCombinationsSlots(int combinationsSlotNumber, int slotStatus) {

        switch (currentPlayerNumber){
            case 1:
                playersCombinationsSlots[0][combinationsSlotNumber] = slotStatus;
                break;
            case 2:
                playersCombinationsSlots[1][combinationsSlotNumber] = slotStatus;
                break;
            case 3:
                playersCombinationsSlots[2][combinationsSlotNumber] = slotStatus;
                break;
            case 4:
                playersCombinationsSlots[3][combinationsSlotNumber] = slotStatus;
                break;
            case 5:
                playersCombinationsSlots[4][combinationsSlotNumber] = slotStatus;
                break;
            case 6:
                playersCombinationsSlots[5][combinationsSlotNumber] = slotStatus;
                break;
        }

    }

    public void prepareCombinationsSlots() {
        for (int x = 0; x < combinationsSlots.length; x++) {
            switch (currentPlayerNumber) {
                case 1:
                    if (playersCombinationsSlots[0][x] == 1) {
                        combinationsSlots[x].setText("\u2713");
                        combinationsSlots[x].setGravity(Gravity.CENTER);
                        combinationsSlots[x].setTextSize(16);
                        combinationsSlots[x].setTextColor(Color.rgb(27, 182, 33));
                        combinationsPointsTextView[x].setEnabled(true);
                    } else if (playersCombinationsSlots[0][x] == 2) {
                        combinationsSlots[x].setText("X");
                        combinationsSlots[x].setGravity(Gravity.CENTER);
                        combinationsSlots[x].setTextSize(16);
                        combinationsSlots[x].setTextColor(Color.rgb(140, 17, 17));
                        combinationsPointsTextView[x].setEnabled(false);
                    } else {
                        combinationsSlots[x].setText("");
                        combinationsPointsTextView[x].setEnabled(true);
                    }
                    break;

                case 2:

                    if (playersCombinationsSlots[1][x] == 1) {
                        combinationsSlots[x].setText("\u2713");
                        combinationsSlots[x].setGravity(Gravity.CENTER);
                        combinationsSlots[x].setTextSize(16);
                        combinationsSlots[x].setTextColor(Color.rgb(27, 182, 33));
                        combinationsPointsTextView[x].setEnabled(true);
                    } else if (playersCombinationsSlots[1][x] == 2) {
                        combinationsSlots[x].setText("X");
                        combinationsSlots[x].setGravity(Gravity.CENTER);
                        combinationsSlots[x].setTextSize(16);
                        combinationsSlots[x].setTextColor(Color.rgb(140, 17, 17));
                        combinationsPointsTextView[x].setEnabled(false);
                    } else {
                        combinationsSlots[x].setText("");
                        combinationsPointsTextView[x].setEnabled(true);
                    }
                    break;
                case 3:

                    if (playersCombinationsSlots[2][x] == 1) {
                        combinationsSlots[x].setText("\u2713");
                        combinationsSlots[x].setGravity(Gravity.CENTER);
                        combinationsSlots[x].setTextSize(16);
                        combinationsSlots[x].setTextColor(Color.rgb(27, 182, 33));
                        combinationsPointsTextView[x].setEnabled(true);
                    } else if (playersCombinationsSlots[2][x] == 2) {
                        combinationsSlots[x].setText("X");
                        combinationsSlots[x].setGravity(Gravity.CENTER);
                        combinationsSlots[x].setTextSize(16);
                        combinationsSlots[x].setTextColor(Color.rgb(140, 17, 17));
                        combinationsPointsTextView[x].setEnabled(false);
                    } else {
                        combinationsSlots[x].setText("");
                        combinationsPointsTextView[x].setEnabled(true);
                    }
                    break;
                case 4:

                    if (playersCombinationsSlots[3][x] == 1) {
                        combinationsSlots[x].setText("\u2713");
                        combinationsSlots[x].setGravity(Gravity.CENTER);
                        combinationsSlots[x].setTextSize(16);
                        combinationsSlots[x].setTextColor(Color.rgb(27, 182, 33));
                        combinationsPointsTextView[x].setEnabled(true);
                    } else if (playersCombinationsSlots[3][x] == 2) {
                        combinationsSlots[x].setText("X");
                        combinationsSlots[x].setGravity(Gravity.CENTER);
                        combinationsSlots[x].setTextSize(16);
                        combinationsSlots[x].setTextColor(Color.rgb(140, 17, 17));
                        combinationsPointsTextView[x].setEnabled(false);
                    } else {
                        combinationsSlots[x].setText("");
                        combinationsPointsTextView[x].setEnabled(true);
                    }
                    break;
                case 5:

                    if (playersCombinationsSlots[4][x] == 1) {
                        combinationsSlots[x].setText("\u2713");
                        combinationsSlots[x].setGravity(Gravity.CENTER);
                        combinationsSlots[x].setTextSize(16);
                        combinationsSlots[x].setTextColor(Color.rgb(27, 182, 33));
                        combinationsPointsTextView[x].setEnabled(true);
                    } else if (playersCombinationsSlots[4][x] == 2) {
                        combinationsSlots[x].setText("X");
                        combinationsSlots[x].setGravity(Gravity.CENTER);
                        combinationsSlots[x].setTextSize(16);
                        combinationsSlots[x].setTextColor(Color.rgb(140, 17, 17));
                        combinationsPointsTextView[x].setEnabled(false);
                    } else {
                        combinationsSlots[x].setText("");
                        combinationsPointsTextView[x].setEnabled(true);
                    }
                    break;
                case 6:

                    if (playersCombinationsSlots[5][x] == 1) {
                        combinationsSlots[x].setText("\u2713");
                        combinationsSlots[x].setGravity(Gravity.CENTER);
                        combinationsSlots[x].setTextSize(16);
                        combinationsSlots[x].setTextColor(Color.rgb(27, 182, 33));
                        combinationsPointsTextView[x].setEnabled(true);
                    } else if (playersCombinationsSlots[5][x] == 2) {
                        combinationsSlots[x].setText("X");
                        combinationsSlots[x].setGravity(Gravity.CENTER);
                        combinationsSlots[x].setTextSize(16);
                        combinationsSlots[x].setTextColor(Color.rgb(140, 17, 17));
                        combinationsPointsTextView[x].setEnabled(false);
                    } else {
                        combinationsSlots[x].setText("");
                        combinationsPointsTextView[x].setEnabled(true);
                    }
                    break;

            }

        }
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
}
