package com.paweldyjak.dicegame;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

import com.paweldyjak.dicegame.layouts.PlayerNamesInputScreen;
import com.paweldyjak.dicegame.layouts.TitleScreen;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_screen);
        PlayerNamesInputScreen playerNamesInputScreen = new PlayerNamesInputScreen(this);
        TitleScreen titleScreen = new TitleScreen(this, playerNamesInputScreen);

        //hides status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hides title bar
        Objects.requireNonNull(getSupportActionBar()).hide();
    }

    public void startTwoPlayersGame(String[] playersNames) {
        setContentView(R.layout.game_board_screen);

        //creating class objects
        UIConfig uiConfig = new UIConfig(this, playersNames);
        RerollDices rerollDices = new RerollDices(this, uiConfig);
        DicesCombinationsChecker dicesCombinationsChecker = new DicesCombinationsChecker(uiConfig);
        ScoreInput scoreInput = new ScoreInput(uiConfig);
        Dices dices = new Dices(this, scoreInput, dicesCombinationsChecker, uiConfig, rerollDices);

        //configuring UI
        uiConfig.setDicesSlots();
        uiConfig.setDicesCombinations();
        uiConfig.setAllCombinationsAsActive();
        dices.setRollDicesButton();
        uiConfig.setPlayersNames(playersNames);
        uiConfig.setPlayerTurnWindow();


    }


}