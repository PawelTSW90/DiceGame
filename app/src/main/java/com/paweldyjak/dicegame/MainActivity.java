package com.paweldyjak.dicegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import com.paweldyjak.dicegame.Fragments.*;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    private TitleScreen titleScreen;
    private NewGameCreator newGameCreator;
    private PlayerTurnScreen playerTurnScreen;
    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction fragmentTransaction;
    private View mainBoardLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleScreen = new TitleScreen(this);
        mainBoardLayout = findViewById(R.id.game_board_screen_layout);

        //hides status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hides title bar
        Objects.requireNonNull(getSupportActionBar()).hide();
        showTitleScreen();


    }

    public void showTitleScreen() {
        showMainBoard(false);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_layout, titleScreen);
        fragmentTransaction.commit();


    }

    public void showMainBoard(boolean showMainBoard) {
        if (showMainBoard) {
            mainBoardLayout.setVisibility(View.VISIBLE);
        } else {
            mainBoardLayout.setVisibility(View.INVISIBLE);
        }
    }

    public void replaceFragment(int layout, Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(layout, fragment);
        fragmentTransaction.commit();
    }


    public void showFragment(boolean show) {
        View fragmentLayout = findViewById(R.id.fragment_layout);
        if (show) {
            mainBoardLayout.setVisibility(View.INVISIBLE);
            fragmentLayout.setVisibility(View.VISIBLE);
            playerTurnScreen.displayTurnMessage();


        } else {
            fragmentLayout.setVisibility(View.INVISIBLE);
            mainBoardLayout.setVisibility(View.VISIBLE);
        }
    }

    public void setStartGameScreen(NewGameCreator newGameCreator) {
        this.newGameCreator = newGameCreator;
    }

    public NewGameCreator getStartGameScreen() {
        return newGameCreator;
    }

    public PlayerTurnScreen getPlayerTurnScreen() {
        return playerTurnScreen;
    }

    public void setPlayerTurnScreen(PlayerTurnScreen playerTurnScreen) {
        this.playerTurnScreen = playerTurnScreen;
    }
}