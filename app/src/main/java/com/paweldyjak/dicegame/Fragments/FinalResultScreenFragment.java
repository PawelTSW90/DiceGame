package com.paweldyjak.dicegame.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.paweldyjak.dicegame.R;
import com.paweldyjak.dicegame.UIConfig;

public class FinalResultScreenFragment extends Fragment {
    View view;
    Context context;
    UIConfig uiConfig;

    public FinalResultScreenFragment(Context context, UIConfig uiConfig){
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
        ((Activity) context).setContentView(R.layout.final_result_screen_fragment);
        TextView playerOneTextView = ((Activity)context).findViewById(R.id.player_one_result_textview);
        TextView playerTwoTextView = ((Activity)context).findViewById(R.id.player_two_result_textview);
        TextView winnerPlayerTextView = ((Activity)context).findViewById(R.id.winner_player_textview);
        playerOneTextView.setText(""+uiConfig.getPlayersNames()[0]+"\n"+ uiConfig.getPlayersTotalScore(1));
        playerTwoTextView.setText(""+uiConfig.getPlayersNames()[1]+"\n"+ uiConfig.getPlayersTotalScore(2));
        winnerPlayerTextView.setText("ZWYCIĘZCĄ JEST:"+"\n"+winnerPlayer);


    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.final_result_screen_fragment, container, false);
        return view;
    }
}
