package com.paweldyjak.dicegame.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.paweldyjak.dicegame.*;


public class TitleScreen extends Fragment {
    private final MainActivity mainActivity;
    private final PlayerNamesInputScreen playerNamesInputScreen;
    private Button multiplayerButton;
    private Button exitButton;
    private Button twoPlayersButton;
    private View playersNumberLayout;

    public TitleScreen(MainActivity mainActivity, PlayerNamesInputScreen playerNamesInputScreen) {
        this.mainActivity = mainActivity;
        this.playerNamesInputScreen = playerNamesInputScreen;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.title_screen_fragment, container, false);
        multiplayerButton = view.findViewById(R.id.multiplayer_button);
        exitButton = view.findViewById(R.id.exit_button);
        twoPlayersButton = view.findViewById(R.id.two_players_button);
        playersNumberLayout = view.findViewById(R.id.players_number_layout);
        setButtons();

        return view;
    }

    public void setButtons() {
        multiplayerButton.setOnClickListener(v -> {
            playersNumberLayout.setVisibility(View.VISIBLE);
            multiplayerButton.setEnabled(false);
        });
        twoPlayersButton.setOnClickListener(v -> mainActivity.replaceFragment(R.id.fragment_layout, playerNamesInputScreen));
        exitButton.setOnClickListener(v -> System.exit(0));


    }
}
