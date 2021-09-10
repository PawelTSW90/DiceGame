package com.paweldyjak.dicegame.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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
import com.paweldyjak.dicegame.GameModes.MultiplayerGame;

import java.util.Objects;
import java.util.concurrent.Executor;

public class MultiplayerQueueActivity extends AppCompatActivity {
    private DatabaseReference multiplayerQueueReference;
    private TextView opponentFoundTextView;
    private int playersInQueue;
    private String opponentUid;
    private String playerUid;
    private String opponentName;
    private String[] playersNames;
    private DatabaseReference multiplayerRoomReference;
    private DatabaseReference playerNameReference;

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
        playerNameReference = FirebaseDatabase.getInstance().getReference().child("users").child(playerUid);
        joinMultiplayerQueue();
        lookForOpponentPlayer();
    }


    public void joinMultiplayerQueue() {
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
                        multiplayerRoomReference = FirebaseDatabase.getInstance().getReference().child("users").child(opponentUid).child("MultiplayerRoom").child(playerUid);
                        multiplayerRoomReference.setValue(0);
                        opponentNameReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                opponentName = snapshot.getValue(String.class);
                                opponentFoundTextView.setText(opponentName);
                                playersInQueue -= 2;
                                multiplayerQueueReference.child("playersInQueue").setValue(playersInQueue);
                                multiplayerQueueReference.child("playersUid").removeValue();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        break;

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

    public void startMultiplayerGame() {
        Executor executor = ContextCompat.getMainExecutor(this);
        executor.execute(() -> {
            try {
                Thread.sleep(5000);
                GameBoardActivity gameBoardActivity = new GameBoardActivity();
                UIConfig uiConfig = new UIConfig(gameBoardActivity);
                MultiplayerGame multiplayerGame = new MultiplayerGame(uiConfig, gameBoardActivity, playersNames);
                RerollDices rerollDices = new RerollDices(uiConfig);
                DicesCombinationsChecker dicesCombinationsChecker = new DicesCombinationsChecker(multiplayerGame);
                ScoreInputSetter scoreInputSetter = new ScoreInputSetter(gameBoardActivity, uiConfig, multiplayerGame);
                GameBoardManager gameBoardManager = new GameBoardManager(gameBoardActivity, gameBoardActivity, scoreInputSetter, dicesCombinationsChecker, uiConfig, rerollDices, multiplayerGame);
                //configuring UI
                uiConfig.setComponents();
                playersNames[0] = "Pawel";
                playersNames[1] = "Marta";
                uiConfig.getCurrentPlayerName().setText(playersNames[0]);
                multiplayerGame.setPlayersNames(playersNames);
                multiplayerGame.setAllCombinationsAsActive();
                gameBoardManager.setRollDicesButton();
                gameBoardActivity.hideFragment();


            } catch (Exception e) {

            }


        });
    }

}