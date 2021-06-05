package com.paweldyjak.dicegame;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class RollDiceClass {
    public Activity activity;
    private Button rollDiceButton;
    private TextView displayNumbers;
    String[] rolledValues = new String[6];

    RollDiceClass(Activity activity) {
        this.activity = activity;
        setRollDiceButton();
    }

    public void setRollDiceButton() {
        rollDiceButton = activity.findViewById(R.id.roll_dice_button);
        rollDiceButton.setOnClickListener(v -> {
            rollDices();
            displayNumbers();
        });

    }

    public int[] rollDices() {
        int[] values = new int[6];
        Random randomValue = new Random();

        for (int x = 0; x < 6; x++) {
            int value = randomValue.nextInt(6 - 1 + 1) + 1;
            rolledValues[x] = String.valueOf(value);
        }

        return values;
    }

    public void displayNumbers() {
        displayNumbers = activity.findViewById(R.id.numbers_display);
        displayNumbers.setText("Siemano");
    }

}
