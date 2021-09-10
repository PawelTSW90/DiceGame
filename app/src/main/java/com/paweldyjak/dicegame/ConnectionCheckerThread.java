package com.paweldyjak.dicegame;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Button;

import com.paweldyjak.dicegame.Activities.MainMenuActivity;

public class ConnectionCheckerThread implements Runnable {
    private final MainMenuActivity mainMenuActivity;
    private final Context context;
    private Button connectionButton;


    public ConnectionCheckerThread(Context context, MainMenuActivity mainMenuActivity) {
        this.context = context;
        this.mainMenuActivity = mainMenuActivity;
    }

    @Override
    public void run() {
            connectionButton = ((Activity) context).findViewById(R.id.connection_status_button);
            ((Activity) context).runOnUiThread(() -> {
                if (!isConnectedToNetwork()) {
                    connectionButton.setText(context.getText(R.string.offline_mode));
                } else {
                    connectionButton.setText(mainMenuActivity.getUserName());
                }

            });
        }



    private boolean isConnectedToNetwork() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


}
