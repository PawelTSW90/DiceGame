package com.paweldyjak.dicegame.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.R;
import com.paweldyjak.dicegame.UIConfig;

public class FinalResultScreen extends Fragment {
    private View view;
    private final Context context;
    private final UIConfig uiConfig;
    private final GameBoardActivity gameBoardActivity;
    TextView playerOne;
    TextView playerTwo;
    TextView winnerPlayer;

    public FinalResultScreen(GameBoardActivity gameBoardActivity, Context context, UIConfig uiConfig) {
        this.context = context;
        this.uiConfig = uiConfig;
        this.gameBoardActivity = gameBoardActivity;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.final_result_screen_fragment, container, false);
        playerOne = view.findViewById(R.id.player_one_result_textview);
        playerTwo = view.findViewById(R.id.player_two_result_textview);
        winnerPlayer = view.findViewById(R.id.winner_player_textview);
        setFinalResultScreen();
        return view;
    }



    public void setFinalResultScreen() {
        String winnerPlayer;
        if (uiConfig.getPlayersTotalScore(1) > uiConfig.getPlayersTotalScore(2)) {
            winnerPlayer = uiConfig.getPlayersNames()[0];
        } else {
            winnerPlayer = uiConfig.getPlayersNames()[1];
        }
        playerOne.setText("" + uiConfig.getPlayersNames()[0] + "\n" + uiConfig.getPlayersTotalScore(1));
        playerTwo.setText("" + uiConfig.getPlayersNames()[1] + "\n" + uiConfig.getPlayersTotalScore(2));
        this.winnerPlayer.setText("ZWYCIĘZCĄ JEST:" + "\n" + winnerPlayer);
        gameBoardActivity.showFragment(true);


    }


}
