package com.paweldyjak.dicegame.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.GameModes.HotSeatGame;
import com.paweldyjak.dicegame.R;

public class FinalResultScreenFragment extends Fragment {
    private final GameBoardActivity gameBoardActivity;
    private final HotSeatGame hotSeatGame;
    TextView playerOne;
    TextView playerTwo;
    TextView winnerPlayer;

    public FinalResultScreenFragment(GameBoardActivity gameBoardActivity, HotSeatGame hotSeatGame) {
        this.gameBoardActivity = gameBoardActivity;
        this.hotSeatGame = hotSeatGame;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.final_result_screen_fragment, container, false);
        playerOne = view.findViewById(R.id.player_one_result_textview);
        playerTwo = view.findViewById(R.id.player_two_result_textview);
        winnerPlayer = view.findViewById(R.id.winner_player_textview);
        setFinalResultScreen();
        return view;
    }



    public void setFinalResultScreen() {
        String winnerPlayer;
        if (hotSeatGame.getPlayersTotalScore(1) > hotSeatGame.getPlayersTotalScore(2)) {
            winnerPlayer = hotSeatGame.getPlayersNames()[0];
        } else {
            winnerPlayer = hotSeatGame.getPlayersNames()[1];
        }
        playerOne.setText("" + hotSeatGame.getPlayersNames()[0] + "\n" + hotSeatGame.getPlayersTotalScore(1));
        playerTwo.setText("" + hotSeatGame.getPlayersNames()[1] + "\n" + hotSeatGame.getPlayersTotalScore(2));
        this.winnerPlayer.setText("ZWYCIĘZCĄ JEST:" + "\n" + winnerPlayer);
        gameBoardActivity.showNewTurnScreen(true);


    }


}
