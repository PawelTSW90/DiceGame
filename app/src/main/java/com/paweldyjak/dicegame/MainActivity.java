package com.paweldyjak.dicegame;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.text.Layout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_screen);
        //hides status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hides title bar
        Objects.requireNonNull(getSupportActionBar()).hide();
        //creating class object
        TitleScreen titleScreen = new TitleScreen(this);


    }

    public void startTwoPlayersGame() {
//player names field
        String[] playersNames = new String[2];

        setContentView(R.layout.game_started_screen);
        //create view components
        View playerTurnLayout = this.findViewById(R.id.player_turn_message_layout);
        View playerNamesInputLayout = findViewById(R.id.player_names_input_layout);
        View playerTurnMessageLayout = findViewById(R.id.player_turn_message_layout);


        TextView playerTurnWindowTextView = this.findViewById(R.id.player_turn_textview);
        TextView playerTitle = findViewById(R.id.player_title);
        TextView playerNameText = findViewById(R.id.player_name_textView);

        Button playerTurnStartButton = this.findViewById(R.id.player_turn_message_start_button);
        Button start = findViewById(R.id.player_input_start_button);
        EditText editTextPlayerOneName = findViewById(R.id.edit_text_name_one);

        //creating input keyboard field
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        playerTurnMessageLayout.setVisibility(View.GONE);
        //allows to proceed when players name is at least one character long
        editTextPlayerOneName.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE && v.getText().length() > 0) {
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
                playerNamesInputLayout.setVisibility(View.GONE);
                playerTurnLayout.setVisibility(View.VISIBLE);
                playerTurnWindowTextView.setVisibility(View.VISIBLE);
                playerTurnStartButton.setVisibility(View.VISIBLE);

            });

        });
        //creating class objects
        UIConfig uiConfig = new UIConfig(this);
        RerollDices rerollDices = new RerollDices(this, uiConfig);
        DicesScoreChecker dicesScoreChecker = new DicesScoreChecker(uiConfig);
        ScoreInput scoreInput = new ScoreInput(uiConfig);
        Dices dices = new Dices(this, scoreInput, dicesScoreChecker, uiConfig, rerollDices, playersNames);
        //hiding start components
        playerTurnLayout.setVisibility(View.INVISIBLE);
        playerTurnWindowTextView.setVisibility(View.INVISIBLE);
        playerTurnStartButton.setVisibility(View.INVISIBLE);
        playerNameText.setVisibility(View.INVISIBLE);
        //configuring UI
        uiConfig.setDicesSlots();
        uiConfig.setDicesCombinations();
        uiConfig.setAllCombinationsAsActive();
        dices.setRollDicesButton();
        //ad config
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }


}