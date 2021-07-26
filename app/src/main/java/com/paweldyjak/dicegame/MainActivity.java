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
        setContentView(R.layout.activity_main);
        //hides status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hides title bar
        Objects.requireNonNull(getSupportActionBar()).hide();
        //creating class objects
        UIConfig uiConfig = new UIConfig(this);
        RerollDices rerollDices = new RerollDices(this, uiConfig);
        DicesScoreChecker dicesScoreChecker = new DicesScoreChecker(uiConfig);
        ScoreInput scoreInput = new ScoreInput(this, uiConfig);
        Dices dices = new Dices(this, scoreInput, dicesScoreChecker, uiConfig, rerollDices);
        //configuring UI
        uiConfig.setDicesSlots();
        uiConfig.setDicesCombinations();
        uiConfig.setCombinationsAsActive();
        dices.setRollDicesButton();
        //ad config
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


    }


}