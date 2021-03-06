package com.paweldyjak.dicegame.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.R;

public class PlayerNamesInputScreenFragment extends Fragment {
    private int currentPlayerNameInput = 0;
    private final GameBoardActivity gameBoardActivity;
    private Button start;
    private EditText playerNameEditText;
    private TextView playerNameTextView;
    private final int numberOfPlayers;
    private final String[] playersNames = new String[6];


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_names_input_screen, container, false);
        start = view.findViewById(R.id.player_input_start_button);
        playerNameEditText = view.findViewById(R.id.edit_text_name);
        playerNameTextView = view.findViewById(R.id.player_title);
        playerNameTextView.setText(R.string.player_one);
        playerInputScreen();
        return view;

    }

    public PlayerNamesInputScreenFragment(GameBoardActivity gameBoardActivity, int numberOfPlayers) {
        this.gameBoardActivity = gameBoardActivity;
        this.numberOfPlayers = numberOfPlayers;
    }

    public void playerInputScreen() {


        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        //allows to proceed when players name is at least 3 characters long
        playerNameEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (v.getText().length() > 2) {
                {
                    start.setVisibility(View.VISIBLE);
                    //hide keyboard after players name input finished
                    imm.hideSoftInputFromWindow(playerNameEditText.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
                    return true;
                }
            } else {
                start.setVisibility(View.INVISIBLE);
            }

            return false;
        });


        //setting start button to save player name and start the game unless no player name entered or wrong format entered
        start.setOnClickListener(v -> {
            if (playerNameEditText.getText().length() > 2 && nameDuplicateChecker()) {
                playersNames[currentPlayerNameInput] = playerNameEditText.getText().toString();
                playerNameEditText.setText(null);
                currentPlayerNameInput++;
                if (currentPlayerNameInput == numberOfPlayers) {
                    gameBoardActivity.startHotSeatGame(playersNames, numberOfPlayers);
                    gameBoardActivity.manageFragments(false, true, this);
                } else {
                    switch (currentPlayerNameInput) {
                        case 1:
                            playerNameTextView.setText(R.string.player_two);
                            break;
                        case 2:
                            playerNameTextView.setText(R.string.player_three);
                            break;
                        case 3:
                            playerNameTextView.setText(R.string.player_four);
                            break;
                        case 4:
                            playerNameTextView.setText(R.string.player_five);
                            break;
                        case 5:
                            playerNameTextView.setText(R.string.player_six);
                            break;
                    }
                    start.setVisibility(View.INVISIBLE);
                }

            } else {
                if (!nameDuplicateChecker()) {
                    start.setVisibility(View.INVISIBLE);
                    displayNameInUseToast();
                } else {
                    start.setVisibility(View.INVISIBLE);
                    displayWrongLengthToast();
                }
            }
        });
    }

    public void displayWrongLengthToast() {
        Toast toast = Toast.makeText(getContext(), R.string.username_length_error, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void displayNameInUseToast() {
        Toast toast = Toast.makeText(getContext(), R.string.username_in_use_error, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public boolean nameDuplicateChecker() {
        String currentName = playerNameEditText.getText().toString();
        for (String playersName : playersNames) {
            if (currentName.equals(playersName)) {
                return false;
            }
        }
        return true;
    }

}






