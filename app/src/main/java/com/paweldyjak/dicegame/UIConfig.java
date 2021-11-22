package com.paweldyjak.dicegame;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import com.paweldyjak.dicegame.Activities.GameBoardActivity;

public class UIConfig {
    private final GameBoardActivity gameBoardActivity;
    private final ImageView[] dicesSlots = new ImageView[5];
    private final ImageView[] training_dicesSlots = new ImageView[5];
    private ImageView rollDicesButton;
    private final TextView[] combinationsText = new TextView[16];
    private final TextView[] combinationsPoints = new TextView[16];
    private final TextView[] combinationsSlots = new TextView[16];
    private final LinearLayout[] combinationsLayouts = new LinearLayout[16];
    private final ValueAnimator[] valueAnimators = new ValueAnimator[16];
    private TextView currentPlayerName;
    private TextView totalScore;
    private ConstraintLayout blockCombinationQuestionLayout;
    private ConstraintLayout gameBoardLayout;
    private TextView blockCombinationTextView;
    private Button blockCombinationYesButton;
    private Button blockCombinationNoButton;


    public UIConfig(GameBoardActivity gameBoardActivity) {
        this.gameBoardActivity = gameBoardActivity;


    }

    public void setComponents() {
        blockCombinationQuestionLayout = gameBoardActivity.findViewById(R.id.block_combination_question_layout);
        blockCombinationTextView = gameBoardActivity.findViewById(R.id.gameMenu_exitGameQuestion);
        blockCombinationYesButton = gameBoardActivity.findViewById(R.id.gameMenu_exitQuestion_yes_button);
        blockCombinationNoButton = gameBoardActivity.findViewById(R.id.gameMenu_exitQuestion_no_button);
        gameBoardLayout = gameBoardActivity.findViewById(R.id.game_board_screen_layout);
        rollDicesButton = gameBoardActivity.findViewById(R.id.roll_dices);
        dicesSlots[0] = gameBoardActivity.findViewById(R.id.diceSlot1);
        dicesSlots[1] = gameBoardActivity.findViewById(R.id.diceSlot2);
        dicesSlots[2] = gameBoardActivity.findViewById(R.id.diceSlot3);
        dicesSlots[3] = gameBoardActivity.findViewById(R.id.diceSlot4);
        dicesSlots[4] = gameBoardActivity.findViewById(R.id.diceSlot5);
        training_dicesSlots[0] = gameBoardActivity.findViewById(R.id.training_diceSlot1);
        training_dicesSlots[1] = gameBoardActivity.findViewById(R.id.training_diceSlot2);
        training_dicesSlots[2] = gameBoardActivity.findViewById(R.id.training_diceSlot3);
        training_dicesSlots[3] = gameBoardActivity.findViewById(R.id.training_diceSlot4);
        training_dicesSlots[4] = gameBoardActivity.findViewById(R.id.training_diceSlot5);
        for (int x = 0; x < 16; x++) {
            switch (x) {
                case 0:
                    combinationsText[0] = gameBoardActivity.findViewById(R.id.textView_1);
                    combinationsText[0].setText(R.string.ones);
                    combinationsPoints[0] = gameBoardActivity.findViewById(R.id.textView_1_pts);
                    combinationsSlots[0] = gameBoardActivity.findViewById(R.id.textView_1_slot);
                    LinearLayout linearLayout;
                    linearLayout = gameBoardActivity.findViewById(R.id.one_layout);
                    combinationsLayouts[x] = linearLayout;
                    break;

                case 1:
                    combinationsText[1] = gameBoardActivity.findViewById(R.id.textView_2);
                    combinationsText[1].setText(R.string.twos);
                    combinationsPoints[1] = gameBoardActivity.findViewById(R.id.textView_2_pts);
                    combinationsSlots[1] = gameBoardActivity.findViewById(R.id.textView_2_slot);
                    LinearLayout linearLayout2;
                    linearLayout2 = gameBoardActivity.findViewById(R.id.two_layout);
                    combinationsLayouts[x] = linearLayout2;
                    break;

                case 2:
                    combinationsText[2] = gameBoardActivity.findViewById(R.id.textView_3);
                    combinationsText[2].setText(R.string.threes);
                    combinationsPoints[2] = gameBoardActivity.findViewById(R.id.textView_3_pts);
                    combinationsSlots[2] = gameBoardActivity.findViewById(R.id.textView_3_slot);
                    LinearLayout linearLayout3;
                    linearLayout3 = gameBoardActivity.findViewById(R.id.three_layout);
                    combinationsLayouts[x] = linearLayout3;
                    break;

                case 3:
                    combinationsText[3] = gameBoardActivity.findViewById(R.id.textView_4);
                    combinationsText[3].setText(R.string.fours);
                    combinationsPoints[3] = gameBoardActivity.findViewById(R.id.textView_4_pts);
                    combinationsSlots[3] = gameBoardActivity.findViewById(R.id.textView_4_slot);
                    LinearLayout linearLayout4;
                    linearLayout4 = gameBoardActivity.findViewById(R.id.four_layout);
                    combinationsLayouts[x] = linearLayout4;
                    break;

                case 4:
                    combinationsText[4] = gameBoardActivity.findViewById(R.id.textView_5);
                    combinationsText[4].setText(R.string.fives);
                    combinationsPoints[4] = gameBoardActivity.findViewById(R.id.textView_5_pts);
                    combinationsSlots[4] = gameBoardActivity.findViewById(R.id.textView_5_slot);
                    LinearLayout linearLayout5;
                    linearLayout5 = gameBoardActivity.findViewById(R.id.five_layout);
                    combinationsLayouts[x] = linearLayout5;
                    break;

                case 5:
                    combinationsText[5] = gameBoardActivity.findViewById(R.id.textView_6);
                    combinationsText[5].setText(R.string.sixes);
                    combinationsPoints[5] = gameBoardActivity.findViewById(R.id.textView_6_pts);
                    combinationsSlots[5] = gameBoardActivity.findViewById(R.id.textView_6_slot);
                    LinearLayout linearLayout6;
                    linearLayout6 = gameBoardActivity.findViewById(R.id.six_layout);
                    combinationsLayouts[x] = linearLayout6;
                    break;

                case 6:
                    combinationsText[6] = gameBoardActivity.findViewById(R.id.textView_pair);
                    combinationsText[6].setText(R.string.pair);
                    combinationsPoints[6] = gameBoardActivity.findViewById(R.id.textView_pair_pts);
                    combinationsSlots[6] = gameBoardActivity.findViewById(R.id.textView_pair_slot);
                    LinearLayout linearLayout7;
                    linearLayout7 = gameBoardActivity.findViewById(R.id.pair_layout);
                    combinationsLayouts[x] = linearLayout7;
                    break;

                case 7:
                    combinationsText[7] = gameBoardActivity.findViewById(R.id.textView_2pairs);
                    combinationsText[7].setText(R.string.two_pairs);
                    combinationsPoints[7] = gameBoardActivity.findViewById(R.id.textView_2pairs_pts);
                    combinationsSlots[7] = gameBoardActivity.findViewById(R.id.textView_2pairs_slot);
                    LinearLayout linearLayout8;
                    linearLayout8 = gameBoardActivity.findViewById(R.id.twoPairs_layout);
                    combinationsLayouts[x] = linearLayout8;
                    break;

                case 8:
                    combinationsText[8] = gameBoardActivity.findViewById(R.id.textView_evens);
                    combinationsText[8].setText(R.string.evens);
                    combinationsPoints[8] = gameBoardActivity.findViewById(R.id.textView_evens_pts);
                    combinationsSlots[8] = gameBoardActivity.findViewById(R.id.textView_evens_slot);
                    LinearLayout linearLayout9;
                    linearLayout9 = gameBoardActivity.findViewById(R.id.evens_layout);
                    combinationsLayouts[x] = linearLayout9;
                    break;

                case 9:
                    combinationsText[9] = gameBoardActivity.findViewById(R.id.textView_odds);
                    combinationsText[9].setText(R.string.odds);
                    combinationsPoints[9] = gameBoardActivity.findViewById(R.id.textView_odds_pts);
                    combinationsSlots[9] = gameBoardActivity.findViewById(R.id.textView_odds_slot);
                    LinearLayout linearLayout10;
                    linearLayout10 = gameBoardActivity.findViewById(R.id.odds_layout);
                    combinationsLayouts[x] = linearLayout10;
                    break;
                case 10:
                    combinationsText[10] = gameBoardActivity.findViewById(R.id.textView_smallStraight);
                    combinationsText[10].setText(R.string.small_straight);
                    combinationsPoints[10] = gameBoardActivity.findViewById(R.id.textView_smallStraight_pts);
                    combinationsSlots[10] = gameBoardActivity.findViewById(R.id.textView_smallStraight_slot);
                    LinearLayout linearLayout11;
                    linearLayout11 = gameBoardActivity.findViewById(R.id.smallStraight_layout);
                    combinationsLayouts[x] = linearLayout11;
                    break;
                case 11:
                    combinationsText[11] = gameBoardActivity.findViewById(R.id.textView_largeStraight);
                    combinationsText[11].setText(R.string.large_straight);
                    combinationsPoints[11] = gameBoardActivity.findViewById(R.id.textView_largeStraight_pts);
                    combinationsSlots[11] = gameBoardActivity.findViewById(R.id.textView_largeStraight_slot);
                    LinearLayout linearLayout12;
                    linearLayout12 = gameBoardActivity.findViewById(R.id.largeStraight_layout);
                    combinationsLayouts[x] = linearLayout12;
                    break;
                case 12:
                    combinationsText[12] = gameBoardActivity.findViewById(R.id.textView_fullHouse);
                    combinationsText[12].setText(R.string.full_house);
                    combinationsPoints[12] = gameBoardActivity.findViewById(R.id.textView_fullHouse_pts);
                    combinationsSlots[12] = gameBoardActivity.findViewById(R.id.textView_fullHouse_slot);
                    LinearLayout linearLayout13;
                    linearLayout13 = gameBoardActivity.findViewById(R.id.fullHouse_layout);
                    combinationsLayouts[x] = linearLayout13;
                    break;
                case 13:
                    combinationsText[13] = gameBoardActivity.findViewById(R.id.textView_4ofAKind);
                    combinationsText[13].setText(R.string.four_of_a_kind);
                    combinationsPoints[13] = gameBoardActivity.findViewById(R.id.textView_4ofAKind_pts);
                    combinationsSlots[13] = gameBoardActivity.findViewById(R.id.textView_4ofAKind_slot);
                    LinearLayout linearLayout14;
                    linearLayout14 = gameBoardActivity.findViewById(R.id.fourOfAkind_layout);
                    combinationsLayouts[x] = linearLayout14;
                    break;
                case 14:
                    combinationsText[14] = gameBoardActivity.findViewById(R.id.textView_5ofAKind);
                    combinationsText[14].setText(R.string.five_of_a_kind);
                    combinationsPoints[14] = gameBoardActivity.findViewById(R.id.textView_5ofAKind_pts);
                    combinationsSlots[14] = gameBoardActivity.findViewById(R.id.textView_5ofAKind_slot);
                    LinearLayout linearLayout15;
                    linearLayout15 = gameBoardActivity.findViewById(R.id.fiveOfAkind_layout);
                    combinationsLayouts[x] = linearLayout15;
                    break;
                case 15:
                    combinationsText[15] = gameBoardActivity.findViewById(R.id.textView_sos);
                    combinationsText[15].setText(R.string.SOS);
                    combinationsPoints[15] = gameBoardActivity.findViewById(R.id.textView_sos_pts);
                    combinationsSlots[15] = gameBoardActivity.findViewById(R.id.textView_sos_slot);
                    LinearLayout linearLayout16;
                    linearLayout16 = gameBoardActivity.findViewById(R.id.sos_layout);
                    combinationsLayouts[x] = linearLayout16;
                    break;

            }
        }

        for (int x = 0; x < combinationsText.length; x++) {
            combinationsPoints[x].setText(gameBoardActivity.getResources().getString(R.string.points_value, 0));
        }

        for (TextView textView : combinationsText) {
            textView.setEnabled(true);
        }

        totalScore = new TextView(gameBoardActivity);
        totalScore = gameBoardActivity.findViewById(R.id.textView_score_pts);
        currentPlayerName = gameBoardActivity.findViewById(R.id.player_name_textView);
        totalScore.setText(gameBoardActivity.getResources().getString(R.string.points_value, 0));

    }


