package com.paweldyjak.dicegame.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import com.google.firebase.FirebaseApp;
import com.paweldyjak.dicegame.*;
import com.paweldyjak.dicegame.Fragments.*;
import com.paweldyjak.dicegame.GameModes.*;
import java.util.Objects;

public class GameBoardActivity extends AppCompatActivity {
    private TurnScreenFragment turnScreenFragment;
    private MultiplayerTurnScreenFragment multiplayerTurnScreenFragment;
    private GameSettingsFragment gameSettingsFragment;
    private CombinationsChartFragment combinationsChartFragment;
    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction fragmentTransaction;
    private boolean multiplayerMode;
    private OpponentUIConfig opponentUIConfig;
    private boolean isSoundOn;
    private boolean isCombinationsHighlightOn;
    private boolean isCrossOutCombinationOn;
    private TrainingGame trainingGame;
    private GameBoardManager gameBoardManager;
    private int numberOfPlayers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        numberOfPlayers = getIntent().getIntExtra("numberOfPlayers", 0);
        multiplayerMode = getIntent().getBooleanExtra("MultiplayerMode", false);
        isSoundOn = getIntent().getBooleanExtra("isSoundOn", true);
        isCombinationsHighlightOn = getIntent().getBooleanExtra("isCombinationsHighlightOn", true);
        isCrossOutCombinationOn = getIntent().getBooleanExtra("isCrossOutConfirmationOn", true);
        String[] playersNames = getIntent().getStringArrayExtra("playersNames");
        String opponentUid = getIntent().getStringExtra("opponentUid");
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_game_board);
        PlayerNamesInputScreenFragment playerNamesInputScreenFragment = new PlayerNamesInputScreenFragment(this, numberOfPlayers);
        gameSettingsFragment = new GameSettingsFragment(this);
        combinationsChartFragment = new CombinationsChartFragment(this);
        addFragment(playerNamesInputScreenFragment);
        addFragment(combinationsChartFragment);
        addFragment(gameSettingsFragment);
        ImageView gameSettings = findViewById(R.id.game_settings);
        gameSettings.setOnClickListener(v -> manageFragments(true, false, gameSettingsFragment));
        //hides status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hides title bar
        Objects.requireNonNull(getSupportActionBar()).hide();
        if (multiplayerMode) {
            startMultiplayerGame(playersNames, opponentUid);
        } else if (numberOfPlayers == 1) {
            startTrainingGame();
        } else {
            manageFragments(true, false, playerNamesInputScreenFragment);
        }


    }

    public void showNextTurnFragment() {
        if (multiplayerMode) {
            manageFragments(true, false, multiplayerTurnScreenFragment);
            multiplayerTurnScreenFragment.setNextPlayerName();
        } else {
            manageFragments(true, false, turnScreenFragment);
            turnScreenFragment.displayTurnMessage();
        }
    }

    public void addFragment(Fragment fragment){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_layout, fragment);
        fragmentTransaction.hide(fragment);
        fragmentTransaction.commit();
    }

    public void manageFragments(boolean showFragment, boolean hideFragment, Fragment fragment){
        fragmentTransaction = fragmentManager.beginTransaction();
        if(showFragment){
            fragmentTransaction.show(fragment);
            fragmentTransaction.commit();
        } if(hideFragment){
            fragmentTransaction.hide(fragment);
            fragmentTransaction.commit();
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
        gameBoardManager = new GameBoardManager(this, dicesCombinationsChecker, uiConfig, hotSeatGame, null);
        turnScreenFragment = new TurnScreenFragment(this, hotSeatGame, gameBoardManager);
        addFragment(turnScreenFragment);
        //configuring UI
        uiConfig.setComponents();
        uiConfig.getCurrentPlayerName().setText(playersNames[0]);
        hotSeatGame.setPlayersNames(playersNames);
        hotSeatGame.setNumberOfPlayers(numberOfPlayers);
        hotSeatGame.setAllCombinationsAsActive();
        gameBoardManager.setRollDicesButton();
        manageFragments(true, false, turnScreenFragment);
    }

    public void startMultiplayerGame(String[] playersNames, String opponentUid) {
        //creating class objects
        UIConfig uiConfig = new UIConfig(this);
        MultiplayerGame multiplayerGame = new MultiplayerGame(this, playersNames, opponentUid);
        DicesCombinationsChecker dicesCombinationsChecker = new DicesCombinationsChecker(multiplayerGame, uiConfig);
        gameBoardManager = new GameBoardManager(this, dicesCombinationsChecker, uiConfig, multiplayerGame, opponentUid);
        multiplayerTurnScreenFragment = new MultiplayerTurnScreenFragment(this, uiConfig, multiplayerGame, gameBoardManager, opponentUid);
        opponentUIConfig = new OpponentUIConfig(this, uiConfig, multiplayerGame, gameBoardManager, opponentUid);
        addFragment(multiplayerTurnScreenFragment);
        //configuring UI
        uiConfig.setComponents();
        uiConfig.setCurrentPlayerName(playersNames[0]);
        multiplayerGame.setPlayersNames(playersNames);
        multiplayerGame.setNumberOfPlayers(2);
        multiplayerGame.setStartBoardValues();
        gameBoardManager.setRollDicesButton();
        manageFragments(true, false, multiplayerTurnScreenFragment);
    }

    public void startTrainingGame() {
        String playerName = getResources().getString(R.string.training);
        UIConfig uiConfig = new UIConfig(this);
        TrainingGame trainingGame = new TrainingGame(this, uiConfig);
        this.trainingGame = trainingGame;
        uiConfig.setComponents();
        uiConfig.getCurrentPlayerName().setText(playerName);
        trainingGame.startTrainingMode();

    }

    public void updateSoundSettings(){
        if(numberOfPlayers==1){
            trainingGame.setSounds(new Sounds(this));
        } else{
            gameBoardManager.setSounds(new Sounds(this));
        }
    }

    public OpponentUIConfig getOpponentOnlineUIConfig() {
        return opponentUIConfig;
    }

    public boolean isSoundOn() {
        return isSoundOn;
    }

    public boolean isCrossOutCombinationOn() {
        return isCrossOutCombinationOn;
    }

    public boolean isCombinationsHighlightOn() {
        return isCombinationsHighlightOn;
    }


    public void setSoundOn(boolean soundOn) {
        isSoundOn = soundOn;
    }

    public void setCombinationsHighlightOn(boolean combinationsHighlightOn) {
        isCombinationsHighlightOn = combinationsHighlightOn;
    }

    public void setCrossOutCombinationOn(boolean crossOutCombinationOn) {
        isCrossOutCombinationOn = crossOutCombinationOn;
    }

    public CombinationsChartFragment getCombinationsChartFragment() {
        return combinationsChartFragment;
    }
}