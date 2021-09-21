package com.paweldyjak.dicegame;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;

public class UIConfig {
    private final Context context;
    private final ImageView[] dicesSlots = new ImageView[5];
    private final TextView[] combinationsTextView = new TextView[16];
    private final TextView[] combinationsPointsTextView = new TextView[16];
    private final TextView[] combinationsSlots = new TextView[16];
    private TextView currentPlayerName;
    private TextView totalScoreTextView;


    public UIConfig(Context context) {
        this.context = context;


    }

    public void setComponents() {
        dicesSlots[0] = (((Activity) context).findViewById(R.id.diceSlot1));
        dicesSlots[1] = (((Activity) context).findViewById(R.id.diceSlot2));
        dicesSlots[2] = (((Activity) context).findViewById(R.id.diceSlot3));
        dicesSlots[3] = (((Activity) context).findViewById(R.id.diceSlot4));
        dicesSlots[4] = (((Activity) context).findViewById(R.id.diceSlot5));
        for(int x = 0; x<combinationsTextView.length; x++){
            switch (x){
                case 0:
                    combinationsTextView[0] = ((Activity) context).findViewById(R.id.textView_1);
                    combinationsTextView[0].setText(R.string._1);
                    combinationsPointsTextView[0] = ((Activity) context).findViewById(R.id.textView_1_pts);
                    combinationsSlots[0] = ((Activity) context).findViewById(R.id.textView_1_slot);
                    break;

                case 1:
                    combinationsTextView[1] = ((Activity) context).findViewById(R.id.textView_2);
                    combinationsTextView[1].setText(R.string._2);
                    combinationsPointsTextView[1] = ((Activity) context).findViewById(R.id.textView_2_pts);
                    combinationsSlots[1] = ((Activity) context).findViewById(R.id.textView_2_slot);
                    break;

                case 2:
                    combinationsTextView[2] = ((Activity) context).findViewById(R.id.textView_3);
                    combinationsTextView[2].setText(R.string._3);
                    combinationsPointsTextView[2] = ((Activity) context).findViewById(R.id.textView_3_pts);
                    combinationsSlots[2] = ((Activity) context).findViewById(R.id.textView_3_slot);
                    break;

                case 3:
                    combinationsTextView[3] = ((Activity) context).findViewById(R.id.textView_4);
                    combinationsTextView[3].setText(R.string._4);
                    combinationsPointsTextView[3] = ((Activity) context).findViewById(R.id.textView_4_pts);
                    combinationsSlots[3] = ((Activity) context).findViewById(R.id.textView_4_slot);
                    break;

                case 4:
                    combinationsTextView[4] = ((Activity) context).findViewById(R.id.textView_5);
                    combinationsTextView[4].setText(R.string._5);
                    combinationsPointsTextView[4] = ((Activity) context).findViewById(R.id.textView_5_pts);
                    combinationsSlots[4] = ((Activity) context).findViewById(R.id.textView_5_slot);
                    break;

                case 5:
                    combinationsTextView[5] = ((Activity) context).findViewById(R.id.textView_6);
                    combinationsTextView[5].setText(R.string._6);
                    combinationsPointsTextView[5] = ((Activity) context).findViewById(R.id.textView_6_pts);
                    combinationsSlots[5] = ((Activity) context).findViewById(R.id.textView_6_slot);
                    break;

                case 6:
                    combinationsTextView[6] = ((Activity) context).findViewById(R.id.textView_pair);
                    combinationsTextView[6].setText(R.string.pair);
                    combinationsPointsTextView[6] = ((Activity) context).findViewById(R.id.textView_pair_pts);
                    combinationsSlots[6] = ((Activity) context).findViewById(R.id.textView_pair_slot);
                    break;

                case 7:
                    combinationsTextView[7] = ((Activity) context).findViewById(R.id.textView_2pairs);
                    combinationsTextView[7].setText(R.string.two_pairs);
                    combinationsPointsTextView[7] = ((Activity) context).findViewById(R.id.textView_2pairs_pts);
                    combinationsSlots[7] = ((Activity) context).findViewById(R.id.textView_2pairs_slot);
                    break;

                case 8:
                    combinationsTextView[8] = ((Activity) context).findViewById(R.id.textView_evens);
                    combinationsTextView[8].setText(R.string.evens);
                    combinationsPointsTextView[8] = ((Activity) context).findViewById(R.id.textView_evens_pts);
                    combinationsSlots[8] = ((Activity) context).findViewById(R.id.textView_evens_slot);
                    break;

                case 9:
                    combinationsTextView[9] = ((Activity) context).findViewById(R.id.textView_odds);
                    combinationsTextView[9].setText(R.string.odds);
                    combinationsPointsTextView[9] = ((Activity) context).findViewById(R.id.textView_odds_pts);
                    combinationsSlots[9] = ((Activity) context).findViewById(R.id.textView_odds_slot);
                    break;
                case 10:
                    combinationsTextView[10] = ((Activity) context).findViewById(R.id.textView_smallStraight);
                    combinationsTextView[10].setText(R.string.small_straight);
                    combinationsPointsTextView[10] = ((Activity) context).findViewById(R.id.textView_smallStraight_pts);
                    combinationsSlots[10] = ((Activity) context).findViewById(R.id.textView_smallStraight_slot);
                    break;
                case 11:
                    combinationsTextView[11] = ((Activity) context).findViewById(R.id.textView_largeStraight);
                    combinationsTextView[11].setText(R.string.large_straight);
                    combinationsPointsTextView[11] = ((Activity) context).findViewById(R.id.textView_largeStraight_pts);
                    combinationsSlots[11] = ((Activity) context).findViewById(R.id.textView_largeStraight_slot);
                    break;
                case 12:
                    combinationsTextView[12] = ((Activity) context).findViewById(R.id.textView_fullHouse);
                    combinationsTextView[12].setText(R.string.full_house);
                    combinationsPointsTextView[12] = ((Activity) context).findViewById(R.id.textView_fullHouse_pts);
                    combinationsSlots[12] = ((Activity) context).findViewById(R.id.textView_fullHouse_slot);
                    break;
                case 13:
                    combinationsTextView[13] = ((Activity) context).findViewById(R.id.textView_4ofAKind);
                    combinationsTextView[13].setText(R.string.four_of_a_kind);
                    combinationsPointsTextView[13] = ((Activity) context).findViewById(R.id.textView_4ofAKind_pts);
                    combinationsSlots[13] = ((Activity) context).findViewById(R.id.textView_4ofAKind_slot);
                    break;
                case 14:
                    combinationsTextView[14] = ((Activity) context).findViewById(R.id.textView_5ofAKind);
                    combinationsTextView[14].setText(R.string.five_of_a_kind);
                    combinationsPointsTextView[14] = ((Activity) context).findViewById(R.id.textView_5ofAKind_pts);
                    combinationsSlots[14] = ((Activity) context).findViewById(R.id.textView_5ofAKind_slot);
                    break;
                case 15:
                    combinationsTextView[15] = ((Activity) context).findViewById(R.id.textView_sos);
                    combinationsTextView[15].setText(R.string.SOS);
                    combinationsPointsTextView[15] = ((Activity) context).findViewById(R.id.textView_sos_pts);
                    combinationsSlots[15] = ((Activity) context).findViewById(R.id.textView_sos_slot);
                    break;

            }
        }

        for(int x = 0; x<combinationsTextView.length; x++){
            combinationsPointsTextView[x].setText(R.string.zero_points);
        }

        for(TextView textView: combinationsTextView){
            textView.setEnabled(true);
        }

        totalScoreTextView = new TextView(context);
        totalScoreTextView = ((Activity) context).findViewById(R.id.textView_score_pts);
        currentPlayerName = ((Activity) context).findViewById(R.id.player_name_textView);
        totalScoreTextView.setText(R.string.zero_points);

    }


    public void hideDices() {
        for (int x = 0; x < getDicesSlots().length; x++) {
            getDicesSlots()[x].setImageResource(0);
        }
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


    public TextView getCurrentPlayerName() {
        return currentPlayerName;
    }

    public void setCurrentPlayerName(String currentPlayerName) {
        this.currentPlayerName.setText(currentPlayerName);
    }

    public TextView[] getCombinationsSlots() {
        return combinationsSlots;
    }

    public TextView getTotalScoreTextView() {
        return totalScoreTextView;
    }
}
