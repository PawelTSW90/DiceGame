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
    private int playerNumber = 2;
    private int playerOneTotalScore = 0;
    private int playerTwoTotalScore = 0;
    private final int[] playerOneCombinationsScore = new int[16];
    private final int[] playerTwoCombinationsScore = new int[16];
    private final int[] playerOneCombinationsSlots = new int [16];
    private final int[] playerTwoCombinationsSlots = new int [16];
    private final boolean[] playerOneIsCombinationActive = new boolean[16];
    private final boolean[] playerTwoIsCombinationActive = new boolean[16];


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
        combinationsSlots[0] = ((Activity)context).findViewById(R.id.textView_1_slot);
        combinationsTextView[1] = ((Activity) context).findViewById(R.id.textView_2);
        combinationsPointsTextView[1] = ((Activity) context).findViewById(R.id.textView_2_pts);
        combinationsSlots[1] = ((Activity)context).findViewById(R.id.textView_2_slot);
        combinationsTextView[2] = ((Activity) context).findViewById(R.id.textView_3);
        combinationsPointsTextView[2] = ((Activity) context).findViewById(R.id.textView_3_pts);
        combinationsSlots[2] = ((Activity)context).findViewById(R.id.textView_3_slot);
        combinationsTextView[3] = ((Activity) context).findViewById(R.id.textView_4);
        combinationsPointsTextView[3] = ((Activity) context).findViewById(R.id.textView_4_pts);
        combinationsSlots[3] = ((Activity)context).findViewById(R.id.textView_4_slot);
        combinationsTextView[4] = ((Activity) context).findViewById(R.id.textView_5);
        combinationsPointsTextView[4] = ((Activity) context).findViewById(R.id.textView_5_pts);
        combinationsSlots[4] = ((Activity)context).findViewById(R.id.textView_5_slot);
        combinationsTextView[5] = ((Activity) context).findViewById(R.id.textView_6);
        combinationsPointsTextView[5] = ((Activity) context).findViewById(R.id.textView_6_pts);
        combinationsSlots[5] = ((Activity)context).findViewById(R.id.textView_6_slot);
        combinationsTextView[6] = ((Activity) context).findViewById(R.id.textView_pair);
        combinationsPointsTextView[6] = ((Activity) context).findViewById(R.id.textView_pair_pts);
        combinationsSlots[6] = ((Activity)context).findViewById(R.id.textView_pair_slot);
        combinationsTextView[7] = ((Activity) context).findViewById(R.id.textView_2pairs);
        combinationsPointsTextView[7] = ((Activity) context).findViewById(R.id.textView_2pairs_pts);
        combinationsSlots[7] = ((Activity)context).findViewById(R.id.textView_2pairs_slot);
        combinationsTextView[8] = ((Activity) context).findViewById(R.id.textView_evens);
        combinationsPointsTextView[8] = ((Activity) context).findViewById(R.id.textView_evens_pts);
        combinationsSlots[8] = ((Activity)context).findViewById(R.id.textView_evens_slot);
        combinationsTextView[9] = ((Activity) context).findViewById(R.id.textView_odds);
        combinationsPointsTextView[9] = ((Activity) context).findViewById(R.id.textView_odds_pts);
        combinationsSlots[9] = ((Activity)context).findViewById(R.id.textView_odds_slot);
        combinationsTextView[10] = ((Activity) context).findViewById(R.id.textView_smallStraight);
        combinationsPointsTextView[10] = ((Activity) context).findViewById(R.id.textView_smallStraight_pts);
        combinationsSlots[10] = ((Activity)context).findViewById(R.id.textView_smallStraight_slot);
        combinationsTextView[11] = ((Activity) context).findViewById(R.id.textView_largeStraight);
        combinationsPointsTextView[11] = ((Activity) context).findViewById(R.id.textView_largeStraight_pts);
        combinationsSlots[11] = ((Activity)context).findViewById(R.id.textView_largeStraight_slot);
        combinationsTextView[12] = ((Activity) context).findViewById(R.id.textView_fullHouse);
        combinationsPointsTextView[12] = ((Activity) context).findViewById(R.id.textView_fullHouse_pts);
        combinationsSlots[12] = ((Activity)context).findViewById(R.id.textView_fullHouse_slot);
        combinationsTextView[13] = ((Activity) context).findViewById(R.id.textView_4ofAKind);
        combinationsPointsTextView[13] = ((Activity) context).findViewById(R.id.textView_4ofAKind_pts);
        combinationsSlots[13] = ((Activity)context).findViewById(R.id.textView_4ofAKind_slot);
        combinationsTextView[14] = ((Activity) context).findViewById(R.id.textView_5ofAKind);
        combinationsPointsTextView[14] = ((Activity) context).findViewById(R.id.textView_5ofAKind_pts);
        combinationsSlots[14] = ((Activity)context).findViewById(R.id.textView_5ofAKind_slot);
        combinationsTextView[15] = ((Activity) context).findViewById(R.id.textView_sos);
        combinationsPointsTextView[15] = ((Activity) context).findViewById(R.id.textView_sos_pts);
        combinationsSlots[15] = ((Activity)context).findViewById(R.id.textView_sos_slot);
        totalScoreTextView = new TextView(context);
        totalScoreTextView = ((Activity) context).findViewById(R.id.textView_score_pts);
        currentPlayerName = ((Activity) context).findViewById(R.id.player_name_textView);

    }

    public void setAllCombinationsAsActive() {
        Arrays.fill(playerOneIsCombinationActive, true);
        Arrays.fill(playerTwoIsCombinationActive, true);
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


    public void prepareScoreBoard() {

        for (int x = 0; x < 16; x++) {
            if (playerNumber == 2) {
                getCombinationsTextView()[x].setEnabled(playerTwoIsCombinationActive[x]);
                combinationsPointsTextView[x].setText(playerTwoCombinationsScore[x] + " pkt");
                totalScoreTextView.setText(playerTwoTotalScore + " pkt");

            } else {
                getCombinationsTextView()[x].setEnabled(playerOneIsCombinationActive[x]);
                combinationsPointsTextView[x].setText(playerOneCombinationsScore[x] + " pkt");
                totalScoreTextView.setText(playerOneTotalScore + " pkt");

            }

        }

    }

    public void configureUI(){
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
        if (playerNumber == 1) {
            return playerOneIsCombinationActive;
        } else
            return playerTwoIsCombinationActive;
    }

    public void setIsCombinationActive(boolean isCombinationActive, int combinationNr) {
        if (playerNumber == 1) {
            this.playerOneIsCombinationActive[combinationNr] = isCombinationActive;
        } else {
            this.playerTwoIsCombinationActive[combinationNr] = isCombinationActive;
        }
    }

    public int getCombinationScore(int combinationNr) {
        if (playerNumber == 1) {
            return playerOneCombinationsScore[combinationNr];
        } else {
            return playerTwoCombinationsScore[combinationNr];
        }
    }

    public void setCombinationScore(int score, int combinationNr) {
        if (playerNumber == 1) {
            this.playerOneCombinationsScore[combinationNr] = score;
        } else {
            this.playerTwoCombinationsScore[combinationNr] = score;
        }
    }

    public void setDicesBorder(ImageView dice, boolean setBorder){
        Drawable dicesBorder = ResourcesCompat.getDrawable(context.getResources(), R.drawable.dices_border, null);
        if(setBorder){
            dice.setBackground(dicesBorder);
        } else{
            dice.setBackground(null);
        }
    }

    public void clearDicesBorder(){
        for(ImageView dices: dicesSlots){
            dices.setBackground(null);
        }
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public String[] getPlayersNames() {
        return playersNames;
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

    public TextView getCurrentPlayerName() {
        return currentPlayerName;
    }

    public void setCombinationsSlots(int combinationsSlotNumber, int slotStatus){
        if(playerNumber==1){
            playerOneCombinationsSlots[combinationsSlotNumber] = slotStatus;
        } else{
            playerTwoCombinationsSlots[combinationsSlotNumber] = slotStatus;
        }

    }

    public void prepareCombinationsSlots(){
        for(int x = 0; x<combinationsSlots.length; x++){
            if(playerNumber ==1){
                if(playerOneCombinationsSlots[x]==1){
                    combinationsSlots[x].setText("\u2713");
                    combinationsSlots[x].setGravity(Gravity.CENTER);
                    combinationsSlots[x].setTextSize(16);
                    combinationsSlots[x].setTextColor(Color.rgb(27,182,33));
                } else if(playerOneCombinationsSlots[x]==2){
                    combinationsSlots[x].setText("X");
                    combinationsSlots[x].setGravity(Gravity.CENTER);
                    combinationsSlots[x].setTextSize(16);
                    combinationsSlots[x].setTextColor(Color.rgb(140,17,17));
                } else{
                    combinationsSlots[x].setText("");
                }
            } else{
                if(playerTwoCombinationsSlots[x]==1){
                    combinationsSlots[x].setText("\u2713");
                    combinationsSlots[x].setGravity(Gravity.CENTER);
                    combinationsSlots[x].setTextSize(16);
                    combinationsSlots[x].setTextColor(Color.rgb(27,182,33));
                } else if(playerTwoCombinationsSlots[x]==2){
                    combinationsSlots[x].setText("X");
                    combinationsSlots[x].setGravity(Gravity.CENTER);
                    combinationsSlots[x].setTextSize(16);
                    combinationsSlots[x].setTextColor(Color.rgb(140,17,17));
                } else{
                    combinationsSlots[x].setText("");
                }
            }
        }

    }






}
