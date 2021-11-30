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
import java.util.ArrayList;

public class UIConfig {
    private final GameBoardActivity gameBoardActivity;
    private final ArrayList<ImageView> dicesSlots = new ArrayList<>();
    private final ArrayList<ImageView> training_dicesSlots = new ArrayList<>();
    private final ArrayList<TextView> combinationsText = new ArrayList<>();
    private final ArrayList<TextView> combinationsPoints = new ArrayList<>();
    private final ArrayList<TextView> combinationsSlots = new ArrayList<>();
    private final ArrayList<LinearLayout> combinationsLayouts = new ArrayList<>();
    private final ArrayList<ValueAnimator> valueAnimators = new ArrayList<>();
    private ImageView rollDicesButton;
    private TextView currentPlayerName;
    private TextView totalScore;
    private ConstraintLayout crossOutCombinationQuestionLayout;
    private ConstraintLayout gameBoardLayout;
    private TextView crossOutCombinationTextView;
    private Button crossOutCombinationYesButton;
    private Button crossOutCombinationNoButton;

    public UIConfig(GameBoardActivity gameBoardActivity) {
        this.gameBoardActivity = gameBoardActivity;

    }

    public void setComponents() {
        final int green = Color.rgb(246, 93, 44);
        final int white = Color.rgb(255, 255, 255);
        crossOutCombinationQuestionLayout = gameBoardActivity.findViewById(R.id.cross_out_combination_question_layout);
        crossOutCombinationTextView = gameBoardActivity.findViewById(R.id.cross_out_combination_question_textView);
        crossOutCombinationYesButton = gameBoardActivity.findViewById(R.id.cross_out_combination_question_yes_button);
        crossOutCombinationNoButton = gameBoardActivity.findViewById(R.id.cross_out_combination_question_no_button);
        gameBoardLayout = gameBoardActivity.findViewById(R.id.game_board_screen_layout);
        rollDicesButton = gameBoardActivity.findViewById(R.id.roll_dices);
        dicesSlots.add(gameBoardActivity.findViewById(R.id.diceSlot1));
        dicesSlots.add(gameBoardActivity.findViewById(R.id.diceSlot2));
        dicesSlots.add(gameBoardActivity.findViewById(R.id.diceSlot3));
        dicesSlots.add(gameBoardActivity.findViewById(R.id.diceSlot4));
        dicesSlots.add(gameBoardActivity.findViewById(R.id.diceSlot5));
        training_dicesSlots.add(gameBoardActivity.findViewById(R.id.training_diceSlot1));
        training_dicesSlots.add(gameBoardActivity.findViewById(R.id.training_diceSlot2));
        training_dicesSlots.add(gameBoardActivity.findViewById(R.id.training_diceSlot3));
        training_dicesSlots.add(gameBoardActivity.findViewById(R.id.training_diceSlot4));
        training_dicesSlots.add(gameBoardActivity.findViewById(R.id.training_diceSlot5));
        for (int x = 0; x < 16; x++) {
            switch (x) {
                case 0:
                    combinationsText.add(gameBoardActivity.findViewById(R.id.textView_1));
                    combinationsText.get(x).setText(R.string.ones);
                    combinationsPoints.add(gameBoardActivity.findViewById(R.id.textView_1_pts));
                    combinationsSlots.add(gameBoardActivity.findViewById(R.id.textView_1_slot));
                    LinearLayout linearLayout;
                    linearLayout = gameBoardActivity.findViewById(R.id.one_layout);
                    combinationsLayouts.add(linearLayout);
                    break;

                case 1:
                    combinationsText.add(gameBoardActivity.findViewById(R.id.textView_2));
                    combinationsText.get(x).setText(R.string.twos);
                    combinationsPoints.add(gameBoardActivity.findViewById(R.id.textView_2_pts));
                    combinationsSlots.add(gameBoardActivity.findViewById(R.id.textView_2_slot));
                    LinearLayout linearLayout2;
                    linearLayout2 = gameBoardActivity.findViewById(R.id.two_layout);
                    combinationsLayouts.add(linearLayout2);
                    break;

                case 2:
                    combinationsText.add(gameBoardActivity.findViewById(R.id.textView_3));
                    combinationsText.get(x).setText(R.string.threes);
                    combinationsPoints.add(gameBoardActivity.findViewById(R.id.textView_3_pts));
                    combinationsSlots.add(gameBoardActivity.findViewById(R.id.textView_3_slot));
                    LinearLayout linearLayout3;
                    linearLayout3 = gameBoardActivity.findViewById(R.id.three_layout);
                    combinationsLayouts.add(linearLayout3);
                    break;

                case 3:
                    combinationsText.add(gameBoardActivity.findViewById(R.id.textView_4));
                    combinationsText.get(x).setText(R.string.fours);
                    combinationsPoints.add(gameBoardActivity.findViewById(R.id.textView_4_pts));
                    combinationsSlots.add(gameBoardActivity.findViewById(R.id.textView_4_slot));
                    LinearLayout linearLayout4;
                    linearLayout4 = gameBoardActivity.findViewById(R.id.four_layout);
                    combinationsLayouts.add(linearLayout4);
                    break;

                case 4:
                    combinationsText.add(gameBoardActivity.findViewById(R.id.textView_5));
                    combinationsText.get(x).setText(R.string.fives);
                    combinationsPoints.add(gameBoardActivity.findViewById(R.id.textView_5_pts));
                    combinationsSlots.add(gameBoardActivity.findViewById(R.id.textView_5_slot));
                    LinearLayout linearLayout5;
                    linearLayout5 = gameBoardActivity.findViewById(R.id.five_layout);
                    combinationsLayouts.add(linearLayout5);
                    break;

                case 5:
                    combinationsText.add(gameBoardActivity.findViewById(R.id.textView_6));
                    combinationsText.get(x).setText(R.string.sixes);
                    combinationsPoints.add(gameBoardActivity.findViewById(R.id.textView_6_pts));
                    combinationsSlots.add(gameBoardActivity.findViewById(R.id.textView_6_slot));
                    LinearLayout linearLayout6;
                    linearLayout6 = gameBoardActivity.findViewById(R.id.six_layout);
                    combinationsLayouts.add(linearLayout6);
                    break;

                case 6:
                    combinationsText.add(gameBoardActivity.findViewById(R.id.textView_pair));
                    combinationsText.get(x).setText(R.string.pair);
                    combinationsPoints.add(gameBoardActivity.findViewById(R.id.textView_pair_pts));
                    combinationsSlots.add(gameBoardActivity.findViewById(R.id.textView_pair_slot));
                    LinearLayout linearLayout7;
                    linearLayout7 = gameBoardActivity.findViewById(R.id.pair_layout);
                    combinationsLayouts.add(linearLayout7);
                    break;

                case 7:
                    combinationsText.add(gameBoardActivity.findViewById(R.id.textView_2pairs));
                    combinationsText.get(x).setText(R.string.two_pairs);
                    combinationsPoints.add(gameBoardActivity.findViewById(R.id.textView_2pairs_pts));
                    combinationsSlots.add(gameBoardActivity.findViewById(R.id.textView_2pairs_slot));
                    LinearLayout linearLayout8;
                    linearLayout8 = gameBoardActivity.findViewById(R.id.twoPairs_layout);
                    combinationsLayouts.add(linearLayout8);
                    break;

                case 8:
                    combinationsText.add(gameBoardActivity.findViewById(R.id.textView_evens));
                    combinationsText.get(x).setText(R.string.evens);
                    combinationsPoints.add(gameBoardActivity.findViewById(R.id.textView_evens_pts));
                    combinationsSlots.add(gameBoardActivity.findViewById(R.id.textView_evens_slot));
                    LinearLayout linearLayout9;
                    linearLayout9 = gameBoardActivity.findViewById(R.id.evens_layout);
                    combinationsLayouts.add(linearLayout9);
                    break;

                case 9:
                    combinationsText.add(gameBoardActivity.findViewById(R.id.textView_odds));
                    combinationsText.get(x).setText(R.string.odds);
                    combinationsPoints.add(gameBoardActivity.findViewById(R.id.textView_odds_pts));
                    combinationsSlots.add(gameBoardActivity.findViewById(R.id.textView_odds_slot));
                    LinearLayout linearLayout10;
                    linearLayout10 = gameBoardActivity.findViewById(R.id.odds_layout);
                    combinationsLayouts.add(linearLayout10);
                    break;
                case 10:
                    combinationsText.add(gameBoardActivity.findViewById(R.id.textView_smallStraight));
                    combinationsText.get(x).setText(R.string.small_straight);
                    combinationsPoints.add(gameBoardActivity.findViewById(R.id.textView_smallStraight_pts));
                    combinationsSlots.add(gameBoardActivity.findViewById(R.id.textView_smallStraight_slot));
                    LinearLayout linearLayout11;
                    linearLayout11 = gameBoardActivity.findViewById(R.id.smallStraight_layout);
                    combinationsLayouts.add(linearLayout11);
                    break;
                case 11:
                    combinationsText.add(gameBoardActivity.findViewById(R.id.textView_largeStraight));
                    combinationsText.get(x).setText(R.string.large_straight);
                    combinationsPoints.add(gameBoardActivity.findViewById(R.id.textView_largeStraight_pts));
                    combinationsSlots.add(gameBoardActivity.findViewById(R.id.textView_largeStraight_slot));
                    LinearLayout linearLayout12;
                    linearLayout12 = gameBoardActivity.findViewById(R.id.largeStraight_layout);
                    combinationsLayouts.add(linearLayout12);
                    break;
                case 12:
                    combinationsText.add(gameBoardActivity.findViewById(R.id.textView_fullHouse));
                    combinationsText.get(x).setText(R.string.full_house);
                    combinationsPoints.add(gameBoardActivity.findViewById(R.id.textView_fullHouse_pts));
                    combinationsSlots.add(gameBoardActivity.findViewById(R.id.textView_fullHouse_slot));
                    LinearLayout linearLayout13;
                    linearLayout13 = gameBoardActivity.findViewById(R.id.fullHouse_layout);
                    combinationsLayouts.add(linearLayout13);
                    break;
                case 13:
                    combinationsText.add(gameBoardActivity.findViewById(R.id.textView_4ofAKind));
                    combinationsText.get(x).setText(R.string.four_of_a_kind);
                    combinationsPoints.add(gameBoardActivity.findViewById(R.id.textView_4ofAKind_pts));
                    combinationsSlots.add(gameBoardActivity.findViewById(R.id.textView_4ofAKind_slot));
                    LinearLayout linearLayout14;
                    linearLayout14 = gameBoardActivity.findViewById(R.id.fourOfAkind_layout);
                    combinationsLayouts.add(linearLayout14);
                    break;
                case 14:
                    combinationsText.add(gameBoardActivity.findViewById(R.id.textView_5ofAKind));
                    combinationsText.get(x).setText(R.string.five_of_a_kind);
                    combinationsPoints.add(gameBoardActivity.findViewById(R.id.textView_5ofAKind_pts));
                    combinationsSlots.add(gameBoardActivity.findViewById(R.id.textView_5ofAKind_slot));
                    LinearLayout linearLayout15;
                    linearLayout15 = gameBoardActivity.findViewById(R.id.fiveOfAkind_layout);
                    combinationsLayouts.add(linearLayout15);
                    break;
                case 15:
                    combinationsText.add(gameBoardActivity.findViewById(R.id.textView_sos));
                    combinationsText.get(x).setText(R.string.SOS);
                    combinationsPoints.add(gameBoardActivity.findViewById(R.id.textView_sos_pts));
                    combinationsSlots.add(gameBoardActivity.findViewById(R.id.textView_sos_slot));
                    LinearLayout linearLayout16;
                    linearLayout16 = gameBoardActivity.findViewById(R.id.sos_layout);
                    combinationsLayouts.add(linearLayout16);
                    break;

            }
        }

        for (int y = 0; y < 16; y++) {
            valueAnimators.add(ObjectAnimator.ofInt(combinationsSlots.get(y), "backgroundColor", green, white));
            valueAnimators.get(y).setDuration(1500);
            valueAnimators.get(y).setEvaluator(new ArgbEvaluator());
            valueAnimators.get(y).setRepeatCount(ValueAnimator.INFINITE);
            valueAnimators.get(y).setRepeatMode(ValueAnimator.REVERSE);
        }

        for (int x = 0; x < combinationsText.size(); x++) {
            combinationsPoints.get(x).setText(gameBoardActivity.getResources().getString(R.string.points_value, 0));
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
        } else {
            for (ImageView training_dicesSlot : training_dicesSlots) {
                if (visible) {
                    training_dicesSlot.setVisibility(View.VISIBLE);
                } else {
                    training_dicesSlot.setVisibility(View.INVISIBLE);
                }
            }
        }
    }


