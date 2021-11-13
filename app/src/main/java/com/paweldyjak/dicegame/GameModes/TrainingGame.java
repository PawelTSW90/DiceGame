package com.paweldyjak.dicegame.GameModes;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
        nextButton = gameBoardActivity.findViewById(R.id.next_button);
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
                    showNextButton(false);
                    trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_15));
                    trainingTextNr++;
                    int[] dices1 = {4, 4, 3, 4, 5};
                    throwDices(dices1);
                    break;
                case 15:
                    trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_17));
                    showNextButton(false);
                    uiConfig.getCombinationsLayouts()[3].setOnClickListener(v1 -> {
                        trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_18));
                        uiConfig.updateCombinationsUI(1, 3);
                        sounds.playCompleteCombinationSound();
                        uiConfig.combinationHighlighter(0, true);
                        uiConfig.getCombinationsPoints()[3].setText(gameBoardActivity.getResources().getString(R.string.points_value, 24));
                        uiConfig.setTotalScore(24);
                        uiConfig.setDicesVisibility(false, true);
                        int[] dices2 = {2, 2, 3, 5, 6};
                        throwDices2(dices2);
                    });
                    trainingTextNr++;
                    break;
                case 16:
                    trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_20));
                    trainingTextNr++;
                    break;
                case 17:
                    trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_21));
                    int[] dices3 = {2, 2, 6, 6, 6};
                    setDiceSelection(uiConfig.getTraining_dicesSlots()[2]);
                    setDiceSelection(uiConfig.getTraining_dicesSlots()[3]);
                    setDiceSelection(uiConfig.getTraining_dicesSlots()[4]);
                    uiConfig.getRollDicesButton().setOnClickListener(v1 -> {
                        if (uiConfig.getTraining_dicesSlots()[2].getBackground() != null && uiConfig.getTraining_dicesSlots()[3].getBackground() != null && uiConfig.getTraining_dicesSlots()[4].getBackground() != null) {
                            throwDices3(dices3);
                            blockDicesSelection();
                            trainingTextNr++;
                        }
                    });
                    break;
                case 18:
                    trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_23));
                    uiConfig.getCombinationsLayouts()[12].setOnClickListener(v1 -> {
                        uiConfig.updateCombinationsUI(1, 12);
                        sounds.playCompleteCombinationSound();
                        uiConfig.combinationHighlighter(0, true);
                        uiConfig.getCombinationsPoints()[12].setText(gameBoardActivity.getResources().getString(R.string.points_value, 22));
                        uiConfig.setTotalScore(46);
                        uiConfig.setDicesVisibility(false, true);
                        blockCombinationsLayouts();
                        trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_24));
                        trainingTextNr++;
                    });
                    break;
                case 19:
                    showNextButton(false);
                    trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_25));
                    uiConfig.updateCombinationsUI(1, 0);
                    uiConfig.updateCombinationsUI(1, 1);
                    uiConfig.updateCombinationsUI(1, 2);
                    uiConfig.updateCombinationsUI(2, 3);
                    uiConfig.updateCombinationsUI(2, 4);
                    uiConfig.updateCombinationsUI(2, 5);
                    uiConfig.updateCombinationsUI(1, 6);
                    uiConfig.updateCombinationsUI(1, 7);
                    uiConfig.updateCombinationsUI(1, 8);
                    uiConfig.updateCombinationsUI(1, 9);
                    uiConfig.updateCombinationsUI(2, 10);
                    uiConfig.updateCombinationsUI(2, 11);
                    uiConfig.updateCombinationsUI(2, 12);
                    uiConfig.getCombinationsPoints()[0].setText(gameBoardActivity.getResources().getString(R.string.points_value, 6));
                    uiConfig.getCombinationsPoints()[1].setText(gameBoardActivity.getResources().getString(R.string.points_value, 12));
                    uiConfig.getCombinationsPoints()[2].setText(gameBoardActivity.getResources().getString(R.string.points_value, 9));
                    uiConfig.getCombinationsPoints()[3].setText(gameBoardActivity.getResources().getString(R.string.points_value, 0));
                    uiConfig.getCombinationsPoints()[6].setText(gameBoardActivity.getResources().getString(R.string.points_value, 20));
                    uiConfig.getCombinationsPoints()[7].setText(gameBoardActivity.getResources().getString(R.string.points_value, 36));
                    uiConfig.getCombinationsPoints()[8].setText(gameBoardActivity.getResources().getString(R.string.points_value, 20));
                    uiConfig.getCombinationsPoints()[9].setText(gameBoardActivity.getResources().getString(R.string.points_value, 34));
                    uiConfig.getCombinationsPoints()[12].setText(gameBoardActivity.getResources().getString(R.string.points_value, 0));
                    uiConfig.setTotalScore(137);
                    trainingTextNr++;
                    int[] dices4 = {1, 6, 3, 4, 6};
                    throwDices4(dices4);
                    break;
                case 20:
                    trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_25));

            }

        });
    }

    public void throwDices(int[] dices) {
        uiConfig.getRollDicesButton().setOnClickListener(v -> {
            sounds.playRollDiceSound();
            uiConfig.showDices(dices, true);
            uiConfig.combinationHighlighter(4, false);
            trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_16));
            showNextButton(true);
            blockThrowDicesButton();

        });

    }

    public void throwDices2(int[] dices) {
        blockCombinationsLayouts();
        uiConfig.getRollDicesButton().setOnClickListener(v -> {
            sounds.playRollDiceSound();
            uiConfig.showDices(dices, true);
            uiConfig.combinationHighlighter(7, false);
            trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_19));
            showNextButton(true);
            blockThrowDicesButton();

        });
    }

    public void throwDices3(int[] dices) {
        uiConfig.clearDicesBorder(true);
        sounds.playRollDiceSound();
        uiConfig.showDices(dices, true);
        uiConfig.combinationHighlighter(6, false);
        uiConfig.combinationHighlighter(7, false);
        uiConfig.combinationHighlighter(8, false);
        uiConfig.combinationHighlighter(9, false);
        uiConfig.combinationHighlighter(13, false);
        trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_22));
        blockThrowDicesButton();

    }

    public void throwDices4(int[] dices) {
        uiConfig.getRollDicesButton().setOnClickListener(v -> {
            sounds.playRollDiceSound();
            uiConfig.showDices(dices, true);
            trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_26));
            int[] dices2 = {2, 6, 6, 5, 6};
            throwDices5(dices2);
        });

    }

    public void throwDices5(int[] dices) {
        setDiceSelection(uiConfig.getTraining_dicesSlots()[0]);
        setDiceSelection(uiConfig.getTraining_dicesSlots()[2]);
        setDiceSelection(uiConfig.getTraining_dicesSlots()[3]);
        uiConfig.getRollDicesButton().setOnClickListener(v -> {
            if (uiConfig.getTraining_dicesSlots()[0].getBackground() != null && uiConfig.getTraining_dicesSlots()[2].getBackground() != null && uiConfig.getTraining_dicesSlots()[3].getBackground() != null) {
                sounds.playRollDiceSound();
                uiConfig.showDices(dices, true);
                uiConfig.clearDicesBorder(true);
                trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_27));
                int[] dices2 = {1, 6, 6, 1, 6};
                throwDices6(dices2);
            }
        });
    }

    public void throwDices6(int[] dices){
        blockDicesSelection();
        setDiceSelection(uiConfig.getTraining_dicesSlots()[0]);
        setDiceSelection(uiConfig.getTraining_dicesSlots()[3]);
        uiConfig.getRollDicesButton().setOnClickListener(v ->{
            if(uiConfig.getTraining_dicesSlots()[0].getBackground()!=null && uiConfig.getTraining_dicesSlots()[3].getBackground()!=null){
                sounds.playRollDiceSound();
                uiConfig.showDices(dices, true);
                uiConfig.clearDicesBorder(true);
                uiConfig.combinationHighlighter(16, false);
                trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_28));
                blockDicesSelection();
                blockThrowDicesButton();
                uiConfig.getCombinationsLayouts()[15].setOnClickListener(v1 ->{
                    uiConfig.updateCombinationsUI(1, 15);
                    sounds.playCompleteCombinationSound();
                    uiConfig.combinationHighlighter(0, true);
                    uiConfig.getCombinationsPoints()[15].setText(gameBoardActivity.getResources().getString(R.string.points_value, 20));
                    uiConfig.setTotalScore(157);
                    uiConfig.setDicesVisibility(false, true);
                    blockCombinationsLayouts();
                    trainingText.setText(gameBoardActivity.getResources().getString(R.string.training_text_29));
                    trainingTextNr++;
                    showNextButton(true);
                });
            }
        });
    }

    public void blockThrowDicesButton() {
        uiConfig.getRollDicesButton().setOnClickListener(v1 -> {
        });


    }

    public void blockDicesSelection() {
        for (int x = 0; x < 5; x++) {
            uiConfig.getTraining_dicesSlots()[x].setOnClickListener(v -> {

            });
        }
    }

    public void blockCombinationsLayouts() {
        for (int x = 0; x < 15; x++) {
            uiConfig.getCombinationsLayouts()[x].setOnClickListener(v -> {

            });
        }
    }

    public void showNextButton(boolean showButton) {
        if (showButton) {
            nextButton.setVisibility(View.VISIBLE);
        } else {
            nextButton.setVisibility(View.INVISIBLE);
        }
    }

    public void setDiceSelection(ImageView imageView) {
        imageView.setOnClickListener(v -> {
            if (imageView.getBackground() != null) {
                imageView.setBackground(null);
            } else {
                uiConfig.setDicesBorder(imageView);
            }
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
