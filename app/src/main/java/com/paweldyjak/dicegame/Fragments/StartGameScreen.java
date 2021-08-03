package com.paweldyjak.dicegame.Fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.paweldyjak.dicegame.*;

public class StartGameScreen extends Fragment {
    private final MainActivity mainActivity;
    private Button nextTurnButton;
    private final String[] names;

    public StartGameScreen(MainActivity mainActivity, String[] names) {
        this.mainActivity = mainActivity;
        this.names = names;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.start_game_screen_fragment, container, false);
        nextTurnButton = view.findViewById(R.id.start_game_button);
        TextView playerName = view.findViewById(R.id.start_game_textview);
        playerName.setText(names[0]);
        startTwoPlayersGame();
        return view;
    }


    public void startTwoPlayersGame(){
        nextTurnButton.setOnClickListener(v -> {
            //creating class objects
            UIConfig uiConfig = new UIConfig(mainActivity, mainActivity, names);
            RerollDices rerollDices = new RerollDices(mainActivity, uiConfig);
            DicesCombinationsChecker dicesCombinationsChecker = new DicesCombinationsChecker(uiConfig);
            ScoreInput scoreInput = new ScoreInput(mainActivity,uiConfig);
            GameBoard gameBoard = new GameBoard(mainActivity, mainActivity, scoreInput, dicesCombinationsChecker, uiConfig, rerollDices);
            //configuring UI
            uiConfig.configureUI();
            uiConfig.setPlayersNames(names);
            uiConfig.getCurrentPlayerName().setText(""+names[0]);
            gameBoard.setRollDicesButton();
            mainActivity.showMainBoard(true);

        });
    }


}
