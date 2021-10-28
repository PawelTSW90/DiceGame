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
import com.paweldyjak.dicegame.GameModes.HotSeatGame;
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
    private String winnerPlayer;
    private String playerUid;
    private DatabaseReference userReference;
    private int totalGames;
    private int wins;
    private int draw;
    private int lost;
    private boolean drawResult;

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
        if (gameMode instanceof HotSeatGame) {
            setButtons();
            displayFinalScreen(null);
        } else {
            playerUid = FirebaseAuth.getInstance().getUid();
            userReference = FirebaseDatabase.getInstance().getReference().child("users").child(playerUid);
            getPlayerName();
        }


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

    public void displayFinalScreen(String playerName) {

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
        playerOneTextView.setText(gameBoardActivity.getResources().getString(R.string.playerResult, gameMode.getPlayersNames()[0],gameBoardActivity.getResources().getString(R.string.points_value, gameMode.getPlayersTotalScore(0))));
        playerTwoTextView.setText(gameBoardActivity.getResources().getString(R.string.playerResult, gameMode.getPlayersNames()[1],gameBoardActivity.getResources().getString(R.string.points_value, gameMode.getPlayersTotalScore(1))));
        if (gameMode.getPlayersTotalScore(0) != gameMode.getPlayersTotalScore(1)) {
            if (gameMode instanceof MultiplayerGame) {
                if (winnerPlayer.equals(playerName)) {
                    winnerTextView.setText(getActivity().getString(R.string.you_won));
                } else {
                    winnerTextView.setText(getActivity().getString(R.string.you_lost));
                }
            } else {
                winnerTextView.setText(getActivity().getResources().getString(R.string.the_winner_is, winnerPlayer));
            }
        } else {
            winnerTextView.setText(R.string.draw);
        }
        if (gameMode instanceof MultiplayerGame) {
            updateDatabaseStatistics(winnerPlayer.equals(playerName));
        } else {
            gameBoardActivity.showFragment();
        }


    }

    public void updateDatabaseStatistics(boolean isWinner) {


        DatabaseReference rankingReference = FirebaseDatabase.getInstance().getReference().child("users").child(playerUid).
                child("rankingPoints");
        rankingReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                playerRanking = snapshot.getValue(Integer.class);
                if (isWinner && !drawResult) {
                    playerRanking += 1;
                    updateWinsDatabase();
                    rankingReference.setValue(playerRanking);
                    rankingPointsTextView.setText(gameBoardActivity.getResources().getString(R.string.winnerRankingPoint,playerRanking));
                } else if (!isWinner && !drawResult) {
                    playerRanking -= 1;
                    if (playerRanking < 0) {
                        playerRanking = 0;
                    }
                    updateLostDatabase();
                    rankingReference.setValue(playerRanking);
                    rankingPointsTextView.setText(gameBoardActivity.getResources().getString(R.string.loserRankingPoint,playerRanking));
                } else {
                    updateDrawDatabase();
                }
                updateTotalGamesDatabase();
                gameBoardActivity.showFragment();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updateDrawDatabase() {
        DatabaseReference drawGamesReference = userReference.child("draw");
        drawGamesReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                draw = snapshot.getValue(Integer.class);
                draw += 1;
                drawGamesReference.setValue(draw);
                drawResult = false;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updateTotalGamesDatabase() {
        DatabaseReference totalGamesReference = userReference.child("gamesTotal");
        totalGamesReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                totalGames = snapshot.getValue(Integer.class);
                totalGames += 1;
                totalGamesReference.setValue(totalGames);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updateWinsDatabase() {
        DatabaseReference winGamesReference = userReference.child("win");
        winGamesReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                wins = snapshot.getValue(Integer.class);
                wins += 1;
                winGamesReference.setValue(wins);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updateLostDatabase() {
        DatabaseReference lostGamesReference = userReference.child("lost");
        lostGamesReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lost = snapshot.getValue(Integer.class);
                lost += 1;
                lostGamesReference.setValue(lost);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getPlayerName() {
        DatabaseReference playerNameReference = userReference.child("name");
        playerNameReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String playerName = snapshot.getValue(String.class);
                setButtons();
                displayFinalScreen(playerName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
