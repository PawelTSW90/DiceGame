package com.paweldyjak.dicegame.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.paweldyjak.dicegame.*;
import com.paweldyjak.dicegame.Activities.MainActivity;

public class PlayerTurnScreen extends Fragment {
    private final UIConfig uiConfig;
    private TextView playerName;
    private Button nextPlayerButton;
    private final MainActivity mainActivity;

    public PlayerTurnScreen(MainActivity mainActivity, UIConfig uiConfig) {
        this.uiConfig = uiConfig;
        this.mainActivity = mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player_turn_screen_fragment, container, false);
        playerName = view.findViewById(R.id.player_turn_textview);
        nextPlayerButton = view.findViewById(R.id.player_turn_button);
        displayTurnMessage();
        return view;
    }

    public void displayTurnMessage() {
        //update next player name
        switch (uiConfig.getCurrentPlayerNumber()) {
            case 1:
                playerName.setText(uiConfig.getPlayersNames()[1]);
                break;
            case 2:
                if (uiConfig.getNumberOfPlayers() > 2) {
                    playerName.setText(uiConfig.getPlayersNames()[2]);
                } else {
                    playerName.setText(uiConfig.getPlayersNames()[0]);
                }
                break;
            case 3:
                if (uiConfig.getNumberOfPlayers() > 3) {
                    playerName.setText(uiConfig.getPlayersNames()[3]);
                } else {
                    playerName.setText(uiConfig.getPlayersNames()[0]);
                }
                break;
            case 4:
                if (uiConfig.getNumberOfPlayers() > 4) {
                    playerName.setText(uiConfig.getPlayersNames()[4]);
                } else {
                    playerName.setText(uiConfig.getPlayersNames()[0]);
                }
                break;
            case 5:
                if (uiConfig.getNumberOfPlayers() > 5) {
                    playerName.setText(uiConfig.getPlayersNames()[5]);
                } else {
                    playerName.setText(uiConfig.getPlayersNames()[0]);
                }
                break;
            case 6:
                playerName.setText(uiConfig.getPlayersNames()[0]);
                break;
        }
        nextPlayerButton.setOnClickListener(v -> {
            switch (uiConfig.getCurrentPlayerNumber()) {
                case 1:
                    uiConfig.getCurrentPlayerName().setText((uiConfig.getPlayersNames()[0]));
                    break;
                case 2:
                    uiConfig.getCurrentPlayerName().setText((uiConfig.getPlayersNames()[1]));
                    break;
                case 3:
                    uiConfig.getCurrentPlayerName().setText((uiConfig.getPlayersNames()[2]));
                    break;
                case 4:
                    uiConfig.getCurrentPlayerName().setText((uiConfig.getPlayersNames()[3]));
                    break;
                case 5:
                    uiConfig.getCurrentPlayerName().setText((uiConfig.getPlayersNames()[4]));
                    break;
                case 6:
                    uiConfig.getCurrentPlayerName().setText((uiConfig.getPlayersNames()[5]));
                    break;
            }
            uiConfig.prepareScoreBoard();
            mainActivity.showFragment(false);

        });
        //update player number
        switch (uiConfig.getCurrentPlayerNumber()) {
            case 1:
                uiConfig.setCurrentPlayerNumber(2);
                break;
            case 2:
                if (uiConfig.getNumberOfPlayers() > 2) {
                    uiConfig.setCurrentPlayerNumber(3);
                } else {
                    uiConfig.setCurrentPlayerNumber(1);
                }
                break;
            case 3:
                if (uiConfig.getNumberOfPlayers() > 3) {
                    uiConfig.setCurrentPlayerNumber(4);
                } else {
                    uiConfig.setCurrentPlayerNumber(1);
                }
                break;
            case 4:
                if (uiConfig.getNumberOfPlayers() > 4) {
                    uiConfig.setCurrentPlayerNumber(5);
                } else {
                    uiConfig.setCurrentPlayerNumber(1);
                }
                break;
            case 5:
                if (uiConfig.getNumberOfPlayers() > 5) {
                    uiConfig.setCurrentPlayerNumber(6);
                } else {
                    uiConfig.setCurrentPlayerNumber(1);
                }
                break;
            case 6:
                uiConfig.setCurrentPlayerNumber(1);

                break;
        }
        uiConfig.prepareCombinationsSlots();
    }

}
