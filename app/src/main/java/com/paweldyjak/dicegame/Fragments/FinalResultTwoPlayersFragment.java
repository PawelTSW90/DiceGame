package com.paweldyjak.dicegame.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.Activities.MainMenuActivity;
import com.paweldyjak.dicegame.GameModes.GameMode;
import com.paweldyjak.dicegame.GameModes.MultiplayerGame;
import com.paweldyjak.dicegame.R;
import com.paweldyjak.dicegame.Sounds;

public class FinalResultTwoPlayersFragment extends Fragment {
    private final GameBoardActivity gameBoardActivity;
    private final GameMode gameMode;
    private Sounds sounds;
    private TextView winnerTextView;
    private TextView playerOneTextView;
    private TextView playerTwoTextView;
    private TextView rankingPointsTextView;
    private ImageView playerOneImageView;
    private ImageView playerTwoImageView;
    private Button rematchButton;
    private Button exitButton;
    private int playerRanking;
    private String playerName;
    private String playerUid;

    public FinalResultTwoPlayersFragment(GameBoardActivity gameBoardActivity, GameMode gameMode) {
        this.gameBoardActivity = gameBoardActivity;
        this.gameMode = gameMode;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_final_result_two_players, container, false);
        sounds = new Sounds(gameBoardActivity);
        winnerTextView = view.findViewById(R.id.winner_name_two_players_mode);
        rankingPointsTextView = view.findViewById(R.id.ranking_points_text);
        playerOneTextView = view.findViewById(R.id.first_player_two_players_mode);
        playerTwoTextView = view.findViewById(R.id.second_player_two_players_mode);
        playerOneImageView = view.findViewById(R.id.firstPlayerGoldMedal);
        playerTwoImageView = view.findViewById(R.id.secondPlayerGoldMedal);
        rematchButton = view.findViewById(R.id.rematch_button_twoPlayers);
        exitButton = view.findViewById(R.id.exit_button_twoPlayers);
        getPlayerName();
        setButtons();
        setPlayersPosition();

        return view;
    }

    public void setButtons() {
        exitButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MainMenuActivity.class);
            startActivity(intent);
            getActivity().finish();

        });

        rematchButton.setOnClickListener(v -> gameBoardActivity.startHotSeatGame(gameMode.getPlayersNames(), gameMode.getNumberOfPlayers()));
    }

    public void setPlayersPosition() {
        String winnerPlayer = null;
        if (gameMode.getPlayersTotalScore(0) > gameMode.getPlayersTotalScore(1)) {
            winnerPlayer = gameMode.getPlayersNames()[0];
            playerOneImageView.setVisibility(View.VISIBLE);
        } else if (gameMode.getPlayersTotalScore(0) < gameMode.getPlayersTotalScore(1)) {
            winnerPlayer = gameMode.getPlayersNames()[1];
            playerTwoImageView.setVisibility(View.VISIBLE);
        } else {
            playerOneImageView.setVisibility(View.VISIBLE);
            playerTwoImageView.setVisibility(View.VISIBLE);
        }
        sounds.playFinalResultSound();
        playerOneTextView.setText(gameMode.getPlayersNames()[0] + "\n" + "\n" + gameMode.getPlayersTotalScore(0) + " " + getContext().getText(R.string.points));
        playerTwoTextView.setText(gameMode.getPlayersNames()[1] + "\n" + "\n" + gameMode.getPlayersTotalScore(1) + " " + getContext().getText(R.string.points));
        if (gameMode.getPlayersTotalScore(0) != gameMode.getPlayersTotalScore(1)) {
            winnerTextView.setText(getActivity().getString(R.string.the_winner_is) + " " + winnerPlayer + "!");
        } else {
            winnerTextView.setText(R.string.draw);
        }
        if (gameMode instanceof MultiplayerGame) {
            if (winnerPlayer.equals(playerName)) {
                changeRankingPoints(true);
                rankingPointsTextView.setText((getContext().getText(R.string.winnerRankingPoint))+" "+playerRanking+" "+getContext().getText(R.string.points));
            } else {
                changeRankingPoints(false);
                rankingPointsTextView.setText((getContext().getText(R.string.loserRankingPoint))+" "+playerRanking+" "+getContext().getText(R.string.points));
            }
        }
        gameBoardActivity.showFragment();


    }

    public void changeRankingPoints(boolean isWinner) {
        String playerUid = FirebaseAuth.getInstance().getUid();
        DatabaseReference rankingReference = FirebaseDatabase.getInstance().getReference().child("users").child(playerUid).
                child("ranking");
        rankingReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                playerRanking = snapshot.getValue(Integer.class);
                if (isWinner) {
                    playerRanking += 1;

                } else {
                    playerRanking -= 1;
                    if(playerRanking<0){
                        playerRanking = 0;
                    }
                }
                rankingReference.setValue(playerRanking);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getPlayerName() {
        String playerUid = FirebaseAuth.getInstance().getUid();
        DatabaseReference playerNameReference = FirebaseDatabase.getInstance().getReference().child("users").child(playerUid).
                child("name");
        playerNameReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                playerName = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
