package com.paweldyjak.dicegame.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.paweldyjak.dicegame.*;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.GameModes.GameMode;
import com.paweldyjak.dicegame.GameModes.HotSeatGame;

public class TurnScreenFragment extends Fragment {
    private final UIConfig uiConfig;
    private TextView playerName;
    private Button nextPlayerButton;
    private final GameBoardActivity gameBoardActivity;
    private final GameMode gameMode;

    public TurnScreenFragment(GameBoardActivity gameBoardActivity, UIConfig uiConfig, GameMode gameMode) {
        this.uiConfig = uiConfig;
        this.gameBoardActivity = gameBoardActivity;
        this.gameMode = gameMode;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_turn_screen, container, false);
        playerName = view.findViewById(R.id.player_turn_textview);
        nextPlayerButton = view.findViewById(R.id.player_turn_button);
        displayTurnMessage();
        return view;
    }

    public void displayTurnMessage() {
        //update next player name
        switch (gameMode.getCurrentPlayerNumber()) {
            case 1:
                playerName.setText(gameMode.getPlayersNames()[1]);
                gameMode.setCurrentPlayerNumber(2);
                break;
            case 2:
                if (gameMode.getNumberOfPlayers() > 2) {
                    playerName.setText(gameMode.getPlayersNames()[2]);
                    gameMode.setCurrentPlayerNumber(3);
                } else {
                    playerName.setText(gameMode.getPlayersNames()[0]);
                    gameMode.setCurrentPlayerNumber(1);
                }
                break;
            case 3:
                if (gameMode.getNumberOfPlayers() > 3) {
                    playerName.setText(gameMode.getPlayersNames()[3]);
                    gameMode.setCurrentPlayerNumber(4);
                } else {
                    playerName.setText(gameMode.getPlayersNames()[0]);
                    gameMode.setCurrentPlayerNumber(1);
                }
                break;
            case 4:
                if (gameMode.getNumberOfPlayers() > 4) {
                    playerName.setText(gameMode.getPlayersNames()[4]);
                    gameMode.setCurrentPlayerNumber(5);
                } else {
                    playerName.setText(gameMode.getPlayersNames()[0]);
                    gameMode.setCurrentPlayerNumber(1);
                }
                break;
            case 5:
                if (gameMode.getNumberOfPlayers() > 5) {
                    playerName.setText(gameMode.getPlayersNames()[5]);
                    gameMode.setCurrentPlayerNumber(6);
                } else {
                    playerName.setText(gameMode.getPlayersNames()[0]);
                    gameMode.setCurrentPlayerNumber(1);
                }
                break;
            case 6:
                playerName.setText(gameMode.getPlayersNames()[0]);
                gameMode.setCurrentPlayerNumber(1);
                break;
        }
        ((HotSeatGame)gameMode).prepareCombinationsSlots();
        nextPlayerButton.setOnClickListener(v -> {
            int playerNumber = gameMode.getCurrentPlayerNumber() - 1;
            uiConfig.getCurrentPlayerName().setText((gameMode.getPlayersNames()[playerNumber]));
            gameMode.updateGameBoard();
            gameBoardActivity.hideFragment();

        });
    }



}
