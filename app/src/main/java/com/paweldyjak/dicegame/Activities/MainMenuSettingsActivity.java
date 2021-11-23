package com.paweldyjak.dicegame.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paweldyjak.dicegame.*;
import java.util.Objects;

public class MainMenuSettingsActivity extends AppCompatActivity {
    private CheckBox soundsCheckBox;
    private CheckBox combinationsHighlightCheckBox;
    private CheckBox crossOutConfirmationCheckBox;
    private TextView playerNameTextView;
    private TextView totalGamesTextView;
    private TextView winGamesTextView;
    private TextView drawGamesTextView;
    private TextView lostGamesTextView;
    private TextView rankingPointsTextView;
    private TextView rankingPositionTextView;
    private final String userSettingsPref = "userSettingsPref";
    private final String soundPref = "soundPref";
    private final String highlightPref = "highlightPref";
    private final String crossOutConfirmPref = "crossOutConfirmPref";
    private boolean isSoundOn = true;
    private boolean isCombinationsHighlightOn = true;
    private boolean isCrossOutConfirmationOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_settings);
        //hides status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hides title bar
        Objects.requireNonNull(getSupportActionBar()).hide();
        soundsCheckBox = findViewById(R.id.sounds_checkBox);
        combinationsHighlightCheckBox = findViewById(R.id.highlight_checkBox);
        crossOutConfirmationCheckBox = findViewById(R.id.crossOutConfirm_checkBox);
        playerNameTextView = findViewById(R.id.playerName_mainMenu);
        totalGamesTextView = findViewById(R.id.totalGames_mainMenu);
        winGamesTextView = findViewById(R.id.winGames_mainMenu);
        drawGamesTextView = findViewById(R.id.drawGames_mainMenu);
        lostGamesTextView = findViewById(R.id.lostGames_mainMenu);
        rankingPointsTextView = findViewById(R.id.rankingPoints_mainMenu);
        rankingPositionTextView = findViewById(R.id.rankingPosition_mainMenu);
        setButtons();
        loadSettings();
        updateSettings();

    }

    public void setButtons() {
        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    String playerUid = FirebaseAuth.getInstance().getUid();
                    DatabaseReference playerDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(playerUid);
                    playerDatabaseReference.child("name").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String playerName = snapshot.getValue(String.class);
                            playerNameTextView.setText(getResources().getString(R.string.playerName_mainMenu)+" "+playerName);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    playerDatabaseReference.child("gamesTotal").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int totalGames = snapshot.getValue(Integer.class);
                            totalGamesTextView.setText(getResources().getString(R.string.totalGames_mainMenu)+" "+totalGames);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    playerDatabaseReference.child("win").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int winGames = snapshot.getValue(Integer.class);
                            winGamesTextView.setText(getResources().getString(R.string.winGames_mainMenu)+" "+winGames);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    playerDatabaseReference.child("draw").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int drawGames = snapshot.getValue(Integer.class);
                            drawGamesTextView.setText(getResources().getString(R.string.drawGames_mainMenu)+" "+drawGames);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    playerDatabaseReference.child("lost").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int lostGames = snapshot.getValue(Integer.class);
                            lostGamesTextView.setText(getResources().getString(R.string.lostGames_mainMenu)+" "+lostGames);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    playerDatabaseReference.child("rankingPoints").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int rankingPoints = snapshot.getValue(Integer.class);
                            rankingPointsTextView.setText(getResources().getString(R.string.rankingPoints_mainMenu)+" "+rankingPoints);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    playerDatabaseReference.child("rankingPosition").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int rankingPosition = snapshot.getValue(Integer.class);
                            rankingPositionTextView.setText(getResources().getString(R.string.rankingPosition_mainMenu)+" "+rankingPosition);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                } else {
                    playerNameTextView.setText(getResources().getString(R.string.playerName_mainMenu)+" "+getResources().getString(R.string.offline_mode));
                    totalGamesTextView.setText(getResources().getString(R.string.totalGames_mainMenu)+" -");
                    winGamesTextView.setText(getResources().getString(R.string.winGames_mainMenu)+" -");
                    drawGamesTextView.setText(getResources().getString(R.string.drawGames_mainMenu)+" -");
                    lostGamesTextView.setText(getResources().getString(R.string.lostGames_mainMenu)+" -");
                    rankingPointsTextView.setText(getResources().getString(R.string.rankingPoints_mainMenu)+" -");
                    rankingPositionTextView.setText(getResources().getString(R.string.rankingPosition_mainMenu)+" -");






                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });



        soundsCheckBox.setOnClickListener(v -> saveSettings());
        combinationsHighlightCheckBox.setOnClickListener(v -> saveSettings());
        crossOutConfirmationCheckBox.setOnClickListener(v -> saveSettings());

    }

    public void saveSettings() {
        SharedPreferences sharedPreferences = getSharedPreferences(userSettingsPref, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(soundPref, soundsCheckBox.isChecked());
        editor.putBoolean(highlightPref, combinationsHighlightCheckBox.isChecked());
        editor.putBoolean(crossOutConfirmPref, crossOutConfirmationCheckBox.isChecked());
        editor.apply();
        loadSettings();
    }

    public void loadSettings() {
        SharedPreferences sharedPreferences = getSharedPreferences(userSettingsPref, MODE_PRIVATE);
        isSoundOn = sharedPreferences.getBoolean(soundPref, true);
        isCombinationsHighlightOn = sharedPreferences.getBoolean(highlightPref, true);
        isCrossOutConfirmationOn = sharedPreferences.getBoolean(crossOutConfirmPref, true);
        updateSettings();
    }

    public void updateSettings() {
        soundsCheckBox.setChecked(isSoundOn);
        combinationsHighlightCheckBox.setChecked(isCombinationsHighlightOn);
        crossOutConfirmationCheckBox.setChecked(isCrossOutConfirmationOn);
        Intent intent = new Intent();
        intent.putExtra("soundPref", isSoundOn);
        intent.putExtra("highlightPref", isCombinationsHighlightOn);
        intent.putExtra("crossOutConfirmPref", isCrossOutConfirmationOn);
        setResult(RESULT_OK, intent);
    }


}