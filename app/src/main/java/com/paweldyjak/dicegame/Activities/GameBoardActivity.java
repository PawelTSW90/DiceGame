package com.paweldyjak.dicegame.Activities;

import androidx.annotation.NonNull;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
    private DatabaseReference playerNameReference;
    private boolean multiplayerMode;
    private OpponentOnlineUIConfig opponentOnlineUIConfig;
    private GameBoardManager gameBoardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int numberOfPlayers = getIntent().getIntExtra("numberOfPlayers", 0);
        multiplayerMode = getIntent().getBooleanExtra("MultiplayerMode", false);
        String[] playersNames = getIntent().getStringArrayExtra("playersNames");
        String opponentUid = getIntent().getStringExtra("opponentUid");
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_game_board);
        playerNamesInputScreenFragment = new PlayerNamesInputScreenFragment(this, numberOfPlayers);
        playerNameReference = FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid()).child("name");
        mainBoardLayout = findViewById(R.id.game_board_screen_layout);
        //hides status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hides title bar
        Objects.requireNonNull(getSupportActionBar()).hide();
        AdView mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
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
        if(!multiplayerMode) {
            turnScreenFragment.displayTurnMessage();
        } else{
            multiplayerTurnScreenFragment.displayTurnMessage();
        }
    }


    //disable back button
    @Override
    public void onBackPressed() {

    }

    public void startHotSeatGame(String[] playersNames, int numberOfPlayers) {
        //creating class objects
        UIConfig uiConfig = new UIConfig(this, null);
        HotSeatGame hotSeatGame = new HotSeatGame(uiConfig, this, playersNames);
        RerollDices rerollDices = new RerollDices(uiConfig);
        turnScreenFragment = new TurnScreenFragment(this, uiConfig, hotSeatGame);
        DicesCombinationsChecker dicesCombinationsChecker = new DicesCombinationsChecker(hotSeatGame);
        ScoreInputSetter scoreInputSetter = new ScoreInputSetter(this, uiConfig, hotSeatGame);
        GameBoardManager gameBoardManager = new GameBoardManager(this, this, scoreInputSetter, dicesCombinationsChecker, uiConfig, null, rerollDices, hotSeatGame);
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

        UIConfig uiConfig = new UIConfig(this, opponentUid);

        this.opponentOnlineUIConfig = opponentOnlineUIConfig;
        MultiplayerGame multiplayerGame = new MultiplayerGame(uiConfig, this,playersNames,opponentUid);
        OpponentOnlineUIConfig opponentOnlineUIConfig = new OpponentOnlineUIConfig(this, uiConfig, opponentUid);
        RerollDices rerollDices = new RerollDices(uiConfig);
        multiplayerTurnScreenFragment = new MultiplayerTurnScreenFragment(this, uiConfig, multiplayerGame, opponentUid);
        DicesCombinationsChecker dicesCombinationsChecker = new DicesCombinationsChecker(multiplayerGame);
        ScoreInputSetter scoreInputSetter = new ScoreInputSetter(this, uiConfig, multiplayerGame);
        GameBoardManager gameBoardManager = new GameBoardManager(this, this, scoreInputSetter, dicesCombinationsChecker, uiConfig, opponentOnlineUIConfig, rerollDices, multiplayerGame);
        this.gameBoardManager = gameBoardManager;
        //configuring UI
        uiConfig.setComponents();
        uiConfig.setCurrentPlayerName(playersNames[0]);
        multiplayerGame.setPlayersNames(playersNames);
        multiplayerGame.setNumberOfPlayers(2);
        multiplayerGame.setAllCombinationsAsActive();
        gameBoardManager.setRollDicesButton();
        replaceFragment(R.id.fragment_layout, multiplayerTurnScreenFragment);
        playerNameReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.getValue(String.class).equals(multiplayerGame.getPlayersNames()[0])) {
                    multiplayerGame.setOpponentTurn(true);


                }
                showFragment();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public OpponentOnlineUIConfig getOpponentOnlineUIConfig() {
        return opponentOnlineUIConfig;
    }

    public GameBoardManager getGameBoardManager(){
        return gameBoardManager;
    }
}