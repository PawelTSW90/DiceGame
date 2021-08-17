package com.paweldyjak.dicegame.Fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.GameModes.HotSeatGame;
import com.paweldyjak.dicegame.R;
import com.paweldyjak.dicegame.Sounds;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FinalResultScreenFragment extends Fragment {
    private final GameBoardActivity gameBoardActivity;
    private final HotSeatGame hotSeatGame;
    private Sounds sounds;
    private final TextView[] playersTextViews = new TextView[6];
    private Button rematchButton;
    private Button exitButton;

    public FinalResultScreenFragment(GameBoardActivity gameBoardActivity, HotSeatGame hotSeatGame) {
        this.gameBoardActivity = gameBoardActivity;
        this.hotSeatGame = hotSeatGame;

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.final_result_screen_fragment, container, false);
        sounds = new Sounds(gameBoardActivity);
        playersTextViews[0] = view.findViewById(R.id.winner_textview);
        playersTextViews[1] = view.findViewById(R.id.player_two_textview);
        playersTextViews[2] = view.findViewById(R.id.player_three_textview);
        playersTextViews[3] = view.findViewById(R.id.player_four_textview);
        playersTextViews[4] = view.findViewById(R.id.player_five_textview);
        playersTextViews[5] = view.findViewById(R.id.player_six_textview);
        rematchButton = view.findViewById(R.id.rematch_button);
        exitButton = view.findViewById(R.id.exit_button);
        setFinalResultScreen();
        return view;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setFinalResultScreen() {
        int numberOfPlayers = hotSeatGame.getNumberOfPlayers();
        int[] playersTotalScore = hotSeatGame.getPlayersScore();
        String[] playersNames = hotSeatGame.getPlayersNames();
        Map<String, Integer> playersResults = new HashMap<>();

        for (int x = 0; x < numberOfPlayers; x++) {
            playersResults.put(playersNames[x], playersTotalScore[x]);
        }
        checkPlayersResults(playersResults);

        for(int x = 0; x<playersResults.size(); x++){
            switch (x){
                case 0:
                    
            }
        }

        sounds.playFinalResultSound();
        String winnerPlayer;
        int winnerScore;
        if (hotSeatGame.getPlayersTotalScore(1) > hotSeatGame.getPlayersTotalScore(2)) {
            winnerPlayer = hotSeatGame.getPlayersNames()[0];
            winnerScore = hotSeatGame.getPlayersTotalScore(1);

        } else {
            winnerPlayer = hotSeatGame.getPlayersNames()[1];
            winnerScore = hotSeatGame.getPlayersTotalScore(2);
        }
        playersTextViews[0].setText(winnerPlayer + "-" + winnerScore + " " + R.string.points);
        gameBoardActivity.showNewTurnScreen(true);


    }

    public void setButtons() {
        exitButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), GameBoardActivity.class);
            startActivity(intent);
            getActivity().finish();

        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Map<String, Integer> checkPlayersResults(Map<String, Integer> playersResults) {
        return playersResults.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }


}
