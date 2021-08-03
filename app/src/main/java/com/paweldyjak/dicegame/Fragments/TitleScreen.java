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
    private Button startGameButton;

    public TitleScreen(MainActivity mainActivity, PlayerNamesInputScreen playerNamesInputScreen) {
        this.mainActivity = mainActivity;
        this.playerNamesInputScreen = playerNamesInputScreen;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.title_screen_fragment, container, false);
        startGameButton = view.findViewById(R.id.twoPlayersButton);
        setTwoPlayersButton();
        return view;
    }

    public void setTwoPlayersButton() {
        startGameButton.setOnClickListener(v -> mainActivity.replaceFragment(R.id.fragment_layout, playerNamesInputScreen));




    }
}
