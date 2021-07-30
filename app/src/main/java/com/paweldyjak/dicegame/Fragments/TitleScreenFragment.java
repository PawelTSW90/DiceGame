package com.paweldyjak.dicegame.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.paweldyjak.dicegame.MainActivity;
import com.paweldyjak.dicegame.R;


public class TitleScreenFragment extends Fragment {
    MainActivity mainActivity;
    PlayerNamesInputScreenFragment playerNamesInputScreenFragment;
    View view;
    Button twoPlayersButton;

    public TitleScreenFragment(MainActivity mainActivity, PlayerNamesInputScreenFragment playerNamesInputScreenFragment) {
        this.mainActivity = mainActivity;
        this.playerNamesInputScreenFragment = playerNamesInputScreenFragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.title_screen_fragment, container, false);
        setTwoPlayersButton();
        return view;
    }

    public void setTwoPlayersButton() {
        twoPlayersButton = view.findViewById(R.id.twoPlayersButton);
        twoPlayersButton.setOnClickListener(v -> mainActivity.setFragment(playerNamesInputScreenFragment));


    }
}
