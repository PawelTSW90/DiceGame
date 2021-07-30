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

public class PlayerNamesInputScreenFragment extends Fragment {
    View view;
    Context context;
    MainActivity mainActivity;
    Button start;
    EditText editTextPlayerOneName;
    TextView playerTitle;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.player_names_input_screen_fragment, container, false);
        start = view.findViewById(R.id.player_input_start_button);
        editTextPlayerOneName = view.findViewById(R.id.edit_text_name_one);
        playerTitle = view.findViewById(R.id.player_title);
        playerInputScreen(context);
        return view;

    }

    public PlayerNamesInputScreenFragment(MainActivity mainActivity) {
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

        editTextPlayerOneName.setOnEditorActionListener((v, actionId, event) -> {
            if (v.getText().length() > 0) {
                {
                    start.setVisibility(View.VISIBLE);
                    //hide keyboard after players name input finished
                    imm.hideSoftInputFromWindow(editTextPlayerOneName.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);

                    return true;
                }
            } else {
                start.setVisibility(View.INVISIBLE);
            }

            return false;
        });
        //setting start button to save players names and start the game
        start.setOnClickListener(v -> {
            playersNames[0] = editTextPlayerOneName.getText().toString();
            playerTitle.setText("GRACZ 2");
            editTextPlayerOneName.setText(null);
            start.setVisibility(View.INVISIBLE);
            start.setOnClickListener(v1 -> {
                playersNames[1] = editTextPlayerOneName.getText().toString();
                mainActivity.startTwoPlayersGame(playersNames);
            });


        });


    }
}
