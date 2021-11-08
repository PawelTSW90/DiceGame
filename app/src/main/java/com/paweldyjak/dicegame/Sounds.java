package com.paweldyjak.dicegame;

import android.content.Context;
import android.media.MediaPlayer;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.Activities.MainMenuActivity;


public class Sounds {

    private final Context context;
    private final boolean isSoundOn;

    public Sounds(Context context) {
        this.context = context;
        if (context instanceof MainMenuActivity) {
            isSoundOn = ((MainMenuActivity) context).getIsSoundOn();
        } else {
            isSoundOn = ((GameBoardActivity) context).getIsSoundOn();
        }
    }


    public void playRollDiceSound() {
        if(isSoundOn) {
            MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.sound_roll_dices);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);
        }
    }

    public void playCompleteCombinationSound() {
        if(isSoundOn) {
            MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.sound_combination_tick);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);
        }
    }

    public void playEraseCombinationSound() {
        if(isSoundOn) {
            MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.sound_cross_out_combination);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);
        }
    }

    public void playTickSound() {
        if(isSoundOn) {
            MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.sound_button_tick);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);
        }
    }

    public void playFinalResultSound() {
        if(isSoundOn) {
            MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.sound_final_result);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);
        }
    }
}
