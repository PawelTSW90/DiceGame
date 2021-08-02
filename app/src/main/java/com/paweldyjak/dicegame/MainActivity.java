package com.paweldyjak.dicegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.WindowManager;
import com.paweldyjak.dicegame.Fragments.*;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    public TitleScreenFragment titleScreenFragment;
    public PlayerNamesInputScreenFragment playerNamesInputScreenFragment;
    public PlayerTurnScreenFragment playerTurnScreenFragment;
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerNamesInputScreenFragment = new PlayerNamesInputScreenFragment(this);
        titleScreenFragment = new TitleScreenFragment(this, playerNamesInputScreenFragment);

        //hides status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hides title bar
        Objects.requireNonNull(getSupportActionBar()).hide();
        showTitleScreen();


    }

    public void showTitleScreen() {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.title_screen_fragment_layout_slot, titleScreenFragment);
        fragmentTransaction.commit();


    }

    public void addFragment(int layout, Fragment fragment){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(layout, fragment);
        fragmentTransaction.commit();

    }



    public void destroyFragment(Fragment fragmentToDestroy){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragmentToDestroy);
    }


}