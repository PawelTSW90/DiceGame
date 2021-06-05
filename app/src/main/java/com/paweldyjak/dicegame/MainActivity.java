package com.paweldyjak.dicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //hides title bar
        getSupportActionBar().hide();
        RollDiceClass rollDiceClass = new RollDiceClass(this);
        rollDiceClass.setRollDiceButton();
        rollDiceClass.setDicesSlots();
    }
}