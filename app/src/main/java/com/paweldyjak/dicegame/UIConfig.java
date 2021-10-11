package com.paweldyjak.dicegame;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.paweldyjak.dicegame.Activities.GameBoardActivity;

public class UIConfig {
    private final String playerUid = FirebaseAuth.getInstance().getUid();
    private final String opponentUid;
    private final GameBoardActivity gameBoardActivity;
    private final ImageView[] dicesSlots = new ImageView[5];
    private ImageView rollDicesButton;
    private final TextView[] combinationsText = new TextView[16];
    private final TextView[] combinationsPoints = new TextView[16];
    private final TextView[] combinationsSlots = new TextView[16];
    private TextView currentPlayerName;
    private TextView totalScore;


    public UIConfig(GameBoardActivity gameBoardActivity, String opponentUid) {
        this.gameBoardActivity = gameBoardActivity;
        this.opponentUid = opponentUid;


    }

    public void setComponents() {
        rollDicesButton = (gameBoardActivity.findViewById(R.id.roll_dices));
        dicesSlots[0] = (gameBoardActivity.findViewById(R.id.diceSlot1));
        dicesSlots[1] = (gameBoardActivity.findViewById(R.id.diceSlot2));
        dicesSlots[2] = (gameBoardActivity.findViewById(R.id.diceSlot3));
        dicesSlots[3] = (gameBoardActivity.findViewById(R.id.diceSlot4));
        dicesSlots[4] = (gameBoardActivity.findViewById(R.id.diceSlot5));
        for (int x = 0; x < combinationsText.length; x++) {
            switch (x) {
                case 0:
                    combinationsText[0] = (gameBoardActivity.findViewById(R.id.textView_1));
                    combinationsText[0].setText(R.string._1);
                    combinationsPoints[0] = (gameBoardActivity.findViewById(R.id.textView_1_pts));
                    combinationsSlots[0] = (gameBoardActivity.findViewById(R.id.textView_1_slot));
                    break;

                case 1:
                    combinationsText[1] = (gameBoardActivity.findViewById(R.id.textView_2));
                    combinationsText[1].setText(R.string._2);
                    combinationsPoints[1] = (gameBoardActivity.findViewById(R.id.textView_2_pts));
                    combinationsSlots[1] = (gameBoardActivity.findViewById(R.id.textView_2_slot));
                    break;

                case 2:
                    combinationsText[2] = (gameBoardActivity.findViewById(R.id.textView_3));
                    combinationsText[2].setText(R.string._3);
                    combinationsPoints[2] = (gameBoardActivity.findViewById(R.id.textView_3_pts));
                    combinationsSlots[2] = (gameBoardActivity.findViewById(R.id.textView_3_slot));
                    break;

                case 3:
                    combinationsText[3] = (gameBoardActivity.findViewById(R.id.textView_4));
                    combinationsText[3].setText(R.string._4);
                    combinationsPoints[3] = (gameBoardActivity.findViewById(R.id.textView_4_pts));
                    combinationsSlots[3] = (gameBoardActivity.findViewById(R.id.textView_4_slot));
                    break;

                case 4:
                    combinationsText[4] = (gameBoardActivity.findViewById(R.id.textView_5));
                    combinationsText[4].setText(R.string._5);
                    combinationsPoints[4] = (gameBoardActivity.findViewById(R.id.textView_5_pts));
                    combinationsSlots[4] = (gameBoardActivity.findViewById(R.id.textView_5_slot));
                    break;

                case 5:
                    combinationsText[5] = (gameBoardActivity.findViewById(R.id.textView_6));
                    combinationsText[5].setText(R.string._6);
                    combinationsPoints[5] = (gameBoardActivity.findViewById(R.id.textView_6_pts));
                    combinationsSlots[5] = (gameBoardActivity.findViewById(R.id.textView_6_slot));
                    break;

                case 6:
                    combinationsText[6] = (gameBoardActivity.findViewById(R.id.textView_pair));
                    combinationsText[6].setText(R.string.pair);
                    combinationsPoints[6] = (gameBoardActivity.findViewById(R.id.textView_pair_pts));
                    combinationsSlots[6] = (gameBoardActivity.findViewById(R.id.textView_pair_slot));
                    break;

                case 7:
                    combinationsText[7] = (gameBoardActivity.findViewById(R.id.textView_2pairs));
                    combinationsText[7].setText(R.string.two_pairs);
                    combinationsPoints[7] = (gameBoardActivity.findViewById(R.id.textView_2pairs_pts));
                    combinationsSlots[7] = (gameBoardActivity.findViewById(R.id.textView_2pairs_slot));
                    break;

                case 8:
                    combinationsText[8] = (gameBoardActivity.findViewById(R.id.textView_evens));
                    combinationsText[8].setText(R.string.evens);
                    combinationsPoints[8] = (gameBoardActivity.findViewById(R.id.textView_evens_pts));
                    combinationsSlots[8] = (gameBoardActivity.findViewById(R.id.textView_evens_slot));
                    break;

                case 9:
                    combinationsText[9] = (gameBoardActivity.findViewById(R.id.textView_odds));
                    combinationsText[9].setText(R.string.odds);
                    combinationsPoints[9] = (gameBoardActivity.findViewById(R.id.textView_odds_pts));
                    combinationsSlots[9] = (gameBoardActivity.findViewById(R.id.textView_odds_slot));
                    break;
                case 10:
                    combinationsText[10] = (gameBoardActivity.findViewById(R.id.textView_smallStraight));
                    combinationsText[10].setText(R.string.small_straight);
                    combinationsPoints[10] = (gameBoardActivity.findViewById(R.id.textView_smallStraight_pts));
                    combinationsSlots[10] = (gameBoardActivity.findViewById(R.id.textView_smallStraight_slot));
                    break;
                case 11:
                    combinationsText[11] = (gameBoardActivity.findViewById(R.id.textView_largeStraight));
                    combinationsText[11].setText(R.string.large_straight);
                    combinationsPoints[11] = (gameBoardActivity.findViewById(R.id.textView_largeStraight_pts));
                    combinationsSlots[11] = (gameBoardActivity.findViewById(R.id.textView_largeStraight_slot));
                    break;
                case 12:
                    combinationsText[12] = (gameBoardActivity.findViewById(R.id.textView_fullHouse));
                    combinationsText[12].setText(R.string.full_house);
                    combinationsPoints[12] = (gameBoardActivity.findViewById(R.id.textView_fullHouse_pts));
                    combinationsSlots[12] = (gameBoardActivity.findViewById(R.id.textView_fullHouse_slot));
                    break;
                case 13:
                    combinationsText[13] = (gameBoardActivity.findViewById(R.id.textView_4ofAKind));
                    combinationsText[13].setText(R.string.four_of_a_kind);
                    combinationsPoints[13] = (gameBoardActivity.findViewById(R.id.textView_4ofAKind_pts));
                    combinationsSlots[13] = (gameBoardActivity.findViewById(R.id.textView_4ofAKind_slot));
                    break;
                case 14:
                    combinationsText[14] = (gameBoardActivity.findViewById(R.id.textView_5ofAKind));
                    combinationsText[14].setText(R.string.five_of_a_kind);
                    combinationsPoints[14] = (gameBoardActivity.findViewById(R.id.textView_5ofAKind_pts));
                    combinationsSlots[14] = (gameBoardActivity.findViewById(R.id.textView_5ofAKind_slot));
                    break;
                case 15:
                    combinationsText[15] = (gameBoardActivity.findViewById(R.id.textView_sos));
                    combinationsText[15].setText(R.string.SOS);
                    combinationsPoints[15] = (gameBoardActivity.findViewById(R.id.textView_sos_pts));
                    combinationsSlots[15] = (gameBoardActivity.findViewById(R.id.textView_sos_slot));
                    break;

            }
        }

        for (int x = 0; x < combinationsText.length; x++) {
            combinationsPoints[x].setText(R.string.zero_points);
        }

        for (TextView textView : combinationsText) {
            textView.setEnabled(true);
        }

        totalScore = new TextView(gameBoardActivity);
        totalScore = (gameBoardActivity.findViewById(R.id.textView_score_pts));
        currentPlayerName = (gameBoardActivity.findViewById(R.id.player_name_textView));
        totalScore.setText(R.string.zero_points);

    }


