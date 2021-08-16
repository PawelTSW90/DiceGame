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
import com.paweldyjak.dicegame.GameModes.HotSeatGame;

public class StartGameScreenFragment extends Fragment {
    private final GameBoardActivity gameBoardActivity;
    private Button startGameButton;
    private final String[] names;
    private final int numberOfPlayers;

    public StartGameScreenFragment(GameBoardActivity gameBoardActivity, String[] names, int numberOfPlayers) {
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
        startHotSeatGame();
        return view;
    }


    public void startHotSeatGame(){
        startGameButton.setOnClickListener(v -> {
            //creating class objects
            UIConfig uiConfig = new UIConfig(gameBoardActivity);
            HotSeatGame hotSeatGame = new HotSeatGame(uiConfig, gameBoardActivity, names);
            RerollDices rerollDices = new RerollDices(uiConfig);
            DicesCombinationsChecker dicesCombinationsChecker = new DicesCombinationsChecker(hotSeatGame);
            ScoreInput scoreInput = new ScoreInput(gameBoardActivity,uiConfig, hotSeatGame);
            GameBoardManager gameBoardManager = new GameBoardManager(gameBoardActivity, gameBoardActivity, scoreInput, dicesCombinationsChecker, uiConfig, rerollDices, hotSeatGame);
            //configuring UI
            uiConfig.setComponents();
            uiConfig.getCurrentPlayerName().setText(names[0]);
            hotSeatGame.setPlayersNames(names);
            hotSeatGame.setNumberOfPlayers(numberOfPlayers);
            hotSeatGame.setAllCombinationsAsActive();
            gameBoardManager.setRollDicesButton();
            gameBoardActivity.showGameBoard(true);

        });
    }


}