package com.paweldyjak.dicegame;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class RollDiceClass {
    private final Context context;
    private ImageView rollDices;
    private final String[] rolledValues = new String[6];
    private ImageView[] dicesSlots = new ImageView[6];
    private boolean dicesActive = true;

    RollDiceClass(Context context) {
        this.context = context;
    }

    public int[] rollDices() {
        if (!dicesActive) {

        } else {
            SoundClass soundClass = new SoundClass(context);
            soundClass.playRollDiceSound();
            int[] values = new int[6];
            Random randomValue = new Random();

            for (int x = 0; x < 6; x++) {
                int value = randomValue.nextInt(6 - 1 + 1) + 1;
                rolledValues[x] = String.valueOf(value);
            }


            return values;
        }
        return null;
    }

    public void setDicesSlots() {
        dicesSlots[0] = ((Activity) context).findViewById(R.id.diceSlot1);
        dicesSlots[1] = ((Activity) context).findViewById(R.id.diceSlot2);
        dicesSlots[2] = ((Activity) context).findViewById(R.id.diceSlot3);
        dicesSlots[3] = ((Activity) context).findViewById(R.id.diceSlot4);
        dicesSlots[4] = ((Activity) context).findViewById(R.id.diceSlot5);
        dicesSlots[5] = ((Activity) context).findViewById(R.id.diceSlot6);

    }

    public void setRollDiceButton() {
        rollDices = ((Activity) context).findViewById(R.id.roll_dices);
        rollDices.setOnClickListener(v -> {
            rollDices();
            rollDices.setAlpha(0.5f);
            //dicesActive = false;
        });

    }

    public void showDices(){

    }


}
