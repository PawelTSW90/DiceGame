package com.paweldyjak.dicegame.Fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.paweldyjak.dicegame.*;

public class PlayerTurnScreen extends Fragment {
    private final UIConfig uiConfig;
    private TextView playerName;
    private Button nextPlayerButton;
    private final MainActivity mainActivity;

    public PlayerTurnScreen(MainActivity mainActivity, UIConfig uiConfig){
        this.uiConfig = uiConfig;
        this.mainActivity = mainActivity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player_turn_screen_fragment, container, false);
        playerName = view.findViewById(R.id.player_turn_textview);
        nextPlayerButton = view.findViewById(R.id.player_turn_button);
        displayTurnMessage();
        return view;
    }

    public void displayTurnMessage(){
        if (uiConfig.getPlayerNumber() == 1) {
            playerName.setText("" + uiConfig.getPlayersNames()[1]);
        } else {
            playerName.setText("" + uiConfig.getPlayersNames()[0]);
        }
        nextPlayerButton.setOnClickListener(v -> {
            if (uiConfig.getPlayerNumber() == 1) {
                uiConfig.prepareScoreBoard();
                uiConfig.getCurrentPlayerName().setText((""+ uiConfig.getPlayersNames()[0]));
                mainActivity.showFragment(false);


            } else {
                uiConfig.prepareScoreBoard();

                uiConfig.getCurrentPlayerName().setText((""+ uiConfig.getPlayersNames()[1]));
                mainActivity.showFragment(false);

            }

        });
        //update player number
        if (uiConfig.getPlayerNumber() == 1) {
            uiConfig.setPlayerNumber(2);

        } else {
            uiConfig.setPlayerNumber(1);

        }
        uiConfig.prepareCombinationsSlots();
    }

}
