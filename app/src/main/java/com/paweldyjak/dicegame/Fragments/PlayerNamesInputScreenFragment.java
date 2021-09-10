package com.paweldyjak.dicegame.Fragments;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
    private Context context;
    private final GameBoardActivity gameBoardActivity;
    private Button start;
    private EditText playerNameEditText;
    private TextView playerName;
    private final int numberOfPlayers;
    private final String[] playersNames = new String[6];


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_names_input_screen, container, false);
        start = view.findViewById(R.id.player_input_start_button);
        playerNameEditText = view.findViewById(R.id.edit_text_name);
        playerName = view.findViewById(R.id.player_title);
        playerName.setText(R.string.player_one);
        playerInputScreen(context);

        return view;

    }

    public PlayerNamesInputScreenFragment(GameBoardActivity gameBoardActivity, int numberOfPlayers) {
        this.gameBoardActivity = gameBoardActivity;
        this.numberOfPlayers = numberOfPlayers;
    }

    public void playerInputScreen(Context context) {
        this.context = context;




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


        //setting start button to save players names and start the game unless no player name entered
        start.setOnClickListener(v -> {
            Log.i("testApp",""+numberOfPlayers);
            if (playerNameEditText.getText().length() >2 && nameDuplicateChecker()) {
                playersNames[0] = playerNameEditText.getText().toString();
                playerName.setText(R.string.player_two);
                playerNameEditText.setText(null);
                start.setVisibility(View.INVISIBLE);
                start.setOnClickListener(v1 -> {
                    if (playerNameEditText.getText().length() >2 && nameDuplicateChecker()) {
                        playersNames[1] = playerNameEditText.getText().toString();
                        playerNameEditText.setText(null);
                        start.setVisibility(View.INVISIBLE);
                        if (numberOfPlayers > 2) {
                            playerName.setText(R.string.player_three);
                            start.setOnClickListener(v2 -> {
                                if (playerNameEditText.getText().length() >2 && nameDuplicateChecker()) {
                                    playersNames[2] = playerNameEditText.getText().toString();
                                    playerNameEditText.setText(null);
                                    start.setVisibility(View.INVISIBLE);
                                    if (numberOfPlayers > 3) {
                                        playerName.setText(R.string.player_four);
                                        start.setOnClickListener(v3 -> {
                                            if (playerNameEditText.getText().length() >2 && nameDuplicateChecker()) {
                                                playersNames[3] = playerNameEditText.getText().toString();
                                                playerNameEditText.setText(null);
                                                start.setVisibility(View.INVISIBLE);
                                                if (numberOfPlayers > 4) {
                                                    playerName.setText(R.string.player_five);
                                                    start.setOnClickListener(v4 -> {
                                                        if (playerNameEditText.getText().length() >2 && nameDuplicateChecker()) {
                                                            playersNames[4] = playerNameEditText.getText().toString();
                                                            playerNameEditText.setText(null);
                                                            start.setVisibility(View.INVISIBLE);
                                                            if (numberOfPlayers > 5) {
                                                                playerName.setText(R.string.player_six);
                                                                start.setOnClickListener(v5 -> {
                                                                    if (playerNameEditText.getText().length() >2 && nameDuplicateChecker()) {
                                                                        playersNames[5] = playerNameEditText.getText().toString();
                                                                        StartHotSeatGameFragment startHotSeatGameFragment = new StartHotSeatGameFragment(gameBoardActivity, playersNames, numberOfPlayers);
                                                                        gameBoardActivity.replaceFragment(R.id.fragment_layout, startHotSeatGameFragment);
                                                                    } else {
                                                                        if (!nameDuplicateChecker()) {
                                                                            displayNameInUseToast();
                                                                        } else {
                                                                            displayWrongLengthToast();
                                                                        }
                                                                    }
                                                                });
                                                            } else {
                                                                StartHotSeatGameFragment startHotSeatGameFragment = new StartHotSeatGameFragment(gameBoardActivity, playersNames, numberOfPlayers);
                                                                gameBoardActivity.replaceFragment(R.id.fragment_layout, startHotSeatGameFragment);
                                                            }
                                                        } else {
                                                            if (!nameDuplicateChecker()) {
                                                                displayNameInUseToast();
                                                            } else {
                                                                displayWrongLengthToast();
                                                            }
                                                        }
                                                    });
                                                } else {
                                                    StartHotSeatGameFragment startHotSeatGameFragment = new StartHotSeatGameFragment(gameBoardActivity, playersNames, numberOfPlayers);
                                                    gameBoardActivity.replaceFragment(R.id.fragment_layout, startHotSeatGameFragment);
                                                }

                                            } else {
                                                if (!nameDuplicateChecker()) {
                                                    displayNameInUseToast();
                                                } else {
                                                    displayWrongLengthToast();
                                                }
                                            }
                                        });
                                    } else {
                                        StartHotSeatGameFragment startHotSeatGameFragment = new StartHotSeatGameFragment(gameBoardActivity, playersNames, numberOfPlayers);
                                        gameBoardActivity.replaceFragment(R.id.fragment_layout, startHotSeatGameFragment);
                                    }
                                } else {
                                    if (!nameDuplicateChecker()) {
                                        displayNameInUseToast();
                                    } else {
                                        displayWrongLengthToast();
                                    }
                                }

                            });

                        } else {
                            StartHotSeatGameFragment startHotSeatGameFragment = new StartHotSeatGameFragment(gameBoardActivity, playersNames, numberOfPlayers);
                            gameBoardActivity.replaceFragment(R.id.fragment_layout, startHotSeatGameFragment);
                        }

                    } else {
                        if (!nameDuplicateChecker()) {
                            displayNameInUseToast();
                        } else {
                            displayWrongLengthToast();
                        }
                    }
                });


            } else {
                if (!nameDuplicateChecker()) {
                    displayNameInUseToast();
                } else {
                    displayWrongLengthToast();
                }
            }

        });
    }

    public void displayWrongLengthToast(){
        Toast toast = Toast.makeText(getContext(), R.string.username_length_error, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    public void displayNameInUseToast(){
        Toast toast = Toast.makeText(getContext(), R.string.username_in_use_error, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    public boolean nameDuplicateChecker(){
        String currentName = playerNameEditText.getText().toString();
        for (String playersName : playersNames) {
            if (currentName.equals(playersName)) {
                return false;
            }
        }
        return true;
    }

}






