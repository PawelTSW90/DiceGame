package com.paweldyjak.dicegame.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.Activities.MainMenuActivity;
import com.paweldyjak.dicegame.R;
import com.paweldyjak.dicegame.Sounds;

public class GameSettingsFragment extends Fragment {
    private final GameBoardActivity gameBoardActivity;
    private final Sounds sounds;
    private TextView backButton;
    private TextView combinationsChart;
    private TextView exit;
    private Button exitYesButton;
    private Button exitNoButton;
    private SwitchCompat soundSwitch;
    private SwitchCompat combinationHighlightSwitch;
    private SwitchCompat crossOutCombinationSwitch;
    private ConstraintLayout exitQuestionLayout;

    public GameSettingsFragment(GameBoardActivity gameBoardActivity) {
        this.gameBoardActivity = gameBoardActivity;
        sounds = new Sounds(gameBoardActivity);
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
        soundSwitch = view.findViewById(R.id.gameMenu_sounds_switch);
        combinationHighlightSwitch = view.findViewById(R.id.gameMenu_combinationsHighlighter_switch);
        crossOutCombinationSwitch = view.findViewById(R.id.gameMenu_crossOutCombination_switch);
        getSettings();
        setButtons();
        return view;
    }

    public void setButtons() {
        CombinationsChartFragment combinationsChartFragment = new CombinationsChartFragment(gameBoardActivity);
        backButton.setOnClickListener(v -> {
            sounds.playTickSound();
            saveSettings();
            gameBoardActivity.removeFragment(this);
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
            saveSettings();
            Intent intent = new Intent(gameBoardActivity.getApplicationContext(), MainMenuActivity.class);
            gameBoardActivity.startActivity(intent);
            gameBoardActivity.finish();
        });
        exitNoButton.setOnClickListener(v -> {
            sounds.playTickSound();
            exitQuestionLayout.setVisibility(View.INVISIBLE);

        });
    }

    public void getSettings(){
        boolean isSoundOn = gameBoardActivity.isSoundOn();
        boolean isCombinationsHighlightOn = gameBoardActivity.isCombinationsHighlightOn();
        boolean isCrossOutConfirmationOn = gameBoardActivity.isCrossOutCombinationOn();
        soundSwitch.setChecked(isSoundOn);
        combinationHighlightSwitch.setChecked(isCombinationsHighlightOn);
        crossOutCombinationSwitch.setChecked(isCrossOutConfirmationOn);
    }

    public void saveSettings() {
        String userSettingsPref = "userSettingsPref";
        SharedPreferences sharedPreferences = gameBoardActivity.getSharedPreferences(userSettingsPref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String soundPref = "soundPref";
        editor.putBoolean(soundPref, soundSwitch.isChecked());
        String highlightPref = "highlightPref";
        editor.putBoolean(highlightPref, combinationHighlightSwitch.isChecked());
        String crossOutConfirmPref = "crossOutConfirmPref";
        editor.putBoolean(crossOutConfirmPref, crossOutCombinationSwitch.isChecked());
        editor.apply();
        gameBoardActivity.setSoundOn(soundSwitch.isChecked());
        gameBoardActivity.setCombinationsHighlightOn(combinationHighlightSwitch.isChecked());
        gameBoardActivity.setCrossOutCombinationOn(crossOutCombinationSwitch.isChecked());
        gameBoardActivity.updateSoundSettings();
    }


}
