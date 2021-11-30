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
import com.paweldyjak.dicegame.DicesCombinationsChecker;
import com.paweldyjak.dicegame.GameBoardManager;
import com.paweldyjak.dicegame.R;
import com.paweldyjak.dicegame.Sounds;
import com.paweldyjak.dicegame.UIConfig;

public class GameSettingsFragment extends Fragment {
    private final GameBoardActivity gameBoardActivity;
    private final GameBoardManager gameBoardManager;
    private final DicesCombinationsChecker dicesCombinationsChecker;
    private final UIConfig uiConfig;
    private Sounds sounds;
    private TextView backButton;
    private TextView combinationsChart;
    private TextView exit;
    private Button exitYesButton;
    private Button exitNoButton;
    private SwitchCompat soundSwitch;
    private SwitchCompat combinationHighlightSwitch;
    private SwitchCompat crossOutCombinationSwitch;
    private ConstraintLayout exitQuestionLayout;

    public GameSettingsFragment(GameBoardActivity gameBoardActivity, GameBoardManager gameBoardManager, DicesCombinationsChecker dicesCombinationsChecker, UIConfig uiConfig) {
        this.gameBoardActivity = gameBoardActivity;
        this.gameBoardManager = gameBoardManager;
        this.dicesCombinationsChecker = dicesCombinationsChecker;
        this.uiConfig = uiConfig;
        sounds = new Sounds(gameBoardActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_settings, container, false);
        backButton = view.findViewById(R.id.gameMenu_backButton);
        exit = view.findViewById(R.id.gameMenu_exitGame);
        exitNoButton = view.findViewById(R.id.gameMenu_exit_question_no_button);
        exitYesButton = view.findViewById(R.id.gameMenu_exit_question_yes_button);
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
        if(gameBoardActivity.getNumberOfPlayers()==1){
            combinationHighlightSwitch.setEnabled(false);
            crossOutCombinationSwitch.setEnabled(false);
        }
        soundSwitch.setOnClickListener(v -> saveSettings());
        combinationHighlightSwitch.setOnClickListener(v -> saveSettings());
        crossOutCombinationSwitch.setOnClickListener(v -> saveSettings());

        backButton.setOnClickListener(v -> {
            sounds.playTickSound();
            gameBoardActivity.manageFragments(false, true, this);
        });

        combinationsChart.setOnClickListener(v -> {
            sounds.playTickSound();
            gameBoardActivity.manageFragments(false, true, this);
            gameBoardActivity.manageFragments(true, false, gameBoardActivity.getCombinationsChartFragment());
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

    public void getSettings() {
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
        if (gameBoardActivity.getNumberOfPlayers() > 1) {
            if (combinationHighlightSwitch.isChecked() && gameBoardManager.getThrowNumber() > 0) {
                dicesCombinationsChecker.combinationChecker(gameBoardManager.getDices(), gameBoardManager.getThrowNumber());
            } else if (!combinationHighlightSwitch.isChecked()) {
                uiConfig.combinationHighlighter(0, true);
            }
        }
        sounds = new Sounds(gameBoardActivity);
    }

}