    public void setDicesVisibility(boolean visible, boolean isTrainingMode) {
        if (!isTrainingMode) {

            for (ImageView dicesSlot : dicesSlots) {
                if (visible) {
                    dicesSlot.setVisibility(View.VISIBLE);
                } else {
                    dicesSlot.setVisibility(View.INVISIBLE);
                }
            }
        } else{
            for (ImageView training_dicesSlot : training_dicesSlots) {
                if (visible) {
                    training_dicesSlot.setVisibility(View.VISIBLE);
                } else {
                    training_dicesSlot.setVisibility(View.INVISIBLE);
                }
            }
        }
    }


    //setters and getters
    public ImageView[] getDicesSlots() {
        return dicesSlots;
    }

    public TextView[] getCombinationsPoints() {
        return combinationsPoints;
    }

    public void setDicesBorder(ImageView dice) {
        Drawable dicesBorder = ResourcesCompat.getDrawable(gameBoardActivity.getResources(), R.drawable.background_dices, null);
            dice.setBackground(dicesBorder);
        }


    public void clearDicesBorder(boolean isTrainingMode) {
        if(!isTrainingMode) {
            for (ImageView dices : dicesSlots) {
                dices.setBackground(null);
            }
        } else{
            for(ImageView trainingDices: training_dicesSlots){
                trainingDices.setBackground(null);
            }
        }
    }


