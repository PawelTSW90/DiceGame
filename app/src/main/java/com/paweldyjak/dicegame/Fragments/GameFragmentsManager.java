package com.paweldyjak.dicegame.Fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.paweldyjak.dicegame.*;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;

public class GameFragmentsManager extends Fragment {
    private final GameBoardActivity gameBoardActivity;
    private Button startGameButton;
    private final String[] names;
    private final int numberOfPlayers;

    public GameFragmentsManager(GameBoardActivity gameBoardActivity, String[] names, int numberOfPlayers) {
        this.gameBoardActivity = gameBoardActivity;
        this.names = names;
        this.numberOfPlayers = numberOfPlayers;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.start_game_screen_fragment, container, false);
        startGameButton = view.findViewById(R.id.start_game_button);
        TextView playerName = view.findViewById(R.id.start_game_textview);
        playerName.setText(names[0]);
        startTwoPlayersGame();
        return view;
    }


    public void startTwoPlayersGame(){
        startGameButton.setOnClickListener(v -> {
            //creating class objects
            UIConfig uiConfig = new UIConfig(gameBoardActivity, gameBoardActivity, names);
            RerollDices rerollDices = new RerollDices(uiConfig);
            DicesCombinationsChecker dicesCombinationsChecker = new DicesCombinationsChecker(uiConfig);
            ScoreInput scoreInput = new ScoreInput(gameBoardActivity,uiConfig);
            GameBoard gameBoard = new GameBoard(gameBoardActivity, gameBoardActivity, scoreInput, dicesCombinationsChecker, uiConfig, rerollDices);
            //configuring UI
            uiConfig.configureUI();
            uiConfig.setPlayersNames(names);
            uiConfig.setNumberOfPlayers(numberOfPlayers);
            uiConfig.getCurrentPlayerName().setText(names[0]);
            gameBoard.setRollDicesButton();
            gameBoardActivity.showGameBoard(true);

        });
    }


}
