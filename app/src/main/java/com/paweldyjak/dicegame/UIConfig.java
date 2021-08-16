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

    public TextView[] getCombinationsSlots() {
        return combinationsSlots;
    }

    public TextView getTotalScoreTextView() {
        return totalScoreTextView;
    }
}
