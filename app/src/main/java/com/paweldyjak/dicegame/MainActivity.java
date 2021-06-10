package com.paweldyjak.dicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hides title bar
        getSupportActionBar().hide();
        //creating class objects
        UIConfig uiConfig = new UIConfig(this);
        DicesChecker dicesChecker = new DicesChecker();
        ScoreInput scoreInput = new ScoreInput(this, uiConfig);
        RollDices rollDices = new RollDices(this, scoreInput, dicesChecker, uiConfig);
        setContentView(R.layout.activity_main);
        //configuring UI
        uiConfig.setDicesSlots();
        uiConfig.setDicesCombinations();
        rollDices.setRollDicesButton();
        //ad config
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


    }
}