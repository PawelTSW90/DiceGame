package com.paweldyjak.dicegame.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.CheckBox;

import com.paweldyjak.dicegame.*;

import java.util.Objects;

public class MainMenuSettingsActivity extends AppCompatActivity {
    private MainMenuActivity mainMenuActivity;
    private CheckBox soundsCheckBox;
    private CheckBox combinationsHighlightCheckBox;
    private CheckBox blockConfirmationCheckBox;
    public final String userSettingsPref = "userSettingsPref";
    public final String soundPref = "soundPref";
    public final String highlightPref = "highlightPref";
    public final String blockConfirmPref = "blockConfirmPref";
    private boolean isSoundOn = true;
    private boolean isCombinationsHighlightOn = true;
    private boolean isBlockConfirmationOn = false;


    public MainMenuSettingsActivity() {

    }

    public MainMenuSettingsActivity(MainMenuActivity mainMenuActivity) {
        this.mainMenuActivity = mainMenuActivity;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_settings);
        //hides status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hides title bar
        Objects.requireNonNull(getSupportActionBar()).hide();
        soundsCheckBox = findViewById(R.id.sounds_checkBox);
        combinationsHighlightCheckBox = findViewById(R.id.highlight_checkBox);
        blockConfirmationCheckBox = findViewById(R.id.blockConfirm_checkBox);
        setButtons();
        loadSettings();
        updateSettings();

    }

    public void setButtons() {

        soundsCheckBox.setOnClickListener(v -> saveSettings());
        combinationsHighlightCheckBox.setOnClickListener(v -> saveSettings());
        blockConfirmationCheckBox.setOnClickListener(v -> saveSettings());
    }

    public void saveSettings() {
        SharedPreferences sharedPreferences = getSharedPreferences(userSettingsPref, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(soundPref, soundsCheckBox.isChecked());
        editor.putBoolean(highlightPref, combinationsHighlightCheckBox.isChecked());
        editor.putBoolean(blockConfirmPref, blockConfirmationCheckBox.isChecked());
        editor.apply();
        loadSettings();
    }

    public void loadSettings() {
        SharedPreferences sharedPreferences = getSharedPreferences(userSettingsPref, MODE_PRIVATE);
        isSoundOn = sharedPreferences.getBoolean(soundPref, true);
        isCombinationsHighlightOn = sharedPreferences.getBoolean(highlightPref, true);
        isBlockConfirmationOn = sharedPreferences.getBoolean(blockConfirmPref, true);
        updateSettings();
    }

    public void updateSettings() {
        soundsCheckBox.setChecked(isSoundOn);
        combinationsHighlightCheckBox.setChecked(isCombinationsHighlightOn);
        blockConfirmationCheckBox.setChecked(isBlockConfirmationOn);
        Intent intent = new Intent();
        intent.putExtra("soundPref", isSoundOn);
        intent.putExtra("highlightPref", isCombinationsHighlightOn);
        intent.putExtra("blockConfirmPref", isBlockConfirmationOn);
        setResult(RESULT_OK, intent);
    }


}