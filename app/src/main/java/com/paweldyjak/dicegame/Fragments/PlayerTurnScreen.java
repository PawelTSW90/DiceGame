package com.paweldyjak.dicegame.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.paweldyjak.dicegame.*;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.GameModes.HotSeatGame;

public class PlayerTurnScreen extends Fragment {
    private final UIConfig uiConfig;
    private TextView playerName;
    private Button nextPlayerButton;
    private final GameBoardActivity gameBoardActivity;
    private final HotSeatGame hotSeatGame;

    public PlayerTurnScreen(GameBoardActivity gameBoardActivity, UIConfig uiConfig, HotSeatGame hotSeatGame) {
        this.uiConfig = uiConfig;
        this.gameBoardActivity = gameBoardActivity;
        this.hotSeatGame = hotSeatGame;
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
        switch (hotSeatGame.getCurrentPlayerNumber()) {
            case 1:
                playerName.setText(hotSeatGame.getPlayersNames()[1]);
                break;
            case 2:
                if (hotSeatGame.getNumberOfPlayers() > 2) {
                    playerName.setText(hotSeatGame.getPlayersNames()[2]);
                } else {
                    playerName.setText(hotSeatGame.getPlayersNames()[0]);
                }
                break;
            case 3:
                if (hotSeatGame.getNumberOfPlayers() > 3) {
                    playerName.setText(hotSeatGame.getPlayersNames()[3]);
                } else {
                    playerName.setText(hotSeatGame.getPlayersNames()[0]);
                }
                break;
            case 4:
                if (hotSeatGame.getNumberOfPlayers() > 4) {
                    playerName.setText(hotSeatGame.getPlayersNames()[4]);
                } else {
                    playerName.setText(hotSeatGame.getPlayersNames()[0]);
                }
                break;
            case 5:
                if (hotSeatGame.getNumberOfPlayers() > 5) {
                    playerName.setText(hotSeatGame.getPlayersNames()[5]);
                } else {
                    playerName.setText(hotSeatGame.getPlayersNames()[0]);
                }
                break;
            case 6:
                playerName.setText(hotSeatGame.getPlayersNames()[0]);
                break;
        }
        nextPlayerButton.setOnClickListener(v -> {
            int playerNumber = hotSeatGame.getCurrentPlayerNumber() - 1;
            uiConfig.getCurrentPlayerName().setText((hotSeatGame.getPlayersNames()[playerNumber]));
            hotSeatGame.prepareScoreBoard();
            gameBoardActivity.showNewTurnScreen(false);

        });
        //update player number
        switch (hotSeatGame.getCurrentPlayerNumber()) {
            case 1:
                hotSeatGame.setCurrentPlayerNumber(2);
                break;
            case 2:
                if (hotSeatGame.getNumberOfPlayers() > 2) {
                    hotSeatGame.setCurrentPlayerNumber(3);
                } else {
                    hotSeatGame.setCurrentPlayerNumber(1);
                }
                break;
            case 3:
                if (hotSeatGame.getNumberOfPlayers() > 3) {
                    hotSeatGame.setCurrentPlayerNumber(4);
                } else {
                    hotSeatGame.setCurrentPlayerNumber(1);
                }
                break;
            case 4:
                if (hotSeatGame.getNumberOfPlayers() > 4) {
                    hotSeatGame.setCurrentPlayerNumber(5);
                } else {
                    hotSeatGame.setCurrentPlayerNumber(1);
                }
                break;
            case 5:
                if (hotSeatGame.getNumberOfPlayers() > 5) {
                    hotSeatGame.setCurrentPlayerNumber(6);
                } else {
                    hotSeatGame.setCurrentPlayerNumber(1);
                }
                break;
            case 6:
                hotSeatGame.setCurrentPlayerNumber(1);

                break;
        }
        hotSeatGame.prepareCombinationsSlots();
    }

}
