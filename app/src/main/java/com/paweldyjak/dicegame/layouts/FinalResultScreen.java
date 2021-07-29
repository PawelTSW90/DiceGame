package com.paweldyjak.dicegame.layouts;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import com.paweldyjak.dicegame.R;
import com.paweldyjak.dicegame.UIConfig;

public class FinalResultScreen {
    Context context;
    UIConfig uiConfig;

    public FinalResultScreen(Context context, UIConfig uiConfig){
        this.context = context;
        this.uiConfig = uiConfig;

    }

    public void setFinalResultScreen(){
        String winnerPlayer;
        if(uiConfig.getPlayersTotalScore(1)>uiConfig.getPlayersTotalScore(2)){
            winnerPlayer = uiConfig.getPlayersNames()[0];
        } else{
            winnerPlayer = uiConfig.getPlayersNames()[1];
        }
        ((Activity) context).setContentView(R.layout.final_result_screen);
        TextView playerOneTextView = ((Activity)context).findViewById(R.id.player_one_result_textview);
        TextView playerTwoTextView = ((Activity)context).findViewById(R.id.player_two_result_textview);
        TextView winnerPlayerTextView = ((Activity)context).findViewById(R.id.winner_player_textview);
        playerOneTextView.setText(""+uiConfig.getPlayersNames()[0]+"\n"+ uiConfig.getPlayersTotalScore(1));
        playerTwoTextView.setText(""+uiConfig.getPlayersNames()[1]+"\n"+ uiConfig.getPlayersTotalScore(2));
        winnerPlayerTextView.setText("ZWYCIĘZCĄ JEST:"+"\n"+winnerPlayer);


    }
}
