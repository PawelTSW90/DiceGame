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

public class StartHotSeatGameFragment extends Fragment {
    private final GameBoardActivity gameBoardActivity;
    private final String[] playersNames;
    private final int numberOfPlayers;

    public StartHotSeatGameFragment(GameBoardActivity gameBoardActivity, String[] playersNames, int numberOfPlayers) {
        this.gameBoardActivity = gameBoardActivity;
        this.playersNames = playersNames;
        this.numberOfPlayers = numberOfPlayers;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_game_screen, container, false);
        Button startGameButton = view.findViewById(R.id.start_game_button);
        TextView playerName = view.findViewById(R.id.start_game_textview);
        playerName.setText(playersNames[0]);
        startGameButton.setOnClickListener(v -> gameBoardActivity.startHotSeatGame(playersNames, numberOfPlayers));
        return view;
    }





}
