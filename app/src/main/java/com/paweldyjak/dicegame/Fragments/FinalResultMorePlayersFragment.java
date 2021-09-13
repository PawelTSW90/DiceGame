package com.paweldyjak.dicegame.Fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.Activities.MainMenuActivity;
import com.paweldyjak.dicegame.GameModes.GameMode;
import com.paweldyjak.dicegame.R;
import com.paweldyjak.dicegame.Sounds;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FinalResultMorePlayersFragment extends Fragment {
    private final GameBoardActivity gameBoardActivity;
    private final GameMode gameMode;
    private Sounds sounds;
    private final TextView[] playersTextViews = new TextView[6];
    private TextView winnerPlayer;
    private Button rematchButton;
    private Button exitButton;
    private ImageView bronzeMedalImageView;
    private ImageView silverMedalImageVIew;

    public FinalResultMorePlayersFragment(GameBoardActivity gameBoardActivity, GameMode gameMode) {
        this.gameBoardActivity = gameBoardActivity;
        this.gameMode = gameMode;

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_final_result_screen_more_players, container, false);
        sounds = new Sounds(gameBoardActivity);
        playersTextViews[0] = view.findViewById(R.id.first_place_more_players);
        playersTextViews[1] = view.findViewById(R.id.second_place_more_players);
        playersTextViews[2] = view.findViewById(R.id.third_place_more_players);
        playersTextViews[3] = view.findViewById(R.id.fourth_place_more_players);
        playersTextViews[4] = view.findViewById(R.id.fifth_place_more_players);
        playersTextViews[5] = view.findViewById(R.id.sixth_place_more_players);
        winnerPlayer = view.findViewById(R.id.winner_name_more_players);
        rematchButton = view.findViewById(R.id.rematch_button_more_players);
        exitButton = view.findViewById(R.id.exit_button_more_players);
        bronzeMedalImageView = view.findViewById(R.id.bronzeMedal);
        silverMedalImageVIew = view.findViewById(R.id.silverMedal);
        setButtons();
        setPlayersPositions();
        return view;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setPlayersPositions() {
        sounds.playFinalResultSound();
        int numberOfPlayers = gameMode.getNumberOfPlayers();
        int playerNr = 0;
        int[] sortedResults = new int[6];
        int[] playersTotalScore = gameMode.getPlayersScore();
        String[] playersNames = gameMode.getPlayersNames();
        Map<String, Integer> unsortedPlayersResults = new HashMap<>();

        for (int x = 0; x < numberOfPlayers; x++) {
            unsortedPlayersResults.put(playersNames[x], playersTotalScore[x]);
        }
        LinkedHashMap<String, Integer> sortedPlayersWithResults;
        sortedPlayersWithResults = sortPlayersResult(unsortedPlayersResults);
        for (Map.Entry<String, Integer> entry : sortedPlayersWithResults.entrySet()) {
            String name = entry.getKey();
            Integer score = entry.getValue();
            switch (playerNr) {
                case 0:
                    winnerPlayer.setText(gameBoardActivity.getString(R.string.the_winner_is) + " " + name + " !!!");
                    playersTextViews[playerNr].setText(name + "\n" + score + " " + gameBoardActivity.getString(R.string.points));
                    sortedResults[0] = score;
                    playerNr++;
                    break;
                case 1:
                    playersTextViews[playerNr].setText(name + "\n" + score + " " + gameBoardActivity.getString(R.string.points));
                    sortedResults[1] = score;
                    if (sortedResults[0] == sortedResults[1]) {
                        winnerPlayer.setText(R.string.draw);
                        silverMedalImageVIew.setImageResource(R.drawable.gold_medal);
                    }
                    playerNr++;
                    break;
                case 2:
                    playersTextViews[playerNr].setText(name + "\n" + score + " " + gameBoardActivity.getString(R.string.points));
                    sortedResults[2] = score;
                    if (sortedResults[1] == score && sortedResults[1] != sortedResults[0]) {
                        bronzeMedalImageView.setImageResource(R.drawable.silver_medal);
                    } else if (sortedResults[2] == sortedResults[0]) {
                        bronzeMedalImageView.setImageResource(R.drawable.gold_medal);
                    }
                    playerNr++;
                    break;
                case 3:
                    sortedResults[3] = score;
                    if (sortedResults[3] != sortedResults[2]) {
                        playersTextViews[playerNr].setText("4." + name + " - " + score + " " + gameBoardActivity.getString(R.string.points));
                    } else {

                        if (sortedResults[3] == sortedResults[0]) {
                            playersTextViews[playerNr].setText("1. " + name + " - " + score + " " + gameBoardActivity.getString(R.string.points));
                        } else if(sortedResults[3]==sortedResults[1]){
                            playersTextViews[playerNr].setText("2. " + name + " - " + score + " " + gameBoardActivity.getString(R.string.points));
                        } else if(sortedResults[3]==sortedResults[2]){
                            playersTextViews[playerNr].setText("3. " + name + " - " + score + " " + gameBoardActivity.getString(R.string.points));
                        }
                    }
                    playerNr++;
                    break;
                case 4:
                    sortedResults[4] = score;
                    if (sortedResults[4] != sortedResults[3]) {
                        playersTextViews[playerNr].setText("5." + name + " - " + score + " " + gameBoardActivity.getString(R.string.points));
                    } else {

                        if (sortedResults[4] == sortedResults[0]) {
                            playersTextViews[playerNr].setText("1. " + name + " - " + score + " " + gameBoardActivity.getString(R.string.points));
                        } else if(sortedResults[3]==sortedResults[1]){
                            playersTextViews[playerNr].setText("2. " + name + " - " + score + " " + gameBoardActivity.getString(R.string.points));
                        } else if(sortedResults[3]==sortedResults[2]){
                            playersTextViews[playerNr].setText("3. " + name + " - " + score + " " + gameBoardActivity.getString(R.string.points));
                        } else if(sortedResults[3]==sortedResults[3]){
                            playersTextViews[playerNr].setText("4. " + name + " - " + score + " " + gameBoardActivity.getString(R.string.points));
                        }
                    }
                    playerNr++;
                    break;
                case 5:
                    sortedResults[5] = score;
                    if (sortedResults[5] != sortedResults[4]) {
                        playersTextViews[playerNr].setText("6." + name + " - " + score + " " + gameBoardActivity.getString(R.string.points));
                    } else {

                        if (sortedResults[5] == sortedResults[0]) {
                            playersTextViews[playerNr].setText("1. " + name + " - " + score + " " + gameBoardActivity.getString(R.string.points));
                        } else if(sortedResults[5]==sortedResults[1]){
                            playersTextViews[playerNr].setText("2. " + name + " - " + score + " " + gameBoardActivity.getString(R.string.points));
                        } else if(sortedResults[5]==sortedResults[2]){
                            playersTextViews[playerNr].setText("3. " + name + " - " + score + " " + gameBoardActivity.getString(R.string.points));
                        } else if(sortedResults[5]==sortedResults[3]){
                            playersTextViews[playerNr].setText("4. " + name + " - " + score + " " + gameBoardActivity.getString(R.string.points));
                        } else if(sortedResults[5]==sortedResults[4]){
                            playersTextViews[playerNr].setText("5. " + name + " - " + score + " " + gameBoardActivity.getString(R.string.points));
                        }
                    }
                    playerNr++;
                    break;
            }


        }
        gameBoardActivity.showNextTurnFragment();


    }

    public void setButtons() {
        exitButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MainMenuActivity.class);
            startActivity(intent);
            getActivity().finish();

        });

        rematchButton.setOnClickListener(v -> {
            StartHotSeatGameFragment startHotSeatGameFragment = new StartHotSeatGameFragment(gameBoardActivity, gameMode.getPlayersNames(), gameMode.getNumberOfPlayers());
            gameBoardActivity.replaceFragment(R.id.fragment_layout, startHotSeatGameFragment);
            gameBoardActivity.hideFragment();


        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public LinkedHashMap<String, Integer> sortPlayersResult(Map<String, Integer> unsortedPlayersMap) {
        LinkedHashMap<String, Integer> sortedPlayersMap = new LinkedHashMap<>();
        unsortedPlayersMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedPlayersMap.put(x.getKey(), x.getValue()));
        return sortedPlayersMap;
    }
}
