package com.paweldyjak.dicegame.Fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.paweldyjak.dicegame.DicesCombinationsChecker;
import com.paweldyjak.dicegame.GameBoard;
import com.paweldyjak.dicegame.MainActivity;
import com.paweldyjak.dicegame.R;
import com.paweldyjak.dicegame.RerollDices;
import com.paweldyjak.dicegame.ScoreInput;
import com.paweldyjak.dicegame.UIConfig;

public class StartGameScreen extends Fragment {
    View view;
    MainActivity mainActivity;
    Button playerTurnMessageButton;
    String[] names;
    TextView textView;

    public StartGameScreen(MainActivity mainActivity, String[] names) {
        this.mainActivity = mainActivity;
        this.names = names;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.start_game_screen_fragment, container, false);
        playerTurnMessageButton = view.findViewById(R.id.start_game_button);
        textView = view.findViewById(R.id.start_game_textview);
        textView.setText(names[0]);
        startTwoPlayersGame();
        return view;
    }


    public void startTwoPlayersGame(){
        playerTurnMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creating class objects
                UIConfig uiConfig = new UIConfig(mainActivity, names);
                RerollDices rerollDices = new RerollDices(mainActivity, uiConfig);
                DicesCombinationsChecker dicesCombinationsChecker = new DicesCombinationsChecker(uiConfig);
                ScoreInput scoreInput = new ScoreInput(mainActivity,uiConfig);
                GameBoard gameBoard = new GameBoard(mainActivity, mainActivity, scoreInput, dicesCombinationsChecker, uiConfig, rerollDices);
                //configuring UI
                uiConfig.setPlayersNames(names);
                uiConfig.setDicesSlots();
                uiConfig.setDicesCombinations();
                uiConfig.setAllCombinationsAsActive();
                uiConfig.setPlayersNames(names);
                uiConfig.getPlayerNameBoardTextView().setText(""+names[0]);
                gameBoard.setRollDicesButton();
                mainActivity.destroyFragment(mainActivity.playerNamesInputScreen);
                mainActivity.showMainBoard(true);
                //create new player turn fragment


            }
        });
    }


}
