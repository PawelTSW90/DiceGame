package com.paweldyjak.dicegame.GameModes;

import android.content.Context;
import android.widget.TextView;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.R;
import com.paweldyjak.dicegame.UIConfig;
import java.util.Arrays;

public class HotSeat {
    private final UIConfig uiConfig;
    private final Context context;
    private final GameBoardActivity gameBoardActivity;
    private TextView currentPlayerName;
    private TextView totalScoreTextView;
    private String[] playersNames;
    private int numberOfPlayers;
    private int currentPlayerNumber = 6;
    private final int[][] playersCombinationsScore = new int[6][16];
    private final int[][] playersCombinationsSlots = new int[6][16];
    private final boolean[][] playersIsCombinationActive = new boolean[6][16];
    private final int[] playersTotalScore = new int[6];

    public HotSeat(Context context, UIConfig uiConfig, GameBoardActivity gameBoardActivity){
        this.uiConfig = uiConfig;
        this.context = context;
        this.gameBoardActivity = gameBoardActivity;
    }

    public void setAllCombinationsAsActive() {
        for (boolean[] row: playersIsCombinationActive)
            Arrays.fill(row, true);
    }

    public void setTotalScore(int score) {
        String string = gameBoardActivity.getResources().getString(R.string.points);
        switch (currentPlayerNumber) {
            case 1:
                playersTotalScore[0] += score;
                totalScoreTextView.setText(playersTotalScore[0] + " " + string);
                break;
            case 2:
                playersTotalScore[1] += score;
                totalScoreTextView.setText(playersTotalScore[1] + " " + string);
                break;

            case 3:
                playersTotalScore[2] += score;
                totalScoreTextView.setText(playersTotalScore[2] + " " + string);
                break;

            case 4:
                playersTotalScore[3] += score;
                totalScoreTextView.setText(playersTotalScore[3] + " " + string);
                break;

            case 5:
                playersTotalScore[4] += score;
                totalScoreTextView.setText(playersTotalScore[4] + " " + string);
                break;

            case 6:
                playersTotalScore[5] += score;
                totalScoreTextView.setText(playersTotalScore[5] + " " + string);
                break;
        }

    }



}