    public void showDices(int[] dices, boolean isTrainingMode) {
        setDicesVisibility(true, isTrainingMode);
        if (!isTrainingMode) {
            for (int x = 0; x < 5; x++) {
                dicesSlots.get(x).setImageResource(0);
            }

            int valueToDisplay;
            for (int x = 0; x < 5; x++) {

                valueToDisplay = dices[x];

                for (int y = 0; y < 5; y++) {
                    if (dicesSlots.get(y).getDrawable() == null) {

                        switch (valueToDisplay) {
                            case 1:
                                dicesSlots.get(y).setImageResource(R.drawable.dice1);
                                y = 5;
                                break;
                            case 2:
                                dicesSlots.get(y).setImageResource(R.drawable.dice2);
                                y = 5;
                                break;
                            case 3:
                                dicesSlots.get(y).setImageResource(R.drawable.dice3);
                                y = 5;
                                break;
                            case 4:
                                dicesSlots.get(y).setImageResource(R.drawable.dice4);
                                y = 5;
                                break;
                            case 5:
                                dicesSlots.get(y).setImageResource(R.drawable.dice5);
                                y = 5;
                                break;
                            case 6:
                                dicesSlots.get(y).setImageResource(R.drawable.dice6);
                                y = 5;
                                break;
                        }

                    }
                }
            }
        } else {
            for (int x = 0; x < 5; x++) {
                training_dicesSlots.get(x).setImageResource(0);
            }

            int valueToDisplay;
            for (int x = 0; x < 5; x++) {

                valueToDisplay = dices[x];


                for (int y = 0; y < 5; y++) {
                    if (training_dicesSlots.get(y).getDrawable() == null) {


                        switch (valueToDisplay) {
                            case 1:
                                training_dicesSlots.get(y).setImageResource(R.drawable.dice1);
                                y = 5;
                                break;
                            case 2:
                                training_dicesSlots.get(y).setImageResource(R.drawable.dice2);
                                y = 5;
                                break;
                            case 3:
                                training_dicesSlots.get(y).setImageResource(R.drawable.dice3);
                                y = 5;
                                break;
                            case 4:
                                training_dicesSlots.get(y).setImageResource(R.drawable.dice4);
                                y = 5;
                                break;
                            case 5:
                                training_dicesSlots.get(y).setImageResource(R.drawable.dice5);
                                y = 5;
                                break;
                            case 6:
                                training_dicesSlots.get(y).setImageResource(R.drawable.dice6);
                                y = 5;
                                break;
                        }

                    }
                }
            }
        }

    }

