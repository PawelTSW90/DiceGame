package com.paweldyjak.dicegame;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TitleScreen extends AppCompatActivity {
    MainActivity mainActivity;

    public TitleScreen(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        setTwoPlayerButton();
    }


    public void setTwoPlayerButton(){
        Button twoPlayerButton = mainActivity.findViewById(R.id.twoPlayersButton);
        twoPlayerButton.setOnClickListener(v-> mainActivity.startTwoPlayersGame());
    }
}
