package com.paweldyjak.dicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hides title bar
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        RollDiceClass rollDiceClass = new RollDiceClass(this);
        rollDiceClass.setRollDiceButton();
        rollDiceClass.setDicesSlots();
    }
}