    public void setDicesBorder(ImageView dice) {
        Drawable dicesBorder = ResourcesCompat.getDrawable(gameBoardActivity.getResources(), R.drawable.background_dices, null);
        dice.setBackground(dicesBorder);
    }


    public void clearDicesBorder(boolean isTrainingMode) {
        if (!isTrainingMode) {
            for (ImageView dices : dicesSlots) {
                dices.setBackground(null);
            }
        } else {
            for (ImageView trainingDices : training_dicesSlots) {
                trainingDices.setBackground(null);
            }
        }
    }

    public void updateCombinationsUI(int combinationStatus, int combinationNumber) {
        /*combination status:

        0 - enabled, empty
        1 - disabled, done
        2 - disabled, crossed out*/

        if (combinationStatus == 0) {
            combinationsSlots.get(combinationNumber).setText("");
            combinationsPoints.get(combinationNumber).setEnabled(true);
            combinationsText.get(combinationNumber).setEnabled(true);
            combinationsSlots.get(combinationNumber).setEnabled(true);
        } else if (combinationStatus == 1) {
            combinationsSlots.get(combinationNumber).setText("\u2713");
            combinationsSlots.get(combinationNumber).setGravity(Gravity.CENTER);
            combinationsSlots.get(combinationNumber).setTextSize(16);
            combinationsSlots.get(combinationNumber).setTextColor(Color.rgb(27, 182, 33));
            combinationsPoints.get(combinationNumber).setEnabled(true);
            combinationsText.get(combinationNumber).setEnabled(false);
            combinationsSlots.get(combinationNumber).setEnabled(false);

        } else {
            combinationsSlots.get(combinationNumber).setText("X");
            combinationsSlots.get(combinationNumber).setGravity(Gravity.CENTER);
            combinationsSlots.get(combinationNumber).setTextSize(16);
            combinationsSlots.get(combinationNumber).setTextColor(Color.rgb(140, 17, 16));
            combinationsPoints.get(combinationNumber).setEnabled(false);
            combinationsText.get(combinationNumber).setEnabled(false);
            combinationsSlots.get(combinationNumber).setEnabled(false);
        }
    }

