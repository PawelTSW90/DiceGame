package com.paweldyjak.dicegame.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.paweldyjak.dicegame.MainActivity;
import com.paweldyjak.dicegame.R;

public class PlayerNamesInputScreen extends Fragment {
    private View view;
    private Context context;
    private final MainActivity mainActivity;
    private Button start;
    private EditText playerNameEditText;
    private TextView playerName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.player_names_input_screen_fragment, container, false);
        start = view.findViewById(R.id.player_input_start_button);
        playerNameEditText = view.findViewById(R.id.edit_text_name_one);
        playerName = view.findViewById(R.id.player_title);
        playerInputScreen(context);
        return view;

    }

    public PlayerNamesInputScreen(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void playerInputScreen(Context context) {
        this.context = context;
        String[] playersNames = new String[2];

        //ad config
        AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        //allows to proceed when players name is at least one character long
        playerNameEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (v.getText().length() > 0) {
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
            if (playerNameEditText.getText().length() < 1) {

            } else {
                playersNames[0] = playerNameEditText.getText().toString();
                playerName.setText("GRACZ 2");
                playerNameEditText.setText(null);
                start.setVisibility(View.INVISIBLE);
                start.setOnClickListener(v1 -> {
                    if (playerNameEditText.getText().length() < 1) {

                    } else {
                        playersNames[1] = playerNameEditText.getText().toString();
                        mainActivity.setStartGameScreen(new StartGameScreen(mainActivity, playersNames));
                        mainActivity.replaceFragment(R.id.fragment_layout, mainActivity.getStartGameScreen());
                    }
                });
            }

        });


    }
}
