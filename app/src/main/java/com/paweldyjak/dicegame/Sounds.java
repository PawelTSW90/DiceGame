package com.paweldyjak.dicegame;

import android.content.Context;
import android.media.MediaPlayer;

public class Sounds {

    private final Context context;

    public Sounds(Context context) {
        this.context = context;
    }


    public void playRollDiceSound() {
        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.roll_dices);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(MediaPlayer::release);
    }

    public void playCompleteCombinationSound() {
        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.combination_tick);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(MediaPlayer::release);
    }

    public void playEraseCombinationSound() {
        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.cross_out_combination_sound);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(MediaPlayer::release);
    }

    public void playTickSound() {
        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.tick);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(MediaPlayer::release);
    }

    public void playFinalResultSound() {
        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.final_result);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(MediaPlayer::release);
    }
}