    public TextView getCurrentPlayerName() {
        return currentPlayerName;
    }

    public void setCurrentPlayerName(String currentPlayerName) {
        this.currentPlayerName.setText(currentPlayerName);
    }

    //method shows dices
    public void showDices(int[] dices, boolean isTrainingMode) {
        setDicesVisibility(true, isTrainingMode);
        if(!isTrainingMode) {
            for (int x = 0; x < 5; x++) {
                dicesSlots[x].setImageResource(0);
            }

            int valueToDisplay;
            for (int x = 0; x < 5; x++) {

                valueToDisplay = dices[x];


                for (int y = 0; y < 5; y++) {
                    if (dicesSlots[y].getDrawable() == null) {


                        switch (valueToDisplay) {
                            case 1:
                                dicesSlots[y].setImageResource(R.drawable.dice1);
                                y = 5;
                                break;
                            case 2:
                                dicesSlots[y].setImageResource(R.drawable.dice2);
                                y = 5;
                                break;
                            case 3:
                                dicesSlots[y].setImageResource(R.drawable.dice3);
                                y = 5;
                                break;
                            case 4:
                                dicesSlots[y].setImageResource(R.drawable.dice4);
                                y = 5;
                                break;
                            case 5:
                                dicesSlots[y].setImageResource(R.drawable.dice5);
                                y = 5;
                                break;
                            case 6:
                                dicesSlots[y].setImageResource(R.drawable.dice6);
                                y = 5;
                                break;
                        }

                    }
                }
            }
        } else{
            for (int x = 0; x < 5; x++) {
                training_dicesSlots[x].setImageResource(0);
            }

            int valueToDisplay;
            for (int x = 0; x < 5; x++) {

                valueToDisplay = dices[x];


                for (int y = 0; y < 5; y++) {
                    if (training_dicesSlots[y].getDrawable() == null) {


                        switch (valueToDisplay) {
                            case 1:
                                training_dicesSlots[y].setImageResource(R.drawable.dice1);
                                y = 5;
                                break;
                            case 2:
                                training_dicesSlots[y].setImageResource(R.drawable.dice2);
                                y = 5;
                                break;
                            case 3:
                                training_dicesSlots[y].setImageResource(R.drawable.dice3);
                                y = 5;
                                break;
                            case 4:
                                training_dicesSlots[y].setImageResource(R.drawable.dice4);
                                y = 5;
                                break;
                            case 5:
                                training_dicesSlots[y].setImageResource(R.drawable.dice5);
                                y = 5;
                                break;
                            case 6:
                                training_dicesSlots[y].setImageResource(R.drawable.dice6);
                                y = 5;
                                break;
                        }

                    }
                }
            }
        }

    }

