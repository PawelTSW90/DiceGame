package com.paweldyjak.dicegame.Activities;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paweldyjak.dicegame.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class MainMenuActivity extends AppCompatActivity {
    private GameBoardActivity gameBoardActivity;
    private Sounds sounds;
    private ConnectionCheckerThread connectionCheckerThread;
    private final ScheduledExecutorService connectionChecker = Executors.newScheduledThreadPool(1);
    private DatabaseReference userNameReference;
    private DatabaseReference namesInUseReference;
    private Button playButton;
    private Button hotSeatButton;
    private Button logoutButton;
    private Button multiplayerButton;
    private final Button[] playersNumberButtons = new Button[5];
    private Button backButton;
    private Button settingPlayerNameButton;
    private EditText nameEditText;
    private View playersNumberLayout;
    private View userNameCreatorLayout;
    private ImageView settingsButton;
    private String userName;
    private MainMenuSettingsActivity mainMenuSettings;
    private boolean isSoundOn = true;
    private boolean isCombinationHighlightOn = true;
    private boolean isBlockConfirmationOn = false;
    private ActivityResultLauncher<Intent> activityResultLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        //hides status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hides title bar
        Objects.requireNonNull(getSupportActionBar()).hide();
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK){
                    Intent data = result.getData();
                    boolean isSoundOn = data.getBooleanExtra("soundPref", true);
                    Toast.makeText(MainMenuActivity.this, isSoundOn+"", Toast.LENGTH_SHORT).show();

                }
            }
        });
        gameBoardActivity = new GameBoardActivity();
        sounds = new Sounds(this);
        playButton = findViewById(R.id.play_button);
        hotSeatButton = findViewById(R.id.hotseat_mode_button);
        backButton = findViewById(R.id.back_button);
        logoutButton = findViewById(R.id.logout_button_titleScreen);
        multiplayerButton = findViewById(R.id.multiplayer_mode_button);
        settingPlayerNameButton = findViewById(R.id.setting_player_name_button);
        userNameCreatorLayout = findViewById(R.id.user_name_creator_layout);
        playersNumberButtons[0] = findViewById(R.id.two_players_button);
        playersNumberButtons[1] = findViewById(R.id.three_players_button);
        playersNumberButtons[2] = findViewById(R.id.four_players_button);
        playersNumberButtons[3] = findViewById(R.id.five_players_button);
        playersNumberButtons[4] = findViewById(R.id.six_playersButton);
        playersNumberLayout = findViewById(R.id.players_number_layout);
        nameEditText = findViewById(R.id.setting_name_editText);
        settingsButton = findViewById(R.id.settings_imageview);
        mainMenuSettings = new MainMenuSettingsActivity(this);
        setButtons();
        try {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            String userUID = firebaseAuth.getUid();
            userNameReference = FirebaseDatabase.getInstance().getReference().child("users").child(userUID).child("name");
            namesInUseReference = FirebaseDatabase.getInstance().getReference().child("namesInUse");
            connectionCheckerThread = new ConnectionCheckerThread(this, this);
            internetConnectionChecker();
            checkIfUserCreatedName();
        } catch (NullPointerException ignored) {

        }

    }

    public void setButtons() {

        for (int x = 0; x < 5; x++) {
            int numberOfPlayers = x + 2;
            playersNumberButtons[x].setOnClickListener(v -> {
                connectionChecker.shutdown();
                sounds.playTickSound();
                Intent intent = new Intent(this, GameBoardActivity.class);
                intent.putExtra("numberOfPlayers", numberOfPlayers);
                intent.putExtra("MultiplayerMode", false);
                intent.putExtra("isSoundOn", isSoundOn);
                intent.putExtra("isCombinationsHighlightOn", isCombinationHighlightOn);
                intent.putExtra("isBlockingConfirmationOn", isBlockConfirmationOn);
                startActivity(intent);
                this.finish();
            });
        }

        settingsButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, mainMenuSettings.getClass());
            activityResultLauncher.launch(intent);
        });

        logoutButton.setOnClickListener(v -> {
            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                connectionChecker.shutdown();
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(this, this.getText(R.string.logged_out), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, StartActivity.class);
                startActivity(intent);
                this.finish();

            }
        });

        playButton.setOnClickListener(v -> {
            sounds.playTickSound();
            backButton.setVisibility(View.VISIBLE);
            hotSeatButton.setVisibility(View.VISIBLE);
            multiplayerButton.setVisibility(View.VISIBLE);
            playButton.setEnabled(false);
        });

        backButton.setOnClickListener(v -> {
            sounds.playTickSound();
            if (playersNumberLayout.getVisibility() == View.VISIBLE) {
                playersNumberLayout.setVisibility(View.INVISIBLE);
                hotSeatButton.setEnabled(true);
                multiplayerButton.setEnabled(true);
            } else if (hotSeatButton.getVisibility() == View.VISIBLE) {
                hotSeatButton.setVisibility(View.INVISIBLE);
                multiplayerButton.setVisibility(View.INVISIBLE);
                playButton.setEnabled(true);
                backButton.setVisibility(View.INVISIBLE);
            }
        });

        hotSeatButton.setOnClickListener(v -> {
            sounds.playTickSound();
            playersNumberLayout.setVisibility(View.VISIBLE);
            multiplayerButton.setEnabled(false);
            hotSeatButton.setEnabled(false);
        });

        multiplayerButton.setOnClickListener(v -> {
            connectionChecker.shutdown();
            sounds.playTickSound();
            Intent intent = new Intent(this, MultiplayerQueueActivity.class);
            startActivity(intent);
            this.finish();

        });

    }

    public void setPlayerNameInput() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        playButton.setVisibility(View.INVISIBLE);
        userNameCreatorLayout.setVisibility(View.VISIBLE);
        //allows to proceed when players name is at least one character long
        nameEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (v.getText().length() > 2) {
                {
                    settingPlayerNameButton.setVisibility(View.VISIBLE);
                    //hide keyboard after players name input finished
                    imm.hideSoftInputFromWindow(nameEditText.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
                    return true;
                }
            } else {
                settingPlayerNameButton.setVisibility(View.INVISIBLE);
            }

            return false;
        });

        settingPlayerNameButton.setOnClickListener(v -> {
            if (nameEditText.length() < 3 || nameEditText.length() > 10) {
                Toast.makeText(gameBoardActivity, R.string.username_length_error, Toast.LENGTH_SHORT).show();
            } else {
                userName = nameEditText.getText().toString();
                checkIfNameIsAvailable();


            }
        });
    }

    public void checkIfUserCreatedName() {


        userNameReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {


                    if (snapshot.getValue().equals("null")) {
                        playButton.setVisibility(View.INVISIBLE);
                        userName = "false";
                        setPlayerNameInput();
                    } else {
                        userName = snapshot.getValue(String.class);
                    }
                } catch (NullPointerException ignored) {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void checkIfNameIsAvailable() {

        namesInUseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(userName).exists()) {
                    Toast.makeText(gameBoardActivity, R.string.username_in_use_error, Toast.LENGTH_SHORT).show();
                } else {
                    userNameReference.setValue(nameEditText.getText().toString());
                    Map<String, Object> map = new HashMap<>();
                    map.put(userName, 0);
                    namesInUseReference.updateChildren(map);
                    userName = nameEditText.getText().toString();
                    userNameCreatorLayout.setVisibility(View.INVISIBLE);
                    playButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    public void internetConnectionChecker() {
        connectionChecker.scheduleAtFixedRate(connectionCheckerThread, 0, 1, TimeUnit.SECONDS);
    }


    public String getUserName() {
        return userName;
    }

    @Override
    public void onBackPressed() {

    }

    public boolean getIsSoundOn() {
        return isSoundOn;
    }

    public void setIsSoundOn(boolean isSoundOn) {
        this.isSoundOn = isSoundOn;
    }

    public boolean isCombinationHighlightOn() {
        return isCombinationHighlightOn;
    }

    public void setCombinationHighlightOn(boolean combinationHighlightOn) {
        isCombinationHighlightOn = combinationHighlightOn;
    }

    public boolean isBlockConfirmationOn() {
        return isBlockConfirmationOn;
    }

    public void setBlockConfirmationOn(boolean blockConfirmationOn) {
        isBlockConfirmationOn = blockConfirmationOn;
    }
}
