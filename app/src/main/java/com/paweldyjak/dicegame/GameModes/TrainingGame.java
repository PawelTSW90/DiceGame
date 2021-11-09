package com.paweldyjak.dicegame.GameModes;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.Fragments.CombinationsChartFragment;
import com.paweldyjak.dicegame.R;
import com.paweldyjak.dicegame.Sounds;
import com.paweldyjak.dicegame.UIConfig;

public class TrainingGame implements GameMode {
    GameBoardActivity gameBoardActivity;
    UIConfig uiConfig;
    Sounds sounds;
    Button nextButton;
    private TextView trainingText;
    private int trainingTextNr = 1;

    public TrainingGame(GameBoardActivity gameBoardActivity, UIConfig uiConfig) {
        this.gameBoardActivity = gameBoardActivity;
        this.uiConfig = uiConfig;
        sounds = new Sounds(gameBoardActivity);
        startTrainingMode();
    }


    public void startTrainingMode() {
        gameBoardActivity.replaceFragment(R.id.fragment_layout, new CombinationsChartFragment(gameBoardActivity, true));
        nextButton=gameBoardActivity.findViewById(R.id.next_button);
        nextButton.setVisibility(View.VISIBLE);
        trainingText = gameBoardActivity.findViewById(R.id.training_textView);
        trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_1));
        nextButton.setOnClickListener(v -> {
            switch (trainingTextNr) {
                case 1:
                    trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_2));
                    trainingTextNr++;
                    break;
                case 2:
                    trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_3));
                    trainingTextNr++;
                    break;
                case 3:
                    trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_4));
                    trainingTextNr++;
                    break;
                case 4:
                    trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_5));
                    gameBoardActivity.showFragment();
                    trainingTextNr++;
                    break;
                case 5:
                    trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_6));
                    trainingTextNr++;
                    break;
                case 6:
                    trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_7));
                    trainingTextNr++;
                    break;
                case 7:
                    trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_8));
                    trainingTextNr++;
                    break;
                case 8:
                    trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_9));
                    trainingTextNr++;
                    break;
                case 9:
                    trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_10));
                    trainingTextNr++;
                    break;
                case 10:
                    trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_11));
                    trainingTextNr++;
                    break;
                case 11:
                    trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_12));
                    trainingTextNr++;
                    break;
                case 12:
                    trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_13));
                    trainingTextNr++;
                    break;
                case 13:
                    trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_14));
                    trainingTextNr++;
                    break;
                case 14:
                    nextButton.setVisibility(View.INVISIBLE);
                    trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_15));
                    trainingTextNr++;
                    int[] dices = {4, 4, 3, 4, 5};
                    throwDices(dices);
                    break;
            }

        });
    }

    public void throwDices(int[] dices) {
        uiConfig.getRollDicesButton().setOnClickListener(v -> {
            sounds.playRollDiceSound();
            uiConfig.showDices(dices, true);
            uiConfig.combinationHighlighter(4, false);
            trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_16));


        });
        uiConfig.getCombinationsLayouts()[3].setOnClickListener(v -> {
            uiConfig.updateCombinationsUI(1, 3);
            sounds.playCompleteCombinationSound();
            uiConfig.combinationHighlighter(4, true);
            uiConfig.getCombinationsPoints()[3].setText(gameBoardActivity.getResources().getString(R.string.points_value, 24));
            uiConfig.setTotalScore(24);
            trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_17));
            uiConfig.setDicesVisibility(false, true);
            uiConfig.getRollDicesButton().setOnClickListener(v1 -> {

            });
        });


    }


    @Override
    public boolean checkIfAllCombinationsAreDone() {
        return false;
    }

    @Override
    public void setFinalResultScreen() {

    }

    @Override
    public void setAllCombinationsAsActive() {

    }

    @Override
    public void setTotalScore(int score) {

    }

    @Override
    public void setCombinationsPointsValues(int score, int combinationNr) {

    }

    @Override
    public void setCombinationsSlots(int combinationsSlotNumber, int slotStatus) {

    }

    @Override
    public void setNumberOfPlayers(int numberOfPlayers) {

    }

    @Override
    public void setPlayersNames(String[] playersNames) {

    }

    @Override
    public void setCurrentPlayerNumber(int currentPlayerNumber) {

    }

    @Override
    public int getCombinationsPointsValues(int playerNumber, int combinationNumber) {
        return 0;
    }

    @Override
    public int[][] getCombinationsSlotsValues() {
        return new int[0][];
    }

    @Override
    public String[] getPlayersNames() {
        return new String[0];
    }

    @Override
    public int getNumberOfPlayers() {
        return 0;
    }

    @Override
    public int getPlayersTotalScore(int playerNumber) {
        return 0;
    }

    @Override
    public int[] getPlayersScore() {
        return new int[0];
    }

    @Override
    public int getCurrentPlayerNumber() {
        return 0;
    }
}
