package com.paweldyjak.dicegame;

import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.GameModes.GameMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public class OpponentOnlineUIConfig {
    private final String playerUid = FirebaseAuth.getInstance().getUid();
    private final GameBoardActivity gameBoardActivity;
    private final UIConfig uiConfig;
    private List<Integer> opponentDices = new ArrayList<>(5);
    private final String opponentUid;
    private Sounds sounds;

    public OpponentOnlineUIConfig(GameBoardActivity gameBoardActivity, UIConfig uiConfig, String opponentUid) {
        this.gameBoardActivity = gameBoardActivity;
        this.uiConfig = uiConfig;
        this.opponentUid = opponentUid;

    }

    public void displayOpponentScreen() {
        gameBoardActivity.getGameBoardManager().hideRollDices();
        sounds = new Sounds(gameBoardActivity);
        uiConfig.hideDices();
        checkOpponentDices();
        checkOpponentCombinations();
        checkOpponentTotalScore();
        checkOpponentIsCombinationActive();
        checkOpponentCombinationSlot();

    }

    public void checkOpponentDices() {
        FirebaseDatabase.getInstance().getReference().child("users").child(playerUid).child("multiplayerRoom").child(opponentUid).child("dices").addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                opponentDices = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    opponentDices.add(dataSnapshot.getValue(Integer.class));
                }
                displayOpponentDices();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void updateDatabaseWithDicesValues(int[] dicesValues) {
        Map<String, Integer> dices = new HashMap<>();
        for (int x = 0; x < 5; x++) {
            dices.put(String.valueOf(x + 1), dicesValues[x]);
        }

        FirebaseDatabase.getInstance().getReference().child("users").child(opponentUid).child("multiplayerRoom").child(playerUid).child("dices").setValue(dices);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void displayOpponentDices() {
        boolean containsZero = false;

        for (int x = 0; x < 5; x++) {
            uiConfig.getDicesSlots()[x].setImageResource(0);
        }


        int valueToDisplay;
        for (int x = 0; x < 5; x++) {

            valueToDisplay = opponentDices.get(x);


            for (int y = 0; y < 5; y++) {
                if (uiConfig.getDicesSlots()[y].getDrawable() == null) {


                    switch (valueToDisplay) {
                        case 1:
                            uiConfig.getDicesSlots()[y].setImageResource(R.drawable.dice1);
                            y = 5;
                            break;
                        case 2:
                            uiConfig.getDicesSlots()[y].setImageResource(R.drawable.dice2);
                            y = 5;
                            break;
                        case 3:
                            uiConfig.getDicesSlots()[y].setImageResource(R.drawable.dice3);
                            y = 5;
                            break;
                        case 4:
                            uiConfig.getDicesSlots()[y].setImageResource(R.drawable.dice4);
                            y = 5;
                            break;
                        case 5:
                            uiConfig.getDicesSlots()[y].setImageResource(R.drawable.dice5);
                            y = 5;
                            break;
                        case 6:
                            uiConfig.getDicesSlots()[y].setImageResource(R.drawable.dice6);
                            y = 5;
                            break;
                    }

                }
            }
        }
        //play roll dices sound (ignore on start database read read)
        for (int z = 0; z < 5; z++) {
            if (opponentDices.get(z) == 0) {
                containsZero = true;
                break;
            }
        }
        if (!containsZero) {
            sounds.playRollDiceSound();
        }

    }


    public void checkOpponentCombinations() {
        FirebaseDatabase.getInstance().getReference().child("users").child(playerUid).child("multiplayerRoom").child(opponentUid)
                .child("combinationsPoints").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Integer> pointsMap = new LinkedHashMap<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    pointsMap.put(dataSnapshot.getKey(), dataSnapshot.getValue(Integer.class));
                }
                displayOpponentCombinationsPoints(pointsMap);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void displayOpponentCombinationsPoints(Map<String, Integer> pointsMap) {
        int tmp;
        for (int x = 0; x < 16; x++) {
            tmp = x + 1;
            String tmpValue = String.valueOf(tmp);

            uiConfig.getCombinationsPointsTextView()[x].setText(pointsMap.get(tmpValue) + " " + gameBoardActivity.getString(R.string.points));
        }


    }

    public void checkOpponentTotalScore() {
        FirebaseDatabase.getInstance().getReference().child("users").child(playerUid).child("multiplayerRoom")
                .child(opponentUid).child("totalScore").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int totalScore;
                totalScore = snapshot.getValue(Integer.class);
                displayOpponentTotalScore(totalScore);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void displayOpponentTotalScore(int totalScore) {
        if (totalScore != 0) {
            sounds.playCompleteCombinationSound();
            uiConfig.getTotalScoreTextView().setText(totalScore + " " + gameBoardActivity.getString(R.string.points));

        }

    }


    public void checkOpponentIsCombinationActive() {
        FirebaseDatabase.getInstance().getReference().child("users").child(playerUid).child("multiplayerRoom")
                .child(opponentUid).child("isCombinationActive").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Boolean> valuesMap = new LinkedHashMap<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    valuesMap.put(dataSnapshot.getKey(), dataSnapshot.getValue(Boolean.class));
                }
                displayOpponentIsCombinationActive(valuesMap);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void displayOpponentIsCombinationActive(Map<String, Boolean> valuesMap) {
        int tmp;
        for (int x = 0; x < 16; x++) {
            tmp = x + 1;
            if (!valuesMap.get(String.valueOf(tmp))) {
                uiConfig.getCombinationsTextView()[x].setEnabled(false);
            }
        }
    }

    public void checkOpponentCombinationSlot() {
        FirebaseDatabase.getInstance().getReference().child("users").child(playerUid).child("multiplayerRoom")
                .child(opponentUid).child("combinationsSlots").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Integer> valuesMap = new LinkedHashMap<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    valuesMap.put(dataSnapshot.getKey(), dataSnapshot.getValue(Integer.class));
                }
                displayOpponentCombinationSlot(valuesMap);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void displayOpponentCombinationSlot(Map<String, Integer> valuesMap) {
        Executor executor = ContextCompat.getMainExecutor(gameBoardActivity);

        int tmp;
        for (int x = 0; x < 16; x++) {
            tmp = x + 1;
            String tmpValue = String.valueOf(tmp);
            if (valuesMap.get(tmpValue) == 1) {
                uiConfig.getCombinationsSlots()[x].setText("\u2713");
                uiConfig.getCombinationsSlots()[x].setGravity(Gravity.CENTER);
                uiConfig.getCombinationsSlots()[x].setTextSize(16);
                uiConfig.getCombinationsSlots()[x].setTextColor(Color.rgb(27, 182, 33));
                executor.execute(() -> {
                    try {
                        Thread.sleep(2000);
                        updatePlayerTurnDatabase();
                        gameBoardActivity.showNextTurnFragment();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                });

            } else if (valuesMap.get(tmpValue) == 2) {
                uiConfig.getCombinationsSlots()[x].setText("X");
                uiConfig.getCombinationsSlots()[x].setGravity(Gravity.CENTER);
                uiConfig.getCombinationsSlots()[x].setTextSize(16);
                uiConfig.getCombinationsSlots()[x].setTextColor(Color.rgb(140, 17, 17));
                uiConfig.getCombinationsPointsTextView()[x].setEnabled(false);
                executor.execute(() -> {
                    try {
                        Thread.sleep(2000);
                        updatePlayerTurnDatabase();
                        gameBoardActivity.showNextTurnFragment();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                });

            }
        }
    }

    public void updatePlayerTurnDatabase(){
        FirebaseDatabase.getInstance().getReference().child("users").child(opponentUid).child("multiplayerRoom").child(playerUid).child("opponentTurn").setValue(1);
    }


}
