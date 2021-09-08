package com.paweldyjak.dicegame;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Button;

import com.paweldyjak.dicegame.Fragments.MainMenuScreenFragment;

public class ConnectionCheckerThread implements Runnable {
    private final MainMenuScreenFragment mainMenuScreenFragment;
    private final Context context;
    private Button connectionButton;


    public ConnectionCheckerThread(Context context, MainMenuScreenFragment mainMenuScreenFragment) {
        this.context = context;
        this.mainMenuScreenFragment = mainMenuScreenFragment;
    }

    @Override
    public void run() {
        connectionButton = ((Activity) context).findViewById(R.id.offline_mode_button);
        ((Activity) context).runOnUiThread(() -> {
            if (!isConnectedToNetwork()) {
                connectionButton.setText(context.getText(R.string.offline_mode));
            } else {
                connectionButton.setText(mainMenuScreenFragment.getUserName());
            }

        });
    }


    private boolean isConnectedToNetwork() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


}
