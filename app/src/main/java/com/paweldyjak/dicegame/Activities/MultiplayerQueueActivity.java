package com.paweldyjak.dicegame.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.paweldyjak.dicegame.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

public class MultiplayerQueueActivity extends AppCompatActivity {
    private DatabaseReference multiplayerQueueReference;
    private TextView opponentFoundTextView;
    private int playersInQueue;
    private String opponentUid;
    private String playerUid;
    private String opponentName;
    private String playerName;
    private String[] playersNames;
    private DatabaseReference multiplayerRoomReference;
    private DatabaseReference playerNameReference;
    private int playerRanking;
    private int opponentRanking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_queue);
        //hides status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hides title bar
        Objects.requireNonNull(getSupportActionBar()).hide();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        opponentFoundTextView = findViewById(R.id.user_found_textView);
        multiplayerQueueReference = FirebaseDatabase.getInstance().getReference().child("multiplayerQueue");
        playerUid = firebaseAuth.getUid();
        playerNameReference = FirebaseDatabase.getInstance().getReference().child("users").child(playerUid).child("name");
        playersNames = new String[2];
        joinMultiplayerQueue();
        lookForOpponentPlayer();
    }


    public void joinMultiplayerQueue() {
        //join multiplayer queue
        playerNameReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                playerName = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //update players in queue number
        multiplayerQueueReference.child("playersInQueue").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                playersInQueue = snapshot.getValue(Integer.class);
                playersInQueue += 1;
                multiplayerQueueReference.child("playersInQueue").setValue(playersInQueue);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //create database entry for players in queue
        multiplayerQueueReference.child("playersUid").child(playerUid).setValue(0);


    }

    public void lookForOpponentPlayer() {
        multiplayerQueueReference.child("playersUid").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (!dataSnapshot.getKey().equals(playerUid)) {
                        opponentUid = dataSnapshot.getKey();
                        DatabaseReference opponentNameReference = FirebaseDatabase.getInstance().getReference().child("users").child(opponentUid).child("name");
                        multiplayerRoomReference = FirebaseDatabase.getInstance().getReference().child("users").child(opponentUid).child("multiplayerRoom").child(playerUid);
                        multiplayerRoomReference.setValue(0);
                        opponentNameReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                opponentName = snapshot.getValue(String.class);
                                opponentName = snapshot.getValue(String.class);
                                opponentFoundTextView.setText(opponentName);
                                multiplayerQueueReference.child("playersUid").removeValue();
                                setPlayersOrder();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        multiplayerQueueReference.child("playersInQueue").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData currentData) {
                                currentData.setValue((Long) currentData.getValue() - 1);
                                return Transaction.success(currentData);
                            }

                            @Override
                            public void onComplete(@Nullable @org.jetbrains.annotations.Nullable DatabaseError error, boolean committed, @Nullable @org.jetbrains.annotations.Nullable DataSnapshot currentData) {
                            }
                        });


                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        if (!opponentFoundTextView.getText().equals(getResources().getString(R.string.opponent_search))) {
            Executor executor = ContextCompat.getMainExecutor(this);
            executor.execute(this::lookForOpponentPlayer);

        }


    }

    @Override
    public void onBackPressed() {

    }


    public void startMultiplayerGame(String[] playersNames) {
        Executor executor = ContextCompat.getMainExecutor(this);
        executor.execute(() -> {
            try {
                Thread.sleep(3000);


            } catch (Exception ignored) {

            }

        });
        GameBoardActivity gameBoardActivity = new GameBoardActivity();
        Intent intent = new Intent(this, gameBoardActivity.getClass());
        intent.putExtra("MultiplayerMode", true);
        intent.putExtra("playersNames", playersNames);
        startActivity(intent);
    }

    public void setPlayersOrder(){
        DatabaseReference playerRankingReference = FirebaseDatabase.getInstance().getReference().child("users").child(playerUid).child("ranking");
        DatabaseReference opponentRankingReference = FirebaseDatabase.getInstance().getReference().child("users").child(opponentUid).child("ranking");
        playerRankingReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                playerRanking = snapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        opponentRankingReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                opponentRanking = snapshot.getValue(Integer.class);
                if(playerRanking>opponentRanking){
                    playersNames[0] = playerName;
                    playersNames[1] = opponentName;
                } else if(opponentRanking>playerRanking){
                    playersNames[0] = opponentName;
                    playersNames[1] = playerName;
                } else{
                    List<String> players = new ArrayList<>();
                    players.add(playerName);
                    players.add(opponentName);
                    Collections.sort(players);
                    playersNames[0] = players.get(0);
                    playersNames[1] = players.get(1);

                }
                startMultiplayerGame(playersNames);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

}