    public void setRollDicesVisibility(boolean visible) {
        if (visible) {
            rollDicesButton.setVisibility(View.VISIBLE);
        } else {
            rollDicesButton.setVisibility(View.INVISIBLE);
        }
    }

    public void combinationHighlighter(int combinationNr, boolean turnHighlightsOff) {
        Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(3000);
        Animation out = new AlphaAnimation(1.0f, 0.0f);
        out.setDuration(3000);
        Drawable combinationSlotBorder = ResourcesCompat.getDrawable(gameBoardActivity.getResources(), R.drawable.background_combination_slot, null);
            if (turnHighlightsOff) {
                for (int x = 0; x < 16; x++) {
                    valueAnimators.get(x).pause();
                    combinationsSlots.get(x).setBackground(combinationSlotBorder);
                    if (combinationsSlots.get(x).getText().equals("?")) {
                        combinationsSlots.get(x).setText(null);
                    }
                }

            } else if (gameBoardActivity.isCombinationsHighlightOn()) {
                combinationsSlots.get(combinationNr - 1).setText("?");
                combinationsSlots.get(combinationNr - 1).setGravity(Gravity.CENTER);
                combinationsSlots.get(combinationNr - 1).setTextSize(16);
                combinationsSlots.get(combinationNr - 1).setTextColor(Color.rgb(69, 48, 106));
                valueAnimators.get(combinationNr - 1).start();
            }


    }