    public void updateCombinationsUI(int combinationStatus, int combinationNumber) {
        /*combination status:

        0 - enabled, empty
        1 - disabled, done
        2 - disabled, crossed out*/

        if (combinationStatus == 0) {
            combinationsSlots[combinationNumber].setText("");
            combinationsPoints[combinationNumber].setEnabled(true);
            combinationsText[combinationNumber].setEnabled(true);
            combinationsSlots[combinationNumber].setEnabled(true);
        } else if (combinationStatus == 1) {
            combinationsSlots[combinationNumber].setText("\u2713");
            combinationsSlots[combinationNumber].setGravity(Gravity.CENTER);
            combinationsSlots[combinationNumber].setTextSize(16);
            combinationsSlots[combinationNumber].setTextColor(Color.rgb(27, 182, 33));
            combinationsPoints[combinationNumber].setEnabled(true);
            combinationsText[combinationNumber].setEnabled(false);
            combinationsSlots[combinationNumber].setEnabled(false);

        } else {
            combinationsSlots[combinationNumber].setText("X");
            combinationsSlots[combinationNumber].setGravity(Gravity.CENTER);
            combinationsSlots[combinationNumber].setTextSize(16);
            combinationsSlots[combinationNumber].setTextColor(Color.rgb(140, 17, 16));
            combinationsPoints[combinationNumber].setEnabled(false);
            combinationsText[combinationNumber].setEnabled(false);
            combinationsSlots[combinationNumber].setEnabled(false);
        }
    }

