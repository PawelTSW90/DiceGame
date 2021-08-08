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
    private Button playButton;
    private Button hotSeatButton;
    private Button exitButton;
    private final Button[] playersNumberButtons = new Button[5];
    private Button backButton;
    private View playersNumberLayout;
    private final Sounds sounds;

    public TitleScreen(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        sounds = new Sounds(mainActivity);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.title_screen_fragment, container, false);
        playButton = view.findViewById(R.id.play_button);
        exitButton = view.findViewById(R.id.exit_button);
        hotSeatButton = view.findViewById(R.id.hotseat_mode_button);
        backButton = view.findViewById(R.id.back_button);
        playersNumberButtons[0] = view.findViewById(R.id.two_players_button);
        playersNumberButtons[1] = view.findViewById(R.id.three_players_button);
        playersNumberButtons[2] = view.findViewById(R.id.four_players_button);
        playersNumberButtons[3] = view.findViewById(R.id.five_players_button);
        playersNumberButtons[4] = view.findViewById(R.id.six_playersButton);
        playersNumberLayout = view.findViewById(R.id.players_number_layout);
        setButtons();

        return view;
    }

    public void setButtons() {

        playButton.setOnClickListener(v -> {
            sounds.playTickSound();
            backButton.setVisibility(View.VISIBLE);
            hotSeatButton.setVisibility(View.VISIBLE);
            playButton.setEnabled(false);
        });

        backButton.setOnClickListener(v -> {
            sounds.playTickSound();
            if (playersNumberLayout.getVisibility() == View.VISIBLE) {
                playersNumberLayout.setVisibility(View.INVISIBLE);
                hotSeatButton.setEnabled(true);
            } else if (hotSeatButton.getVisibility() == View.VISIBLE) {
                hotSeatButton.setVisibility(View.INVISIBLE);
                playButton.setEnabled(true);
                backButton.setVisibility(View.INVISIBLE);
            }
        });

        hotSeatButton.setOnClickListener(v -> {
            sounds.playTickSound();
            playersNumberLayout.setVisibility(View.VISIBLE);
            hotSeatButton.setEnabled(false);
        });
        playersNumberButtons[0].setOnClickListener(v -> {
            PlayerNamesInputScreen playerNamesInputScreen = new PlayerNamesInputScreen(mainActivity, 2);
            sounds.playTickSound();
            mainActivity.replaceFragment(R.id.fragment_layout, playerNamesInputScreen);
        });
        playersNumberButtons[1].setOnClickListener(v -> {
            PlayerNamesInputScreen playerNamesInputScreen = new PlayerNamesInputScreen(mainActivity, 3);
            sounds.playTickSound();
            mainActivity.replaceFragment(R.id.fragment_layout, playerNamesInputScreen);
        });
        playersNumberButtons[2].setOnClickListener(v -> {
            PlayerNamesInputScreen playerNamesInputScreen = new PlayerNamesInputScreen(mainActivity, 4);
            sounds.playTickSound();
            mainActivity.replaceFragment(R.id.fragment_layout, playerNamesInputScreen);
        });
        playersNumberButtons[3].setOnClickListener(v -> {
            PlayerNamesInputScreen playerNamesInputScreen = new PlayerNamesInputScreen(mainActivity, 5);
            sounds.playTickSound();
            mainActivity.replaceFragment(R.id.fragment_layout, playerNamesInputScreen);
        });
        playersNumberButtons[4].setOnClickListener(v -> {
            PlayerNamesInputScreen playerNamesInputScreen = new PlayerNamesInputScreen(mainActivity, 6);
            sounds.playTickSound();
            mainActivity.replaceFragment(R.id.fragment_layout, playerNamesInputScreen);
        });
        exitButton.setOnClickListener(v -> System.exit(0));


    }
}
