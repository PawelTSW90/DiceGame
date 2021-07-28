package com.paweldyjak.dicegame.layouts;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.paweldyjak.dicegame.MainActivity;
import com.paweldyjak.dicegame.R;

public class PlayerNamesInputScreen extends AppCompatActivity {
    Context context;
    MainActivity mainActivity;

    public PlayerNamesInputScreen(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void playerInputScreen(Context context) {
        this.context = context;

        ((Activity) context).setContentView(R.layout.player_names_input_screen);
        String[] playersNames = new String[2];
        Button start = ((Activity) context).findViewById(R.id.player_input_start_button);
        EditText editTextPlayerOneName = ((Activity) context).findViewById(R.id.edit_text_name_one);
        TextView playerTitle = ((Activity) context).findViewById(R.id.player_title);

        //ad config
        AdView mAdView = ((Activity) context).findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        //allows to proceed when players name is at least one character long

        editTextPlayerOneName.setOnEditorActionListener((v, actionId, event) -> {
            if (v.getText().length() > 0) {
                {
                    start.setVisibility(View.VISIBLE);
                    //hide keyboard after players name input finished
                    imm.hideSoftInputFromWindow(editTextPlayerOneName.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);

                    return true;
                }
            } else {
                start.setVisibility(View.INVISIBLE);
            }

            return false;
        });
        //setting start button to save players names and start the game
        start.setOnClickListener(v -> {
            playersNames[0] = editTextPlayerOneName.getText().toString();
            playerTitle.setText("GRACZ 2");
            editTextPlayerOneName.setText(null);
            start.setVisibility(View.INVISIBLE);
            start.setOnClickListener(v1 -> {
                playersNames[1] = editTextPlayerOneName.getText().toString();
                mainActivity.startTwoPlayersGame(playersNames);
            });


        });


    }
}
