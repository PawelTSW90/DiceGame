package com.paweldyjak.dicegame.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import com.paweldyjak.dicegame.*;

import java.util.Objects;

public class MainMenuSettingsActivity extends AppCompatActivity {
    Button backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_settings);
        //hides status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hides title bar
        Objects.requireNonNull(getSupportActionBar()).hide();
        setButton();
    }

    public void setButton() {

    }

}