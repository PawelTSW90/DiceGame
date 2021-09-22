package com.paweldyjak.dicegame.Activities;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.ValueEventListener;
import com.paweldyjak.dicegame.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;

public class MultiplayerQueueActivity extends AppCompatActivity {
    private DatabaseReference multiplayerQueueReference;
    private TextView opponentFoundTextView;
    private int playersInQueue;
    private String[] playersNames;
    private String opponentUid;
    private String playerUid;
    private String opponentName;
    private String playerName;
    private int playerRanking;
    private int opponentRanking;
    private DatabaseReference databaseReference;

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
        playersNames = new String[2];
        databaseReference = FirebaseDatabase.getInstance().getReference();
        joinMultiplayerQueue();
        updatePlayersInQueueNumber();
        setPlayerName();
        lookForOpponentPlayer();

    }

    public void joinMultiplayerQueue() {
        multiplayerQueueReference.child("playersUid").child(playerUid).setValue(0);
    }

    public void updatePlayersInQueueNumber() {
        multiplayerQueueReference.child("playersInQueue").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (opponentName == null) {
                    playersInQueue = snapshot.getValue(Integer.class);
                    playersInQueue += 1;
                    multiplayerQueueReference.child("playersInQueue").setValue(playersInQueue);
                } else {
                    playersInQueue = snapshot.getValue(Integer.class);
                    playersInQueue -= 1;
                    multiplayerQueueReference.child("playersInQueue").setValue(playersInQueue);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void setPlayerName() {
        databaseReference.child("users").child(playerUid).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                playerName = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void lookForOpponentPlayer() {
        multiplayerQueueReference.child("playersUid").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (!dataSnapshot.getKey().equals(playerUid)) {
                        opponentUid = dataSnapshot.getKey();
                        createMultiplayerRoom();
                        getOpponentName();
                        updatePlayersInQueueNumber();
                        setPlayersOrder();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        if (opponentName != null) {
            Executor executor = ContextCompat.getMainExecutor(this);
            executor.execute(this::lookForOpponentPlayer);
        }
    }

    public void createMultiplayerRoom() {
        Map<String, Integer> opponentData = new HashMap<>();
        opponentData.put("opponentTurn", 0);
        opponentData.put("opponentTurnStarted", 0);
        opponentData.put("combinationsPoints", 0);
        opponentData.put("isCombinationActive", 0);
        opponentData.put("combinationsSlots", 0);
        opponentData.put("dices", 0);
        opponentData.put("totalScore", 0);
        databaseReference.child("users").child(opponentUid).child("multiplayerRoom").child(playerUid).setValue(opponentData);
        setCombinationPointsValues();
        setIsCombinationActiveValues();
        setCombinationsSlotsValues();
        setDices();
    }

    public void getOpponentName() {
        databaseReference.child("users").child(opponentUid).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                opponentName = snapshot.getValue(String.class);
                opponentFoundTextView.setText(opponentName);
                multiplayerQueueReference.child("playersUid").removeValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
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
        intent.putExtra("opponentUid", opponentUid);
        startActivity(intent);
    }

    //sort players by ranking
    public void setPlayersOrder() {
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
                if (playerRanking > opponentRanking) {
                    playersNames[0] = playerName;
                    playersNames[1] = opponentName;
                } else if (opponentRanking > playerRanking) {
                    playersNames[0] = opponentName;
                    playersNames[1] = playerName;
                } else {
                    //if players ranking is the same, sort players by alphabetic order
                    List<String> players = new ArrayList<>();
                    players.add(playerName);
                    players.add(opponentName);
                    Collections.sort(players);
                    playersNames[0] = players.get(0);
                    playersNames[1] = players.get(1);
                }
                updatePlayerTurnDatabaseValue();
                startMultiplayerGame(playersNames);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void updatePlayerTurnDatabaseValue() {
        if (playersNames[0].equals(playerName)) {
            databaseReference.child("users").child(opponentUid).child("multiplayerRoom").child(playerUid).child("opponentTurn").setValue(1);
        }

    }

    public void setCombinationPointsValues() {
        Map<String, Integer> valuesMap = new HashMap<>();
        for (int x = 0; x < 16; x++) {
            valuesMap.put(String.valueOf(x + 1), 0);
        }
        databaseReference.child("users").child(opponentUid).child("multiplayerRoom").child(playerUid).child("combinationsPoints").setValue(valuesMap);


    }

    public void setIsCombinationActiveValues() {
        Map<String, Boolean> valuesMap = new HashMap<>();
        for (int x = 0; x < 16; x++) {
            valuesMap.put(String.valueOf(x + 1), true);
        }
        databaseReference.child("users").child(opponentUid).child("multiplayerRoom").child(playerUid).child("isCombinationActive").setValue(valuesMap);

    }

    public void setCombinationsSlotsValues(){
        Map<String, Integer> valuesMap = new HashMap<>();
        for(int x = 0; x<16; x++){
            valuesMap.put(String.valueOf(x+1), 0);
        }
        databaseReference.child("users").child(opponentUid).child("multiplayerRoom").child(playerUid).child("combinationsSlots").setValue(valuesMap);
    }

    public void setDices() {
        Map<String, Integer> valuesMap = new HashMap<>();
        for (int x = 0; x < 5; x++) {
            valuesMap.put(String.valueOf(x + 1), 0);
        }
        databaseReference.child("users").child(opponentUid).child("multiplayerRoom").child(playerUid).child("dices").setValue(valuesMap);
    }


}