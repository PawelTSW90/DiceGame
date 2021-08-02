package com.paweldyjak.dicegame;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.paweldyjak.dicegame.Fragments.FinalResultScreen;

import java.util.Arrays;

public class UIConfig {
    private final Context context;
    private final TextView[] combinationsTextView = new TextView[16];
    private final TextView[] combinationsPointsTextView = new TextView[16];
    private String[] playersNames;
    private int playerNumber = 2;
    private TextView totalScoreTextView;
    private final ImageView[] dicesSlots = new ImageView[5];
    private final boolean[] playerOneIsCombinationActive = new boolean[16];
    private final int[] playerOneScoreValues = new int[16];
    private int playerOneTotalScore = 0;
    private int playerTwoTotalScore = 0;
    private final boolean[] playerTwoIsCombinationActive = new boolean[16];
    private final int[] playerTwoScoreValues = new int[16];
    private TextView playerNameBoardTextView;


    public UIConfig(Context context, String[] playersNames) {
        this.context = context;
        this.playersNames = playersNames;
        totalScoreTextView = new TextView(context);

    }


    public void setDicesSlots() {
        dicesSlots[0] = (((Activity) context).findViewById(R.id.diceSlot1));
        dicesSlots[1] = (((Activity) context).findViewById(R.id.diceSlot2));
        dicesSlots[2] = (((Activity) context).findViewById(R.id.diceSlot3));
        dicesSlots[3] = (((Activity) context).findViewById(R.id.diceSlot4));
        dicesSlots[4] = (((Activity) context).findViewById(R.id.diceSlot5));

    }


