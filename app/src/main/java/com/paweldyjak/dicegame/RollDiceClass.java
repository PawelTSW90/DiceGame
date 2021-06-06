package com.paweldyjak.dicegame;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RollDiceClass {
    private final Context context;
    private ImageView rollDices;
    private final int[] rolledValues = new int[6];
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
                rolledValues[x] = value;
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
            rollDices.setAlpha(0.5f);
            rollDices();
            dicesActive = false;
            showDices();

        });

    }

    public void showDices() {
        int valueToDisplay;
        for (int x = 0; x < 6; x++) {
            valueToDisplay = rolledValues[x];

            for (int y = 0; y < 6; y++) {
                if (dicesSlots[y].getDrawable() != null) {

                } else {
                    switch (valueToDisplay) {
                        case 1:
                            dicesSlots[y].setImageResource(R.drawable.dice1);
                            y = 5;
                            break;
                        case 2:
                            dicesSlots[y].setImageResource(R.drawable.dice2);
                            y = 5;
                            break;
                        case 3:
                            dicesSlots[y].setImageResource(R.drawable.dice3);
                            y = 5;
                            break;
                        case 4:
                            dicesSlots[y].setImageResource(R.drawable.dice4);
                            y = 5;
                            break;
                        case 5:
                            dicesSlots[y].setImageResource(R.drawable.dice5);
                            y = 5;
                            break;
                        case 6:
                            dicesSlots[y].setImageResource(R.drawable.dice6);
                            y = 5;
                            break;
                    }
                }
            }
        }

    }


}