    public void setDicesVisibility(boolean visible) {
        for (int x = 0; x < getDicesSlots().length; x++) {
            if (visible) {
                getDicesSlots()[x].setVisibility(View.VISIBLE);
            } else {
                getDicesSlots()[x].setVisibility(View.INVISIBLE);
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

    public TextView[] getCombinationsText() {
        return combinationsText;
    }


    public void setDicesBorder(ImageView dice, boolean setBorder) {
        Drawable dicesBorder = ResourcesCompat.getDrawable(gameBoardActivity.getResources(), R.drawable.dices_border, null);
        if (setBorder) {
            dice.setBackground(dicesBorder);
        } else {
            dice.setBackground(null);
        }
    }

    public void clearDicesBorder() {
        for (ImageView dices : dicesSlots) {
            dices.setBackground(null);
        }
    }


    public TextView getCurrentPlayerName() {
        return currentPlayerName;
    }

    public void setCurrentPlayerName(String currentPlayerName) {
        this.currentPlayerName.setText(currentPlayerName);
    }

    public TextView[] getCombinationsSlots() {
        return combinationsSlots;
    }

    public TextView getTotalScore() {
        return totalScore;
    }


    //method shows dices
    public void showDices(int[] dices) {
        setDicesVisibility(true);
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

    }

    public void updateCombinationsUI(int combinationStatus, int combinationNumber) {
        /*combination status:

        0 - enabled, empty
        1 - disabled, done
        2 - disabled, not done*/

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


    public String getPlayerUid() {
        return playerUid;
    }

    public String getOpponentUid() {
        return opponentUid;
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

}