    public void setDicesCombinations() {
        combinationsTextView[0] = ((Activity) context).findViewById(R.id.textView_1);
        combinationsPointsTextView[0] = ((Activity) context).findViewById(R.id.textView_1_pts);
        combinationsTextView[1] = ((Activity) context).findViewById(R.id.textView_2);
        combinationsPointsTextView[1] = ((Activity) context).findViewById(R.id.textView_2_pts);
        combinationsTextView[2] = ((Activity) context).findViewById(R.id.textView_3);
        combinationsPointsTextView[2] = ((Activity) context).findViewById(R.id.textView_3_pts);
        combinationsTextView[3] = ((Activity) context).findViewById(R.id.textView_4);
        combinationsPointsTextView[3] = ((Activity) context).findViewById(R.id.textView_4_pts);
        combinationsTextView[4] = ((Activity) context).findViewById(R.id.textView_5);
        combinationsPointsTextView[4] = ((Activity) context).findViewById(R.id.textView_5_pts);
        combinationsTextView[5] = ((Activity) context).findViewById(R.id.textView_6);
        combinationsPointsTextView[5] = ((Activity) context).findViewById(R.id.textView_6_pts);
        combinationsTextView[6] = ((Activity) context).findViewById(R.id.textView_pair);
        combinationsPointsTextView[6] = ((Activity) context).findViewById(R.id.textView_pair_pts);
        combinationsTextView[7] = ((Activity) context).findViewById(R.id.textView_2pairs);
        combinationsPointsTextView[7] = ((Activity) context).findViewById(R.id.textView_2pairs_pts);
        combinationsTextView[8] = ((Activity) context).findViewById(R.id.textView_evens);
        combinationsPointsTextView[8] = ((Activity) context).findViewById(R.id.textView_evens_pts);
        combinationsTextView[9] = ((Activity) context).findViewById(R.id.textView_odds);
        combinationsPointsTextView[9] = ((Activity) context).findViewById(R.id.textView_odds_pts);
        combinationsTextView[10] = ((Activity) context).findViewById(R.id.textView_smallStraight);
        combinationsPointsTextView[10] = ((Activity) context).findViewById(R.id.textView_smallStraight_pts);
        combinationsTextView[11] = ((Activity) context).findViewById(R.id.textView_largeStraight);
        combinationsPointsTextView[11] = ((Activity) context).findViewById(R.id.textView_largeStraight_pts);
        combinationsTextView[12] = ((Activity) context).findViewById(R.id.textView_fullHouse);
        combinationsPointsTextView[12] = ((Activity) context).findViewById(R.id.textView_fullHouse_pts);
        combinationsTextView[13] = ((Activity) context).findViewById(R.id.textView_4ofAKind);
        combinationsPointsTextView[13] = ((Activity) context).findViewById(R.id.textView_4ofAKind_pts);
        combinationsTextView[14] = ((Activity) context).findViewById(R.id.textView_5ofAKind);
        combinationsPointsTextView[14] = ((Activity) context).findViewById(R.id.textView_5ofAKind_pts);
        combinationsTextView[15] = ((Activity) context).findViewById(R.id.textView_sos);
        combinationsPointsTextView[15] = ((Activity) context).findViewById(R.id.textView_sos_pts);
        totalScoreTextView = ((Activity) context).findViewById(R.id.textView_score_pts);
        playerNameBoardTextView = ((Activity) context).findViewById(R.id.player_name_textView);

    }


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
        if (playerNumber == 1) {
            return playerOneIsCombinationActive;
        } else
            return playerTwoIsCombinationActive;
    }

    public void setAllCombinationsAsActive() {
        Arrays.fill(playerOneIsCombinationActive, true);
        Arrays.fill(playerTwoIsCombinationActive, true);
    }

    public void setIsCombinationActive(boolean isCombinationActive, int combinationNr) {
        if (playerNumber == 1) {
            this.playerOneIsCombinationActive[combinationNr] = isCombinationActive;
        } else {
            this.playerTwoIsCombinationActive[combinationNr] = isCombinationActive;
        }
    }

    public void setTotalScore(int score) {
        if (playerNumber == 1) {
            playerOneTotalScore += score;
            totalScoreTextView.setText(playerOneTotalScore + " pkt");
        } else {
            playerTwoTotalScore += score;
            totalScoreTextView.setText(playerTwoTotalScore + " pkt");
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

    public int getScoreValues(int combinationNr) {
        if (playerNumber == 1) {
            return playerOneScoreValues[combinationNr];
        } else {
            return playerTwoScoreValues[combinationNr];
        }
    }

    public void setScoreValues(int score, int combinationNr) {
        if (playerNumber == 1) {
            this.playerOneScoreValues[combinationNr] = score;
        } else {
            this.playerTwoScoreValues[combinationNr] = score;
        }
    }

    public void prepareScreenForPlayer() {

        for (int x = 0; x < 16; x++) {
            if (playerNumber == 2) {
                if (!playerTwoIsCombinationActive[x]) {
                    getCombinationsTextView()[x].setEnabled(false);
                } else {
                    getCombinationsTextView()[x].setEnabled(true);
                }
                combinationsPointsTextView[x].setText(playerTwoScoreValues[x] + " pkt");
                totalScoreTextView.setText(playerTwoTotalScore + " pkt");

            } else {
                if (!playerOneIsCombinationActive[x]) {
                    getCombinationsTextView()[x].setEnabled(false);
                } else {
                    getCombinationsTextView()[x].setEnabled(true);
                }
                combinationsPointsTextView[x].setText(playerOneScoreValues[x] + " pkt");
                totalScoreTextView.setText(playerOneTotalScore + " pkt");

            }

        }





    }

    public String[] getPlayersNames() {
        return playersNames;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayersNames(String[] playersNames) {
        this.playersNames = playersNames;
    }

    public int getPlayersTotalScore(int playerNumber) {
        if (playerNumber == 1) {
            return playerOneTotalScore;
        } else {
            return playerTwoTotalScore;
        }
    }

    public void setFinalResultScreen() {
        FinalResultScreen finalResultScreen = new FinalResultScreen(context, this);
        finalResultScreen.setFinalResultScreen();
    }

    public TextView getPlayerNameBoardTextView() {
        return playerNameBoardTextView;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
}
