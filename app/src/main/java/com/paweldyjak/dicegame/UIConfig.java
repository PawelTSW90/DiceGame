package com.paweldyjak.dicegame;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

public class UIConfig {
    private final Context context;
    private final TextView[] textViews = new TextView[33];
    private final ImageView[] dicesSlots = new ImageView[6];
    private ImageView rollDices;

    UIConfig(Context context){
        this.context = context;

    }


    public void setDicesSlots() {
        dicesSlots[0] = (((Activity) context).findViewById(R.id.diceSlot1));
        dicesSlots[1] =(((Activity) context).findViewById(R.id.diceSlot2));
        dicesSlots[2] =(((Activity) context).findViewById(R.id.diceSlot3));
        dicesSlots[3] =(((Activity) context).findViewById(R.id.diceSlot4));
        dicesSlots[4] =(((Activity) context).findViewById(R.id.diceSlot5));
        dicesSlots[5] =(((Activity) context).findViewById(R.id.diceSlot6));

    }


    public void setDicesCombinations(){
        textViews[0] = ((Activity) context).findViewById(R.id.textView_1);
        textViews[1] = ((Activity) context).findViewById(R.id.textView_1_pts);
        textViews[2] = ((Activity) context).findViewById(R.id.textView_2);
        textViews[3] = ((Activity) context).findViewById(R.id.textView_2_pts);
        textViews[4] = ((Activity) context).findViewById(R.id.textView_3);
        textViews[5] = ((Activity) context).findViewById(R.id.textView_3_pts);
        textViews[6] = ((Activity) context).findViewById(R.id.textView_4);
        textViews[7] = ((Activity) context).findViewById(R.id.textView_4_pts);
        textViews[8] = ((Activity) context).findViewById(R.id.textView_5);
        textViews[9] = ((Activity) context).findViewById(R.id.textView_5_pts);
        textViews[10] = ((Activity) context).findViewById(R.id.textView_6);
        textViews[11] = ((Activity) context).findViewById(R.id.textView_6_pts);
        textViews[12] = ((Activity) context).findViewById(R.id.textView_pair);
        textViews[13] = ((Activity) context).findViewById(R.id.textView_pair_pts);
        textViews[14] = ((Activity) context).findViewById(R.id.textView_2pairs);
        textViews[15] = ((Activity) context).findViewById(R.id.textView_2pairs_pts);
        textViews[16] = ((Activity) context).findViewById(R.id.textView_evens);
        textViews[17] = ((Activity) context).findViewById(R.id.textView_evens_pts);
        textViews[18] = ((Activity) context).findViewById(R.id.textView_odds);
        textViews[19] = ((Activity) context).findViewById(R.id.textView_odds_pts);
        textViews[20] = ((Activity) context).findViewById(R.id.textView_smallStraight);
        textViews[21] = ((Activity) context).findViewById(R.id.textView_smallStraight_pts);
        textViews[22] = ((Activity) context).findViewById(R.id.textView_largeStraight);
        textViews[23] = ((Activity) context).findViewById(R.id.textView_largeStraight_pts);
        textViews[24] = ((Activity) context).findViewById(R.id.textView_fullHouse);
        textViews[25] = ((Activity) context).findViewById(R.id.textView_fullHouse_pts);
        textViews[26] = ((Activity) context).findViewById(R.id.textView_4ofAKind);
        textViews[27] = ((Activity) context).findViewById(R.id.textView_4ofAKind_pts);
        textViews[28] = ((Activity) context).findViewById(R.id.textView_5ofAKind);
        textViews[29] = ((Activity) context).findViewById(R.id.textView_5ofAKind_pts);
        textViews[30] = ((Activity) context).findViewById(R.id.textView_sos);
        textViews[31] = ((Activity) context).findViewById(R.id.textView_sos_pts);
        textViews[32] = ((Activity) context).findViewById(R.id.textView_total);

    }

    public TextView[] getTextViews(){
        return textViews;
    }

    public ImageView[] getDicesSlots() {
        return dicesSlots;
    }

    public void setRollDices(ImageView rollDices) {
        this.rollDices = rollDices;
    }

    public ImageView getRollDices() {
        return rollDices;
    }
}
