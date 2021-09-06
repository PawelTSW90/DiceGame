package com.paweldyjak.dicegame.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.GameModes.HotSeatGame;
import com.paweldyjak.dicegame.R;
import com.paweldyjak.dicegame.Sounds;

public class FinalResultTwoPlayersFragment extends Fragment {
    private final GameBoardActivity gameBoardActivity;
    private final HotSeatGame hotSeatGame;
    private Sounds sounds;
    private TextView winnerTextView;
    private TextView playerOneTextView;
    private TextView playerTwoTextView;
    private ImageView playerOneImageView;
    private ImageView playerTwoImageView;
    private Button rematchButton;
    private Button exitButton;

    public FinalResultTwoPlayersFragment(GameBoardActivity gameBoardActivity, HotSeatGame hotSeatGame) {
        this.gameBoardActivity = gameBoardActivity;
        this.hotSeatGame = hotSeatGame;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_final_result_two_players, container, false);
        sounds = new Sounds(gameBoardActivity);
        winnerTextView = view.findViewById(R.id.winner_name_two_players);
        playerOneTextView = view.findViewById(R.id.first_player_two_players);
        playerTwoTextView = view.findViewById(R.id.second_player_two_players);
        playerOneImageView = view.findViewById(R.id.firstPlayerGoldMedal);
        playerTwoImageView = view.findViewById(R.id.secondPlayerGoldMedal);
        rematchButton = view.findViewById(R.id.rematch_button_twoPlayers);
        exitButton = view.findViewById(R.id.exit_button_twoPlayers);
        setButtons();
        setFinalResultScreen();

        return view;
    }

    public void setButtons() {
        exitButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), GameBoardActivity.class);
            startActivity(intent);
            getActivity().finish();

        });

        rematchButton.setOnClickListener(v -> {
            StartGameScreenFragment startGameScreenFragment = new StartGameScreenFragment(gameBoardActivity, hotSeatGame.getPlayersNames(), hotSeatGame.getNumberOfPlayers());
            gameBoardActivity.replaceFragment(R.id.fragment_layout, startGameScreenFragment);
            gameBoardActivity.showFragment();


        });
    }

    public void setFinalResultScreen() {
        String winnerPlayer = null;
        if (hotSeatGame.getPlayersTotalScore(1) > hotSeatGame.getPlayersTotalScore(2)) {
            winnerPlayer = hotSeatGame.getPlayersNames()[0];
            playerOneImageView.setVisibility(View.VISIBLE);
        } else if (hotSeatGame.getPlayersTotalScore(1) < hotSeatGame.getPlayersTotalScore(2)) {
            winnerPlayer = hotSeatGame.getPlayersNames()[1];
            playerTwoImageView.setVisibility(View.VISIBLE);
        } else{
            playerOneImageView.setVisibility(View.VISIBLE);
            playerTwoImageView.setVisibility(View.VISIBLE);
        }
        sounds.playFinalResultSound();
        playerOneTextView.setText(hotSeatGame.getPlayersNames()[0] + "\n" + "\n" + hotSeatGame.getPlayersTotalScore(1) + " " + getContext().getText(R.string.points));
        playerTwoTextView.setText(hotSeatGame.getPlayersNames()[1] + "\n" + "\n" + hotSeatGame.getPlayersTotalScore(2) + " " + getContext().getText(R.string.points));
        if(hotSeatGame.getPlayersTotalScore(1)!=hotSeatGame.getPlayersTotalScore(2)) {
            winnerTextView.setText(getActivity().getString(R.string.the_winner_is) + " " + winnerPlayer + "!");
        } else{
            winnerTextView.setText(R.string.draw);
        }
        gameBoardActivity.showNextTurnFragment();


    }
}