    public void changeCurrentPlayerName(String playerName) {
        currentPlayerName.setText(playerName);
    }


    public ImageView getRollDicesButton() {
        return rollDicesButton;
    }

    public void setRollDicesVisibility(boolean visible) {
        if (visible) {
            rollDicesButton.setVisibility(View.VISIBLE);
        } else {
            rollDicesButton.setVisibility(View.INVISIBLE);
        }
    }

    public void setTotalScore(int totalScoreValue) {
        totalScore.setText(gameBoardActivity.getResources().getString(R.string.points_value, totalScoreValue));
    }

    public void combinationHighlighter(int combinationNr, boolean turnHighlightsOff) {
        Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(3000);
        Animation out = new AlphaAnimation(1.0f, 0.0f);
        out.setDuration(3000);
        Drawable combinationSlotBorder = ResourcesCompat.getDrawable(gameBoardActivity.getResources(), R.drawable.background_combination_slot, null);

        if (gameBoardActivity.isCombinationsHighlightOn()) {
            final int green = Color.rgb(246, 93, 44);
            final int white = Color.rgb(255, 255, 255);


            if (turnHighlightsOff) {
                for (int x = 0; x < 16; x++) {
                    if (valueAnimators[x] != null) {
                        valueAnimators[x].pause();
                        combinationsSlots[x].setBackground(combinationSlotBorder);
                        if (combinationsSlots[x].getText().equals("?")) {
                            combinationsSlots[x].setText(null);
                        }

                    }

                }
            } else {
                if (valueAnimators[combinationNr - 1] == null) {
                    valueAnimators[combinationNr - 1] = ObjectAnimator.ofInt(combinationsSlots[combinationNr - 1], "backgroundColor", green, white);
                    valueAnimators[combinationNr - 1].setDuration(1500);
                    valueAnimators[combinationNr - 1].setEvaluator(new ArgbEvaluator());
                    valueAnimators[combinationNr - 1].setRepeatCount(ValueAnimator.INFINITE);
                    valueAnimators[combinationNr - 1].setRepeatMode(ValueAnimator.REVERSE);


                }
                combinationsSlots[combinationNr - 1].setText("?");
                combinationsSlots[combinationNr - 1].setGravity(Gravity.CENTER);
                combinationsSlots[combinationNr - 1].setTextSize(16);
                combinationsSlots[combinationNr - 1].setTextColor(Color.rgb(69, 48, 106));
                valueAnimators[combinationNr - 1].start();
            }


        }
    }

    public LinearLayout[] getCombinationsLayouts() {
        return combinationsLayouts;
    }

    public void showBlockCombinationQuestion(boolean showQuestion) {

        gameBoardEnableController(!showQuestion, gameBoardLayout);
        if (showQuestion) {
            blockCombinationQuestionLayout.setVisibility(View.VISIBLE);
        } else {
            blockCombinationQuestionLayout.setVisibility(View.INVISIBLE);
        }


    }

    public void gameBoardEnableController(boolean enable, ViewGroup vg) {

        for (int i = 0; i < vg.getChildCount(); i++) {
            View child = vg.getChildAt(i);
            child.setEnabled(enable);
            if (child instanceof ViewGroup) {
                gameBoardEnableController(enable, (ViewGroup) child);
            }
        }
    }

    public Button getBlockCombinationYesButton() {
        return blockCombinationYesButton;
    }

    public Button getBlockCombinationNoButton() {
        return blockCombinationNoButton;
    }

    public TextView getBlockCombinationTextView() {
        return blockCombinationTextView;
    }

    public ConstraintLayout getGameBoardLayout() {
        return gameBoardLayout;
    }

    public ImageView[] getTraining_dicesSlots() {
        return training_dicesSlots;
    }

    public TextView[] getCombinationsText() {
        return combinationsText;
    }
}
