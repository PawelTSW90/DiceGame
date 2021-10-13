package com.paweldyjak.dicegame.Fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
import com.paweldyjak.dicegame.GameBoardManager;
import com.paweldyjak.dicegame.GameModes.GameMode;
import com.paweldyjak.dicegame.R;
import com.paweldyjak.dicegame.UIConfig;


public class MultiplayerTurnScreenFragment extends Fragment {
    private DatabaseReference multiplayerRoomReference;
    private final UIConfig uiConfig;
    private TextView playerNameTextview;
    private Button nextPlayerButton;
    private final GameBoardActivity gameBoardActivity;
    private final GameMode gameMode;
    private final GameBoardManager gameBoardManager;
    private final String opponentUid;
    private boolean displayBoardListenerRunning = false;


    public MultiplayerTurnScreenFragment(GameBoardActivity gameBoardActivity, UIConfig uiConfig, GameMode gameMode, GameBoardManager gameBoardManager, String opponentUid) {
        this.gameBoardActivity = gameBoardActivity;
        this.uiConfig = uiConfig;
        this.gameMode = gameMode;
        this.gameBoardManager = gameBoardManager;
        this.opponentUid = opponentUid;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multiplayer_turn_screen, container, false);
        playerNameTextview = view.findViewById(R.id.multiplayer_player_turn_textview);
        nextPlayerButton = view.findViewById(R.id.multiplayer_player_turn_button);
        multiplayerRoomReference =FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid())
                .child("multiplayerRoom").child(opponentUid);
        displayTurnMessage();
        return view;
    }


    public void displayTurnMessage() {
        setNextPlayerName();
        DatabaseReference opponentTurnStarted = multiplayerRoomReference.child("opponentTurn");
        opponentTurnStarted.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue(Integer.class) == 0) {
                    nextPlayerButton.setVisibility(View.VISIBLE);

                } else if (snapshot.getValue(Integer.class)==1) {
                    nextPlayerButton.setVisibility(View.INVISIBLE);
                    if(!displayBoardListenerRunning) {
                        displayOpponentBoard();
                        displayBoardListenerRunning = true;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        nextPlayerButton.setOnClickListener(v -> {
            gameBoardManager.updatePlayerBoard();
            updatePlayerTurnStartedValue();
            uiConfig.setRollDicesVisibility(true);
            uiConfig.setDicesVisibility(false);
            gameBoardActivity.hideFragment();
        });

    }



    public void setNextPlayerName(){
        if(gameMode.getCurrentPlayerNumber()==1){
            playerNameTextview.setText(gameMode.getPlayersNames()[0]);
        } else{
            playerNameTextview.setText(gameMode.getPlayersNames()[1]);
        }
    }




    //update opponentTurnStarted value in opponents database record
    public void updatePlayerTurnStartedValue() {
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
                    gameBoardManager.updatePlayerBoard();
                    gameBoardActivity.getOpponentOnlineUIConfig().displayOpponentScreen();
                    gameBoardActivity.hideFragment();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}