package com.paweldyjak.dicegame.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.paweldyjak.dicegame.*;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.GameModes.GameMode;

public class TurnScreenFragment extends Fragment {
    private final UIConfig uiConfig;
    private TextView playerName;
    private Button nextPlayerButton;
    private final GameBoardActivity gameBoardActivity;
    private final GameMode gameMode;
    private final GameBoardManager gameBoardManager;

    public TurnScreenFragment(GameBoardActivity gameBoardActivity, UIConfig uiConfig, GameMode gameMode, GameBoardManager gameBoardManager) {
        this.uiConfig = uiConfig;
        this.gameBoardActivity = gameBoardActivity;
        this.gameMode = gameMode;
        this.gameBoardManager = gameBoardManager;
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
        gameBoardManager.changeCurrentPlayer();
        playerName.setText(gameMode.getPlayersNames()[gameMode.getCurrentPlayerNumber()-1]);
        nextPlayerButton.setOnClickListener(v -> {
            gameBoardManager.updatePlayerBoard();
            gameBoardActivity.hideFragment();

        });
    }

}
