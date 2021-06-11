package com.paweldyjak.dicegame;
import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import java.util.Random;


public class RollDices {
    private final Context context;
    private final ScoreInput scoreInput;
    private final UIConfig uiConfig;
    private final DicesChecker dicesChecker;
    private final EraseCombinations eraseCombinations;
    private final int[] dices = new int[6];
    private boolean isFirstThrow = true;
    private int throwNumber = 0;


    RollDices(Context context, ScoreInput scoreInput, DicesChecker dicesChecker, UIConfig uiConfig, EraseCombinations eraseCombinations) {
        this.context = context;
        this.scoreInput = scoreInput;
        this.dicesChecker = dicesChecker;
        this.uiConfig = uiConfig;
        this.eraseCombinations = eraseCombinations;



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
            if(scoreInput.getResetThrowCounter()){
                throwNumber = 0;
                isFirstThrow = true;
                scoreInput.setResetThrowCounter(false);
            }
            if (throwNumber <3) {
                if (throwNumber>0) {
                    isFirstThrow = false;
                }
                rollDices();
                showDices();
                scoreInput.InputScoreOne(dicesChecker.checkOne(dices, isFirstThrow));
                scoreInput.InputScoreTwo(dicesChecker.checkTwo(dices, isFirstThrow));
                scoreInput.InputScoreThree(dicesChecker.checkThree(dices, isFirstThrow));
                scoreInput.InputScoreFour(dicesChecker.checkFour(dices, isFirstThrow));
                scoreInput.InputScoreFive(dicesChecker.checkFive(dices, isFirstThrow));
                scoreInput.InputScoreSix(dicesChecker.checkSix(dices, isFirstThrow));
                throwNumber++;
                if (throwNumber == 3){
                    if(!checkForAvailableCombination())
                        eraseCombinations.combinationEraser();
                }

            }   else {


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

    public boolean checkForAvailableCombination(){
        return dicesChecker.checkOne(dices, isFirstThrow) != 0 || dicesChecker.checkTwo(dices, isFirstThrow) != 0 || dicesChecker.checkThree(dices, isFirstThrow) != 0
                || dicesChecker.checkFour(dices, isFirstThrow) != 0 || dicesChecker.checkFive(dices, isFirstThrow) != 0 || dicesChecker.checkSix(dices, isFirstThrow) != 0;
    }


}