    public void showCrossOutCombinationQuestion(boolean showQuestion) {

        gameBoardEnableController(!showQuestion, gameBoardLayout);
        if (showQuestion) {
            crossOutCombinationQuestionLayout.setVisibility(View.VISIBLE);
        } else {
            crossOutCombinationQuestionLayout.setVisibility(View.INVISIBLE);
        }


    }

    public void gameBoardEnableController(boolean enableGameBoard, ViewGroup gameBoardLayout) {

        for (int i = 0; i < gameBoardLayout.getChildCount(); i++) {
            View child = gameBoardLayout.getChildAt(i);
            child.setEnabled(enableGameBoard);
            if (child instanceof ViewGroup) {
                gameBoardEnableController(enableGameBoard, (ViewGroup) child);
            }
        }
    }

    public Button getCrossOutCombinationYesButton() {
        return crossOutCombinationYesButton;
    }

    public Button getCrossOutCombinationNoButton() {
        return crossOutCombinationNoButton;
    }

    public TextView getCrossOutCombinationTextView() {
        return crossOutCombinationTextView;
    }

    public ConstraintLayout getGameBoardLayout() {
        return gameBoardLayout;
    }

    public ArrayList<ImageView> getTraining_dicesSlots() {
        return training_dicesSlots;
    }

    public ArrayList<TextView> getCombinationsText() {
        return combinationsText;
    }

    public ArrayList<ImageView> getDicesSlots() {
        return dicesSlots;
    }

    public ArrayList<TextView> getCombinationsPoints() {
        return combinationsPoints;
    }

    public ArrayList<LinearLayout> getCombinationsLayouts() {
        return combinationsLayouts;
    }

    public void setTotalScore(int totalScoreValue) {
        totalScore.setText(gameBoardActivity.getResources().getString(R.string.points_value, totalScoreValue));
    }

    public TextView getCurrentPlayerName() {
        return currentPlayerName;
    }

    public void setCurrentPlayerName(String currentPlayerName) {
        this.currentPlayerName.setText(currentPlayerName);
    }

    public ImageView getRollDicesButton() {
        return rollDicesButton;
    }
}
