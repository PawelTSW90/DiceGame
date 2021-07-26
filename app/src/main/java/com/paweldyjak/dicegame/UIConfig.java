package com.paweldyjak.dicegame;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class UIConfig {
    private final Context context;
    private final TextView[] combinations = new TextView[16];
    private final TextView[] combinationsPoints = new TextView[16];
    private final ImageView[] dicesSlots = new ImageView[5];
    private final boolean[] isCombinationActive = new boolean[16];
    private final int[] scoreValues = new int[16];
    private TextView totalScore;
    UIConfig(Context context){
        this.context = context;
        totalScore = new TextView(context);

    }


    public void setDicesSlots() {
        dicesSlots[0] =(((Activity) context).findViewById(R.id.diceSlot1));
        dicesSlots[1] =(((Activity) context).findViewById(R.id.diceSlot2));
        dicesSlots[2] =(((Activity) context).findViewById(R.id.diceSlot3));
        dicesSlots[3] =(((Activity) context).findViewById(R.id.diceSlot4));
        dicesSlots[4] =(((Activity) context).findViewById(R.id.diceSlot5));

    }


    public void setDicesCombinations(){
        combinations[0] = ((Activity) context).findViewById(R.id.textView_1);
        combinationsPoints[0] = ((Activity) context).findViewById(R.id.textView_1_pts);
        combinations[1] = ((Activity) context).findViewById(R.id.textView_2);
        combinationsPoints[1] = ((Activity) context).findViewById(R.id.textView_2_pts);
        combinations[2] = ((Activity) context).findViewById(R.id.textView_3);
        combinationsPoints[2] = ((Activity) context).findViewById(R.id.textView_3_pts);
        combinations[3] = ((Activity) context).findViewById(R.id.textView_4);
        combinationsPoints[3] = ((Activity) context).findViewById(R.id.textView_4_pts);
        combinations[4] = ((Activity) context).findViewById(R.id.textView_5);
        combinationsPoints[4] = ((Activity) context).findViewById(R.id.textView_5_pts);
        combinations[5] = ((Activity) context).findViewById(R.id.textView_6);
        combinationsPoints[5] = ((Activity) context).findViewById(R.id.textView_6_pts);
        combinations[6] = ((Activity) context).findViewById(R.id.textView_pair);
        combinationsPoints[6] = ((Activity) context).findViewById(R.id.textView_pair_pts);
        combinations[7] = ((Activity) context).findViewById(R.id.textView_2pairs);
        combinationsPoints[7] = ((Activity) context).findViewById(R.id.textView_2pairs_pts);
        combinations[8] = ((Activity) context).findViewById(R.id.textView_evens);
        combinationsPoints[8] = ((Activity) context).findViewById(R.id.textView_evens_pts);
        combinations[9] = ((Activity) context).findViewById(R.id.textView_odds);
        combinationsPoints[9] = ((Activity) context).findViewById(R.id.textView_odds_pts);
        combinations[10] = ((Activity) context).findViewById(R.id.textView_smallStraight);
        combinationsPoints[10] = ((Activity) context).findViewById(R.id.textView_smallStraight_pts);
        combinations[11] = ((Activity) context).findViewById(R.id.textView_largeStraight);
        combinationsPoints[11] = ((Activity) context).findViewById(R.id.textView_largeStraight_pts);
        combinations[12] = ((Activity) context).findViewById(R.id.textView_fullHouse);
        combinationsPoints[12] = ((Activity) context).findViewById(R.id.textView_fullHouse_pts);
        combinations[13] = ((Activity) context).findViewById(R.id.textView_4ofAKind);
        combinationsPoints[13] = ((Activity) context).findViewById(R.id.textView_4ofAKind_pts);
        combinations[14] = ((Activity) context).findViewById(R.id.textView_5ofAKind);
        combinationsPoints[14] = ((Activity) context).findViewById(R.id.textView_5ofAKind_pts);
        combinations[15] = ((Activity) context).findViewById(R.id.textView_sos);
        combinationsPoints[15] = ((Activity) context).findViewById(R.id.textView_sos_pts);
        totalScore = ((Activity)context).findViewById(R.id.textView_score_pts);

    }

    public ImageView[] getDicesSlots() {
        return dicesSlots;
    }

    public TextView[] getCombinationsPoints() {
        return combinationsPoints;
    }

    public TextView[] getCombinations() {
        return combinations;
    }

    public boolean[] getIsCombinationActive() {
        return isCombinationActive;
    }

    public void setCombinationsAsActive(){
        Arrays.fill(isCombinationActive, true);
    }

    public void setIsCombinationActive(boolean isCombinationActive, int combinationNr){
        this.isCombinationActive[combinationNr] = isCombinationActive;
    }

    public void setTotalScore(int score){
        totalScore.setText(score+" pkt");
    }

    public void hideDices(){
        for (int x = 0; x<getDicesSlots().length; x++){
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

    public int getScoreValues(int combinationNr){
        return scoreValues[combinationNr];
    }

    public void setScoreValues(int score, int combinationNr){
        this.scoreValues[combinationNr] = score;
    }


}
