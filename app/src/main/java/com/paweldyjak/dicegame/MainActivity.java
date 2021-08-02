package com.paweldyjak.dicegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import com.paweldyjak.dicegame.Fragments.*;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    public TitleScreen titleScreen;
    public PlayerNamesInputScreen playerNamesInputScreen;
    public StartGameScreen startGameScreen;
    public PlayerTurnScreen playerTurnScreen;
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction;
    View mainBoardLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerNamesInputScreen = new PlayerNamesInputScreen(this);
        titleScreen = new TitleScreen(this, playerNamesInputScreen);
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
        fragmentTransaction.add(R.id.title_screen_fragment_layout_slot, titleScreen);
        fragmentTransaction.commit();


    }

    public void addFragment(int layout, Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(layout, fragment);
        fragmentTransaction.commit();

    }


    public void destroyFragment(Fragment fragmentToDestroy) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragmentToDestroy);
        fragmentTransaction.commit();
    }

    public void showMainBoard(boolean showMainBoard) {
        if (showMainBoard) {
            mainBoardLayout.setVisibility(View.VISIBLE);
        } else {
            mainBoardLayout.setVisibility(View.INVISIBLE);
        }
    }

    public int checkFragments(){
        return fragmentManager.getFragments().size();
    }

    public void showPlayerTurnScreen(boolean show){
        View messageLayout = findViewById(R.id.player_turn_message_layout_slot);
        if(show){
            Log.i("testApp", ""+checkFragments());
            mainBoardLayout.setVisibility(View.INVISIBLE);
            messageLayout.setVisibility(View.VISIBLE);
            playerTurnScreen.displayTurnMessage();


        } else{
            messageLayout.setVisibility(View.INVISIBLE);
            mainBoardLayout.setVisibility(View.VISIBLE);
        }
    }


}