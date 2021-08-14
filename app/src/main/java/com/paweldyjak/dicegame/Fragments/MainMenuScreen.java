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
import com.paweldyjak.dicegame.Activities.MainMenuSettings;


public class MainMenuScreen extends Fragment {
    private final GameBoardActivity gameBoardActivity;
    private DatabaseReference databaseReference;
    private Button playButton;
    private Button hotSeatButton;
    private Button logoutButton;
    private final Button[] playersNumberButtons = new Button[5];
    private Button backButton;
    private Button settingPlayerNameButton;
    private EditText nameEditText;
    private View playersNumberLayout;
    private View userNameCreatorLayout;
    private ImageView settings;
    private final Sounds sounds;
    private String userName;

    public MainMenuScreen(GameBoardActivity gameBoardActivity) {
        this.gameBoardActivity = gameBoardActivity;
        sounds = new Sounds(gameBoardActivity);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_menu_screen_fragment, container, false);
        playButton = view.findViewById(R.id.play_button);
        hotSeatButton = view.findViewById(R.id.hotseat_mode_button);
        backButton = view.findViewById(R.id.back_button);
        logoutButton = view.findViewById(R.id.logout_button_titleScreen);
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
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(userUID).child("name");
        setButtons();
        checkIfUserCreatedName();

        return view;
    }

    public void setButtons() {

        settings.setOnClickListener(v -> {
            Intent intent = new Intent(gameBoardActivity, MainMenuSettings.class);
            startActivity(intent);
        });

        logoutButton.setOnClickListener(v -> {
            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(gameBoardActivity, getContext().getText(R.string.logged_out), Toast.LENGTH_SHORT).show();
                gameBoardActivity.quitActivity();

            }
        });

        playButton.setOnClickListener(v -> {
            sounds.playTickSound();
            backButton.setVisibility(View.VISIBLE);
            hotSeatButton.setVisibility(View.VISIBLE);
            playButton.setEnabled(false);
        });

        backButton.setOnClickListener(v -> {
            sounds.playTickSound();
            if (playersNumberLayout.getVisibility() == View.VISIBLE) {
                playersNumberLayout.setVisibility(View.INVISIBLE);
                hotSeatButton.setEnabled(true);
            } else if (hotSeatButton.getVisibility() == View.VISIBLE) {
                hotSeatButton.setVisibility(View.INVISIBLE);
                playButton.setEnabled(true);
                backButton.setVisibility(View.INVISIBLE);
            }
        });

        hotSeatButton.setOnClickListener(v -> {
            sounds.playTickSound();
            playersNumberLayout.setVisibility(View.VISIBLE);
            hotSeatButton.setEnabled(false);
        });
        playersNumberButtons[0].setOnClickListener(v -> {
            PlayerNamesInputScreen playerNamesInputScreen = new PlayerNamesInputScreen(gameBoardActivity, 2);
            sounds.playTickSound();
            gameBoardActivity.replaceFragment(R.id.fragment_layout, playerNamesInputScreen);
        });
        playersNumberButtons[1].setOnClickListener(v -> {
            PlayerNamesInputScreen playerNamesInputScreen = new PlayerNamesInputScreen(gameBoardActivity, 3);
            sounds.playTickSound();
            gameBoardActivity.replaceFragment(R.id.fragment_layout, playerNamesInputScreen);
        });
        playersNumberButtons[2].setOnClickListener(v -> {
            PlayerNamesInputScreen playerNamesInputScreen = new PlayerNamesInputScreen(gameBoardActivity, 4);
            sounds.playTickSound();
            gameBoardActivity.replaceFragment(R.id.fragment_layout, playerNamesInputScreen);
        });
        playersNumberButtons[3].setOnClickListener(v -> {
            PlayerNamesInputScreen playerNamesInputScreen = new PlayerNamesInputScreen(gameBoardActivity, 5);
            sounds.playTickSound();
            gameBoardActivity.replaceFragment(R.id.fragment_layout, playerNamesInputScreen);
        });
        playersNumberButtons[4].setOnClickListener(v -> {
            PlayerNamesInputScreen playerNamesInputScreen = new PlayerNamesInputScreen(gameBoardActivity, 6);
            sounds.playTickSound();
            gameBoardActivity.replaceFragment(R.id.fragment_layout, playerNamesInputScreen);
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
                Toast.makeText(gameBoardActivity, R.string.set_player_name_2, Toast.LENGTH_SHORT).show();
            } else {
                databaseReference.setValue(nameEditText.getText().toString());
                userName = nameEditText.getText().toString();
                userNameCreatorLayout.setVisibility(View.GONE);
                playButton.setVisibility(View.VISIBLE);
                Toast.makeText(gameBoardActivity, getContext().getString(R.string.hello) + " " + userName + " !", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void checkIfUserCreatedName() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue().equals("null")) {
                    userName = "false";
                    setPlayerNameInput();
                } else {
                    userName = snapshot.getValue(String.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}