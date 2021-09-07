package com.paweldyjak.dicegame.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paweldyjak.dicegame.*;

import java.util.Objects;
import java.util.concurrent.Executor;

public class MultiplayerQueueActivity extends AppCompatActivity {
    private DatabaseReference multiplayerQueueReference;
    private TextView opponentFoundTextView;
    private int playersInQueue;
    private String opponentUid;
    private String playerUid;
    private boolean opponentFound;

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
        joinMultiplayerQueue();
        lookForOpponentPlayer();
    }


    public void joinMultiplayerQueue() {
        multiplayerQueueReference.child("playersInQueue").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                playersInQueue = snapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        multiplayerQueueReference.child("playersUid").child(playerUid).setValue(0);
        multiplayerQueueReference.child("playersInQueue").setValue(playersInQueue + 1);

    }

    public void lookForOpponentPlayer() {
        multiplayerQueueReference.child("playersUid").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (!dataSnapshot.getKey().equals(playerUid)) {
                        opponentUid = dataSnapshot.getKey();
                        opponentFound = true;
                        opponentFoundTextView.setText(opponentUid);
                        break;

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        if(!opponentFoundTextView.getText().equals(getResources().getString(R.string.opponent_search))){
            Executor executor = ContextCompat.getMainExecutor(this);
            executor.execute(this::lookForOpponentPlayer);

        }

    }

}