package com.paweldyjak.dicegame.Fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.paweldyjak.dicegame.MainActivity;
import com.paweldyjak.dicegame.R;
import com.paweldyjak.dicegame.UIConfig;

public class PlayerTurnScreen extends Fragment {
    UIConfig uiConfig;
    TextView playerNameTextView;
    Button nextPlayerButton;
    MainActivity mainActivity;

    public PlayerTurnScreen(MainActivity mainActivity, UIConfig uiConfig){
        this.uiConfig = uiConfig;
        this.mainActivity = mainActivity;
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.player_turn_screen_fragment, container, false);
        playerNameTextView = view.findViewById(R.id.player_turn_textview);
        nextPlayerButton = view.findViewById(R.id.player_turn_button);
        displayTurnMessage();
        return view;
    }

    public void displayTurnMessage(){
        if (uiConfig.getPlayerNumber() == 1) {
            playerNameTextView.setText("" + uiConfig.getPlayersNames()[1]);
        } else {
            playerNameTextView.setText("" + uiConfig.getPlayersNames()[0]);
        }
        nextPlayerButton.setOnClickListener(v -> {
            if (uiConfig.getPlayerNumber() == 1) {

                uiConfig.prepareScreenForPlayer();
                uiConfig.getPlayerNameBoardTextView().setText((""+ uiConfig.getPlayersNames()[0]));
                mainActivity.showPlayerTurnScreen(false);


            } else {
                uiConfig.prepareScreenForPlayer();
                uiConfig.getPlayerNameBoardTextView().setText((""+ uiConfig.getPlayersNames()[1]));
                mainActivity.showPlayerTurnScreen(false);

            }

        });
        if (uiConfig.getPlayerNumber() == 1) {
            uiConfig.setPlayerNumber(2);
        } else {
            uiConfig.setPlayerNumber(1);

        }
    }

}
