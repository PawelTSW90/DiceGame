package com.paweldyjak.dicegame.Fragments;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.paweldyjak.dicegame.DicesCombinationsChecker;
import com.paweldyjak.dicegame.MainActivity;
import com.paweldyjak.dicegame.R;
import com.paweldyjak.dicegame.RerollDices;
import com.paweldyjak.dicegame.ScoreInput;
import com.paweldyjak.dicegame.UIConfig;

public class PlayerTurnScreenFragment extends Fragment {
    View view;
    MainActivity mainActivity;
    Button playerTurnMessageButton;
    String[] names;
    TextView textView;

    public PlayerTurnScreenFragment(MainActivity mainActivity,String[] names) {
        this.mainActivity = mainActivity;
        this.names = names;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.player_turn_screen_fragment, container, false);
        playerTurnMessageButton = view.findViewById(R.id.player_turn_message_start_button);
        textView = view.findViewById(R.id.player_turn_textview);
        textView.setText(names[0]);
        startTwoPlayersGame();
        return view;
    }

    public void showPlayerTurnScreen() {
        playerTurnMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void startTwoPlayersGame(){
        playerTurnMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creating class objects
                UIConfig uiConfig = new UIConfig(mainActivity, names);
                RerollDices rerollDices = new RerollDices(mainActivity, uiConfig);
                DicesCombinationsChecker dicesCombinationsChecker = new DicesCombinationsChecker(uiConfig);
                ScoreInput scoreInput = new ScoreInput(uiConfig);
                GameBoardFragment gameBoardFragment = new GameBoardFragment(mainActivity, scoreInput, dicesCombinationsChecker, uiConfig, rerollDices);
                //configuring UI
                uiConfig.setPlayersNames(names);
                mainActivity.destroyFragment(mainActivity.playerNamesInputScreenFragment);
                mainActivity.addFragment(R.id.game_board_screen_fragment_layout_slot, gameBoardFragment);

            }
        });
    }

}
