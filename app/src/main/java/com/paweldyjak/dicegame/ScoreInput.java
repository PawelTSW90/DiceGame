package com.paweldyjak.dicegame;
import android.content.Context;


public class ScoreInput {
    Context context;
    UIConfig uiConfig;


    ScoreInput(Context context, UIConfig uiConfig){
        this.uiConfig = uiConfig;
        this.context = context;
    }

    public void InputScoreOnes(int scoreToInput) {
        uiConfig.getTextViews()[0].setOnClickListener(v -> {
            if(scoreToInput>0)
            uiConfig.getTextViews()[1].setText(String.valueOf(scoreToInput));
        });
    }
}
