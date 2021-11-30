package com.paweldyjak.dicegame.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.paweldyjak.dicegame.R;

import java.util.Objects;

public class StartActivity extends AppCompatActivity {
    Button loginButton;
    Button registerButton;
    Button offlineModeButton;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_start);


        //hides status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hides title bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        //if user is logged in, display main menu
        if (firebaseAuth.getCurrentUser() != null) {
            Intent intent = new Intent(this, MainMenuActivity.class);
            startActivity(intent);
        }
        //if not display log in/register interface
        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
            this.finish();
        });
        loginButton.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            this.finish();
        });
        //start game in offline mode
        offlineModeButton = findViewById(R.id.offline_mode_button);
        offlineModeButton.setOnClickListener(v -> {
            startActivity(new Intent(this, MainMenuActivity.class));
            this.finish();
        });

    }
    @Override
    public void onBackPressed() {

    }

}