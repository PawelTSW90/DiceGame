package com.paweldyjak.dicegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.WindowManager;
import com.paweldyjak.dicegame.Fragments.PlayerNamesInputScreenFragment;
import com.paweldyjak.dicegame.Fragments.TitleScreenFragment;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PlayerNamesInputScreenFragment playerNamesInputScreenFragment = new PlayerNamesInputScreenFragment(this);
        TitleScreenFragment titleScreenFragment = new TitleScreenFragment(this, playerNamesInputScreenFragment);

        //hides status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hides title bar
        Objects.requireNonNull(getSupportActionBar()).hide();
        setFragment(titleScreenFragment);
    }

    public void setFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_game_layout, fragment);
        fragmentTransaction.commit();
    }

    public void startTwoPlayersGame(String[] playersNames) {
        setContentView(R.layout.activity_main);
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