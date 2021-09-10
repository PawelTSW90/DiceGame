package com.paweldyjak.dicegame.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paweldyjak.dicegame.*;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.Activities.MainMenuSettingsActivity;
import com.paweldyjak.dicegame.Activities.MultiplayerQueueActivity;
import java.util.HashMap;
import java.util.Map;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class MainMenuScreenFragment extends Fragment {
    private final GameBoardActivity gameBoardActivity;
    private ConnectionCheckerThread connectionCheckerThread;
    private final ScheduledExecutorService connectionChecker = Executors.newScheduledThreadPool(1);
    private DatabaseReference userNameReference;
    private DatabaseReference namesInUseReference;
    private Button playButton;
    private Button hotSeatButton;
    private Button logoutButton;
    private Button multiplayerButton;
    private Button connectionStatusButton;
    private final Button[] playersNumberButtons = new Button[5];
    private Button backButton;
    private Button settingPlayerNameButton;
    private EditText nameEditText;
    private View playersNumberLayout;
    private View userNameCreatorLayout;
    private ImageView settings;
    private final Sounds sounds;
    private String userName;

    public MainMenuScreenFragment(GameBoardActivity gameBoardActivity) {
        this.gameBoardActivity = gameBoardActivity;
        sounds = new Sounds(gameBoardActivity);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_menu_screen, container, false);
        playButton = view.findViewById(R.id.play_button);
        hotSeatButton = view.findViewById(R.id.hotseat_mode_button);
        backButton = view.findViewById(R.id.back_button);
        connectionStatusButton = view.findViewById(R.id.connection_status_button);
        logoutButton = view.findViewById(R.id.logout_button_titleScreen);
        multiplayerButton = view.findViewById(R.id.multiplayer_mode_button);
        settingPlayerNameButton = view.findViewById(R.id.setting_player_name_button);
        userNameCreatorLayout = view.findViewById(R.id.user_name_creator_layout);
        playersNumberButtons[0] = view.findViewById(R.id.two_players_button);
        playersNumberButtons[1] = view.findViewById(R.id.three_players_button);
        playersNumberButtons[2] = view.findViewById(R.id.four_players_button);
        playersNumberButtons[3] = view.findViewById(R.id.five_players_button);
        playersNumberButtons[4] = view.findViewById(R.id.six_playersButton);
        playersNumberLayout = view.findViewById(R.id.players_number_layout);
        nameEditText = view.findViewById(R.id.setting_name_editText);
        settings = view.findViewById(R.id.settings_imageview);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String userUID = firebaseAuth.getUid();
        userNameReference = FirebaseDatabase.getInstance().getReference().child("users").child(userUID).child("name");
        namesInUseReference = FirebaseDatabase.getInstance().getReference().child("namesInUse");
        connectionCheckerThread = new ConnectionCheckerThread(getContext(), this);
        setButtons();
        checkIfUserCreatedName();
        internetConnectionChecker();
        return view;
    }

    public void setButtons() {

        for (int x = 0; x < 5; x++) {
            int finalX = x;
            playersNumberButtons[x].setOnClickListener(v -> {
                connectionChecker.shutdown();
                PlayerNamesInputScreenFragment playerNamesInputScreenFragment = new PlayerNamesInputScreenFragment(gameBoardActivity, finalX + 2);
                sounds.playTickSound();
                gameBoardActivity.replaceFragment(R.id.fragment_layout, playerNamesInputScreenFragment);
            });
        }

        settings.setOnClickListener(v -> {
            Intent intent = new Intent(gameBoardActivity, MainMenuSettingsActivity.class);
            startActivity(intent);
        });

        logoutButton.setOnClickListener(v -> {
            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                connectionChecker.shutdown();
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(gameBoardActivity, getContext().getText(R.string.logged_out), Toast.LENGTH_SHORT).show();
                getActivity().finish();

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
            Intent intent = new Intent(getContext(), MultiplayerQueueActivity.class);
            startActivity(intent);
            getActivity().finish();

        });

    }

    public void setPlayerNameInput() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
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
                        connectionStatusButton.setVisibility(View.VISIBLE);
                    }
                }catch (NullPointerException ignored){

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
                    connectionStatusButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    public void internetConnectionChecker(){
            connectionChecker.scheduleAtFixedRate(connectionCheckerThread, 2, 2, TimeUnit.SECONDS);
        }


    public String getUserName(){
        return userName;
    }

}
