package com.paweldyjak.dicegame;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class RollDices {
    private final Context context;
    private final ScoreInput scoreInput;
    private final UIConfig uiConfig;
    private final DicesChecker dicesChecker;
    private final int[] dices = new int[6];
    private boolean isFirstThrow = true;
    private int throwNumber = 0;


    RollDices(Context context, ScoreInput scoreInput, DicesChecker dicesChecker, UIConfig uiConfig) {
        this.context = context;
        this.scoreInput = scoreInput;
        this.dicesChecker = dicesChecker;
        this.uiConfig = uiConfig;



    }

    public void rollDices() {
        Sounds sounds = new Sounds(context);
        sounds.playRollDiceSound();
        Random randomValue = new Random();
        for (int x = 0; x < 6; x++) {
            int value = randomValue.nextInt(6 - 1 + 1) + 1;
            dices[x] = value;
        }


    }

    public void setRollDicesButton() {
        uiConfig.setRollDices(new ImageView(context));
        uiConfig.setRollDices(((Activity) context).findViewById(R.id.roll_dices));
        uiConfig.getRollDices().setOnClickListener(v -> {
            if (throwNumber < 3) {
                if (uiConfig.getDicesSlots()[0].getDrawable() != null) {
                    isFirstThrow = false;
                }
                rollDices();
                showDices();
                scoreInput.InputScoreOnes(dicesChecker.checkOnes(dices, isFirstThrow));
                throwNumber++;
            } else {

            }

        });
    }

    public void showDices() {
        for(int x = 0; x<6; x++){
            uiConfig.getDicesSlots()[x].setImageResource(0);
        }

        int valueToDisplay;
        for (int x = 0; x < 6; x++) {
            valueToDisplay = dices[x];

            for (int y = 0; y < 6; y++) {
                if (uiConfig.getDicesSlots()[y].getDrawable() != null) {

                } else {
                    switch (valueToDisplay) {
                        case 1:
                            uiConfig.getDicesSlots()[y].setImageResource(R.drawable.dice1);
                            y = 5;
                            break;
                        case 2:
                            uiConfig.getDicesSlots()[y].setImageResource(R.drawable.dice2);
                            y = 5;
                            break;
                        case 3:
                            uiConfig.getDicesSlots()[y].setImageResource(R.drawable.dice3);
                            y = 5;
                            break;
                        case 4:
                            uiConfig.getDicesSlots()[y].setImageResource(R.drawable.dice4);
                            y = 5;
                            break;
                        case 5:
                            uiConfig.getDicesSlots()[y].setImageResource(R.drawable.dice5);
                            y = 5;
                            break;
                        case 6:
                            uiConfig.getDicesSlots()[y].setImageResource(R.drawable.dice6);
                            y = 5;
                            break;
                    }
                }
            }
        }

    }

    public int[] getDices() {
        return dices;
    }

    public boolean isFirstThrow() {
        return isFirstThrow;
    }

    public void setFirstThrow(boolean firstThrow) {
        isFirstThrow = firstThrow;
    }

    public int getThrowNumber() {
        return throwNumber;
    }


}

