package com.paweldyjak.dicegame;
import android.content.Context;
import android.media.MediaPlayer;

public class Sounds {

    Context context;

    Sounds(Context context){
        this.context = context;
    }


    public void playRollDiceSound() {
        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.roll_dices);
        mediaPlayer.start();
    }
}
