package com.paweldyjak.dicegame.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.GameModes.GameMode;
import com.paweldyjak.dicegame.R;
import com.paweldyjak.dicegame.UIConfig;


public class MultiplayerTurnScreenFragment extends Fragment {
    private final UIConfig uiConfig;
    private TextView playerName;
    private Button nextPlayerButton;
    private final GameBoardActivity gameBoardActivity;
    private final GameMode gameMode;
    private boolean opponentTurn;
    private String opponentUid;


    public MultiplayerTurnScreenFragment(GameBoardActivity gameBoardActivity, UIConfig uiConfig, GameMode gameMode) {
        this.gameBoardActivity = gameBoardActivity;
        this.uiConfig = uiConfig;
        this.gameMode = gameMode;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multiplayer_turn_screen, container, false);
        playerName = view.findViewById(R.id.multiplayer_player_turn_textview);
        nextPlayerButton = view.findViewById(R.id.multiplayer_player_turn_button);
        checkPlayerTurn();
        return view;
    }


    public void displayTurnMessage(boolean opponentTurn) {
        if (opponentTurn) {
            nextPlayerButton.setVisibility(View.INVISIBLE);
        } else {
            nextPlayerButton.setVisibility(View.VISIBLE);
        }

        nextPlayerButton.setOnClickListener(v -> {
            int playerNumber = gameMode.getCurrentPlayerNumber() - 1;
            uiConfig.getCurrentPlayerName().setText((gameMode.getPlayersNames()[playerNumber]));
            gameMode.prepareScoreBoard();
            gameBoardActivity.hideFragment();
        });
        updateCurrentPlayerNumber();
    }

    public void checkPlayerTurn(){

        DatabaseReference opponentUidReference = FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid()).child("multiplayerRoom");
        opponentUidReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    opponentUid = dataSnapshot.getKey();

                }
                opponentUidReference.child(opponentUid).child("opponentsTurn").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.getValue(Integer.class)==0){
                            nextPlayerButton.setVisibility(View.VISIBLE);
                        } else{
                            nextPlayerButton.setVisibility(View.INVISIBLE);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void updateCurrentPlayerNumber() {
        if (gameMode.getCurrentPlayerNumber() == 1) {
            playerName.setText(gameMode.getPlayersNames()[1]);
            gameMode.setCurrentPlayerNumber(2);
        } else {
            gameMode.setCurrentPlayerNumber(1);
            playerName.setText(gameMode.getPlayersNames()[0]);
        }
        gameMode.prepareCombinationsSlots();
    }
}