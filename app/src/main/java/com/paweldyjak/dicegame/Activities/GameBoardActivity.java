package com.paweldyjak.dicegame.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.firebase.FirebaseApp;
import com.paweldyjak.dicegame.*;
import com.paweldyjak.dicegame.Fragments.*;
import com.paweldyjak.dicegame.GameModes.*;

import java.util.Objects;


public class GameBoardActivity extends AppCompatActivity {
    private PlayerNamesInputScreenFragment playerNamesInputScreenFragment;
    private TurnScreenFragment turnScreenFragment;
    private MultiplayerTurnScreenFragment multiplayerTurnScreenFragment;
    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction fragmentTransaction;
    private View mainBoardLayout;
    private boolean multiplayerMode;
    private OpponentUIConfig opponentUIConfig;
    private boolean isSoundOn;
    private boolean isCombinationsHighlightOn;
    private boolean isBlockConfirmationOn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int numberOfPlayers = getIntent().getIntExtra("numberOfPlayers", 0);
        multiplayerMode = getIntent().getBooleanExtra("MultiplayerMode", false);
        isSoundOn = getIntent().getBooleanExtra("isSoundOn", true);
        isCombinationsHighlightOn = getIntent().getBooleanExtra("isCombinationsHighlightOn", true);
        isBlockConfirmationOn = getIntent().getBooleanExtra("isBlockingConfirmationOn", false);
        String[] playersNames = getIntent().getStringArrayExtra("playersNames");
        String opponentUid = getIntent().getStringExtra("opponentUid");
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_game_board);
        playerNamesInputScreenFragment = new PlayerNamesInputScreenFragment(this, numberOfPlayers);
        mainBoardLayout = findViewById(R.id.game_board_screen_layout);
        //hides status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hides title bar
        Objects.requireNonNull(getSupportActionBar()).hide();
        if (multiplayerMode) {
            startMultiplayerGame(playersNames, opponentUid);
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
        if (multiplayerMode) {
            multiplayerTurnScreenFragment.setNextPlayerName();
        } else {
            turnScreenFragment.displayTurnMessage();
        }
    }


    //disable back button
    @Override
    public void onBackPressed() {

    }

    public void startHotSeatGame(String[] playersNames, int numberOfPlayers) {
        //creating class objects
        UIConfig uiConfig = new UIConfig(this);
        HotSeatGame hotSeatGame = new HotSeatGame(this, playersNames);
        DicesCombinationsChecker dicesCombinationsChecker = new DicesCombinationsChecker(hotSeatGame, uiConfig);
        GameBoardManager gameBoardManager = new GameBoardManager(this, dicesCombinationsChecker, uiConfig, hotSeatGame, null);
        turnScreenFragment = new TurnScreenFragment(this, hotSeatGame, gameBoardManager);
        //configuring UI
        uiConfig.setComponents();
        uiConfig.getCurrentPlayerName().setText(playersNames[0]);
        hotSeatGame.setPlayersNames(playersNames);
        hotSeatGame.setNumberOfPlayers(numberOfPlayers);
        hotSeatGame.setAllCombinationsAsActive();
        gameBoardManager.setRollDicesButton();
        replaceFragment(R.id.fragment_layout, turnScreenFragment);
        showFragment();
    }

    public void startMultiplayerGame(String[] playersNames, String opponentUid) {
        //creating class objects
        UIConfig uiConfig = new UIConfig(this);
        MultiplayerGame multiplayerGame = new MultiplayerGame(this, playersNames, opponentUid);
        DicesCombinationsChecker dicesCombinationsChecker = new DicesCombinationsChecker(multiplayerGame, uiConfig);
        GameBoardManager gameBoardManager = new GameBoardManager(this, dicesCombinationsChecker, uiConfig, multiplayerGame, opponentUid);
        multiplayerTurnScreenFragment = new MultiplayerTurnScreenFragment(this, uiConfig, multiplayerGame, gameBoardManager, opponentUid);
        this.opponentUIConfig = new OpponentUIConfig(this, uiConfig, multiplayerGame, gameBoardManager, opponentUid);
        //configuring UI
        uiConfig.setComponents();
        uiConfig.setCurrentPlayerName(playersNames[0]);
        multiplayerGame.setPlayersNames(playersNames);
        multiplayerGame.setNumberOfPlayers(2);
        multiplayerGame.setStartBoardValues();
        gameBoardManager.setRollDicesButton();
        replaceFragment(R.id.fragment_layout, multiplayerTurnScreenFragment);
        showFragment();
    }

    public void startTrainingGame(String playerName){
        String[] playersNames = {playerName, "Training"};
        UIConfig uiConfig = new UIConfig(this);
        TrainingGame trainingGame = new TrainingGame(this);
        DicesCombinationsChecker dicesCombinationsChecker = new DicesCombinationsChecker(trainingGame, uiConfig);
        GameBoardManager gameBoardManager = new GameBoardManager(this, dicesCombinationsChecker, uiConfig, trainingGame, null);
        turnScreenFragment = new TurnScreenFragment(this, trainingGame, gameBoardManager);
        uiConfig.setComponents();
        uiConfig.getCurrentPlayerName().setText(playerName);
        trainingGame.setPlayersNames(playersNames);
        trainingGame.setNumberOfPlayers(2);
        trainingGame.setAllCombinationsAsActive();
        hideFragment();

    }

    public OpponentUIConfig getOpponentOnlineUIConfig() {
        return opponentUIConfig;
    }

    public boolean getIsSoundOn() {
        return isSoundOn;
    }


    public boolean isBlockConfirmationOn() {
        return isBlockConfirmationOn;
    }

    public boolean isCombinationsHighlightOn() {
        return isCombinationsHighlightOn;
    }
}