package com.paweldyjak.dicegame.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
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
    private DatabaseReference multiplayerRoomReference;
    private final UIConfig uiConfig;
    private TextView playerName;
    private Button nextPlayerButton;
    private final GameBoardActivity gameBoardActivity;
    private final GameMode gameMode;
    private final String opponentUid;
    private boolean opponentTurn;


    public MultiplayerTurnScreenFragment(GameBoardActivity gameBoardActivity, UIConfig uiConfig, GameMode gameMode, String opponentUid) {
        this.gameBoardActivity = gameBoardActivity;
        this.uiConfig = uiConfig;
        this.gameMode = gameMode;
        this.opponentUid = opponentUid;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multiplayer_turn_screen, container, false);
        playerName = view.findViewById(R.id.multiplayer_player_turn_textview);
        nextPlayerButton = view.findViewById(R.id.multiplayer_player_turn_button);
        multiplayerRoomReference =FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid())
                .child("multiplayerRoom").child(opponentUid);
        setNextPlayerName();
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
            updateBoardPlayerName();
            gameMode.prepareScoreBoard();
            updateOpponentTurnStartedValue();
            gameBoardActivity.hideFragment();
        });

    }

    public void updateBoardPlayerName(){
        if(gameMode.getCurrentPlayerNumber()==1){
            uiConfig.setCurrentPlayerName(gameMode.getPlayersNames()[1]);
            gameMode.setCurrentPlayerNumber(2);
        } else{
            uiConfig.setCurrentPlayerName(gameMode.getPlayersNames()[0]);
            gameMode.setCurrentPlayerNumber(1);
        }
        gameMode.prepareCombinationsSlots();
    }

    public void checkPlayerTurn() {
        DatabaseReference opponentTurnStarted = multiplayerRoomReference.child("opponentTurn");
        opponentTurnStarted.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue(Integer.class) == 0) {
                    nextPlayerButton.setVisibility(View.VISIBLE);
                    opponentTurn = false;

                } else {
                    nextPlayerButton.setVisibility(View.INVISIBLE);
                    opponentTurn = true;
                }
                displayTurnMessage(opponentTurn);
                displayOpponentBoard();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void setNextPlayerName(){
        if(gameMode.getCurrentPlayerNumber()==1){
            playerName.setText(gameMode.getPlayersNames()[1]);
        } else{
            playerName.setText(gameMode.getPlayersNames()[0]);
        }
    }




    //update opponentTurnStarted value in opponents database record
    public void updateOpponentTurnStartedValue() {
        DatabaseReference opponentTurnStartedFieldReference = FirebaseDatabase.getInstance().getReference().child("users").child(opponentUid).child("multiplayerRoom")
                .child(FirebaseAuth.getInstance().getUid()).child("opponentTurnStarted");
        opponentTurnStartedFieldReference.setValue(1);
    }

    public void displayOpponentBoard(){
        DatabaseReference opponentTurnStartedReference = multiplayerRoomReference.child("opponentTurnStarted");
        opponentTurnStartedReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue(Integer.class)==1){
                    gameMode.prepareScoreBoard();
                    gameBoardActivity.hideFragment();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}