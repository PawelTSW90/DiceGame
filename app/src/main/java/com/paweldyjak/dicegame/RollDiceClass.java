package com.paweldyjak.dicegame;

import android.app.Activity;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

public class RollDiceClass {
    public Activity activity;
    private Button rollDiceButton;
    String[] rolledValues = new String[6];

    RollDiceClass(Activity activity){
        this.activity = activity;
        setRollDiceButton();
    }

    public void setRollDiceButton(){
        rollDiceButton = (Button) this.activity.findViewById(R.id.roll_dice_button);
        rollDiceButton.setOnClickListener(v -> {
            rollSixDices();
            Toast.makeText(activity, rolledValues[0]+rolledValues[1]+rolledValues[2]+rolledValues[3]+rolledValues[4]+rolledValues[5], Toast.LENGTH_LONG).show();

        });

    }

    public int[] rollSixDices(){
        int[] values = new int[6];
        Random randomValue = new Random();

        for (int x = 0; x<6;x++){
            int value = randomValue.nextInt(6-1+1)+1;
            rolledValues[x] = String.valueOf(value);
        }
return values;
    }

}
