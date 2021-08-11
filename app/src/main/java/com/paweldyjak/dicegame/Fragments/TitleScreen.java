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

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.paweldyjak.dicegame.*;
import com.paweldyjak.dicegame.Activities.MainActivity;
import com.paweldyjak.dicegame.Activities.MainMenuSettings;


public class TitleScreen extends Fragment {
    private final MainActivity mainActivity;
    private Button playButton;
    private Button hotSeatButton;
    private Button logoutButton;
    private final Button[] playersNumberButtons = new Button[5];
    private Button backButton;
    private Button settingPlayerNameButton;
    private EditText nameEditText;
    private View playersNumberLayout;
    private ImageView settings;
    private final Sounds sounds;

    public TitleScreen(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        sounds = new Sounds(mainActivity);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.title_screen_fragment, container, false);
        playButton = view.findViewById(R.id.play_button);
        hotSeatButton = view.findViewById(R.id.hotseat_mode_button);
        backButton = view.findViewById(R.id.back_button);
        logoutButton = view.findViewById(R.id.logout_button_titleScreen);
        settingPlayerNameButton = view.findViewById(R.id.setting_player_name_button);
        playersNumberButtons[0] = view.findViewById(R.id.two_players_button);
        playersNumberButtons[1] = view.findViewById(R.id.three_players_button);
        playersNumberButtons[2] = view.findViewById(R.id.four_players_button);
        playersNumberButtons[3] = view.findViewById(R.id.five_players_button);
        playersNumberButtons[4] = view.findViewById(R.id.six_playersButton);
        playersNumberLayout = view.findViewById(R.id.players_number_layout);
        nameEditText = view.findViewById(R.id.setting_name_editText);
        settings = view.findViewById(R.id.settings_imageview);
        setButtons();
        setPlayerNameInput();

        return view;
    }

    public void setButtons() {

        settings.setOnClickListener(v -> {
            Intent intent = new Intent(mainActivity, MainMenuSettings.class);
            startActivity(intent);
        });

        logoutButton.setOnClickListener(v -> {
            if(FirebaseAuth.getInstance().getCurrentUser()!=null){
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(mainActivity, "Logged out", Toast.LENGTH_SHORT).show();
                mainActivity.quitActivity();

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
            PlayerNamesInputScreen playerNamesInputScreen = new PlayerNamesInputScreen(mainActivity, 2);
            sounds.playTickSound();
            mainActivity.replaceFragment(R.id.fragment_layout, playerNamesInputScreen);
        });
        playersNumberButtons[1].setOnClickListener(v -> {
            PlayerNamesInputScreen playerNamesInputScreen = new PlayerNamesInputScreen(mainActivity, 3);
            sounds.playTickSound();
            mainActivity.replaceFragment(R.id.fragment_layout, playerNamesInputScreen);
        });
        playersNumberButtons[2].setOnClickListener(v -> {
            PlayerNamesInputScreen playerNamesInputScreen = new PlayerNamesInputScreen(mainActivity, 4);
            sounds.playTickSound();
            mainActivity.replaceFragment(R.id.fragment_layout, playerNamesInputScreen);
        });
        playersNumberButtons[3].setOnClickListener(v -> {
            PlayerNamesInputScreen playerNamesInputScreen = new PlayerNamesInputScreen(mainActivity, 5);
            sounds.playTickSound();
            mainActivity.replaceFragment(R.id.fragment_layout, playerNamesInputScreen);
        });
        playersNumberButtons[4].setOnClickListener(v -> {
            PlayerNamesInputScreen playerNamesInputScreen = new PlayerNamesInputScreen(mainActivity, 6);
            sounds.playTickSound();
            mainActivity.replaceFragment(R.id.fragment_layout, playerNamesInputScreen);
        });



    }

    public void setPlayerNameInput(){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

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
            if(nameEditText.length() <3 || nameEditText.length()>10){
                Toast.makeText(mainActivity, R.string.set_player_name_2, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
