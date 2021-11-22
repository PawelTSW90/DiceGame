package com.paweldyjak.dicegame.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.Activities.MainMenuActivity;
import com.paweldyjak.dicegame.R;
import com.paweldyjak.dicegame.Sounds;

public class GameSettingsFragment extends Fragment {
    private final GameBoardActivity gameBoardActivity;
    private Sounds sounds;
    private TextView backButton;
    private TextView combinationsChart;
    private TextView exit;
    private Button exitYesButton;
    private Button exitNoButton;
    private ConstraintLayout exitQuestionLayout;

    public GameSettingsFragment(GameBoardActivity gameBoardActivity) {
        this.gameBoardActivity = gameBoardActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_settings, container, false);
        backButton = view.findViewById(R.id.gameMenu_backButton);
        exit = view.findViewById(R.id.gameMenu_exitGame);
        exitNoButton = view.findViewById(R.id.gameMenu_exitQuestion_no_button);
        exitYesButton = view.findViewById(R.id.gameMenu_exitQuestion_yes_button);
        exitQuestionLayout = view.findViewById(R.id.gameMenu_exit_question_layout);
        combinationsChart = view.findViewById(R.id.gameMenu_combinationsChart);
        sounds = new Sounds(gameBoardActivity);
        setButtons();
        return view;
    }

    public void setButtons() {
        CombinationsChartFragment combinationsChartFragment = new CombinationsChartFragment(gameBoardActivity);
        backButton.setOnClickListener(v -> {
            sounds.playTickSound();
            gameBoardActivity.hideFragment();
        });

        combinationsChart.setOnClickListener(v -> {
            sounds.playTickSound();
            gameBoardActivity.replaceFragment(R.id.fragment_layout, combinationsChartFragment);
            gameBoardActivity.showFragment();
        });
        exit.setOnClickListener(v -> {
            sounds.playTickSound();
            exitQuestionLayout.setVisibility(View.VISIBLE);
        });
        exitYesButton.setOnClickListener(v -> {
            sounds.playTickSound();
            Intent intent = new Intent(gameBoardActivity.getApplicationContext(), MainMenuActivity.class);
            gameBoardActivity.startActivity(intent);
            gameBoardActivity.finish();
        });
        exitNoButton.setOnClickListener(v -> {
            sounds.playTickSound();
            exitQuestionLayout.setVisibility(View.INVISIBLE);

        });
    }
}
