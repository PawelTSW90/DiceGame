package com.paweldyjak.dicegame.Fragments;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.paweldyjak.dicegame.*;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.GameModes.HotSeatGame;

public class StartHotSeatGameFragment extends Fragment {
    private final GameBoardActivity gameBoardActivity;
    private final String[] names;
    private final int numberOfPlayers;

    public StartHotSeatGameFragment(GameBoardActivity gameBoardActivity, String[] names, int numberOfPlayers) {
        this.gameBoardActivity = gameBoardActivity;
        this.names = names;
        this.numberOfPlayers = numberOfPlayers;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_game_screen, container, false);
        Button startGameButton = view.findViewById(R.id.start_game_button);
        TextView playerName = view.findViewById(R.id.start_game_textview);
        playerName.setText(names[0]);
        startGameButton.setOnClickListener(v -> startHotSeatGame());
        return view;
    }


    public void startHotSeatGame(){
        Log.i("testApp", ""+numberOfPlayers);
            //creating class objects
            UIConfig uiConfig = new UIConfig(gameBoardActivity);
            HotSeatGame hotSeatGame = new HotSeatGame(uiConfig, gameBoardActivity, names);
            RerollDices rerollDices = new RerollDices(uiConfig);
            DicesCombinationsChecker dicesCombinationsChecker = new DicesCombinationsChecker(hotSeatGame);
            ScoreInputSetter scoreInputSetter = new ScoreInputSetter(gameBoardActivity,uiConfig, hotSeatGame);
            GameBoardManager gameBoardManager = new GameBoardManager(gameBoardActivity, gameBoardActivity, scoreInputSetter, dicesCombinationsChecker, uiConfig, rerollDices, hotSeatGame);
            //configuring UI
            uiConfig.setComponents();
            uiConfig.getCurrentPlayerName().setText(names[0]);
            hotSeatGame.setPlayersNames(names);
            hotSeatGame.setNumberOfPlayers(numberOfPlayers);
            hotSeatGame.setAllCombinationsAsActive();
            gameBoardManager.setRollDicesButton();
            gameBoardActivity.hideFragment();
    }


}
