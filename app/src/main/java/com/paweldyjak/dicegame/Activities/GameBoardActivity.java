package com.paweldyjak.dicegame.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.FirebaseApp;
import com.paweldyjak.dicegame.DicesCombinationsChecker;
import com.paweldyjak.dicegame.Fragments.*;
import com.paweldyjak.dicegame.GameBoardManager;
import com.paweldyjak.dicegame.GameModes.HotSeatGame;
import com.paweldyjak.dicegame.GameModes.MultiplayerGame;
import com.paweldyjak.dicegame.R;
import com.paweldyjak.dicegame.RerollDices;
import com.paweldyjak.dicegame.ScoreInputSetter;
import com.paweldyjak.dicegame.UIConfig;

import java.util.Objects;


public class GameBoardActivity extends AppCompatActivity {
    private PlayerNamesInputScreenFragment playerNamesInputScreenFragment;
    private PlayerTurnScreenFragment playerTurnScreenFragment;
    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction fragmentTransaction;
    private View mainBoardLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int numberOfPlayers = getIntent().getIntExtra("numberOfPlayers", 0);
        boolean multiplayerMode = getIntent().getBooleanExtra("MultiplayerMode", false);
        String[] playersNames = getIntent().getStringArrayExtra("playersNames");
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_game_board);
        playerNamesInputScreenFragment = new PlayerNamesInputScreenFragment(this, numberOfPlayers);
        mainBoardLayout = findViewById(R.id.game_board_screen_layout);
        //hides status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hides title bar
        Objects.requireNonNull(getSupportActionBar()).hide();
        AdView mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        if (multiplayerMode) {
            StartGameScreenFragment startGameScreenFragment = new StartGameScreenFragment(this, playersNames, 2, true);
            replaceFragment(R.id.fragment_layout, startGameScreenFragment);
            showFragment();
        } else {
            showNamesInputFragment();
        }


    }

    public void showNamesInputFragment() {
        showFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_layout, playerNamesInputScreenFragment);
        fragmentTransaction.commit();


    }

    public void replaceFragment(int layout, Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(layout, fragment);
        fragmentTransaction.commit();
    }

    public void showFragment() {
        View fragmentLayout = findViewById(R.id.fragment_layout);
        mainBoardLayout.setVisibility(View.INVISIBLE);
        fragmentLayout.setVisibility(View.VISIBLE);
    }


    public void hideFragment() {
        View fragmentLayout = findViewById(R.id.fragment_layout);
        fragmentLayout.setVisibility(View.INVISIBLE);
        mainBoardLayout.setVisibility(View.VISIBLE);

    }

    public void showNextTurnFragment() {
        View fragmentLayout = findViewById(R.id.fragment_layout);
        mainBoardLayout.setVisibility(View.INVISIBLE);
        fragmentLayout.setVisibility(View.VISIBLE);
        playerTurnScreenFragment.displayTurnMessage();
    }


    //disable back button
    @Override
    public void onBackPressed() {

    }

    public void startHotSeatGame(String[] playersNames, int numberOfPlayers) {
        //creating class objects
        UIConfig uiConfig = new UIConfig(this);
        HotSeatGame hotSeatGame = new HotSeatGame(uiConfig, this, playersNames);
        RerollDices rerollDices = new RerollDices(uiConfig);
        playerTurnScreenFragment = new PlayerTurnScreenFragment(this, uiConfig, hotSeatGame);
        DicesCombinationsChecker dicesCombinationsChecker = new DicesCombinationsChecker(hotSeatGame);
        ScoreInputSetter scoreInputSetter = new ScoreInputSetter(this, uiConfig, hotSeatGame);
        GameBoardManager gameBoardManager = new GameBoardManager(this, this, scoreInputSetter, dicesCombinationsChecker, uiConfig, rerollDices, hotSeatGame);
        //configuring UI
        uiConfig.setComponents();
        uiConfig.getCurrentPlayerName().setText(playersNames[0]);
        hotSeatGame.setPlayersNames(playersNames);
        hotSeatGame.setNumberOfPlayers(numberOfPlayers);
        hotSeatGame.setAllCombinationsAsActive();
        gameBoardManager.setRollDicesButton();
        replaceFragment(R.id.fragment_layout, playerTurnScreenFragment);
        this.hideFragment();
    }

    public void startMultiplayerGame(String[] playersNames) {
        //creating class objects
        UIConfig uiConfig = new UIConfig(this);
        MultiplayerGame multiplayerGame = new MultiplayerGame(uiConfig, this, playersNames);
        RerollDices rerollDices = new RerollDices(uiConfig);
        playerTurnScreenFragment = new PlayerTurnScreenFragment(this, uiConfig, multiplayerGame);
        DicesCombinationsChecker dicesCombinationsChecker = new DicesCombinationsChecker(multiplayerGame);
        ScoreInputSetter scoreInputSetter = new ScoreInputSetter(this, uiConfig, multiplayerGame);
        GameBoardManager gameBoardManager = new GameBoardManager(this, this, scoreInputSetter, dicesCombinationsChecker, uiConfig, rerollDices, multiplayerGame);
        //configuring UI
        uiConfig.setComponents();
        uiConfig.getCurrentPlayerName().setText(playersNames[0]);
        multiplayerGame.setPlayersNames(playersNames);
        multiplayerGame.setNumberOfPlayers(2);
        multiplayerGame.setAllCombinationsAsActive();
        gameBoardManager.setRollDicesButton();
        replaceFragment(R.id.fragment_layout, playerTurnScreenFragment);
        this.hideFragment();
    }

}