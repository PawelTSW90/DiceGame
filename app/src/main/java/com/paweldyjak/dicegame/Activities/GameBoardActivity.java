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
import com.paweldyjak.dicegame.Fragments.*;
import com.paweldyjak.dicegame.R;

import java.util.Objects;


public class GameBoardActivity extends AppCompatActivity {
    private MainMenuScreenFragment mainMenuScreenFragment;
    private PlayerTurnScreenFragment playerTurnScreenFragment;
    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction fragmentTransaction;
    private View mainBoardLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_game_board);
        mainMenuScreenFragment = new MainMenuScreenFragment(this);
        mainBoardLayout = findViewById(R.id.game_board_screen_layout);
        //hides status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hides title bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        AdView mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        showMenuScreen();


    }

    public void showMenuScreen() {
        showFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_layout, mainMenuScreenFragment);
        fragmentTransaction.commit();


    }

    public void replaceFragment(int layout, Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(layout, fragment);
        fragmentTransaction.commit();
    }

    public void showFragment(){
        View fragmentLayout = findViewById(R.id.fragment_layout);
        mainBoardLayout.setVisibility(View.INVISIBLE);
        fragmentLayout.setVisibility(View.VISIBLE);
    }


    public void hideFragment() {
        View fragmentLayout = findViewById(R.id.fragment_layout);
            fragmentLayout.setVisibility(View.INVISIBLE);
            mainBoardLayout.setVisibility(View.VISIBLE);

    }

    public void showNextTurnFragment(){
        View fragmentLayout = findViewById(R.id.fragment_layout);
        mainBoardLayout.setVisibility(View.INVISIBLE);
        fragmentLayout.setVisibility(View.VISIBLE);
        playerTurnScreenFragment.displayTurnMessage();
    }

    public void setPlayerTurnScreen(PlayerTurnScreenFragment playerTurnScreenFragment) {
        this.playerTurnScreenFragment = playerTurnScreenFragment;
    }
    //disable back button
    @Override
    public void onBackPressed(){

    }


}