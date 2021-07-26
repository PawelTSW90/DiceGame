package com.paweldyjak.dicegame;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
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

    public void startTwoPlayersGame(){
        setContentView(R.layout.game_started_screen);
        UIConfig uiConfig = new UIConfig(this);
        RerollDices rerollDices = new RerollDices(this, uiConfig);
        DicesScoreChecker dicesScoreChecker = new DicesScoreChecker(uiConfig);
        ScoreInput scoreInput = new ScoreInput(uiConfig);
        Dices dices = new Dices(this, scoreInput, dicesScoreChecker, uiConfig, rerollDices);
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